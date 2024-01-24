package com.example.article.uiElement.components.composable

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.sharedui.uiElement.components.composable.TextSemiBoldView
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme

@Composable
internal fun EndIconButtonRadiusView(
    dimen: CustomDimen,
    theme: CustomTheme,
    borderSize: Float = dimen.dimen_0_25,
    borderColor: Color = theme.redDark,
    shape: Shape = RoundedCornerShape(
        size = dimen.dimen_1_25.dp
    ),
    icon: Painter,
    iconTint: Color = theme.redDark,
    title: String,
    titleColor: Color = theme.black,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    ConstraintLayout(
        modifier = modifier
            .aspectRatio(5.5f)
            .clip(
                shape = shape
            )
            .border(
                width = borderSize.dp,
                color = borderColor,
                shape = shape
            )
            .clickable(
                interactionSource = remember {
                    MutableInteractionSource()
                },
                indication = null
            ) {
                onClick()
            }
    ) {
        val (iconId, titleId) = createRefs()

        Icon(
            painter = icon,
            contentDescription = "icon",
            tint = iconTint,
            modifier = Modifier
                .constrainAs(iconId) {
                    end.linkTo(
                        parent.end,
                        dimen.dimen_1.dp
                    )
                    top.linkTo(
                        parent.top,
                        dimen.dimen_0_25.dp
                    )
                    bottom.linkTo(
                        parent.bottom,
                        dimen.dimen_0_25.dp
                    )
                }
                .aspectRatio(
                    1f
                )
        )

        Box(
            modifier = Modifier
                .constrainAs(titleId) {
                    start.linkTo(
                        parent.start,
                        dimen.dimen_1.dp
                    )
                    end.linkTo(
                        iconId.start
                    )
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    width = Dimension.fillToConstraints
                    height = Dimension.fillToConstraints
                },//end constrainAs
            contentAlignment = Alignment.Center
        ) {

            TextSemiBoldView(
                theme = theme,
                dimen = dimen,
                text = title,
                size = dimen.dimen_1_25,
                textAlign = TextAlign.Center,
                fontColor = titleColor,
            )

        }//end Box

    }//end ConstraintLayout

}//end MoreButtonView