package com.example.setting.uiElement.screens.more

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.setting.R
import com.example.sharedui.uiElement.components.composable.BackButtonView
import com.example.sharedui.uiElement.components.composable.TextBoldView
import com.example.sharedui.uiElement.components.items.EndIconButtonSection
import com.example.sharedui.uiElement.components.items.HorizontalIconButtonSection
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.dimens.MediSupportAppDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme
import com.example.sharedui.uiElement.style.theme.MediSupportAppTheme


@Composable
internal fun MoreScreen(
    popMoreNavGraph: () -> Unit,
    navigateToContactUsDestination: () -> Unit,
    navigateToAboutDestination: () -> Unit
) {

    MoreContent(
        onClickBack = popMoreNavGraph,
        onClickContactUs = navigateToContactUsDestination,
        onClickAbout = navigateToAboutDestination
    )
}//end MoreScreen

@Composable
private fun MoreContent(
    theme: CustomTheme = MediSupportAppTheme(),
    dimen: CustomDimen = MediSupportAppDimen(),
    onClickBack: () -> Unit,
    onClickContactUs: () -> Unit,
    onClickAbout: () -> Unit
) {

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = theme.background
            )
    ) {
        val (backButton, title, buttonsId) = createRefs()

        BackButtonView(
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
                R.string.more
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
                .constrainAs(buttonsId) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(
                        title.bottom,
                        dimen.dimen_4.dp
                    )
                    bottom.linkTo(parent.bottom)
                    height = Dimension.fillToConstraints
                    width = Dimension.fillToConstraints
                },
            contentPadding = PaddingValues(
                start = dimen.dimen_2.dp,
                end = dimen.dimen_2.dp,
                bottom = dimen.dimen_2.dp
            ),
            verticalArrangement = Arrangement.spacedBy(
                dimen.dimen_3.dp
            )
        ) {

            item(
                key = 1
            ) {

                EndIconButtonSection(
                    theme = theme,
                    dimen = dimen,
                    text = stringResource(
                        com.example.sharedui.R.string.details_of_booking_doctor
                    ),
                    icon = painterResource(
                        id = com.example.sharedui.R.drawable.back_fw
                    ),
                    onClick = {},
                    modifier = Modifier
                        .fillMaxWidth()
                )

            }//end item

            item(
                key = 2
            ) {

                EndIconButtonSection(
                    theme = theme,
                    dimen = dimen,
                    text = stringResource(
                        com.example.sharedui.R.string.about
                    ),
                    icon = painterResource(
                        id = com.example.sharedui.R.drawable.back_fw
                    ),
                    onClick = onClickAbout,
                    modifier = Modifier
                        .fillMaxWidth()
                )

            }//end item

            item(
                key = 3
            ) {

                EndIconButtonSection(
                    theme = theme,
                    dimen = dimen,
                    text = stringResource(
                        com.example.sharedui.R.string.contact_us
                    ),
                    icon = painterResource(
                        id = com.example.sharedui.R.drawable.back_fw
                    ),
                    onClick = onClickContactUs,
                    modifier = Modifier
                        .fillMaxWidth()
                )

            }//end item

            item(
                key = 4
            ) {

                EndIconButtonSection(
                    theme = theme,
                    dimen = dimen,
                    text = stringResource(
                        com.example.sharedui.R.string.log_out
                    ),
                    icon = painterResource(
                        id = com.example.sharedui.R.drawable.back_fw
                    ),
                    onClick = {},
                    modifier = Modifier
                        .fillMaxWidth()
                )

            }//end item

            item(
                key = 5
            ) {

                HorizontalIconButtonSection(
                    theme = theme,
                    dimen = dimen,
                    text = stringResource(
                        com.example.sharedui.R.string.delete_account
                    ),
                    iconEnd = painterResource(
                        id = com.example.sharedui.R.drawable.back_fw
                    ),
                    iconStart = painterResource(
                        id = com.example.sharedui.R.drawable.delete_icon
                    ),
                    onClick = {},
                    modifier = Modifier
                        .fillMaxWidth()
                )

            }//end item

        }//end LazyColumn

    }//end ConstraintLayout

}//end MoreContent