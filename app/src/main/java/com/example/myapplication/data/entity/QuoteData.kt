package com.example.myapplication.data.entity

import kotlinx.serialization.Serializable

//create a entity to use the remote api data
@Serializable
data class QuoteData(
    val id: Int,
    val author: String,
    val body: String
)