package com.mict.connect.appointments.vm

import com.mict.connect.global_objects.TaskStatus
import com.mict.connect.model.Appointment
import com.mict.connect.model.ListOfAppointments
import com.google.gson.Gson
import java.io.FileReader
import java.io.FileWriter
import java.io.IOException
import java.io.PrintWriter

class AppointmentsRepository {

    fun getAppointments(): Pair<MutableList<Appointment>, String> {
        val appointments: MutableList<Appointment>
        var status: String
        try {
            appointments = Gson().fromJson(
                FileReader("appointments.txt"),
                ListOfAppointments::class.java
            ).appointments
            status = TaskStatus.SUCCESS
        } catch (e: IOException) {
            status = TaskStatus.FAILURE
            throw java.lang.RuntimeException(e)
        }
        return Pair(appointments, status)
    }

    fun updateRecords(appointment: MutableList<Appointment>): String {
        var taskStatus: String
        try {
            val writer = FileWriter("appointments.txt")
            val printWriter = PrintWriter(writer, false)
            printWriter.flush()
            writer.write(Gson().toJson(ListOfAppointments(appointment)))
            printWriter.close()
            writer.close()
            taskStatus = TaskStatus.SUCCESS
        } catch (e: IOException) {
            taskStatus = TaskStatus.FAILURE
            throw RuntimeException(e)
        }
        return taskStatus
    }
}