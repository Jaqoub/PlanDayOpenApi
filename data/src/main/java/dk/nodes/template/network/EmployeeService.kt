package dk.nodes.template.network

import dk.nodes.template.models.Employee
import dk.nodes.template.models.EmployeeIdList
import dk.nodes.template.models.Sections
import retrofit2.Call
import retrofit2.http.*

interface EmployeeService {
    @GET("/hr/v1/employees/{id}")
    fun getEmployeesById(@HeaderMap headers: Map<String, String>, @Path("id")id: Int): Call<Employee>

    @GET("hr/v1/employees")
    fun getEmployees(@HeaderMap headers: Map<String, String>): Call<EmployeeIdList>

    @GET("hr/v1/sections")
    fun getSectionsNames(@HeaderMap headers: Map<String, String>): Call<Sections>

    @Headers("Content-Type: application/json")
    @PUT("hr/v1/employees/{id}")
    fun sendEmployee(@HeaderMap headers: Map<String, String>,@Path("id") id: Int,
                     @Body body :String): retrofit2.Call<String>
}