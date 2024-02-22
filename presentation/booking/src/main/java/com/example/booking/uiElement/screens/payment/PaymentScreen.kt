package com.example.booking.uiElement.screens.payment

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.sharedui.R
import com.example.sharedui.uiElement.components.composable.BasicButtonView
import com.example.sharedui.uiElement.components.composable.LinkView
import com.example.sharedui.uiElement.components.items.BasicFieldSection
import com.example.sharedui.uiElement.components.items.HeaderSection
import com.example.sharedui.uiElement.components.modifier.appDefaultContainer
import com.example.sharedui.uiElement.screen.BaseScreen
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.dimens.MediSupportAppDimen
import com.example.sharedui.uiElement.style.robotoMedium
import com.example.sharedui.uiElement.style.theme.CustomTheme
import com.example.sharedui.uiElement.style.theme.MediSupportAppTheme

@Composable
internal fun PaymentScreen(
    popOnlineRoomGraph: () -> Unit,
    navigateToOnlineRoomDestination: () -> Unit
) {

    PaymentContent(
        onClickOnBackButton = popOnlineRoomGraph,
        onClickOnPaymentButton = navigateToOnlineRoomDestination
    )
}//end PaymentScreen

@Composable
private fun PaymentContent(
    dimen: CustomDimen = MediSupportAppDimen(),
    theme: CustomTheme = MediSupportAppTheme(),
    onClickOnBackButton: () -> Unit,
    onClickOnPaymentButton: () -> Unit
) {

    //create base screen for define status and navigation bar color
    BaseScreen(
        navigationColor = theme.background,
        statusColor = theme.background
    ) {

        //create container here
        ConstraintLayout(
            modifier = Modifier
                .appDefaultContainer(
                    color = theme.background
                )
        ) {
            //create ids for screen components here
            val (headerId, containerId) = createRefs()

            //create header here
            HeaderSection(
                dimen = dimen,
                theme = theme,
                onClickOnBackButton = onClickOnBackButton,
                title = stringResource(
                    id = R.string.book_appointment
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
                            (dimen.dimen_3_5 + dimen.dimen_0_125).dp
                        )

                        width = Dimension.fillToConstraints
                    }
            )

            //create column contain on all items
            LazyColumn(
                modifier = Modifier
                    .constrainAs(containerId) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(
                            headerId.bottom,
                            dimen.dimen_2.dp
                        )
                        bottom.linkTo(parent.bottom)
                        height = Dimension.fillToConstraints
                        width = Dimension.fillToConstraints
                    },
                contentPadding = PaddingValues(
                    top = dimen.dimen_0_25.dp,
                    bottom = dimen.dimen_1_5.dp
                )
            ) {

                //create item contain on all screen components without header
                item(
                    key = 1
                ) {

                    //create container here
                    ConstraintLayout(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        //create ids for components here
                        val (
                            amountFieldId, walletNumberFieldId, personalIdentificationFieldId,
                            passwordFieldId, resendOTPAgainLinkId, paymentButtonId
                        ) = createRefs()

                        //create amount field here
                        BasicFieldSection(
                            theme = theme,
                            dimen = dimen,
                            title = stringResource(
                                id = R.string.amount
                            ),
                            hint = stringResource(
                                id = R.string._250_l_e
                            ),
                            value = "",
                            onChange = {},
                            modifier = Modifier
                                .constrainAs(amountFieldId) {
                                    start.linkTo(
                                        parent.start,
                                        dimen.dimen_2.dp
                                    )
                                    end.linkTo(
                                        parent.end,
                                        dimen.dimen_2.dp
                                    )
                                    top.linkTo(parent.top)
                                    width = Dimension.fillToConstraints
                                }
                        )

                        //create wallet number field here
                        BasicFieldSection(
                            theme = theme,
                            dimen = dimen,
                            title = stringResource(
                                id = R.string.wallet_number
                            ),
                            hint = stringResource(
                                id = R.string.big_stars
                            ),
                            value = "",
                            onChange = {},
                            modifier = Modifier
                                .constrainAs(walletNumberFieldId) {
                                    start.linkTo(amountFieldId.start)
                                    end.linkTo(amountFieldId.end)
                                    top.linkTo(
                                        amountFieldId.bottom,
                                        dimen.dimen_2.dp
                                    )
                                    width = Dimension.fillToConstraints
                                }
                        )

                        //create personal identification number field here
                        BasicFieldSection(
                            theme = theme,
                            dimen = dimen,
                            title = stringResource(
                                id = R.string.personal_identification_number_pin
                            ),
                            hint = stringResource(
                                id = R.string.small_stars
                            ),
                            value = "",
                            onChange = {},
                            modifier = Modifier
                                .constrainAs(personalIdentificationFieldId) {
                                    start.linkTo(walletNumberFieldId.start)
                                    end.linkTo(walletNumberFieldId.end)
                                    top.linkTo(
                                        walletNumberFieldId.bottom,
                                        dimen.dimen_2.dp
                                    )
                                    width = Dimension.fillToConstraints
                                }
                        )

                        //create one time password field here
                        BasicFieldSection(
                            theme = theme,
                            dimen = dimen,
                            title = stringResource(
                                id = R.string.one_time_password_otp
                            ),
                            hint = stringResource(
                                id = R.string.small_stars
                            ),
                            value = "",
                            onChange = {},
                            modifier = Modifier
                                .constrainAs(passwordFieldId) {
                                    start.linkTo(personalIdentificationFieldId.start)
                                    end.linkTo(personalIdentificationFieldId.end)
                                    top.linkTo(
                                        personalIdentificationFieldId.bottom,
                                        dimen.dimen_2.dp
                                    )
                                    width = Dimension.fillToConstraints
                                }
                        )

                        //create Resend OTP Again link here
                        LinkView(
                            text = stringResource(
                                id = R.string.resend_otp_again
                            ),
                            color = theme.redDark,
                            size = dimen.dimen_1_5,
                            fontFamily = robotoMedium,
                            onClick = { /*TODO*/ },
                            modifier = Modifier
                                .constrainAs(resendOTPAgainLinkId) {
                                    start.linkTo(passwordFieldId.start)
                                    end.linkTo(passwordFieldId.end)
                                    top.linkTo(
                                        passwordFieldId.bottom,
                                        dimen.dimen_2.dp
                                    )
                                    width = Dimension.fillToConstraints
                                }
                        )

                        //create payment button here
                        BasicButtonView(
                            dimen = dimen,
                            theme = theme,
                            text = stringResource(
                                id = R.string.pay_with_wallet
                            ),
                            onClick = onClickOnPaymentButton,
                            modifier = Modifier
                                .constrainAs(paymentButtonId){
                                    start.linkTo(resendOTPAgainLinkId.start)
                                    end.linkTo(resendOTPAgainLinkId.end)
                                    top.linkTo(
                                        resendOTPAgainLinkId.bottom,
                                        dimen.dimen_2_25.dp
                                    )
                                    width = Dimension.fillToConstraints
                                }
                        )

                    }//end ConstraintLayout

                }//end item

            }//end LazyColumn

        }//end ConstraintLayout

    }//end BaseScreen

}//end PaymentContent