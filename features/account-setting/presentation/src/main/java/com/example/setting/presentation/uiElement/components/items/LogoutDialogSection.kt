package com.example.setting.presentation.uiElement.components.items

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.sharedui.uiElement.components.composable.BasicButtonView
import com.example.sharedui.uiElement.components.composable.TextNormalView
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme

@ExperimentalMaterial3Api
@Composable
fun LogoutDialogSection(
    onDismissRequest: () -> Unit,
    dimen: CustomDimen,
    theme: CustomTheme,
    onClickOnCancel: () -> Unit,
    title: String,
    titleColor: Color = theme.black,
    titleSize: Float = dimen.dimen_2_25,
    cancelButtonTitle: String,
    logoutButtonTitle: String,
    cancelTextColor: Color = theme.black,
    logoutTextColor: Color = theme.redDark,
    buttonTextSize: Float = dimen.dimen_1_75,
    onClickOnLogout: () -> Unit,
    load: Boolean = false,
    buttonHeight: Float = dimen.dimen_5,
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
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(
                    horizontal = dimen.dimen_3.dp
                ),
            shape = RoundedCornerShape(
                size = dimen.dimen_0_25.dp
            )
        ) {

            ConstraintLayout(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .background(
                        color = theme.background
                    ).padding(
                        bottom = dimen.dimen_1.dp
                    )
            ) {
                val (titleId, logoutButtonId, cancelButtonId) = createRefs()

                TextNormalView(
                    theme = theme,
                    dimen = dimen,
                    text = title,
                    size = titleSize,
                    fontColor = titleColor,
                    textAlign = null,
                    modifier = Modifier
                        .constrainAs(titleId) {
                            start.linkTo(
                                parent.start,
                                dimen.dimen_2.dp
                            )
                            end.linkTo(
                                parent.end,
                                dimen.dimen_2.dp
                            )
                            top.linkTo(
                                parent.top,
                                dimen.dimen_3.dp
                            )
                            width = Dimension.fillToConstraints
                        }
                )

                BasicButtonView(
                    dimen = dimen,
                    theme = theme,
                    text = logoutButtonTitle,
                    onClick = onClickOnLogout,
                    fontColor = logoutTextColor,
                    fontSize = buttonTextSize,
                    color = theme.transparent,
                    height = buttonHeight,
                    modifier = Modifier
                        .constrainAs(logoutButtonId) {
                            end.linkTo(
                                parent.end,
                                dimen.dimen_2.dp
                            )
                            top.linkTo(
                                titleId.bottom,
                                dimen.dimen_3.dp
                            )
                        }
                )

                BasicButtonView(
                    dimen = dimen,
                    theme = theme,
                    text = cancelButtonTitle,
                    onClick = onClickOnCancel,
                    color = theme.transparent,
                    fontColor = cancelTextColor,
                    fontSize = buttonTextSize,
                    height = buttonHeight,
                    modifier = Modifier
                        .constrainAs(cancelButtonId) {
                            end.linkTo(
                                logoutButtonId.start,
                                dimen.dimen_1_5.dp
                            )
                            top.linkTo(
                                titleId.bottom,
                                dimen.dimen_3.dp
                            )
                        }
                )

                if (load){


                    CircularProgressIndicator(
                        color = theme.grayLight,
                        trackColor = theme.background,
                        strokeWidth = dimen.dimen_0_5.dp,
                        modifier = Modifier
                            .size(
                                size = dimen.dimen_3.dp
                            ).constrainAs(createRef()){
                                start.linkTo(titleId.start)
                                top.linkTo(cancelButtonId.top)
                                bottom.linkTo(cancelButtonId.bottom)
                            }
                    )

                }//end if

            }//end ConstraintLayout

        }//end Card

    }//end AlertDialog

}//end LogoutDialogSection