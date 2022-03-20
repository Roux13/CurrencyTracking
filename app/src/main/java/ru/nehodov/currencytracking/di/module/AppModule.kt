package ru.nehodov.currencytracking.di.module

import dagger.Module
import dagger.Provides
import ru.nehodov.currencytracking.data.providers.BuildInfoProvider
import ru.nehodov.currencytracking.data.providers.BuildInfoProviderImpl
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideBuildInfoProvider(): BuildInfoProvider {
        return BuildInfoProviderImpl()
    }
}