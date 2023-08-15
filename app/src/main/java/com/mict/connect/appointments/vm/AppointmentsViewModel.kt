package com.mict.connect.appointments.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mict.connect.global_objects.TaskStatus
import com.mict.connect.model.Appointment
import kotlinx.coroutines.launch

class AppointmentsViewModel : ViewModel() {
    val repository = AppointmentsRepository()
    var appointments = mutableListOf<Appointment>()
    val listStatus = MutableLiveData(TaskStatus.NONE)

    fun initAppointments() {
        listStatus.value = TaskStatus.LOADING
        viewModelScope.launch {
            val result = repository.getAppointments()
            appointments = result.first
            listStatus.value = result.second
        }
    }
}