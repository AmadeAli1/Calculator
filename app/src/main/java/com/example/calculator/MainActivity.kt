package com.example.calculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import com.example.calculator.ui.screen.HomeScreen
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val isLight = rememberSaveable(key = "theme") { mutableStateOf(true) }
            val scope = rememberCoroutineScope()
            HomeScreen(isLight = isLight) {
                scope.launch {
                    if (isLight.value) {
                        window.statusBarColor =
                            resources.getColor(R.color.white_low, null)
                    } else {
                        window.statusBarColor = resources.getColor(R.color.black_low,null)
                    }
                }
            }
        }
    }

}