package dk.nodes.template.models

import com.google.gson.annotations.SerializedName

data class EmployeeIdList(
        @SerializedName("data")
        var employeeList: Array<Employee>
)