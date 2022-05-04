package ru.nehodov.currencytracking.data.repository

import ru.nehodov.currencytracking.data.base.FailureResponseException
import ru.nehodov.currencytracking.data.db.dao.CurrencyDao
import ru.nehodov.currencytracking.data.db.entity.CurrencyEntity
import ru.nehodov.currencytracking.data.mappers.IEntityMapper
import ru.nehodov.currencytracking.data.network.ApiService
import ru.nehodov.currencytracking.data.network.response.ApiResponse
import ru.nehodov.currencytracking.domain.repository.AppSettingsRepository
import ru.nehodov.currencytracking.domain.repository.CurrencyRepository
import javax.inject.Inject

class CurrencyRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val currencyDao: CurrencyDao,
    private val appSettingsRepository: AppSettingsRepository,
) : CurrencyRepository {

    override suspend fun updateCurrencies(apiResponseToCurrencyEntitiesMapper: IEntityMapper<ApiResponse, List<CurrencyEntity>>): Result<Unit> {
        return runCatching {
            val apiResponse = apiService.getCurrencies(appSettingsRepository.settings().baseCurrency)
            if (apiResponse.isSuccessful) {
                apiResponse.body()?.let { apiResponseBody ->
                    println("code: ${apiResponse.code()} body: ${apiResponse.body()}")
                    apiResponseToCurrencyEntitiesMapper.map(apiResponseBody).forEach { currencyEntity ->
                        currencyDao.insert(currencyEntity)
                    }
                }
            } else {
                Result.failure<Unit>(FailureResponseException(apiResponse.code()))
            }
        }
    }

    override suspend fun getCurrencies(): Result<List<CurrencyEntity>> {
        return runCatching { currencyDao.getAll() }
    }
}