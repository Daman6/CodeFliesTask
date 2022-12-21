package com.example.codefliestask.repo

import androidx.lifecycle.MutableLiveData
import com.demo.codeflies.model.UserRegister
import com.example.codefliestask.api.RetrofitInstance
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import retrofit2.http.Field

class TherapyRepo {

    suspend fun getAllTherapies()=RetrofitInstance.api.getTherapies()

    suspend fun pushuserRegistration(first_name:String, last_name:String, email:String,password:String, contact_number:Long)=RetrofitInstance.api.userRegister(first_name,last_name, email,password, contact_number)

    suspend fun pushuserLogin(email:String,password:String)=RetrofitInstance.api.userLogin( email,password)


}