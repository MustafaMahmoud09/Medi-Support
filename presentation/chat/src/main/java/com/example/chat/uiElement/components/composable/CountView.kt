package com.example.chat.uiElement.components.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.example.sharedui.uiElement.components.composable.TextSemiBoldView
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme

@Composable
internal fun CountView(
    theme: CustomTheme,
    dimen: CustomDimen,
    number: String = "2",
    numberSize: Float = dimen.dimen_1_25,
    numberColor: Color = theme.background,
    background: Color = theme.orangeFF6C52,
    shape: Shape = CircleShape,
    modifier: Modifier = Modifier
) {

    //create container here
    Box(
        modifier = modifier
            .clip(
                shape = shape
            )
            .background(
                color = background
            )
            .padding(
                horizontal = when (number.length) {
                    1 -> {
                        dimen.dimen_1.dp
                    }

                    2 -> {
                        dimen.dimen_0_75.dp
                    }

                    else -> {
                        dimen.dimen_0_5.dp
                    }
                },
                vertical = dimen.dimen_0_5.dp
            ),
        contentAlignment = Alignment.Center
    ) {

        //create number here
        TextSemiBoldView(
            theme = theme,
            dimen = dimen,
            text = number,
            size = numberSize,
            fontColor = numberColor
        )

    }//end Box

}//end UnseenCountView