package com.damanhour.Graduation.medisupport.ui.uiElement.components.items

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.sharedui.uiElement.components.composable.TextBoldView
import com.example.sharedui.uiElement.components.composable.TextSemiBoldView
import com.example.sharedui.uiElement.components.modifier.clickableWithoutHover
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme

@Composable
internal fun HealthCareSection(
    dimen: CustomDimen,
    theme: CustomTheme,
    title: String,
    titleSize: Float = dimen.dimen_1_75,
    titleColor: Color = theme.black,
    value: String,
    unit: String,
    borderSize: Float = dimen.dimen_0_125,
    borderColor: Color = theme.grayD7D7D7,
    image: Painter,
    shape: Shape = RoundedCornerShape(
        size = dimen.dimen_1_25.dp
    ),
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    //create container here
    ConstraintLayout(
        modifier = modifier
            .aspectRatio(1f)
            .clip(
                shape = shape
            )
            .border(
                width = borderSize.dp,
                color = borderColor,
                shape = shape
            )
            .clickableWithoutHover {
                onClick()
            }
    ) {
        //create ids to components here
        val (titleId, valueId, unitId, imageId) = createRefs()

        //create title here
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
                        dimen.dimen_2.dp
                    )
                    top.linkTo(
                        parent.top,
                        dimen.dimen_2.dp
                    )
                }
        )

        //create value text here
        TextBoldView(
            theme = theme,
            dimen = dimen,
            text = value,
            size = dimen.dimen_1_75,
            color = theme.redDark,
            modifier = Modifier
                .constrainAs(valueId) {
                    start.linkTo(
                        parent.start,
                        dimen.dimen_2.dp
                    )
                    top.linkTo(
                        titleId.bottom,
                        dimen.dimen_1.dp
                    )
                }
        )

        //create unit text here
        TextBoldView(
            theme = theme,
            dimen = dimen,
            text = unit,
            size = dimen.dimen_1_75,
            color = theme.black,
            modifier = Modifier
                .constrainAs(unitId) {
                    start.linkTo(
                        valueId.end,
                        dimen.dimen_0_5.dp
                    )
                    top.linkTo(
                        titleId.bottom,
                        dimen.dimen_1.dp
                    )
                }
        )

        //create image here
        Image(
            painter = image,
            contentDescription = "image",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .constrainAs(imageId) {
                    end.linkTo(
                        parent.end,
                        dimen.dimen_1_5.dp
                    )
                    top.linkTo(
                        unitId.bottom,
                        dimen.dimen_1.dp
                    )
                    bottom.linkTo(
                        parent.bottom,
                        dimen.dimen_1_5.dp
                    )
                    height = Dimension.fillToConstraints
                }
                .aspectRatio(
                    ratio = 1f
                )
        )

    }//end ConstraintLayout

}//end HealthCareSection