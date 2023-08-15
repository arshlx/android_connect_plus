package com.mict.connect.splash.vm

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mict.connect.global_objects.TaskStatus
import com.mict.connect.model.ListOfStudents
import kotlinx.coroutines.launch

class SplashViewModel : ViewModel() {
    private val repository = SplashRepository()
    lateinit var students: ListOfStudents
    var loggedIn = false
    val studentStatus = MutableLiveData(TaskStatus.NONE)

    fun getStudentList() {
        studentStatus.value = TaskStatus.LOADING
        viewModelScope.launch {
            val result = repository.getStudentList()
            students = ListOfStudents(result.second)
            studentStatus.value = result.first
        }
    }

    fun initStudentList(context: Context) {
        studentStatus.value = TaskStatus.LOADING
        viewModelScope.launch {
            val result = repository.getStudentsFromFile(context)
            students = ListOfStudents(result.second)
            studentStatus.value = result.first
        }
    }
}