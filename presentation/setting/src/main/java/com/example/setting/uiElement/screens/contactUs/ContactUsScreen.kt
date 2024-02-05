package com.example.setting.uiElement.screens.contactUs

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.sharedui.R
import com.example.sharedui.uiElement.components.composable.IconButtonView
import com.example.sharedui.uiElement.components.composable.BasicButtonView
import com.example.sharedui.uiElement.components.composable.TextBoldView
import com.example.sharedui.uiElement.components.items.BasicFieldSection
import com.example.sharedui.uiElement.components.items.TransparentDialogSection
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.dimens.MediSupportAppDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme
import com.example.sharedui.uiElement.style.theme.MediSupportAppTheme
import kotlinx.coroutines.delay

@Composable
internal fun ContactUsScreen(
    popContactUsDestination: () -> Unit
) {

    ContactUsContent(
        onClickBack = popContactUsDestination
    )
}//end ContactUsScreen

@Composable
private fun ContactUsContent(
    theme: CustomTheme = MediSupportAppTheme(),
    dimen: CustomDimen = MediSupportAppDimen(),
    onClickBack: () -> Unit
) {

    var stateDialog by rememberSaveable {
        mutableStateOf(false)
    }

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = theme.background
            )
    ) {
        val (backButton, title, fieldsId) = createRefs()

        AnimatedVisibility(
            visible = stateDialog,
            enter = fadeIn(
                animationSpec = tween(
                    durationMillis = 100
                )
            ),
            exit = fadeOut(
                animationSpec = tween(
                    durationMillis = 100
                )
            )
        ) {

            TransparentDialogSection(
                onDismissRequest = { /*TODO*/ },
                dimen = dimen,
                theme = theme,
                logo = painterResource(
                    id = R.drawable.success_bold
                ),
                tint = theme.green,
                modifier = Modifier
                    .fillMaxSize()
            )

        }

        IconButtonView(
            dimen = dimen,
            theme = theme,
            onClick = onClickBack,
            modifier = Modifier
                .constrainAs(backButton) {
                    start.linkTo(
                        parent.start,
                        dimen.dimen_2.dp
                    )
                    top.linkTo(
                        parent.top,
                        dimen.dimen_4.dp
                    )
                }//end constrainAs
        )

        TextBoldView(
            theme = theme,
            dimen = dimen,
            text = stringResource(
                com.example.sharedui.R.string.contact_us_ca
            ),
            size = dimen.dimen_2_25,
            modifier = Modifier
                .constrainAs(title) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(
                        parent.top,
                        dimen.dimen_4.dp
                    )
                }//end constrainAs
        )

        LazyColumn(
            modifier = Modifier
                .constrainAs(fieldsId) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(
                        title.bottom,
                        dimen.dimen_2.dp
                    )
                    bottom.linkTo(parent.bottom)
                    height = Dimension.fillToConstraints
                    width = Dimension.fillToConstraints
                },
            contentPadding = PaddingValues(
                start = dimen.dimen_2.dp,
                end = dimen.dimen_2.dp,
                bottom = dimen.dimen_2.dp,
                top = dimen.dimen_2.dp
            ),
            verticalArrangement = Arrangement.spacedBy(
                dimen.dimen_3.dp
            )
        ) {

            item(
                key = 1
            ) {

                BasicFieldSection(
                    theme = theme,
                    dimen = dimen,
                    title = stringResource(
                        com.example.sharedui.R.string.name
                    ),
                    hint = stringResource(
                        com.example.sharedui.R.string.your_name
                    ),
                    value = "",
                    onChange = {},
                    modifier = Modifier
                        .fillMaxWidth()
                )

            }//end item

            item(
                key = 2
            ) {

                BasicFieldSection(
                    theme = theme,
                    dimen = dimen,
                    title = stringResource(
                        com.example.sharedui.R.string.email_address
                    ),
                    hint = stringResource(
                        com.example.sharedui.R.string.your_email
                    ),
                    value = "",
                    onChange = {},
                    modifier = Modifier
                        .fillMaxWidth()
                )

            }//end item

            item(
                key = 3
            ) {

                BasicFieldSection(
                    theme = theme,
                    dimen = dimen,
                    title = stringResource(
                        com.example.sharedui.R.string.message
                    ),
                    hint = stringResource(
                        com.example.sharedui.R.string.your_message
                    ),
                    fieldHeight = dimen.dimen_17_5,
                    maxLines = Int.MAX_VALUE,
                    value = "",
                    contentCenter = false,
                    onChange = {},
                    modifier = Modifier
                        .fillMaxWidth()
                )

            }//end item

            item(
                key = 4
            ) {

                BasicButtonView(
                    dimen = dimen,
                    theme = theme,
                    text = stringResource(
                        com.example.sharedui.R.string.send
                    ),
                    onClick = { stateDialog = !stateDialog },
                    modifier = Modifier
                        .fillMaxWidth()
                )

            }//end item

        }//end LazyColumn

    }//end ConstraintLayout

    LaunchedEffect(key1 = stateDialog) {

        if (stateDialog) {
            delay(1000)
            stateDialog = false
        }//end if

    }//end LaunchedEffect

}//end ContactUsContent

