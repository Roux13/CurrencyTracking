package ru.nehodov.currencytracking.extension

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

val ViewModel.coroutineExceptionHandler: CoroutineExceptionHandler
    get() = CoroutineExceptionHandler { _, throwable ->
        throwable.printStackTrace()
        throw throwable
    }

fun ViewModel.launchCoroutine(block: suspend CoroutineScope.() -> Unit): Job {
    return viewModelScope.launch(coroutineExceptionHandler) {
        block()
    }
}