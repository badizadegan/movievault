package com.fahimeh.movievault

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.fahimeh.movievault.ui.navigation.AnimatedAppRoot
import com.fahimeh.movievault.ui.theme.MovieVaultTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MovieVaultTheme {

                AnimatedAppRoot()
            }
        }
    }
}