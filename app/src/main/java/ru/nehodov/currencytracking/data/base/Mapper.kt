package ru.nehodov.currencytracking.data.base

interface Mapper<From, To> {
    fun map(from: From): To
}