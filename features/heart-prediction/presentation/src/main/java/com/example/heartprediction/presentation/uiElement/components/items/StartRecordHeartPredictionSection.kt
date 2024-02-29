package com.example.heartprediction.presentation.uiElement.components.items

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.sharedui.uiElement.components.composable.BasicButtonView
import com.example.sharedui.uiElement.components.composable.MultiColorTextView
import com.example.sharedui.uiElement.components.modifier.appBorderShadow
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme

@Composable
internal fun StartRecordHeartPredictionSection(
    dimen: CustomDimen,
    theme: CustomTheme,
    borderWidth: Float = dimen.dimen_0_125,
    borderColor: Color = theme.grayLineLight,
    shape: Shape = RoundedCornerShape(
        size = dimen.dimen_1_25.dp
    ),
    elevationShadow: Float = dimen.dimen_0_5,
    ambientColor: Color = theme.blackTR25,
    spotColor: Color = theme.white,
    parentText: String,
    subTexts: Array<String>,
    subTextColors: Array<Color>,
    parentTextColor: Color = theme.black,
    painter: Painter,
    buttonText: String,
    buttonHeight: Float = dimen.dimen_4_25,
    onClick: () -> Unit,
    buttonFontSize: Float = dimen.dimen_1_75,
    modifier: Modifier = Modifier,
) {

    //create container here
    ConstraintLayout(
        modifier = modifier
            .appBorderShadow(
                borderWidth = borderWidth,
                borderColor = borderColor,
                shape = shape,
                elevation = elevationShadow,
                ambientColor = ambientColor,
                spotColor = spotColor,
            )
            .padding(
                bottom = dimen.dimen_0_75.dp
            )
            .aspectRatio(
                ratio = 1f
            )
    ) {
        //create ids for components here
        val (imageId, messageId, buttonId) = createRefs()
        val guideFromStart15P = createGuidelineFromStart(0.15f)
        val guideFromEnd15P = createGuidelineFromEnd(0.15f)

        //create heart image here
        Image(
            painter = painter,
            contentDescription = "image",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .constrainAs(imageId) {
                    start.linkTo(guideFromStart15P)
                    end.linkTo(guideFromEnd15P)
                    top.linkTo(
                        parent.top,
                        dimen.dimen_0_75.dp
                    )
                    width = Dimension.fillToConstraints
                }
                .aspectRatio(
                    ratio = 1.4f
                )
        )

        //create text message here
        MultiColorTextView(
            dimen = dimen,
            theme = theme,
            parentText = parentText,
            subTexts = subTexts,
            subTextsColors = subTextColors,
            parentTextColor = parentTextColor,
            modifier = Modifier
                .constrainAs(messageId) {
                    start.linkTo(
                        parent.start,
                        dimen.dimen_2_5.dp
                    )
                    end.linkTo(
                        parent.end,
                        dimen.dimen_2_5.dp
                    )
                    top.linkTo(
                        imageId.bottom,
                        dimen.dimen_1_5.dp
                    )
                    width = Dimension.fillToConstraints
                }//end constrainAs
        )

        //create button here
        BasicButtonView(
            dimen = dimen,
            theme = theme,
            text = buttonText,
            onClick = onClick,
            height = buttonHeight,
            fontSize = buttonFontSize,
            modifier = Modifier
                .constrainAs(buttonId) {
                    start.linkTo(
                        messageId.start,
                        dimen.dimen_2_5.dp
                    )
                    end.linkTo(
                        messageId.end,
                        dimen.dimen_2_5.dp
                    )
                    top.linkTo(
                        messageId.bottom,
                        dimen.dimen_2.dp
                    )
                    width = Dimension.fillToConstraints
                }
        )

    }//end ConstraintLayout

}//end StartRecordHeartPredictionSection