package com.example.activity.uiElement.screens.activity.child

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
internal fun BloodPressureScreen() {

    BloodPressureContent()
}//end BloodPressureScreen

@Composable
private fun BloodPressureContent() {

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Blood Pressure")
    }

}//end BloodPressureContent