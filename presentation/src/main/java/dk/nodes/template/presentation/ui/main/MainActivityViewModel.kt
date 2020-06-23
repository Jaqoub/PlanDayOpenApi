package dk.nodes.template.presentation.ui.main

import androidx.lifecycle.viewModelScope
import dk.nodes.template.domain.interactors.*
import dk.nodes.template.models.EditEmployee
import dk.nodes.template.models.Employee
import dk.nodes.template.models.SectionsInfo
import dk.nodes.template.presentation.extensions.asResult
import dk.nodes.template.presentation.nstack.NStackPresenter
import dk.nodes.template.presentation.ui.base.BaseViewModel
import dk.nodes.template.presentation.util.SingleEvent
import dk.nodes.template.presentation.util.ViewErrorController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(
        private val nStackPresenter: NStackPresenter,
        private val getTokenInt: GetTokenInteractor,
        private val getEmployeeInt: GetEmployeeInteractor,
        private val getSectionListInt: GetSectionListInteractor,
        private val getFromIdInt: GetFromIdInteractor,
        private val sendEmployeeInteractor: SendEmployeeInteractor




) : BaseViewModel<MainActivityViewState>() {
    override val initState: MainActivityViewState = MainActivityViewState()
    private val getEmployeesInteractor = getEmployeeInt.asResult()
    private val getTestIntepretor = getTokenInt.asResult()
    private val getSectionsListInteractor = getFromIdInt.asResult()
    private val getFromIDInt = getFromIdInt.asResult()
    private val sendEmployeeInt = sendEmployeeInteractor.asResult()


    fun getAuthentification() = viewModelScope.launch(Dispatchers.Main) {
        val result = withContext(Dispatchers.IO) { getTestIntepretor.invoke() }


        state = mapResultAuth(result)
    }


    private fun mapResultAuth(result: CompleteResult<String?>): MainActivityViewState {
        return when (result) {
            is Success -> state.copy(message = result.data)
            is Loading<*> -> state.copy(isLoading = true)
            is Fail -> state.copy(
                    viewError = SingleEvent(ViewErrorController.mapThrowable(result.throwable)),
                    isLoading = false
            )

            else -> MainActivityViewState()

        }
    }


    fun getEmployees() = viewModelScope.launch(Dispatchers.Main) {
        val result = withContext(Dispatchers.IO) { getEmployeesInteractor.invoke() }
        state = mapResultEmp(result)

    }

    private fun mapResultEmp(result: CompleteResult<ArrayList<Employee>>): MainActivityViewState {
        return when (result) {
            is Success -> state.copy(employeesList = result.data)
            is Loading<*> -> state.copy(isLoading = true)
            is Fail -> state.copy(
                    viewError = SingleEvent(ViewErrorController.mapThrowable(result.throwable)),
                    isLoading = false
            )
            else -> MainActivityViewState()
        }
    }

    fun getSections() = viewModelScope.launch(Dispatchers.Main) {
        val result = withContext(Dispatchers.IO) { getSectionsListInteractor.invoke() }
        state = mapResultSec(result)


    }


    private fun mapResultSec(result: CompleteResult<ArrayList<SectionsInfo>>): MainActivityViewState {
        return when (result) {
            is Success -> state.copy(sectionsList = result.data)
            is Loading<*> -> state.copy(isLoading = true)
            is Fail -> state.copy(
                    viewError = SingleEvent(ViewErrorController.mapThrowable(result.throwable)),
                    isLoading = false
            )
            else -> MainActivityViewState()
        }
    }

    fun getFromId(id: Int) = viewModelScope.launch(Dispatchers.Main) {
        val result = withContext(Dispatchers.IO) { getFromIDInt.invoke(id) }
        state = mapResult(result)

        fun sendEmployee(employee: EditEmployee) = viewModelScope.launch(Dispatchers.Main) {
            val result = withContext(Dispatchers.IO) { sendEmployeeInt.invoke(employee) }
            state = mapSend(result)
        }
    }

    private fun mapSend(result: CompleteResult<Unit?>): MainActivityViewState {
        return when (result) {
            is Fail -> state.copy(
                    viewError = SingleEvent(ViewErrorController.mapThrowable(result.throwable)),
                    isLoading = false
            )
            else -> MainActivityViewState()
        }


    }

    fun sendEmployee(employee: EditEmployee) = viewModelScope.launch(Dispatchers.Main) {
        val result = withContext(Dispatchers.IO) { sendEmployeeInt.invoke(employee) }
        state = mapSend(result)

    }


    private fun mapResult(result: CompleteResult<Employee?>): MainActivityViewState {
        return when (result) {
            is Success -> state.copy(employee = result.data)
            is Loading<*> -> state.copy(isLoading = true)
            is Fail -> state.copy(
                    viewError = SingleEvent(ViewErrorController.mapThrowable(result.throwable)),
                    isLoading = false
            )
            else -> MainActivityViewState()
        }


    }
}

