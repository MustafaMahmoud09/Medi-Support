package com.example.bmi.uiElement.components.items

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.bmi.uiElement.components.composable.GenderView
import com.example.sharedui.uiElement.components.composable.TextSemiBoldView
import com.example.sharedui.uiElement.components.modifier.appBorder
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme

@Composable
internal fun GenderSection(
    theme: CustomTheme,
    dimen: CustomDimen,
    title: String,
    titleSize: Float = dimen.dimen_2_5,
    titleColor: Color = theme.black,
    shape: Shape = RoundedCornerShape(
        size = dimen.dimen_1_25.dp
    ),
    firstType: String,
    firstTypeNumber: Int = 0,
    secondType: String,
    secondTypeNumber: Int = 1,
    borderWidth: Float = dimen.dimen_0_125,
    borderColor: Color = theme.redDark,
    itemSelected: Int,
    onClickOnItem: () -> Unit,
    modifier: Modifier = Modifier,
) {

    //create container here
    ConstraintLayout(
        modifier = modifier
    ) {
        //create ids for screen components here
        val (titleId, gendersId) = createRefs()

        //create gender title here
        TextSemiBoldView(
            theme = theme,
            dimen = dimen,
            text = title,
            size = titleSize,
            fontColor = titleColor,
            modifier = Modifier
                .constrainAs(titleId) {
                    start.linkTo(
                        parent.start,
                        dimen.dimen_0_25.dp
                    )
                    top.linkTo(parent.top)
                }
        )

        //create gender items here
        Row(
            modifier = Modifier
                .constrainAs(gendersId) {
                    start.linkTo(parent.start)
                    top.linkTo(
                        titleId.bottom,
                        dimen.dimen_2.dp
                    )
                }
                .appBorder(
                    shape = shape,
                    borderColor = borderColor,
                    borderWidth = borderWidth
                )
                .background(
                    color = theme.redBF171DTR76
                )
        ) {

            //create first gender here
            GenderView(
                dimen = dimen,
                theme = theme,
                type = firstType,
                numberItem = firstTypeNumber,
                itemSelected = itemSelected,
                onClickOnItem = onClickOnItem,
            )

            //create second gender here
            GenderView(
                dimen = dimen,
                theme = theme,
                type = secondType,
                numberItem = secondTypeNumber,
                itemSelected = itemSelected,
                onClickOnItem = onClickOnItem
            )

        }//end Row

    }//end ConstraintLayout

}//end GenderSection