package com.example.bloodsugar.presentation.uiElement.components.items

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.sharedui.uiElement.components.composable.IconButtonView
import com.example.sharedui.uiElement.components.composable.TextSemiBoldView
import com.example.sharedui.uiElement.components.modifier.appBorder
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme

@Composable
internal fun StatusMenuSection(
    theme: CustomTheme,
    dimen: CustomDimen,
    shape: Shape = RoundedCornerShape(
        size = dimen.dimen_1_25.dp
    ),
    borderWidth: Float = dimen.dimen_0_125,
    borderColor: Color = theme.redDark,
    icon: Painter,
    iconSize: Float = dimen.dimen_1_75,
    iconTheme: Color = theme.redE52D27,
    onClick: () -> Unit,
    status: String,
    statusColor: Color = theme.black,
    statusSize: Float = dimen.dimen_2,
    modifier: Modifier = Modifier
) {

    //create container here
    ConstraintLayout(
        modifier = modifier
            .appBorder(
                borderWidth = borderWidth,
                borderColor = borderColor,
                shape = shape
            )
            .aspectRatio(
                ratio = 3f
            )
    ) {
        //create ids for components here
        val (buttonId, statusId) = createRefs()

        //create drop button here
        IconButtonView(
            dimen = dimen,
            theme = theme,
            icon = icon,
            onClick = onClick,
            size = iconSize,
            tint = iconTheme,
            modifier = Modifier
                .constrainAs(buttonId) {
                    end.linkTo(
                        parent.end,
                        dimen.dimen_2.dp
                    )
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
        )

        //create status text here
        TextSemiBoldView(
            theme = theme,
            dimen = dimen,
            text = status,
            size = statusSize,
            fontColor = statusColor,
            maxLines = 1,
            modifier = Modifier
                .constrainAs(statusId) {
                    start.linkTo(
                        parent.start,
                        dimen.dimen_2.dp
                    )
                    end.linkTo(
                        buttonId.start,
                        dimen.dimen_0_5.dp
                    )
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    width = Dimension.fillToConstraints
                }
        )

    }//end ConstraintLayout

}//end StatusSection