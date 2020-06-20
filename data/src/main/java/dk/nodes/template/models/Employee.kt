package dk.nodes.template.models

import com.google.gson.annotations.SerializedName

data class Employee(
        @SerializedName("id")
        var id: Int?,
        @SerializedName("firstName")
        var firstName: String?,
        @SerializedName("lastName")
        var lastName: String?,
        @SerializedName("sections")
        var sections: Array<String>?
){
    override fun equals(other: Any?): Boolean {
        if(this === other) return true
        if(javaClass != other?.javaClass) return false

        other as Employee

        if(sections != null){
            if(other.sections == null) return false
            if(!sections!!.contentEquals(other.sections!!)) return false

        }else if(other.sections != null) return false

        return true




    }

    override fun hashCode(): Int {
        return sections?.contentHashCode() ?: 0
    }
}