package com.example.presentation.uiElement.components.items

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.presentation.uiElement.components.composable.ProfileView
import com.example.sharedui.uiElement.components.composable.TextNormalView
import com.example.sharedui.uiElement.components.composable.TextSemiBoldView
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme

@Composable
internal fun UserActiveSection(
    dimen: CustomDimen,
    theme: CustomTheme,
    nameColor: Color = theme.black,
    nameSize: Float = dimen.dimen_2,
    activeColor: Color = theme.gray797C7B,
    activeSize: Float = dimen.dimen_1_5,
    modifier: Modifier = Modifier,
) {

    //create container here
    ConstraintLayout(
        modifier = modifier
    ) {
        //create ids for screen components here
        val (profileId, userInfoId) = createRefs()

        //create profile image section here
        com.example.presentation.uiElement.components.composable.ProfileView(
            dimen = dimen,
            theme = theme,
            modifier = Modifier
                .constrainAs(profileId) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                }
        )

        //create user info column here
        Column(
            modifier = Modifier
                .constrainAs(userInfoId) {
                    start.linkTo(
                        profileId.end,
                        dimen.dimen_2.dp
                    )
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    end.linkTo(parent.end)
                    height = Dimension.fillToConstraints
                    width = Dimension.fillToConstraints
                },
            verticalArrangement = Arrangement.Center
        ) {

            //create user name here
            TextSemiBoldView(
                theme = theme,
                dimen = dimen,
                text = "Dr.Ahmed Mohamed",
                size = nameSize,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontColor = nameColor,
                modifier = Modifier
                    .fillMaxWidth()
            )

            //create padding here
            Spacer(
                modifier = Modifier
                    .height(
                        height = dimen.dimen_0_5.dp
                    )
            )

            //create active text here
            TextNormalView(
                theme = theme,
                dimen = dimen,
                text = "Active now",
                size = activeSize,
                fontColor = activeColor,
                maxLines = 1,
                defineLine = true,
                textAlign = null,
                modifier = Modifier
                    .fillMaxWidth()
            )

        }//end Column

    }//end ConstraintLayout

}//end UserActiveSection