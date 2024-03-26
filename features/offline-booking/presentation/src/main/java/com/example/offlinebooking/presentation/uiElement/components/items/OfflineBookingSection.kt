package com.example.offlinebooking.presentation.uiElement.components.items

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.sharedui.R
import com.example.sharedui.uiElement.components.composable.WorkerView
import com.example.sharedui.uiElement.components.items.IconTextSection
import com.example.sharedui.uiElement.components.modifier.appBorder
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.robotoMedium
import com.example.sharedui.uiElement.style.theme.CustomTheme

@Composable
internal fun OfflineBookingSection(
    dimen: CustomDimen,
    theme: CustomTheme,
    shape: Shape = RoundedCornerShape(
        size = dimen.dimen_1_25.dp
    ),
    borderWidth: Float = dimen.dimen_0_125,
    borderColor: Color = theme.grayLight,
    doctorNameSize: Float = dimen.dimen_2,
    doctorNameColor: Color = theme.black,
    jopSize: Float = dimen.dimen_1_5,
    jopColor: Color = theme.grayA7A6A5,
    addressTextColor: Color = theme.hintIconBottom,
    addressTextSize: Float = dimen.dimen_1_75,
    addressIcon: Painter = painterResource(
        id = R.drawable.location_icon
    ),
    addressIconSize: Float = dimen.dimen_2_25,
    addressIconTint: Color = theme.black,
    timeTextSize: Float = dimen.dimen_1_75,
    timeTextColor: Color = theme.hintIconBottom,
    timeIcon: Painter = painterResource(
        id = R.drawable.reminder_booking
    ),
    timeIconSize: Float = dimen.dimen_2_25,
    timeIconTint: Color = theme.black,
    chatSectionTitle: String,
    chatSectionTitleColor: Color = theme.green33A351,
    chatSectionTitleSize: Float = dimen.dimen_1_5,
    chatSectionBackground: Color,
    chatSectionShape: Shape = RoundedCornerShape(
        percent = 90
    ),
    chatSectionElevation: Float = dimen.dimen_0_5,
    chatSectionAmbientColor: Color = theme.black,
    chatSectionSpotColor: Color = theme.black,
    chatSectionHeight: Float = dimen.dimen_2_5,
    chatIcon: Painter = painterResource(
        id = R.drawable.chat_icon
    ),
    chatIconSize: Float = dimen.dimen_2,
    chatIconTint: Color = theme.green33A351,
    onClickOnChatButton: () -> Unit,
    modifier: Modifier = Modifier,
) {

    //create container here
    ConstraintLayout(
        modifier = modifier
            .appBorder(
                borderWidth = borderWidth,
                borderColor = borderColor,
                shape = shape
            )
            .padding(
                bottom = dimen.dimen_1_75.dp
            )
    ) {
        //create ids for components here
        val (doctorNameId, statusId, chatButtonId, timeId, locationId) = createRefs()

        //create guides here
        val guideFromStart60P = createGuidelineFromStart(0.6f)

        //create doctor name here
        WorkerView(
            dimen = dimen,
            theme = theme,
            workerName = "Dr: Ahmed Mohamed",
            workerNameSize = doctorNameSize,
            workerNameColor = doctorNameColor,
            work = "Dentist",
            workSize = jopSize,
            workColor = jopColor,
            doctorIsOnline = false,
            spacingBetweenContent = dimen.dimen_0_5,
            modifier = Modifier
                .constrainAs(doctorNameId) {
                    start.linkTo(
                        parent.start,
                        dimen.dimen_1_5.dp
                    )
                    end.linkTo(
                        parent.end,
                        dimen.dimen_1_5.dp
                    )
                    top.linkTo(
                        parent.top,
                        dimen.dimen_1_75.dp
                    )
                    width = Dimension.fillToConstraints
                }
        )

        //create location box here
        IconTextSection(
            theme = theme,
            dimen = dimen,
            text = "Cairo",
            fontFamily = robotoMedium,
            fontSize = addressTextSize,
            fontColor = addressTextColor,
            icon = addressIcon,
            iconSize = addressIconSize,
            iconTint = addressIconTint,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            spaceBetweenComponents = dimen.dimen_1_25,
            modifier = Modifier
                .constrainAs(locationId) {
                    start.linkTo(doctorNameId.start)
                    end.linkTo(doctorNameId.end)
                    top.linkTo(
                        doctorNameId.bottom,
                        dimen.dimen_2.dp
                    )
                    width = Dimension.fillToConstraints
                }
        )

        //create time box here
        IconTextSection(
            theme = theme,
            dimen = dimen,
            text = "20 Oct 2023 | 12:30 AM",
            fontFamily = robotoMedium,
            fontSize = timeTextSize,
            fontColor = timeTextColor,
            icon = timeIcon,
            iconSize = timeIconSize,
            iconTint = timeIconTint,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            spaceBetweenComponents = dimen.dimen_1_25,
            modifier = Modifier
                .constrainAs(timeId) {
                    start.linkTo(
                        locationId.start
                    )
                    end.linkTo(
                        locationId.end
                    )
                    top.linkTo(
                        locationId.bottom,
                        dimen.dimen_2.dp
                    )
                    width = Dimension.fillToConstraints
                }
        )

        //create chat section here
        BookingChatSection(
            theme = theme,
            dimen = dimen,
            title = chatSectionTitle,
            titleColor = chatSectionTitleColor,
            background = chatSectionBackground,
            titleSize = chatSectionTitleSize,
            shape = chatSectionShape,
            elevation = chatSectionElevation,
            ambientColor = chatSectionAmbientColor,
            spotColor = chatSectionSpotColor,
            height = chatSectionHeight,
            chatIcon = chatIcon,
            chatIconSize = chatIconSize,
            chatIconTint = chatIconTint,
            onClickOnChatButton = onClickOnChatButton,
            modifier = Modifier
                .constrainAs(statusId) {
                    start.linkTo(timeId.start)
                    end.linkTo(guideFromStart60P)
                    top.linkTo(
                        timeId.bottom,
                        dimen.dimen_1_75.dp
                    )
                    width = Dimension.fillToConstraints
                }
        )

    }//end ConstraintLayout

}//end OnlineBookingSection