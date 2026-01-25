package com.fahimeh.movievault.ui.screen.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.fahimeh.movievault.ui.design.Dimens
import com.fahimeh.movievault.ui.theme.MovieVaultTheme

@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(Dimens.lg),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Home",
            style = MaterialTheme.typography.titleLarge
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    MovieVaultTheme {
        HomeScreen()
    }
}