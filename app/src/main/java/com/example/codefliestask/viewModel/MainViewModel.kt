package com.example.codefliestask.viewModel

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demo.codeflies.model.Login
import com.demo.codeflies.model.UserRegister
import com.example.codefliestask.model.TherapiesModel
import com.example.codefliestask.repo.TherapyRepo
import com.example.codefliestask.utils.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(val repo : TherapyRepo):ViewModel() {

    val getTherapies : MutableLiveData<Resource<TherapiesModel>> = MutableLiveData()
    val myPushUser : MutableLiveData<Resource<UserRegister>> = MutableLiveData()
    val myPushUserLogin : MutableLiveData<Resource<Login>> = MutableLiveData()

    init {
        getDocTherapies()
    }

    fun getDocTherapies()=viewModelScope.launch{
        getTherapies.postValue(Resource.Loading())
        val response = repo.getAllTherapies()
        getTherapies.postValue(handleGetTherapiesResponse(response))
    }

    fun pushPost(first_name:String, last_name:String, email:String,password:String, contact_number:Long)=viewModelScope.launch{
        myPushUser.postValue(Resource.Loading())
        val response = repo.pushuserRegistration(first_name, last_name, email,password, contact_number)
        myPushUser.postValue(handlePostUserResponse(response))
    }

   fun pushPostLogin( email:String,password:String)=viewModelScope.launch{
       myPushUserLogin.postValue(Resource.Loading())
        val response = repo.pushuserLogin(email,password)
       myPushUserLogin.postValue(handlePostUserLoginResponse(response))
    }

    private fun handleGetTherapiesResponse(response: Response<TherapiesModel>) : Resource<TherapiesModel>{
        if (response.isSuccessful){
            response.body()?.let {response ->
                return Resource.Success(response)
            }
        }
        return Resource.Error(response.message())
    }

    private fun handlePostUserResponse(response: Response<UserRegister>) : Resource<UserRegister>{
        if (response.isSuccessful){
            response.body()?.let {response ->
                return Resource.Success(response)

            }
        }
        return Resource.Error(response.message())
    }

    private fun handlePostUserLoginResponse(response: Response<Login>) : Resource<Login>{
        if (response.isSuccessful){
            response.body()?.let {response ->
                return Resource.Success(response)

            }
        }
        return Resource.Error(response.message())
    }

}