package ru.nehodov.currencytracking.di.component

import dagger.Component
import dagger.android.AndroidInjector
import ru.nehodov.currencytracking.App
import ru.nehodov.currencytracking.di.module.AppModule
import ru.nehodov.currencytracking.di.module.DbModule
import ru.nehodov.currencytracking.di.module.NetworkModule

@Component(
    modules = [
        AppModule::class,
        DbModule::class,
        NetworkModule::class,
    ]
)

interface AppComponent : AndroidInjector<App>