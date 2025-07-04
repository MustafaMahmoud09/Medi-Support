package com.example.heartprediction.presentation.uiElement.screens.prediction

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.heartprediction.presentation.uiElement.components.items.ResultPredictionSection
import com.example.heartprediction.presentation.uiState.state.ResultHeartDiseaseUiState
import com.example.heartprediction.presentation.uiState.viewModel.ResultHeartDiseaseViewModel
import com.example.sharedui.R
import com.example.sharedui.uiElement.components.items.HeaderSection
import com.example.sharedui.uiElement.screen.BaseScreen
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.dimens.MediSupportAppDimen
import com.example.sharedui.uiElement.style.robotoMedium
import com.example.sharedui.uiElement.style.robotoRegular
import com.example.sharedui.uiElement.style.theme.CustomTheme
import com.example.sharedui.uiElement.style.theme.MediSupportAppTheme

@Composable
internal fun PredictionHeartPredictionScreen(
    viewModel: ResultHeartDiseaseViewModel = hiltViewModel(),
    popPredictionHeartPredictionDestination: () -> Unit
) {
    val state = viewModel.state.collectAsState()

    PredictionHeartPredictionContent(
        onClickBack = popPredictionHeartPredictionDestination,
        uiState = state.value
    )
}//end PredictionHeartPredictionScreen

@Composable
private fun PredictionHeartPredictionContent(
    dimen: CustomDimen = MediSupportAppDimen(),
    theme: CustomTheme = MediSupportAppTheme(),
    onClickBack: () -> Unit,
    uiState: ResultHeartDiseaseUiState
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
            val (headerId, messageContainer) = createRefs()
            val guideFromStart11P = createGuidelineFromStart(.11f)
            val guideFromEnd11P = createGuidelineFromEnd(.11f)

            //create header here
            HeaderSection(
                dimen = dimen,
                theme = theme,
                onClickOnBackButton = onClickBack,
                title = stringResource(
                    id = R.string.heart_disease_prediction
                ),
                modifier = Modifier
                    .constrainAs(headerId) {
                        start.linkTo(
                            parent.start,
                            dimen.dimen_2.dp
                        )
                        end.linkTo(parent.end)
                        top.linkTo(
                            parent.top,
                            dimen.dimen_2_5.dp
                        )

                        width = Dimension.fillToConstraints
                    }
            )

            //create message container here
            LazyColumn(
                modifier = Modifier
                    .constrainAs(messageContainer) {
                        start.linkTo(guideFromStart11P)
                        end.linkTo(guideFromEnd11P)
                        top.linkTo(
                            headerId.bottom,
                            dimen.dimen_2.dp
                        )
                        bottom.linkTo(parent.bottom)
                        width = Dimension.fillToConstraints
                        height = Dimension.fillToConstraints
                    },//end constrainAs
                contentPadding = PaddingValues(
                    top = dimen.dimen_7.dp,
                    bottom = dimen.dimen_2.dp
                ),
                verticalArrangement = Arrangement.spacedBy(
                    space = dimen.dimen_1_5.dp
                )
            ) {

                //create message item here
                item(
                    key = 1
                ) {

                    //create message here
                    ResultPredictionSection(
                        dimen = dimen,
                        theme = theme,
                        parentMessage = if (uiState.classResult == 0) {
                            stringResource(
                                R.string.congratulations_message_heart_prediction
                            )
                        } else {
                            stringResource(R.string.sorry_you_may_have_heart_disease)
                        },
                        subMessages = if (uiState.classResult == 0) {
                            arrayOf(stringResource(R.string.congratulations))
                        } else {
                            arrayOf(stringResource(R.string.sorry))
                        },
                        subMessageFamily = arrayOf(robotoMedium),
                        parentMessageFamily = robotoRegular,
                        painter = if (uiState.classResult == 0) {
                            painterResource(
                                id = R.drawable.smale
                            )
                        } else {
                            painterResource(
                                id = R.drawable.sad
                            )
                        },
                        iconTint = if (uiState.classResult == 0) {
                            theme.greenMed4CAF50
                        } else {
                            theme.redDark
                        },
                        parentNote = stringResource(
                            R.string.note_on_prediction_heart_dis
                        ),
                        subNotes = arrayOf(
                            stringResource(
                                R.string.important_note
                            )
                        ),
                        subNoteColors = arrayOf(theme.redDark),
                        modifier = Modifier
                            .fillMaxWidth()
                    )

                }//end item

            }//end LazyColumn

        }//end ConstraintLayout

    }//end BaseScreen

}//end PredictionHeartPredictionContent