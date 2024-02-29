package com.example.sharedui.uiElement.components.items

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.sharedui.uiElement.components.composable.TextSemiBoldView
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme

@Composable
fun HorizontalIconButtonSection(
    theme: CustomTheme,
    dimen: CustomDimen,
    text: String,
    fontSize: Float = dimen.dimen_2,
    fontColor: Color = theme.white,
    iconEnd: Painter,
    iconSizeEnd: Float = dimen.dimen_3_75,
    iconStart: Painter,
    iconSizeStart: Float = dimen.dimen_3_75,
    tint: Color = theme.white,
    height: Float = dimen.dimen_6_5,
    shape: Shape = RoundedCornerShape(
        size = dimen.dimen_0_75.dp
    ),
    background: Color = theme.redDark,
    borderSize: Float = dimen.dimen_0_125,
    borderColor: Color = theme.redDark,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    ConstraintLayout(
        modifier = modifier
            .height(
                height = height.dp
            )
            .clip(shape)
            .background(
                color = background
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
            ) { onClick() }
    ) {
        val (textId, endIconId, startIconId) = createRefs()

        Icon(
            painter = iconStart,
            contentDescription = "",
            tint = tint,
            modifier = Modifier
                .constrainAs(startIconId) {
                    start.linkTo(
                        parent.start,
                        dimen.dimen_2_25.dp
                    )
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
                .size(
                    size = iconSizeStart.dp
                )
        )

        TextSemiBoldView(
            theme = theme,
            dimen = dimen,
            text = text,
            size = fontSize,
            fontColor = fontColor,
            modifier = Modifier
                .constrainAs(textId) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(
                        startIconId.end,
                        dimen.dimen_1.dp
                    )
                }//end constrainAs
        )

        Icon(
            painter = iconEnd,
            contentDescription = "",
            tint = tint,
            modifier = Modifier
                .constrainAs(endIconId) {
                    end.linkTo(
                        parent.end,
                        dimen.dimen_2_25.dp
                    )
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
                .size(
                    size = iconSizeEnd.dp
                )
        )

    }//end ConstraintLayout

}//end EndIconButtonSection