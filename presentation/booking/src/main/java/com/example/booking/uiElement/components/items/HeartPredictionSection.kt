package com.example.booking.uiElement.components.items

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
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
import com.example.sharedui.uiElement.components.composable.BasicButtonView
import com.example.sharedui.uiElement.components.composable.MultiColorTextView
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme

@Composable
internal fun HeartPredictionSection(
    dimen: CustomDimen,
    theme: CustomTheme,
    shape: Shape = RoundedCornerShape(
        size = dimen.dimen_1_25.dp
    ),
    borderWidth: Float = dimen.dimen_0_125,
    borderColor: Color = theme.redDark,
    image: Painter,
    title: String,
    firstText: String,
    secondText: String,
    thirdText: String,
    firstTextColor: Color = theme.blueLight7891B7,
    secondTextColor: Color = theme.redLightDB9E9C,
    thirdTextColor: Color = theme.redSemiLightC66363,
    otherTextColor: Color = theme.black,
    buttonContent: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    //create container here
    ConstraintLayout(
        modifier = modifier
            .wrapContentHeight()
            .clip(
                shape = shape
            )
            .border(
                width = borderWidth.dp,
                color = borderColor,
                shape = shape
            )
    ) {
        //create ids to components here
        val (imageId, titleId, buttonId, spacerId) = createRefs()
        val guideFromStart42P = createGuidelineFromStart(.42f)

        //create title text here
        MultiColorTextView(
            dimen = dimen,
            theme = theme,
            parentText = title,
            subTexts = arrayOf(firstText, secondText, thirdText),
            subTextsColors = arrayOf(firstTextColor, secondTextColor, thirdTextColor),
            parentTextColor = otherTextColor,
            modifier = Modifier
                .constrainAs(titleId) {
                    start.linkTo(
                        guideFromStart42P,
                        dimen.dimen_1.dp
                    )
                    end.linkTo(
                        parent.end,
                        dimen.dimen_1.dp
                    )
                    top.linkTo(
                        parent.top,
                        dimen.dimen_2.dp
                    )
                    width = Dimension.fillToConstraints
                }
        )

        //create basic button here
        BasicButtonView(
            dimen = dimen,
            theme = theme,
            text = buttonContent,
            onClick = onClick,
            height = dimen.dimen_4_25,
            fontSize = dimen.dimen_1_75,
            modifier = Modifier
                .constrainAs(buttonId) {
                    start.linkTo(
                        guideFromStart42P,
                        dimen.dimen_2.dp
                    )
                    end.linkTo(
                        parent.end,
                        dimen.dimen_2.dp
                    )
                    top.linkTo(
                        titleId.bottom,
                        dimen.dimen_2_25.dp
                    )
                    width = Dimension.fillToConstraints
                }
        )

        //create padding from bottom
        Spacer(
            modifier = Modifier
                .constrainAs(spacerId) {
                    start.linkTo(guideFromStart42P)
                    end.linkTo(parent.end)
                    top.linkTo(buttonId.bottom)
                }
                .height(
                    dimen.dimen_1_25.dp
                )
        )

        //createImage here
        Image(
            painter = image,
            contentDescription = "image",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .constrainAs(imageId) {
                    start.linkTo(parent.start)
                    end.linkTo(guideFromStart42P)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    height = Dimension.fillToConstraints
                    width = Dimension.fillToConstraints
                }
        )


    }//end ConstraintLayout

}//end PredictHeartSection