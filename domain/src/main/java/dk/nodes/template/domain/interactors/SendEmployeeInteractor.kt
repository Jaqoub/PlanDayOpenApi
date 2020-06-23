package dk.nodes.template.domain.interactors

import dk.nodes.template.models.EditEmployee
import dk.nodes.template.repositories.EmployeeRepository
import javax.inject.Inject

class SendEmployeeInteractor @Inject constructor(private val employesRepository: EmployeeRepository) : BaseInputAsyncInteractor<EditEmployee, Unit?> {
    override suspend fun invoke(input: EditEmployee): Unit? {
        return employesRepository.sendEmployee(input)

    }

}