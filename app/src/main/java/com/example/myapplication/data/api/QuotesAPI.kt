package com.example.myapplication.data.api

import com.example.myapplication.data.entity.Quote
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

//connect quotes api and get a random quote
interface QuotesAPI {
    @GET("qotd")
    suspend fun getQuote(): Response<Quote>
}