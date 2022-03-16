package ru.nehodov.currencytracking.extension

import android.content.Context
import ru.nehodov.currencytracking.App
import ru.nehodov.currencytracking.di.component.AppComponent

val Context.appComponent: AppComponent
 get() = when(this) {
     is App -> appComponent
     else -> this.applicationContext.appComponent
 }