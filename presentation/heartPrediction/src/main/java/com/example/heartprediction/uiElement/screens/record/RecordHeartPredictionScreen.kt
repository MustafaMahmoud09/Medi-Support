@file:OptIn(ExperimentalLayoutApi::class)

package com.example.heartprediction.uiElement.screens.record

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.isImeVisible
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.heartprediction.uiElement.components.items.InputFieldSection
import com.example.sharedui.R
import com.example.sharedui.uiElement.components.composable.BasicButtonView
import com.example.sharedui.uiElement.components.composable.IconButtonView
import com.example.sharedui.uiElement.components.composable.TextBoldView
import com.example.sharedui.uiElement.screen.BaseScreen
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.dimens.MediSupportAppDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme
import com.example.sharedui.uiElement.style.theme.MediSupportAppTheme

//function for collect state and execute actions in view model
@Composable
internal fun RecordHeartPredictionScreen(
    popRecordHeartPredictionDestination: () -> Unit,
    navigateToPredictionHeartPredictionDestination: () -> Unit
) {
    //create focus request array have focus request to fields here for control on field any time by code
    val focusRequesters = Array(9) { FocusRequester() }

    //variable have number field is focus now
    val numberFieldIsFocus = rememberSaveable {
        mutableStateOf(0)
    }

    //create lazy column state here to scroll to any field in any time by code
    val containerFieldsState = rememberLazyListState()

    //create components for record heart screen here
    RecordHeartPredictionContent(
        containerFieldsState = containerFieldsState,
        focusRequesters = focusRequesters,
        numberFieldIsFocus = numberFieldIsFocus,
        keyboardIsVisible = WindowInsets.isImeVisible,
        onClickBack = {
            //if first field is focus now pop screen from back stack
            if (numberFieldIsFocus.value == 0) {

                popRecordHeartPredictionDestination()
            }//end if

            //if other field is focus now transport to prev field
            else {

                numberFieldIsFocus.value -= 1
            }//end else
        },
        onClickOnOperationButton = {
            //if field is focus before last field transport to next field
            if (numberFieldIsFocus.value <= focusRequesters.size - 2) {

                numberFieldIsFocus.value += 1
            }//end if

            //if field is focus equal last field transport to next screen
            else {

                navigateToPredictionHeartPredictionDestination()
            }//end else
        }//end onClickOnOperationButton
    )

}//end RecordHeartPredictionScreen


