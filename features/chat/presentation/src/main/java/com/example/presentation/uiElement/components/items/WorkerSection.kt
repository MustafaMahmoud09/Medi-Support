package com.example.presentation.uiElement.components.items

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme
import com.example.sharedui.uiElement.components.composable.OnlineView
import com.example.sharedui.uiElement.components.composable.ServerLoadImageView
import com.example.sharedui.uiElement.components.composable.TextNormalView
import com.example.sharedui.uiElement.components.composable.TextSemiBoldView
import com.google.accompanist.placeholder.placeholder

@Composable
internal fun WorkerSection(
    dimen: CustomDimen,
    theme: CustomTheme,
    profileShape: Shape = RoundedCornerShape(
        size = dimen.dimen_1.dp
    ),
    profileSize: Float = dimen.dimen_5,
    onlineIconSize: Float = dimen.dimen_1,
    onlineIconColor: Color = theme.green66CA98,
    onlineIconShape: Shape = CircleShape,
    profilePath: String = "",
    name: String = "",
    jop: String = "",
    nameColor: Color = theme.black1C1F1E,
    nameSize: Float = dimen.dimen_2,
    jopColor: Color = theme.grayA7A6A5,
    jobSize: Float = dimen.dimen_1_75,
    modifier: Modifier = Modifier,
    activeStatus: Boolean,
    placeHolderState: Boolean = false,
    placeHolderColor: Color = theme.greenLight,
) {

    //create container here
    ConstraintLayout(
        modifier = modifier
    ) {
        //create ids for components here
        val (profileId, onlineId, nameId, jobId) = createRefs()

        if(!placeHolderState) {

            //create profile image here
            ServerLoadImageView(
                theme = theme,
                dimen = dimen,
                imageUrl = profilePath,
                progressSize = dimen.dimen_2,
                modifier = Modifier
                    .constrainAs(profileId) {
                        start.linkTo(parent.start)
                        top.linkTo(
                            parent.top,
                            dimen.dimen_0_25.dp
                        )
                    }
                    .size(
                        size = profileSize.dp
                    )
                    .clip(
                        shape = profileShape
                    )
            )

        }//end if
        else{

            Spacer(
                modifier = Modifier
                    .constrainAs(profileId) {
                        start.linkTo(parent.start)
                        top.linkTo(
                            parent.top,
                            dimen.dimen_0_25.dp
                        )
                    }
                    .size(
                        size = profileSize.dp
                    )
                    .clip(
                        shape = profileShape
                    ).placeholder(
                        color = placeHolderColor,
                        visible = placeHolderState
                    )
            )

        }//end else

        if(activeStatus) {

            //create online box here
            Box(
                modifier = Modifier
                    .constrainAs(onlineId) {
                        start.linkTo(
                            parent.start,
                            (profileSize - onlineIconSize).dp
                        )
                        top.linkTo(
                            parent.top,
                            ((profileSize - onlineIconSize) + dimen.dimen_0_25).dp
                        )
                    }
                    .size(
                        size = (onlineIconSize + dimen.dimen_0_25).dp
                    )
                    .clip(
                        shape = onlineIconShape
                    )
                    .background(
                        color = theme.background
                    ).placeholder(
                        visible = placeHolderState,
                        color = placeHolderColor
                    ),
                contentAlignment = Alignment.Center
            ) {

                //create online view here
                OnlineView(
                    dimen = dimen,
                    theme = theme,
                    iconSize = onlineIconSize,
                    iconShape = onlineIconShape,
                    iconColor = onlineIconColor
                )

            }//end  Box

        }//end if

        //create worker name here
        TextNormalView(
            theme = theme,
            dimen = dimen,
            text = name,
            size = nameSize,
            fontColor = nameColor,
            defineLine = true,
            maxLines = 1,
            textAlign = null,
            modifier = Modifier
                .constrainAs(nameId) {
                    start.linkTo(
                        profileId.end,
                        dimen.dimen_0_75.dp
                    )
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    width = Dimension.fillToConstraints
                }.placeholder(
                    visible = placeHolderState,
                    color = placeHolderColor
                )
        )

        //create worker jop here
        TextSemiBoldView(
            theme = theme,
            dimen = dimen,
            text = jop,
            size = jobSize,
            fontColor = jopColor,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            textAlign = null,
            modifier = Modifier
                .constrainAs(jobId) {
                    start.linkTo(nameId.start)
                    end.linkTo(parent.end)
                    top.linkTo(
                        nameId.bottom,
                        dimen.dimen_0_75.dp
                    )
                    width = Dimension.fillToConstraints
                }.placeholder(
                    visible = placeHolderState,
                    color = placeHolderColor
                )
        )

    }//end ConstraintLayout

}//end WorkerSection