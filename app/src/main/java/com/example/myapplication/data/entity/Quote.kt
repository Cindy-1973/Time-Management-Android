package com.example.myapplication.data.entity

import kotlinx.serialization.Serializable

//create a entity to use the remote api data
@Serializable
data class Quote(
    val qotd_date: String,
    val quote: QuoteData
)


