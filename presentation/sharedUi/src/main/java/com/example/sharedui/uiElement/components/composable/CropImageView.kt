package com.example.sharedui.uiElement.components.composable

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale

@Composable
fun CropImageView(
    painter: Painter,
    modifier: Modifier = Modifier
) {

    Image(
        painter = painter,
        contentDescription = "image",
        contentScale = ContentScale.Crop,
        modifier = modifier
    )

}//end CropImageView