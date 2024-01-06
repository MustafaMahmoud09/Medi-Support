package com.example.sharedui.uiElement.components.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale

@Composable
fun CircleImageView(
    painter: Painter,
    contentDescription: String,
    modifier: Modifier = Modifier
) {

    Image(
        painter = painter,
        contentDescription = contentDescription,
        contentScale = ContentScale.Crop,
        modifier = modifier
            .clip(
                shape = CircleShape
            )
    )

}//end CircleImageView