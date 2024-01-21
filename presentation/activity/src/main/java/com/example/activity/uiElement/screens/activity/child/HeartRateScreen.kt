package com.example.activity.uiElement.screens.activity.child

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
internal fun HeartRateScreen() {

    HeartRateContent()
}//end HeartRateScreen

@Composable
private fun HeartRateContent() {

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Heart Rate")
    }

}//end HeartRateContent