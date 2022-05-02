package ru.nehodov.currencytracking

import android.app.Application
import ru.nehodov.currencytracking.di.component.AppComponent
import ru.nehodov.currencytracking.di.component.DaggerAppComponent

class App : Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory()
            .create(
                context = applicationContext,
                application = this,
            )
    }
}