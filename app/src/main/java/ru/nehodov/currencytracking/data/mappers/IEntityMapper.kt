package ru.nehodov.currencytracking.data.mappers

interface IEntityMapper<From, To> {
    fun map(from: From): To
}