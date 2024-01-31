package com.example.reminder.uiElement.screens.add

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.sharedui.R
import com.example.sharedui.uiElement.components.composable.IconButtonView
import com.example.sharedui.uiElement.components.composable.TextBoldView
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.dimens.MediSupportAppDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme
import com.example.sharedui.uiElement.style.theme.MediSupportAppTheme

@Composable
internal fun AddReminderScreen(
    popAddReminderDestination: () -> Unit,
    navigateToReminderRecordsDestination: () -> Unit
) {

    AddReminderContent(
        onClickBack = popAddReminderDestination,
        onClickOnReminderButton = navigateToReminderRecordsDestination
    )
}//end AddReminderScreen

@Composable
private fun AddReminderContent(
    dimen: CustomDimen = MediSupportAppDimen(),
    theme: CustomTheme = MediSupportAppTheme(),
    onClickBack: () -> Unit,
    onClickOnReminderButton: () -> Unit
) {

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = theme.background
            )
    ) {
        val (backButton, title, reminderButtonId) = createRefs()

        //create back button here
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

        //create medicine reminder title here
        TextBoldView(
            theme = theme,
            dimen = dimen,
            text = stringResource(
                id = R.string.medicine_reminder
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

        //create reminder button here
        IconButtonView(
            dimen = dimen,
            theme = theme,
            onClick = onClickOnReminderButton,
            icon = painterResource(
                id = R.drawable.reminder_icon
            ),
            tint = theme.redDark,
            modifier = Modifier
                .constrainAs(reminderButtonId) {
                    end.linkTo(
                        parent.end,
                        dimen.dimen_2.dp
                    )
                    top.linkTo(
                        parent.top,
                        dimen.dimen_4.dp
                    )
                }//end constrainAs
        )

    }//end ConstraintLayout

}//end AddReminderContent