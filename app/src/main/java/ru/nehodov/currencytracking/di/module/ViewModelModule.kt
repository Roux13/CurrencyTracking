package ru.nehodov.currencytracking.di.module

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import ru.nehodov.currencytracking.presentation.feature.mainScreen.MainScreenViewModel

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindMainScreenViewModel(mainScreenViewModel: MainScreenViewModel): ViewModel
}