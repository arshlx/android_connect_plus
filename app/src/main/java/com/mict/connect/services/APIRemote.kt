package com.mict.connect.services

import com.mict.connect.model.Student
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface APIRemote {
    @Headers("Accept: application/json")
    @GET("schools/")
    suspend fun getStudentList(): Response<List<Student>>
}