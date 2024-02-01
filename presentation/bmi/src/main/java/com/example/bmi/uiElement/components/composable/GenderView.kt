package com.example.bmi.uiElement.components.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.sharedui.uiElement.components.composable.TextNormalView
import com.example.sharedui.uiElement.components.modifier.appBorder
import com.example.sharedui.uiElement.components.modifier.clickableWithoutHover
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme

@Composable
internal fun GenderView(
    dimen: CustomDimen,
    theme: CustomTheme,
    shape: Shape = RoundedCornerShape(
        size = dimen.dimen_1_25.dp
    ),
    containerWidth: Float = dimen.dimen_16_75,
    containerHeight: Float = dimen.dimen_4_75,
    type: String,
    typeSize: Float = dimen.dimen_2_25,
    unselectedTypeTextColor: Color = theme.white,
    selectedTypeTextColor: Color = theme.black,
    borderSelectedColor: Color = theme.redDark,
    borderUnselectedColor: Color = theme.transparent,
    selectedBackground: Color = theme.background,
    unselectedBackground: Color = theme.transparent,
    borderWidth: Float = dimen.dimen_0_125,
    numberItem: Int,
    itemSelected: Int,
    iconSize: Float = dimen.dimen_3,
    iconColor: Color = theme.redDark,
    modifier: Modifier = Modifier,
    onClickOnItem: () -> Unit
) {

    //create container here
    ConstraintLayout(
        modifier = modifier
            .size(
                width = containerWidth.dp,
                height = containerHeight.dp
            )
            .appBorder(
                shape = shape,
                borderWidth = borderWidth,
                borderColor = if (numberItem == itemSelected) borderSelectedColor else borderUnselectedColor,
            )
            .background(
                color = if (numberItem == itemSelected) selectedBackground else unselectedBackground
            )
            .clickableWithoutHover {
                onClickOnItem()
            }
    ) {
        //create ids for components screen here
        val (iconId, typeId) = createRefs()

        //create icon circle here
        Spacer(
            modifier = Modifier
                .constrainAs(iconId) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(
                        parent.start,
                        dimen.dimen_2.dp
                    )
                }
                .size(
                    size = iconSize.dp
                )
                .clip(
                    shape = CircleShape
                )
                .background(
                    color = iconColor
                )
        )

        //create type text here
        TextNormalView(
            theme = theme,
            dimen = dimen,
            text = type,
            size = typeSize,
            textAlign = null,
            fontColor = if (itemSelected == numberItem) selectedTypeTextColor else unselectedTypeTextColor,
            modifier = Modifier
                .constrainAs(typeId) {
                    start.linkTo(
                        iconId.end,
                        dimen.dimen_1_5.dp
                    )
                    end.linkTo(
                        parent.end,
                        dimen.dimen_1.dp
                    )
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    width = Dimension.fillToConstraints
                }
        )

    }//end ConstraintLayout

}//end GenderView