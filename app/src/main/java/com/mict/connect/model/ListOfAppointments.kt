package com.mict.connect.model

import com.google.gson.annotations.SerializedName

data class ListOfAppointments(@SerializedName("appointments") val appointments: MutableList<Appointment>)