package com.example.heartprediction.presentation.uiElement.screens.start

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.heartprediction.presentation.uiElement.components.items.StartRecordHeartPredictionSection
import com.example.sharedui.R
import com.example.sharedui.uiElement.components.composable.IconButtonView
import com.example.sharedui.uiElement.components.composable.TextBoldView
import com.example.sharedui.uiElement.components.items.HeaderSection
import com.example.sharedui.uiElement.screen.BaseScreen
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.dimens.MediSupportAppDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme
import com.example.sharedui.uiElement.style.theme.MediSupportAppTheme

//function for collect state and execute actions in view model
@Composable
internal fun StartHeartPredictionScreen(
    popHeartPredictionNavGraph: () -> Unit,
    navigateToRecordHeartPredictionDestination: () -> Unit
) {

    //create content here
    StartHeartPredictionContent(
        onClickBack = popHeartPredictionNavGraph,
        onClickOnRecordNow = navigateToRecordHeartPredictionDestination
    )
}//end StartHeartPredictionScreen

//function for observe state and draw screen components
@Composable
private fun StartHeartPredictionContent(
    dimen: CustomDimen = MediSupportAppDimen(),
    theme: CustomTheme = MediSupportAppTheme(),
    onClickBack: () -> Unit,
    onClickOnRecordNow: () -> Unit
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
            val (headerId, recordId) = createRefs()
            val guideFromStart20P = createGuidelineFromStart(0.20f)
            val guideFromEnd20P = createGuidelineFromEnd(0.20f)

            //create header here
            HeaderSection(
                dimen = dimen,
                theme = theme,
                onClickOnBackButton = onClickBack,
                title = stringResource(
                    id = R.string.heart_disease_prediction
                ),
                modifier = Modifier
                    .constrainAs(headerId){
                        start.linkTo(
                            parent.start,
                            dimen.dimen_2.dp
                        )
                        end.linkTo(parent.end)
                        top.linkTo(
                            parent.top,
                            dimen.dimen_3_25.dp
                        )

                        width = Dimension.fillToConstraints
                    }
            )


            com.example.heartprediction.presentation.uiElement.components.items.StartRecordHeartPredictionSection(
                dimen = dimen,
                theme = theme,
                parentText = stringResource(
                    id = R.string.predicting_heart_disease_using_artificial_intelligence
                ),
                subTexts = arrayOf(
                    stringResource(
                        id = R.string.artificial
                    ),
                    stringResource(
                        id = R.string.intell
                    )
                ),
                subTextColors = arrayOf(theme.redLightFE8499, theme.binkLightFB5DAA),
                painter = painterResource(
                    id = R.drawable.heart_predict
                ),
                buttonText = stringResource(
                    id = R.string.read_now
                ),
                onClick = onClickOnRecordNow,
                modifier = Modifier
                    .constrainAs(recordId) {
                        start.linkTo(guideFromStart20P)
                        end.linkTo(guideFromEnd20P)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        width = Dimension.fillToConstraints
                    }
            )

        }//end ConstraintLayout

    }//end BaseScreen

}//end StartHeartPredictionContent