package com.example.profile.presentation.uiElement.screens.profile

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
import com.example.profile.presentation.R
import com.example.profile.presentation.uiElement.components.items.UserProfileSection
import com.example.sharedui.uiElement.components.composable.BasicButtonView
import com.example.sharedui.uiElement.components.composable.TextSemiBoldView
import com.example.sharedui.uiElement.components.items.BasicFieldSection
import com.example.sharedui.uiElement.components.items.HeaderSection
import com.example.sharedui.uiElement.components.items.TransparentDialogSection
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.dimens.MediSupportAppDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme
import com.example.sharedui.uiElement.style.theme.MediSupportAppTheme
import kotlinx.coroutines.delay

@Composable
internal fun ProfileScreen(
    popProfileDestination: () -> Unit
) {

    ProfileContent(
        onClickBack = popProfileDestination
    )
}//end ProfileScreen

@Composable
private fun ProfileContent(
    theme: CustomTheme = MediSupportAppTheme(),
    dimen: CustomDimen = MediSupportAppDimen(),
    onClickBack: () -> Unit
) {

    var stateDialog by rememberSaveable {
        mutableStateOf(false)
    }

    //create container here
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = theme.background
            )
    ) {
        //create ids for screen components here
        val (headerId, profile, name, editInfo, fields) = createRefs()

        //create success update profile dialog here
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
                    id = com.example.sharedui.R.drawable.success_bold
                ),
                tint = theme.green,
                modifier = Modifier
                    .fillMaxSize()
            )

        }


        //create header section here
        HeaderSection(
            dimen = dimen,
            theme = theme,
            onClickOnBackButton = onClickBack,
            title = stringResource(
                R.string.profile
            ),
            modifier = Modifier
                .constrainAs(headerId) {
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
                        dimen.dimen_4.dp
                    )
                    width = Dimension.fillToConstraints
                }
        )

        //create profile image user section here
        com.example.profile.presentation.uiElement.components.items.UserProfileSection(
            theme = theme,
            dimen = dimen,
            painter = painterResource(
                id = R.drawable.image
            ),
            onClick = {},
            modifier = Modifier
                .constrainAs(profile) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(
                        headerId.bottom,
                        dimen.dimen_4.dp
                    )
                }//end constrainAs
        )

        //create name user here
        TextSemiBoldView(
            theme = theme,
            dimen = dimen,
            text = "mustafa.salem",
            size = dimen.dimen_2_25,
            fontColor = theme.black,
            modifier = Modifier
                .constrainAs(name) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(
                        profile.bottom,
                        dimen.dimen_2.dp
                    )
                }//end constrainAs
        )

        //create edit info text here
        TextSemiBoldView(
            theme = theme,
            dimen = dimen,
            text = stringResource(
                R.string.edit_info
            ),
            size = dimen.dimen_1_75,
            fontColor = theme.hintIconBottom,
            modifier = Modifier
                .constrainAs(editInfo) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(
                        name.bottom,
                        dimen.dimen_2.dp
                    )
                }//end constrainAs
        )

        //create column contain on fields to update profile data
        LazyColumn(
            modifier = Modifier
                .constrainAs(fields) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(
                        editInfo.bottom,
                        dimen.dimen_2.dp
                    )
                    bottom.linkTo(parent.bottom)
                    width = Dimension.fillToConstraints
                    height = Dimension.fillToConstraints
                },
            contentPadding = PaddingValues(
                bottom = dimen.dimen_2.dp,
                start = dimen.dimen_2.dp,
                end = dimen.dimen_2.dp,
                top = dimen.dimen_2.dp
            ),
            verticalArrangement = Arrangement.spacedBy(
                dimen.dimen_2.dp
            )
        ) {

            //create full name field here
            item(
                key = 1
            ) {

                BasicFieldSection(
                    theme = theme,
                    dimen = dimen,
                    title = stringResource(
                        R.string.full_name
                    ),
                    hint = stringResource(
                        R.string.your_name
                    ),
                    value = "",
                    onChange = {},
                    modifier = Modifier
                        .fillMaxWidth()
                )

            }//end item

            //create password field here
            item(
                key = 2
            ) {

                BasicFieldSection(
                    theme = theme,
                    dimen = dimen,
                    title = stringResource(
                        com.example.sharedui.R.string.password
                    ),
                    hint = stringResource(
                        com.example.sharedui.R.string.your_password
                    ),
                    value = "",
                    fieldIsPassword = true,
                    onChange = {},
                    modifier = Modifier
                        .fillMaxWidth()
                )

            }//end item

            //create confirm password field here
            item(
                key = 3
            ) {

                BasicFieldSection(
                    theme = theme,
                    dimen = dimen,
                    title = stringResource(
                        R.string.confirm_password
                    ),
                    hint = stringResource(
                        com.example.sharedui.R.string.your_password
                    ),
                    value = "",
                    fieldIsPassword = true,
                    onChange = {},
                    modifier = Modifier
                        .fillMaxWidth()
                )

            }//end item

            //create save button here
            item(
                key = 4
            ) {

                BasicButtonView(
                    dimen = dimen,
                    theme = theme,
                    text = stringResource(
                        com.example.sharedui.R.string.save
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

}//end ProfileContent