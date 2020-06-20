package dk.nodes.template.models

import com.google.gson.annotations.SerializedName

data class SectionsInfo(@SerializedName("name")
var name : String?,
@SerializedName("id")
var id : Int?,
@SerializedName("number")
var number : String?)