package com.mict.connect.model

import com.google.gson.annotations.SerializedName
import java.util.*

class Assignment {
    val id = ""

    @SerializedName("assignment_name")
    val assnName = ""

    @SerializedName("group_assignment")
    val isGroup = false
    val startDate = ""

    @SerializedName("subject")
    val subject = ""

    @SerializedName("assignment_duedate")
    val dueDate = ""

    @SerializedName("assignment_description")
    val desc = ""
    var date = Date()
}
