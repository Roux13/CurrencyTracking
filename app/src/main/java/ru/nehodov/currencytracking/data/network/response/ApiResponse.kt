package ru.nehodov.currencytracking.data.network.response

class ApiResponse(
    val success: Boolean,
    val timestamp: Long,
    val base: String,
    val date: String,
    val rates: Map<String, Double>
)