package com.mict.connect.model

import com.google.gson.annotations.SerializedName

data class Student(
    @SerializedName("student_name") val name: String,
    @SerializedName("school_name") val school: String,
    @SerializedName("subjects") val subjects: MutableList<Subject>,
    @SerializedName("total_days") val totalDays: Int,
    @SerializedName("total_attendance_days") val attendedDays: Int,
    @SerializedName("gender") val gender: String,
    @SerializedName("grade") val grade: Int,
    @SerializedName("image_url") val url: String,
    @SerializedName("assignments") val assignments: MutableList<Assignment>
)