package com.hninor.pruebainterrapidisimo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.hninor.pruebainterrapidisimo.core.screens.NavigationScreen
import com.hninor.pruebainterrapidisimo.core.theme.PruebaInterrapidisimoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PruebaInterrapidisimoTheme {
                NavigationScreen()
            }
        }
    }
}











