package ru.nehodov.currencytracking.di.component

import android.app.Application
import android.content.Context
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import ru.nehodov.currencytracking.App
import ru.nehodov.currencytracking.MainActivity
import ru.nehodov.currencytracking.di.module.*

@AppScope
@Component(
    modules = [
        AppModule::class,
        DbModule::class,
        NetworkModule::class,
        RepositoryModule::class,
        PreferenceModule::class,
    ]
)

interface AppComponent : AndroidInjector<App> {

    fun inject(mainActivity: MainActivity)

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance context: Context,
            @BindsInstance application: Application,
        ): AppComponent
    }
}