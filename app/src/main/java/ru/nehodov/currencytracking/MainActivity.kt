package ru.nehodov.currencytracking

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.nehodov.currencytracking.data.mappers.ApiResponseToCurrencyListMapper
import ru.nehodov.currencytracking.data.repository.CurrencyRepository
import ru.nehodov.currencytracking.extension.appComponent
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private val coroutineExceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        throwable.printStackTrace()
    }

    @Inject
    lateinit var currencyRepository: CurrencyRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        appComponent.inject(this)
        lifecycleScope.launchWhenResumed {
            withContext(Dispatchers.IO + coroutineExceptionHandler) {
                currencyRepository.updateCurrencies(ApiResponseToCurrencyListMapper())
            }
        }
    }
}