package com.example.room.presentation.uiElement.data

import androidx.compose.ui.graphics.painter.Painter

data class VideoToolData(
    val icon: Painter,
    val onClick: () -> Unit,
    val position: Int
)
