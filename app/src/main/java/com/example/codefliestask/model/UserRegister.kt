package com.demo.codeflies.model

data class UserRegister(
    val `data`: UserRegisterData,
    val message: String,
    val status_code: Int,
    val token: String
)