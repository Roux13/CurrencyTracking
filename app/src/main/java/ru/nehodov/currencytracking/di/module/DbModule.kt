package ru.nehodov.currencytracking.di.module

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import ru.nehodov.currencytracking.data.db.CurrencyDb
import ru.nehodov.currencytracking.data.db.DbConstants
import javax.inject.Singleton

@Module
class DbModule {

    @Provides
    @Singleton
    fun provideDatabase(application: Application): CurrencyDb {
        return Room.databaseBuilder(
            application,
            CurrencyDb::class.java,
            DbConstants.DB_NAME
        ).build()
    }

}