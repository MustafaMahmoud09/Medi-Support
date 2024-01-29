package com.example.heartprediction.uiElement.components.items

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.sharedui.uiElement.components.composable.MultiColorTextView
import com.example.sharedui.uiElement.components.composable.MultiFamilyTextView
import com.example.sharedui.uiElement.components.modifier.appBorderShadow
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.robotoMedium
import com.example.sharedui.uiElement.style.theme.CustomTheme

@Composable
internal fun ResultPredictionSection(
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
    parentMessage: String,
    subMessages: Array<String>,
    subMessageFamily: Array<FontFamily>,
    parentMessageFamily: FontFamily,
    messageColor: Color = theme.black,
    messageSize: Float = dimen.dimen_2,
    painter: Painter,
    iconTint: Color,
    parentNote: String,
    subNotes: Array<String>,
    subNoteColors: Array<Color>,
    parentNoteColor: Color = theme.black,
    noteSize: Float = dimen.dimen_1_75,
    noteFontFamily: FontFamily = robotoMedium,
    modifier: Modifier = Modifier
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
            .padding(
                vertical = dimen.dimen_6.dp
            )
    ) {
        //create ids for components here
        val (imageId, messageId, noteId) = createRefs()
        val guideFromStart25P = createGuidelineFromStart(0.25f)
        val guideFromEnd25P = createGuidelineFromEnd(0.25f)

        //create icon here
        Icon(
            painter = painter,
            contentDescription = "icon",
            tint = iconTint,
            modifier = Modifier
                .constrainAs(imageId) {
                    start.linkTo(guideFromStart25P)
                    end.linkTo(guideFromEnd25P)
                    width = Dimension.fillToConstraints
                }
                .aspectRatio(1f)
        )

        //create message here
        MultiFamilyTextView(
            dimen = dimen,
            theme = theme,
            parentText = parentMessage,
            subTexts = subMessages,
            subTextsFamily = subMessageFamily,
            parentTextFamily = parentMessageFamily,
            textColor = messageColor,
            textSize = messageSize,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .constrainAs(messageId) {
                    start.linkTo(
                        parent.start,
                        dimen.dimen_3_5.dp
                    )
                    end.linkTo(
                        parent.end,
                        dimen.dimen_3_5.dp
                    )
                    top.linkTo(
                        imageId.bottom,
                        dimen.dimen_3.dp
                    )
                    width = Dimension.fillToConstraints
                }
        )

        //create note here
        MultiColorTextView(
            dimen = dimen,
            theme = theme,
            parentText = parentNote,
            subTexts = subNotes,
            subTextsColors = subNoteColors,
            parentTextColor = parentNoteColor,
            textAlign = TextAlign.Center,
            textSize = noteSize,
            fontFamily = noteFontFamily,
            modifier = Modifier
                .constrainAs(noteId) {
                    start.linkTo(
                        parent.start,
                        dimen.dimen_2_5.dp
                    )
                    end.linkTo(
                        parent.end,
                        dimen.dimen_2_5.dp
                    )
                    top.linkTo(
                        messageId.bottom,
                        dimen.dimen_2_5.dp
                    )
                    width = Dimension.fillToConstraints
                }
        )

    }//end ConstraintLayout

}//end ResultPredictionSection