package com.example.room.uiElement.data

import androidx.compose.ui.graphics.painter.Painter

data class VideoToolData(
    val icon: Painter,
    val onClick: () -> Unit,
    val position: Int
)
