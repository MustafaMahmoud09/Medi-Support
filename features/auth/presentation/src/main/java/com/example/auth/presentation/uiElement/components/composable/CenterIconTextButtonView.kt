package com.example.auth.presentation.uiElement.components.composable

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.sharedui.uiElement.components.composable.TextSemiBoldView
import com.example.sharedui.uiElement.components.modifier.clickableWithoutHover
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme

@Composable
internal fun CenterIconTextButtonView(
    theme: CustomTheme,
    dimen: CustomDimen,
    icon: Painter,
    text: String,
    height: Float = dimen.dimen_6_5,
    onClick: () -> Unit,
    loadingProgressSize: Float = dimen.dimen_4,
    loadingState: Boolean = false,
    modifier: Modifier = Modifier
) {

    ConstraintLayout(
        modifier = modifier
            .height(
                height.dp
            )
            .clip(
                shape = RoundedCornerShape(
                    dimen.dimen_1_25.dp
                )
            )
            .border(
                width = dimen.dimen_0_25.dp,
                shape = RoundedCornerShape(
                    dimen.dimen_1_25.dp
                ),
                color = theme.grayLight
            )
            .clickableWithoutHover {
                onClick()
            }
    ) {
        //create ids for components here
        val (iconId, loadingId) = createRefs()

        Row(
            modifier = Modifier
                .constrainAs(iconId) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
        ) {

            Image(
                painter = icon,
                contentDescription = "icon",
                modifier = Modifier
                    .size(dimen.dimen_3.dp)
            )

            Spacer(
                modifier = Modifier
                    .width(dimen.dimen_2.dp)
            )

            TextSemiBoldView(
                theme = theme,
                dimen = dimen,
                text = text,
                size = dimen.dimen_2
            )

        }//end Row

        AnimatedVisibility(
            visible = loadingState,
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
            modifier = Modifier
                .constrainAs(loadingId) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    end.linkTo(
                        parent.end,
                        dimen.dimen_2.dp
                    )
                }
        ) {

            CircularProgressIndicator(
                color = theme.grayLight,
                trackColor = theme.background,
                strokeWidth = dimen.dimen_0_125.dp,
                modifier = Modifier
                    .size(
                        size = loadingProgressSize.dp
                    )
            )

        }//end AnimatedVisibility

    }//end Row

}//end IconStartButtonView