package ru.nehodov.currencytracking.data.network

import retrofit2.http.GET

interface ApiService {

    companion object {
        const val BASE_URL = "https://api.exchangeratesapi.io"
    }

//    @GET("/latest")
//    fun getCurrencies():
}