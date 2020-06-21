package dk.nodes.template.repositories

import android.content.SharedPreferences
import android.util.Log
import com.google.gson.Gson
import dk.nodes.template.models.EditEmployee
import dk.nodes.template.models.Employee
import dk.nodes.template.models.SectionsInfo
import dk.nodes.template.network.EmployeeService
import javax.inject.Inject

class EmployeeRepository@Inject constructor(private val iEmployeeService: EmployeeService,
                                            private val sharedPreferences: SharedPreferences,
                                            private val gson: Gson) {

    fun getHeader(): HashMap<String, String>{
        val mapHeader = HashMap<String, String>()
        val authToken = sharedPreferences.getString("access_token", null)

        if(authToken !=null){
            mapHeader.put("X-ClientId", " b6e01899-f593-4eec-9b34-56fe41a5ecab")
            mapHeader.put("Authorization", "Bearer $authToken")
        }

        return mapHeader
    }

   suspend fun getEmployees(): ArrayList<Employee>{
       var employeeList = ArrayList<Employee>()
       val response = iEmployeeService.getEmployees(getHeader()).execute()
       if(response.isSuccessful){
           val message = response.body()

       if(message !=null){
           employeeList.addAll(message.employeeList)
       }
       }

       return employeeList

   }

    suspend fun getFromId(id: Int): Employee?{
        val response = iEmployeeService.getEmployeesById(getHeader(), id).execute()
        if(response.isSuccessful){
            var message = response.body()
            if(message !=null){
                return message
            }
        }

        return null
    }

    suspend fun getSectionNames(): ArrayList<SectionsInfo>{
        val response = iEmployeeService.getSectionsNames(getHeader()).execute()
        val sectionsList = ArrayList<SectionsInfo>()
        Log.d("Log.d", "1")

        if(response.isSuccessful){
            val message = response.body()
            Log.d("Log.d", "1")

            if(message != null){
                message.sectionsList?.let {sectionsList.addAll(it)}
            }
        }

        return sectionsList
    }

    suspend fun sendEmployee(employee: EditEmployee): Unit?{
        var employeeHashMap = HashMap<String, String>()
        employeeHashMap.put("firstName", employee.firstName)
        employeeHashMap.put("lastName", employee.lastName)
        employeeHashMap.put("gender", employee.gender)

        val response = iEmployeeService.sendEmployee(getHeader(), employee.id,gson.toJson(employeeHashMap)).execute()

        if(response.isSuccessful){
            var message = response.body()
            if(message != null){
                return null
            }
        }

        return null
    }
}