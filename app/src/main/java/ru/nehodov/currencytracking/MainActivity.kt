package ru.nehodov.currencytracking

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import ru.nehodov.currencytracking.extension.appComponent
import ru.nehodov.currencytracking.presentation.base.daggerViewModel
import ru.nehodov.currencytracking.presentation.feature.mainScreen.MainScreen

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        appComponent.inject(this)
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme() {
                MainScreen(viewModel = daggerViewModel {
                    appComponent.mainScreenViewModel()
                })
            }
        }
    }
}