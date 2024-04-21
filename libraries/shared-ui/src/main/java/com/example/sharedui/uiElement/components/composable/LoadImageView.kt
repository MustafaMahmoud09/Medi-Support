package com.example.sharedui.uiElement.components.composable

import android.net.Uri
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme

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
fun ServerLoadImageView(
    theme: CustomTheme,
    dimen: CustomDimen,
    progressBarColor: Color = theme.redDark,
    progressBarTrackColor: Color = theme.background,
    progressWidth: Float = dimen.dimen_0_125,
    progressSize: Float = dimen.dimen_4,
    imageUrl: String,
    contentScale: ContentScale = ContentScale.Crop,
    modifier: Modifier = Modifier
) {
    val imageLoadRequest = rememberAsyncImagePainter(
        model = ImageRequest
            .Builder(
                LocalContext.current
            ).data(
                data = imageUrl
            ).size(
                coil.size.Size.ORIGINAL
            ).build()
    )

    //if image is loading create progress bar
    AnimatedVisibility(
        visible = imageLoadRequest.state is AsyncImagePainter.State.Loading,
        enter = fadeIn(
            animationSpec = tween(
                durationMillis = 50
            )
        ),
        exit = fadeOut(
            animationSpec = tween(
                durationMillis = 50
            )
        ),
        modifier = modifier
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {

            CircularProgressIndicator(
                color = progressBarColor,
                trackColor = progressBarTrackColor,
                strokeWidth = progressWidth.dp,
                modifier = Modifier
                    .size(
                        size = progressSize.dp
                    )
            )

        }//end Box

    }//end AnimatedVisibility

    //if image is error create error message
    AnimatedVisibility(
        visible = imageLoadRequest.state is AsyncImagePainter.State.Error,
        enter = fadeIn(
            animationSpec = tween(
                durationMillis = 50
            )
        ),
        exit = fadeOut(
            animationSpec = tween(
                durationMillis = 50
            )
        ),
        modifier = modifier
    ) {

    }//end AnimatedVisibility

    //if image is success create image
    AnimatedVisibility(
        visible = imageLoadRequest.state is AsyncImagePainter.State.Success,
        enter = fadeIn(
            animationSpec = tween(
                durationMillis = 50
            )
        ),
        exit = fadeOut(
            animationSpec = tween(
                durationMillis = 50
            )
        ),
        modifier = modifier
    ) {

        Image(
            painter = imageLoadRequest,
            contentDescription = "image",
            contentScale = contentScale,
            modifier = Modifier
                .fillMaxSize()
        )

    }//end AnimatedVisibility

}//end CropImageView


@Composable
fun LoadImageViewII(
    imageUri: Uri,
    contentScale: ContentScale = ContentScale.Crop,
    modifier: Modifier = Modifier
) {

    Image(
        painter = rememberAsyncImagePainter(
            model = imageUri
        ),
        contentDescription = "image",
        contentScale = contentScale,
        modifier = modifier
    )

}//end CropImageView