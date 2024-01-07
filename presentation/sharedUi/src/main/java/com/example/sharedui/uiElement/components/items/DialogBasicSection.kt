@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.sharedui.uiElement.components.items

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.sharedui.uiElement.components.composable.BasicButtonView
import com.example.sharedui.uiElement.components.composable.TextBoldView
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme

@Composable
fun DialogBasicSection(
    onDismissRequest: () -> Unit,
    dimen: CustomDimen,
    theme: CustomTheme,
    logo: Painter,
    tint: Color,
    buttonTitle: String,
    onClick: () -> Unit,
    proText: String,
    modifier: Modifier = Modifier
) {


    AlertDialog(
        onDismissRequest = onDismissRequest,
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        ),
        modifier = modifier
    ) {

        Card(
            shape = RoundedCornerShape(
                dimen.dimen_2_5.dp
            )
        ) {

            ConstraintLayout(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        color = theme.background
                    )
            ) {
                val (successImage, processText, button) = createRefs()
                val guideLineFromTop20 = createGuidelineFromTop(.20f)

                Icon(
                    painter = logo,
                    contentDescription = "",
                    tint = tint,
                    modifier = Modifier
                        .constrainAs(successImage) {
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            top.linkTo(guideLineFromTop20)
                        }
                        .size(dimen.dimen_10.dp)
                )

                TextBoldView(
                    theme = theme,
                    dimen = dimen,
                    text = proText,
                    size = dimen.dimen_3_75,
                    modifier = Modifier
                        .constrainAs(processText) {
                            start.linkTo(
                                parent.start,
                                dimen.dimen_4.dp
                            )
                            end.linkTo(
                                parent.end,
                                dimen.dimen_4.dp
                            )
                            top.linkTo(
                                successImage.bottom,
                                dimen.dimen_4.dp
                            )
                            width = Dimension.fillToConstraints
                        }
                )

                BasicButtonView(
                    dimen = dimen,
                    theme = theme,
                    text = buttonTitle,
                    onClick = onClick,
                    modifier = Modifier
                        .constrainAs(button) {
                            start.linkTo(
                                parent.start,
                                dimen.dimen_4.dp
                            )
                            end.linkTo(
                                parent.end,
                                dimen.dimen_4.dp
                            )
                            top.linkTo(
                                processText.bottom,
                                dimen.dimen_4.dp
                            )
                            width = Dimension.fillToConstraints
                        }
                        .size(dimen.dimen_7.dp)
                )

            }//end ConstraintLayout

        }//end Card

    }//end Dialog

}//end DialogBackSection