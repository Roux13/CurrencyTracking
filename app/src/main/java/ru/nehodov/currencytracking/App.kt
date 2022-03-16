package ru.nehodov.currencytracking

import android.app.Application
import ru.nehodov.currencytracking.di.component.AppComponent
import ru.nehodov.currencytracking.di.DaggerAppComponent

class App : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
//        appComponent = DaggerAppComponent.create()
        appComponent = DaggerAppComponent.builder().build()
    }

}