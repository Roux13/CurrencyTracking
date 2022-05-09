package ru.nehodov.currencytracking.di.module

import dagger.Module
import dagger.Provides
import ru.nehodov.currencytracking.data.providers.BuildInfoProvider
import ru.nehodov.currencytracking.data.providers.BuildInfoProviderImpl
import ru.nehodov.currencytracking.data.providers.DispatcherProvider
import ru.nehodov.currencytracking.data.providers.DispatcherProviderImpl

@Module
object AppModule {

    @AppScope
    @Provides
    fun provideBuildInfoProvider(): BuildInfoProvider {
        return BuildInfoProviderImpl()
    }

    @Provides
    fun provideDispatcherProvider(): DispatcherProvider {
        return DispatcherProviderImpl()
    }
}