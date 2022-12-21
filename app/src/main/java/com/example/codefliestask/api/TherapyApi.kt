package com.example.codefliestask.api

import com.demo.codeflies.model.Login
import com.demo.codeflies.model.UserRegister
import com.example.codefliestask.model.TherapiesModel
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface TherapyApi {


//    @POST("user/login")
//    suspend fun userLogin(@Body requestBody: RequestBody) : Response<>

    //Api for user Register
    @FormUrlEncoded
    @POST("user/register")
    suspend fun userRegister(
        @Field("first_name") first_name:String,
        @Field("last_name") last_name:String,
        @Field("email") email:String,
        @Field("password") password:String,
        @Field("contact_number") contact_number:Long,
    ):Response<UserRegister>


    @FormUrlEncoded
    @POST("user/login")
    suspend fun userLogin(
        @Field("email") email:String,
        @Field("password") password:String,
    ):Response<Login>


    @GET("doctor/therapies")
    suspend fun getTherapies():Response<TherapiesModel>
}