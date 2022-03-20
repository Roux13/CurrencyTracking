package ru.nehodov.currencytracking.data.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import ru.nehodov.currencytracking.data.network.response.ApiResponse

interface ApiService {

    @GET("/latest")
    suspend fun getCurrencies(
        @Query("base") baseCurrency: String,
    ): Response<ApiResponse>
}