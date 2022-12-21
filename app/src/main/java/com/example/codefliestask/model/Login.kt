package com.demo.codeflies.model

data class Login(
    val `data`: LoginData,
    val message: String,
    val status_code: Int,
    val token: String
)