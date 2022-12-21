package com.example.codefliestask.model

data class TherapiesModel(
    val `data`: List<Data>,
    val message: String,
    val status_code: Int,
    val token: String
)