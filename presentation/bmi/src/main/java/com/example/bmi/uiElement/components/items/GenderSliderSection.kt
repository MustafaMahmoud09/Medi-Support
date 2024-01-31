package com.example.bmi.uiElement.components.items

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.bmi.uiElement.components.composable.SliderView
import com.example.sharedui.uiElement.components.composable.TextSemiBoldView
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme

@Composable
internal fun GenderSliderSection(
    dimen: CustomDimen,
    theme: CustomTheme,
    title: String,
    titleSize: Float = dimen.dimen_2_25,
    titleColor: Color = theme.black,
    value: Float,
    onValueChange: (Float) -> Unit,
    valueSize: Float = dimen.dimen_2_25,
    valueColor: Color = theme.blackLight353535,
    unit: String,
    startPoint: Int,
    endPoint: Int,
    pointsColor: Color = theme.grayD7D7D7,
    pointsSize: Float = dimen.dimen_2_25,
    modifier: Modifier = Modifier
) {

    //create container here
    ConstraintLayout(
        modifier = modifier
    ) {
        //create ids for components here
        val (titleId, valueId, sliderId, startPointId, endPointId) = createRefs()

        //create title text here
        TextSemiBoldView(
            theme = theme,
            dimen = dimen,
            text = "$title($unit)",
            size = titleSize,
            fontColor = titleColor,
            modifier = modifier
                .constrainAs(titleId) {
                    start.linkTo(
                        parent.start,
                        dimen.dimen_1_25.dp
                    )
                    top.linkTo(parent.top)
                }
        )

        //create value new text here
        TextSemiBoldView(
            theme = theme,
            dimen = dimen,
            text = "${value.toInt()}",
            size = valueSize,
            fontColor = valueColor,
            modifier = Modifier
                .constrainAs(valueId) {
                    start.linkTo(
                        parent.start,
                        dimen.dimen_1_25.dp
                    )
                    top.linkTo(
                        titleId.bottom,
                        dimen.dimen_1_5.dp
                    )
                }
        )

        //create slider here
        SliderView(
            dimen = dimen,
            theme = theme,
            value = value,
            onValueChange = onValueChange,
            valueRange = startPoint.toFloat()..endPoint.toFloat(),
            modifier = Modifier
                .constrainAs(sliderId) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(
                        valueId.bottom,
                        dimen.dimen_1.dp
                    )
                    width = Dimension.fillToConstraints
                }
        )

        //create start point text here
        TextSemiBoldView(
            theme = theme,
            dimen = dimen,
            text = "$startPoint",
            size = pointsSize,
            fontColor = pointsColor,
            modifier = Modifier
                .constrainAs(startPointId) {
                    start.linkTo(
                        parent.start,
                        dimen.dimen_1_25.dp
                    )
                    top.linkTo(
                        sliderId.bottom,
                        dimen.dimen_2.dp
                    )
                }
        )

        //create end point text here
        TextSemiBoldView(
            theme = theme,
            dimen = dimen,
            text = "$endPoint",
            size = pointsSize,
            fontColor = pointsColor,
            modifier = Modifier
                .constrainAs(endPointId) {
                    end.linkTo(
                        parent.end,
                        dimen.dimen_1_25.dp
                    )
                    top.linkTo(
                        sliderId.bottom,
                        dimen.dimen_2.dp
                    )
                }
        )

    }//end ConstraintLayout

}//end GenderSliderSection