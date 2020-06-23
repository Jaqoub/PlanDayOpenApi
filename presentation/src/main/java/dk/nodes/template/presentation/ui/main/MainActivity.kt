package dk.nodes.template.presentation.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import dk.nodes.template.models.EditEmployee
import dk.nodes.template.models.Employee
import dk.nodes.template.models.SectionsInfo
import dk.nodes.template.presentation.R
import dk.nodes.template.presentation.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber
import kotlin.collections.ArrayList
import dk.nodes.template.presentation.extensions.observeNonNull




class MainActivity : BaseActivity() {


    private val viewModel: by viewModel<MainActivityViewModel>()
    private var adapter: EmployeeAdapter? = null
    private var employeeList: ArrayList<Employee>? = null
    lateinit var editEmployee: Intent


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.getAuthentification()
        viewModel.getEmployees()
        viewModel.getSections()

        adapter = EmployeeAdapter(this, R.layout.employees_queue)
        adapter?.onItemClickedListener = {employee ->

            editEmployee = Intent(this, EditEmployee::class.java)
            editEmployee.putExtra("empId", employee.id)
            startActivity(editEmployee)
        }
        viewModel.viewState.observeNonNull(this){state ->
            getEmployee(state)
            getSection(state)
        }

    }


    private fun getSection(viewState: MainActivityViewState){
        viewState.sectionsList?.let{sectionsList ->
            changeSection(sectionsList)
        }
    }

    private fun changeSection(secList: ArrayList<SectionsInfo>){
        employeeList?.let {
            for (empl in it){
                if(empl.sections!= null){
                    empl.sections!!.forEachIndexed{index, element ->
                        for (sec in secList){
                            if(sec.id.toString() == element){
                                empl.sections!![index] = sec.name.toString()
                            }
                        }
                    }
                }
            }
        }
    }


    private fun getEmployee(viewState:MainActivityViewState){
        viewState.employeesList?.let{employeedata ->
            employeeList = employeedata
            adapter?.addEmployeesList(employeedata)
            Timber.e(employeedata.size.toString())
            updateRecyclerView()
        }
    }

    private fun updateRecyclerView(){
        rv_employeesdetails.layoutManager = LinearLayoutManager(this)
        rv_employeesdetails.adapter = adapter
        adapter?.notifyDataSetChanged()

    }

}



















