package com.example.sharedui.uiElement.components.items

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.sharedui.uiElement.components.composable.WorkerView
import com.example.sharedui.uiElement.components.composable.MultiStyleTextView
import com.example.sharedui.uiElement.components.items.IconTextSection
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.robotoMedium
import com.example.sharedui.uiElement.style.theme.CustomTheme
import com.example.sharedui.R
import com.example.sharedui.uiElement.components.composable.TextNormalView
import com.example.sharedui.uiElement.components.composable.TextSemiBoldView
import com.example.sharedui.uiElement.components.items.InteractionRatingSection
import com.example.sharedui.uiElement.components.items.RatingBarSection
import com.example.sharedui.uiElement.style.robotoRegular

@Composable
fun BookedDoctorInformationSection(
    dimen: CustomDimen,
    theme: CustomTheme,
    doctorName: String,
    doctorNameSize: Float = dimen.dimen_2,
    doctorNameColor: Color = theme.black,
    jop: String,
    jopSize: Float = dimen.dimen_1_5,
    jopColor: Color = theme.grayA7A6A5,
    price: String,
    priceColor: Color = theme.redDark,
    priceSize: Float = dimen.dimen_1_75,
    currency: String,
    currencyColor: Color = theme.brownLight,
    currencySize: Float = dimen.dimen_1_5,
    address: String,
    addressIcon: Painter = painterResource(
        id = R.drawable.location_icon
    ),
    phoneNumber: String,
    phoneNumberSize: Float = dimen.dimen_2,
    phoneNumberColor: Color = theme.black,
    phoneNumberIcon: Painter = painterResource(
        id = R.drawable.phone
    ),
    phoneNumberIconHint: Color = theme.green2BEF4A,
    phoneNumberIconSize: Float = dimen.dimen_2_25,
    addressIconSize: Float = dimen.dimen_2_25,
    addressIconHint: Color = theme.black,
    addressSize: Float = dimen.dimen_1_75,
    addressColor: Color = theme.hintIconBottom,
    interactionRatingTitle: String,
    interactionRatingTitleSize: Float = dimen.dimen_2,
    interactionRatingTitleColor: Color = theme.hintIconBottom,
    interactionRatingValue: Int,
    interactionRatingOnChanged: (Int) -> Unit,
    aboutTitle: String = stringResource(
        id = R.string.about
    ),
    aboutTitleSize: Float = dimen.dimen_2,
    aboutTitleColor: Color = theme.black,
    aboutContent: String,
    aboutContentSize: Float = dimen.dimen_2,
    aboutContentColor: Color = theme.grayDark,
    doctorIsOnline: Boolean,
    modifier: Modifier = Modifier
) {

    //create container here
    ConstraintLayout(
        modifier = modifier
    ) {
        //create ids for components here
        val (
            doctorNameId, priceId, addressId, doctorRateId,
            interactionRatingId, phoneNumberId, aboutTitleId, aboutContentId
        ) = createRefs()

        //create price booking here
        MultiStyleTextView(
            dimen = dimen,
            theme = theme,
            parentText = "$price $currency",
            subTexts = arrayOf(currency),
            subTextsStyle = arrayOf(
                SpanStyle(
                    fontSize = currencySize.sp,
                    color = currencyColor
                )
            ),
            parentTextStyle = SpanStyle(
                fontSize = priceSize.sp,
                color = priceColor
            ),
            fontFamily = robotoMedium,
            modifier = Modifier
                .constrainAs(priceId) {
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                }

        )

        //create doctor name here
        WorkerView(
            dimen = dimen,
            theme = theme,
            workerName = doctorName,
            workerNameSize = doctorNameSize,
            workerNameColor = doctorNameColor,
            work = jop,
            workSize = jopSize,
            workColor = jopColor,
            doctorIsOnline = doctorIsOnline,
            modifier = Modifier
                .constrainAs(doctorNameId) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    end.linkTo(
                        priceId.start,
                        dimen.dimen_1.dp
                    )
                    width = Dimension.fillToConstraints
                }
        )

        //create doctor address here
        IconTextSection(
            theme = theme,
            dimen = dimen,
            text = address,
            fontFamily = robotoMedium,
            fontSize = addressSize,
            fontColor = addressColor,
            icon = addressIcon,
            iconSize = addressIconSize,
            iconTint = addressIconHint,
            spaceBetweenComponents = dimen.dimen_1_25,
            modifier = Modifier
                .constrainAs(addressId) {
                    start.linkTo(parent.start)
                    top.linkTo(
                        doctorNameId.bottom,
                        dimen.dimen_1_25.dp
                    )
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                }
        )

        //create doctor rating here
        RatingBarSection(
            dimen = dimen,
            theme = theme,
            rating = 3f,
            modifier = Modifier
                .constrainAs(doctorRateId) {
                    start.linkTo(parent.start)
                    end.linkTo(addressId.end)
                    top.linkTo(
                        addressId.bottom,
                        dimen.dimen_1_25.dp
                    )
                    width = Dimension.fillToConstraints
                }
        )

        //create phone number section here
        IconTextSection(
            theme = theme,
            dimen = dimen,
            text = phoneNumber,
            fontFamily = robotoRegular,
            fontSize = phoneNumberSize,
            fontColor = phoneNumberColor,
            icon = phoneNumberIcon,
            iconSize = phoneNumberIconSize,
            iconTint = phoneNumberIconHint,
            spaceBetweenComponents = dimen.dimen_1_25,
            modifier = Modifier
                .constrainAs(phoneNumberId) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(
                        doctorRateId.bottom,
                        dimen.dimen_1_25.dp
                    )
                    width = Dimension.fillToConstraints
                }
        )

        //create interaction rating bar here
        InteractionRatingSection(
            dimen = dimen,
            theme = theme,
            title = interactionRatingTitle,
            titleColor = interactionRatingTitleColor,
            titleSize = interactionRatingTitleSize,
            value = interactionRatingValue,
            onChanged = interactionRatingOnChanged,
            modifier = Modifier
                .constrainAs(interactionRatingId) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(
                        phoneNumberId.bottom,
                        dimen.dimen_1_25.dp
                    )
                    width = Dimension.fillToConstraints
                }
        )

        //create about title here
        TextSemiBoldView(
            theme = theme,
            dimen = dimen,
            text = aboutTitle,
            size = aboutTitleSize,
            fontColor = aboutTitleColor,
            modifier = Modifier
                .constrainAs(aboutTitleId) {
                    start.linkTo(parent.start)
                    top.linkTo(
                        interactionRatingId.bottom,
                        dimen.dimen_1_5.dp
                    )
                }
        )

        //create about content here
        TextNormalView(
            theme = theme,
            dimen = dimen,
            text = aboutContent,
            size = aboutContentSize,
            textAlign = null,
            fontColor = aboutContentColor,
            modifier = Modifier
                .constrainAs(aboutContentId) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(
                        aboutTitleId.bottom,
                        dimen.dimen_1_75.dp
                    )
                    width = Dimension.fillToConstraints
                }
        )

    }//end ConstraintLayout

}//end DoctorBookingInfoSection