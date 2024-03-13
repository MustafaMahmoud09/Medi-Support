package com.example.sharedui.uiElement.components.composable

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import coil.compose.rememberAsyncImagePainter

@Composable
fun LoadImageView(
    painter: Painter,
    contentScale: ContentScale = ContentScale.Crop,
    modifier: Modifier = Modifier
) {

    Image(
        painter = painter,
        contentDescription = "image",
        contentScale = contentScale,
        modifier = modifier
    )

}//end CropImageView


@Composable
fun LoadImageViewII(
    imageUri: Uri,
    contentScale: ContentScale = ContentScale.Crop,
    modifier: Modifier = Modifier
) {

    Image(
        painter = rememberAsyncImagePainter(model = imageUri),
        contentDescription = "image",
        contentScale = contentScale,
        modifier = modifier
    )

}//end CropImageView