package com.example.bmi.presentation.uiElement.components.items

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.bmi.presentation.uiElement.components.composable.GenderView
import com.example.sharedui.uiElement.components.composable.TextSemiBoldView
import com.example.sharedui.uiElement.components.modifier.appBorder
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme

@Composable
internal fun GenderSection(
    theme: CustomTheme,
    dimen: CustomDimen,
    title: String,
    titleSize: Float = dimen.dimen_2_25,
    titleColor: Color = theme.black,
    shape: Shape = RoundedCornerShape(
        size = dimen.dimen_1_25.dp
    ),
    firstTypeNumber: Boolean = true,
    secondTypeNumber: Boolean = false,
    typeSelected: Boolean,
    onClickOnType: (Boolean) -> Unit,
    firstType: String,
    secondType: String,
    borderWidth: Float = dimen.dimen_0_125,
    borderColor: Color = theme.redDark,
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
            com.example.bmi.presentation.uiElement.components.composable.GenderView(
                dimen = dimen,
                theme = theme,
                type = firstType,
                typeItem = firstTypeNumber,
                typeSelected = typeSelected,
                onClickOnItem = onClickOnType,
            )

            //create second gender here
            com.example.bmi.presentation.uiElement.components.composable.GenderView(
                dimen = dimen,
                theme = theme,
                type = secondType,
                typeItem = secondTypeNumber,
                typeSelected = typeSelected,
                onClickOnItem = onClickOnType
            )

        }//end Row

    }//end ConstraintLayout

}//end GenderSection