package com.mict.connect.splash.vm

import android.content.Context
import com.mict.connect.global_objects.TaskStatus
import com.mict.connect.model.ListOfStudents
import com.mict.connect.model.Student
import com.mict.connect.services.APIRemote
import com.mict.connect.services.RetrofitService
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException

class SplashRepository {
    private val remote = RetrofitService.getClient()?.create(APIRemote::class.java)

    suspend fun getStudentList(): Pair<String, List<Student>> {
        var studentList = listOf<Student>()
        try {
            val response = remote?.getStudentList()
            response?.let {
                studentList = response.body()!!.toList()
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }

        return Pair(TaskStatus.SUCCESS, studentList)
    }

    suspend fun getStudentsFromFile(context: Context): Pair<String, List<Student>> {
        val studentList: ListOfStudents
        try {
            studentList = withContext(Dispatchers.IO) {
                Gson().fromJson(getJsonDataFromAsset(context), ListOfStudents::class.java)
            }
        } catch (e: IOException) {
            throw RuntimeException(e)
        }
        return Pair(TaskStatus.SUCCESS, studentList.stuList)
    }

    private fun getJsonDataFromAsset(context: Context): String? {
        val jsonString: String
        try {
            jsonString = context.assets.open("students.json").bufferedReader().use { it.readText() }
        } catch (e: IOException) {
            e.printStackTrace()
            return null
        }
        return jsonString
    }
}