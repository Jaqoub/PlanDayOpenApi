package dk.nodes.template.models

import com.google.gson.annotations.SerializedName

data class Sections (@SerializedName("data")
var sectionsList: ArrayList<SectionsInfo>)
