package com.mict.connect.main.vm

import androidx.lifecycle.ViewModel
import com.mict.connect.model.Student

class MainViewModel : ViewModel() {
    lateinit var studentList: List<Student>
    lateinit var selStudent: Student
}