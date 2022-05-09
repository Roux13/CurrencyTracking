package ru.nehodov.currencytracking.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import ru.nehodov.currencytracking.data.base.FailureResponseException
import ru.nehodov.currencytracking.data.base.Mapper
import ru.nehodov.currencytracking.data.db.dao.CurrencyDao
import ru.nehodov.currencytracking.data.db.entity.CurrencyEntity
import ru.nehodov.currencytracking.data.network.ApiService
import ru.nehodov.currencytracking.data.network.response.ApiResponse
import ru.nehodov.currencytracking.data.providers.DispatcherProvider
import ru.nehodov.currencytracking.domain.model.Currency
import ru.nehodov.currencytracking.domain.repository.AppSettingsRepository
import ru.nehodov.currencytracking.domain.repository.CurrencyRepository
import javax.inject.Inject

class CurrencyRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val currencyDao: CurrencyDao,
    private val appSettingsRepository: AppSettingsRepository,
    private val dispatchers: DispatcherProvider,
) : CurrencyRepository {

    override suspend fun updateCurrencies(
        apiResponseToCurrencyEntitiesMapper: Mapper<ApiResponse, List<CurrencyEntity>>
    ): Result<Unit> = withContext(dispatchers.io()) {
        val apiResponse = apiService.getCurrencies(appSettingsRepository.settings().baseCurrency)
        if (apiResponse.isSuccessful) {
            apiResponse.body()?.let { apiResponseBody ->
                println("code: ${apiResponse.code()} body: ${apiResponse.body()}")
                apiResponseToCurrencyEntitiesMapper.map(apiResponseBody)
                    .forEach { currencyEntity ->
                        currencyDao.insert(currencyEntity)
                    }
            }
            Result.success(Unit)
        } else {
            Result.failure<Unit>(FailureResponseException(apiResponse.code()))
        }
    }

    override suspend fun getCurrencies(mapper: Mapper<List<CurrencyEntity>, List<Currency>>): Flow<Result<List<Currency>>> {
        return currencyDao.getAll()
            .map { Result.success(mapper.map(it)) }
            .flowOn(dispatchers.io())
    }
}