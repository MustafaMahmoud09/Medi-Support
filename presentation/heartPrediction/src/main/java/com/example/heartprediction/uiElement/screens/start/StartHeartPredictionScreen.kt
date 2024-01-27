package com.example.heartprediction.uiElement.screens.start

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.sharedui.R
import com.example.sharedui.uiElement.components.composable.IconButtonView
import com.example.sharedui.uiElement.components.composable.TextBoldView
import com.example.sharedui.uiElement.screen.BaseScreen
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.dimens.MediSupportAppDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme
import com.example.sharedui.uiElement.style.theme.MediSupportAppTheme

//function for collect state and execute actions in view model
@Composable
internal fun StartHeartPredictionScreen(
    popHeartPredictionNavGraph: () -> Unit
) {

    //create content here
    StartHeartPredictionContent(
        onClickBack = popHeartPredictionNavGraph
    )
}//end StartHeartPredictionScreen

//function for observe state and draw screen components
@Composable
private fun StartHeartPredictionContent(
    dimen: CustomDimen = MediSupportAppDimen(),
    theme: CustomTheme = MediSupportAppTheme(),
    onClickBack: () -> Unit
) {

    //define navigation color and status color for system ui
    BaseScreen(
        navigationColor = theme.background,
        statusColor = theme.background
    ) {
        //create container here
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .safeDrawingPadding()
                .background(
                    color = theme.background
                )
        ) {
            //create ids for components here
            val (backButton, title) = createRefs()

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
                    id = R.string.heart_disease_prediction
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

        }//end ConstraintLayout

    }//end BaseScreen

}//end StartHeartPredictionContent