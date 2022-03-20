package ru.nehodov.currencytracking.di.component

import android.app.Application
import android.content.Context
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import ru.nehodov.currencytracking.App
import ru.nehodov.currencytracking.MainActivity
import ru.nehodov.currencytracking.di.module.*
import javax.inject.Singleton

@Singleton
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

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(context: Context): Builder

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

}