//function for observe state and draw screen components
@Composable
private fun RecordHeartPredictionContent(
    dimen: CustomDimen = MediSupportAppDimen(),
    theme: CustomTheme = MediSupportAppTheme(),
    onClickBack: () -> Unit,
    focusRequesters: Array<FocusRequester>,
    numberFieldIsFocus: MutableState<Int>,
    onClickOnOperationButton: () -> Unit,
    containerFieldsState: LazyListState,
    keyboardIsVisible: Boolean,
    heightScreen: Int = LocalConfiguration.current.screenHeightDp
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
            val (backButton, title, operationButtonId, fieldsId) = createRefs()

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

            //create title screen here
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

            //if keyboard is not visible
            if (!keyboardIsVisible) {
                //create operation button here
                BasicButtonView(
                    dimen = dimen,
                    theme = theme,
                    text =
                    //if field last is focus now set result text to button
                    if (numberFieldIsFocus.value == focusRequesters.size - 1)
                        stringResource(
                            id = R.string.result
                        )
                    //else set next text to button
                    else
                        stringResource(
                            id = R.string.next
                        ),
                    onClick = onClickOnOperationButton,
                    modifier = Modifier
                        .constrainAs(operationButtonId) {
                            start.linkTo(
                                parent.start,
                                dimen.dimen_2.dp
                            )
                            end.linkTo(
                                parent.end,
                                dimen.dimen_2.dp
                            )

                            //if keyboard is not visible make button constraint from bottom
                            bottom.linkTo(
                                parent.bottom,
                                dimen.dimen_1_5.dp
                            )

                            width = Dimension.fillToConstraints
                        },
                )

            }//end if


            //create fields container here
            LazyColumn(
                state = containerFieldsState,
                modifier = Modifier
                    .constrainAs(fieldsId) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(
                            title.bottom,
                            dimen.dimen_2.dp
                        )
                        //if keyboard is visible set this item above parent
                        if (keyboardIsVisible) {
                            bottom.linkTo(parent.bottom)
                        }
                        //if keyboard is not visible set this item above button
                        else {
                            bottom.linkTo(operationButtonId.top)
                        }
                        height = Dimension.fillToConstraints
                        width = Dimension.fillToConstraints
                    },
                contentPadding = PaddingValues(
                    start = dimen.dimen_2.dp,
                    end = dimen.dimen_2.dp,
                    top = dimen.dimen_2.dp,
                    bottom = dimen.dimen_1.dp
                )
            ) {

                item(
                    key = 1
                ) {

                    Column(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(
                            space = dimen.dimen_2.dp
                        )
                    ) {

                        InputFieldSection(
                            dimen = dimen,
                            theme = theme,
                            title = "Cholesterol Levels",
                            hint = "Your Cholesterol Level",
                            value = "",
                            onChange = {},
                            focusRequester = focusRequesters[0],
                            keyboardType = KeyboardType.Number,
                            numberField = 0,
                            numberFieldIsFocusNow = numberFieldIsFocus.value,
                            modifier = Modifier
                                .fillMaxWidth()
                        )

                        InputFieldSection(
                            dimen = dimen,
                            theme = theme,
                            title = "Resting Electrocardiographic Results",
                            hint = "Your ECG",
                            value = "",
                            onChange = {},
                            focusRequester = focusRequesters[1],
                            keyboardType = KeyboardType.Number,
                            numberField = 1,
                            numberFieldIsFocusNow = numberFieldIsFocus.value,
                            modifier = Modifier
                                .fillMaxWidth()
                        )

                        InputFieldSection(
                            dimen = dimen,
                            theme = theme,
                            title = "Cholesterol Levels",
                            hint = "Your Cholesterol Level",
                            value = "",
                            onChange = {},
                            focusRequester = focusRequesters[2],
                            keyboardType = KeyboardType.Number,
                            numberField = 2,
                            numberFieldIsFocusNow = numberFieldIsFocus.value,
                            modifier = Modifier
                                .fillMaxWidth()
                        )

                        InputFieldSection(
                            dimen = dimen,
                            theme = theme,
                            title = "Resting Electrocardiographic Results",
                            hint = "Your ECG",
                            value = "",
                            onChange = {},
                            focusRequester = focusRequesters[3],
                            keyboardType = KeyboardType.Number,
                            numberField = 3,
                            modifier = Modifier
                                .fillMaxWidth(),
                            numberFieldIsFocusNow = numberFieldIsFocus.value,
                        )

                        InputFieldSection(
                            dimen = dimen,
                            theme = theme,
                            title = "Cholesterol Levels",
                            hint = "Your Cholesterol Level",
                            value = "",
                            onChange = {},
                            focusRequester = focusRequesters[4],
                            keyboardType = KeyboardType.Number,
                            numberField = 4,
                            modifier = Modifier
                                .fillMaxWidth(),
                            numberFieldIsFocusNow = numberFieldIsFocus.value,
                        )

                        InputFieldSection(
                            dimen = dimen,
                            theme = theme,
                            title = "Resting Electrocardiographic Results",
                            hint = "Your ECG",
                            value = "",
                            onChange = {},
                            focusRequester = focusRequesters[5],
                            keyboardType = KeyboardType.Number,
                            numberField = 5,
                            modifier = Modifier
                                .fillMaxWidth(),
                            numberFieldIsFocusNow = numberFieldIsFocus.value,
                        )

                        InputFieldSection(
                            dimen = dimen,
                            theme = theme,
                            title = "Cholesterol Levels",
                            hint = "Your Cholesterol Level",
                            value = "",
                            onChange = {},
                            focusRequester = focusRequesters[6],
                            keyboardType = KeyboardType.Number,
                            numberField = 6,
                            modifier = Modifier
                                .fillMaxWidth(),
                            numberFieldIsFocusNow = numberFieldIsFocus.value,
                        )

                        InputFieldSection(
                            dimen = dimen,
                            theme = theme,
                            title = "Resting Electrocardiographic Results",
                            hint = "Your ECG",
                            value = "",
                            onChange = {},
                            focusRequester = focusRequesters[7],
                            keyboardType = KeyboardType.Number,
                            numberField = 7,
                            modifier = Modifier
                                .fillMaxWidth(),
                            numberFieldIsFocusNow = numberFieldIsFocus.value,
                        )

                        InputFieldSection(
                            dimen = dimen,
                            theme = theme,
                            title = "Cholesterol Levels",
                            hint = "Your Cholesterol Level",
                            value = "",
                            onChange = {},
                            focusRequester = focusRequesters[8],
                            keyboardType = KeyboardType.Number,
                            numberField = 8,
                            modifier = Modifier
                                .fillMaxWidth(),
                            numberFieldIsFocusNow = numberFieldIsFocus.value,
                        )

                    }//end Column

                }//end item


            }//end LazyColumn

            //if keyboard is visible
            if (keyboardIsVisible) {
                //create operation button here

                Box(
                    modifier = Modifier
                        .constrainAs(operationButtonId) {
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)

                            //if keyboard is visible make button constraint from top
                            top.linkTo(
                                parent.top,
                                (heightScreen - (dimen.dimen_6_5 + dimen.dimen_1_5)).dp
                            )
                            bottom.linkTo(parent.bottom)
                            height = Dimension.fillToConstraints
                            width = Dimension.fillToConstraints
                        }
                        .background(
                            color = theme.background
                        )
                        .padding(
                            horizontal = dimen.dimen_2.dp
                        ),
                ) {

                    BasicButtonView(
                        dimen = dimen,
                        theme = theme,
                        text =
                        //if field last is focus now set result text to button
                        if (numberFieldIsFocus.value == focusRequesters.size - 1)
                            stringResource(
                                id = R.string.result
                            )
                        //else set next text to button
                        else
                            stringResource(
                                id = R.string.next
                            ),
                        onClick = onClickOnOperationButton,
                        modifier = Modifier
                            .fillMaxWidth()
                    )

                }//end Box

            }//end if

        }//end ConstraintLayout

    }//end BaseScreen

    //create focus effect here
    LaunchedEffect(
        key1 = numberFieldIsFocus.value
    ) {


        //set focus on field is focus now
        focusRequesters[numberFieldIsFocus.value].requestFocus()


    }//end LaunchedEffect

}//end RecordHeartPredictionContent

