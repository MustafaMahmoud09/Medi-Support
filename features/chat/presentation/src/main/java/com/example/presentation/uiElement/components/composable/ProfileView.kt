package com.example.presentation.uiElement.components.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.chat.domain.model.DoctorModel
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme
import com.example.sharedui.uiElement.components.composable.OnlineView
import com.example.sharedui.uiElement.components.composable.ServerLoadImageView

@Composable
internal fun ProfileView(
    dimen: CustomDimen,
    theme: CustomTheme,
    imageSize: Float = dimen.dimen_5_5,
    imageBackground: Color = theme.redDark,
    imageShape: Shape = CircleShape,
    onlineIconShape: Shape = CircleShape,
    onlineIconSize: Float = dimen.dimen_1,
    onlineIconColor: Color = theme.green2BEF83,
    doctorModel: DoctorModel? = null,
    modifier: Modifier = Modifier
) {

    //create container here
    ConstraintLayout(
        modifier = modifier
    ) {
        //create ids for screen components here
        val (profileImageId, onlineIconId) = createRefs()

        if (doctorModel != null) {

            //create profile image here
            ServerLoadImageView(
                imageUrl = doctorModel?.imageUrl ?: "",
                progressSize = dimen.dimen_3,
                dimen = dimen,
                theme = theme,
                modifier = Modifier
                    .constrainAs(profileImageId) {
                        start.linkTo(parent.start)
                        top.linkTo(parent.top)
                    }
                    .clip(
                        shape = imageShape
                    )
                    .size(
                        size = imageSize.dp
                    )
                    .background(
                        color = imageBackground
                    )
            )

        }//end if
        else {

            Spacer(
                modifier = Modifier
                    .constrainAs(profileImageId) {
                        start.linkTo(parent.start)
                        top.linkTo(parent.top)
                    }
                    .clip(
                        shape = imageShape
                    )
                    .size(
                        size = imageSize.dp
                    )
                    .background(
                        color = theme.grayLight
                    )
            )

        }//end else

        if (doctorModel?.activeStatus == true) {

            //create online icon here
            OnlineView(
                dimen = dimen,
                theme = theme,
                iconColor = onlineIconColor,
                iconShape = onlineIconShape,
                iconSize = onlineIconSize,
                modifier = Modifier
                    .constrainAs(onlineIconId) {
                        bottom.linkTo(
                            parent.bottom,
                            dimen.dimen_0_5.dp
                        )
                        end.linkTo(
                            parent.end,
                            dimen.dimen_0_125.dp
                        )
                    }//end constrainAs
            )

        }//end if

    }//end ConstraintLayout

}//end ProfileView