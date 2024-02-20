package com.example.booking.uiElement.components.items

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.sharedui.uiElement.components.composable.BasicButtonView
import com.example.sharedui.uiElement.components.composable.LoadImageView
import com.example.sharedui.uiElement.components.composable.TextSemiBoldView
import com.example.sharedui.uiElement.components.items.IconTextSection
import com.example.sharedui.uiElement.components.items.RatingBarSection
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.robotoMedium
import com.example.sharedui.uiElement.style.theme.CustomTheme

@Composable
internal fun DoctorPrimaryInformationSection(
    dimen: CustomDimen,
    theme: CustomTheme,
    borderSize: Float = dimen.dimen_0_125,
    borderColor: Color = theme.grayD7D7D7,
    name: String,
    location: String,
    time: String,
    nameColor: Color = theme.black,
    secondaryTextColor: Color = theme.hintIconBottom,
    iconSize: Float = dimen.dimen_1_75,
    iconTint: Color = theme.visibleGray,
    image: Painter,
    shape: Shape = RoundedCornerShape(
        size = dimen.dimen_1_25.dp
    ),
    buttonWidth: Float = dimen.dimen_15,
    buttonHeight: Float = dimen.dimen_3,
    textButton: String,
    doctorIsOnline: Boolean = false,
    onlineIconSize: Float = dimen.dimen_1,
    onlineIconShape: Shape = CircleShape,
    onlineIconColor: Color = theme.green75F94C,
    onClickOnButton: (Boolean, Int) -> Unit = { x, z -> },
    modifier: Modifier = Modifier,
) {

    //create container here
    ConstraintLayout(
        modifier = modifier
            .clip(
                shape = shape
            )
            .border(
                width = borderSize.dp,
                color = borderColor,
                shape = shape
            )
    ) {
        //create ids to components
        val (nameId, locationId, timeId, ratingId,
            buttonId, paddingBottomId, imageId, onlineIconId) = createRefs()
        //create guides
        val guideFromStart42P = createGuidelineFromStart(0.42f)

        //create name text here
        TextSemiBoldView(
            theme = theme,
            dimen = dimen,
            text = name,
            size = dimen.dimen_1_75,
            fontColor = nameColor,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            modifier = Modifier
                .constrainAs(nameId) {
                    start.linkTo(
                        guideFromStart42P,
                        dimen.dimen_1_5.dp
                    )
                    top.linkTo(
                        parent.top,
                        dimen.dimen_2.dp
                    )
                    end.linkTo(
                        parent.end,
                        dimen.dimen_3.dp
                    )
                    width = Dimension.preferredWrapContent
                }
        )

        //if doctor is online create online icon
        if (doctorIsOnline) {

            //create online icon here
            Spacer(
                modifier = Modifier
                    .constrainAs(onlineIconId) {
                        top.linkTo(nameId.top)
                        bottom.linkTo(nameId.bottom)
                        start.linkTo(
                            nameId.end,
                            dimen.dimen_0_5.dp
                        )
                    }
                    .size(
                        size = onlineIconSize.dp
                    )
                    .clip(
                        shape = onlineIconShape
                    )
                    .background(
                        color = onlineIconColor
                    )
            )

        }//end if

        //create location box here
        IconTextSection(
            theme = theme,
            dimen = dimen,
            text = location,
            fontFamily = robotoMedium,
            fontSize = dimen.dimen_1_75,
            fontColor = secondaryTextColor,
            icon = painterResource(
                id = com.example.sharedui.R.drawable.location_icon
            ),
            iconSize = iconSize,
            iconTint = iconTint,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .constrainAs(locationId) {
                    start.linkTo(
                        nameId.start
                    )
                    end.linkTo(
                        parent.end,
                        dimen.dimen_1_5.dp
                    )
                    top.linkTo(
                        nameId.bottom,
                        dimen.dimen_2.dp
                    )
                    width = Dimension.fillToConstraints
                }
        )

        //create time box here
        IconTextSection(
            theme = theme,
            dimen = dimen,
            text = time,
            fontFamily = robotoMedium,
            fontSize = dimen.dimen_1_75,
            fontColor = secondaryTextColor,
            icon = painterResource(
                id = com.example.sharedui.R.drawable.reminder_center_icon
            ),
            iconSize = iconSize,
            iconTint = iconTint,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
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

        //create rating bar here
        RatingBarSection(
            dimen = dimen,
            theme = theme,
            rating = 3f,
            modifier = Modifier
                .constrainAs(ratingId) {
                    start.linkTo(
                        timeId.start
                    )
                    end.linkTo(
                        timeId.end
                    )
                    top.linkTo(
                        timeId.bottom,
                        dimen.dimen_2.dp
                    )
                    width = Dimension.fillToConstraints
                }
        )

        //create button here
        BasicButtonView(
            dimen = dimen,
            theme = theme,
            text = textButton,
            fontSize = dimen.dimen_1_25,
            onClick = { onClickOnButton(doctorIsOnline, 1) },
            modifier = Modifier
                .constrainAs(buttonId) {
                    start.linkTo(ratingId.start)
                    top.linkTo(
                        ratingId.bottom,
                        dimen.dimen_1_5.dp
                    )
                }
                .size(
                    width = buttonWidth.dp,
                    height = buttonHeight.dp
                )
                .aspectRatio(5f)
        )

        //create padding from bottom
        Spacer(
            modifier = Modifier
                .constrainAs(paddingBottomId) {
                    start.linkTo(guideFromStart42P)
                    end.linkTo(parent.end)
                    top.linkTo(buttonId.bottom)
                }
                .height(
                    height = dimen.dimen_0_75.dp
                )
        )

        LoadImageView(
            painter = image,
            modifier = Modifier
                .constrainAs(imageId) {
                    start.linkTo(parent.start)
                    end.linkTo(guideFromStart42P)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    width = Dimension.fillToConstraints
                    height = Dimension.fillToConstraints
                }
        )

    }//end ConstraintLayout

}//end DoctorSearchSection