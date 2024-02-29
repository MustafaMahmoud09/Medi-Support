package com.example.room.presentation.uiElement.components.composable

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import com.example.sharedui.uiElement.components.composable.BasicIconView
import com.example.sharedui.uiElement.components.modifier.appBorder
import com.example.sharedui.uiElement.components.modifier.clickableWithoutHover
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme

@Composable
internal fun ToolButtonView(
    dimen: CustomDimen,
    theme: CustomTheme,
    size: Float = dimen.dimen_6,
    shape: Shape = CircleShape,
    borderWidth: Float = dimen.dimen_0_125,
    unselectedBorderColor: Color = theme.visibleGray,
    selectedBorderColor: Color = theme.redDark,
    icon: Painter,
    iconTint: Color = theme.black,
    iconSize: Float = dimen.dimen_3,
    onClick: () -> Unit,
    itemNumber: Int,
    itemSelected: Int,
    modifier: Modifier = Modifier
) {

    //create animate during convert border color
    val borderColor by animateColorAsState(
        targetValue = if (itemNumber == itemSelected) {
            selectedBorderColor
        }//end if
        else {
            unselectedBorderColor
        },//end else
        label = "border color",
    )

    //create container here
    Box(
        modifier = modifier
            .size(
                size = size.dp
            )
            .appBorder(
                shape = shape,
                borderWidth = borderWidth,
                borderColor = borderColor
            )
            .clickableWithoutHover {
                onClick()
            },
        contentAlignment = Alignment.Center
    ) {

        //create button icon here
        BasicIconView(
            painter = icon,
            color = iconTint,
            size = iconSize
        )

    }//end Box

}//end ToolButtonView