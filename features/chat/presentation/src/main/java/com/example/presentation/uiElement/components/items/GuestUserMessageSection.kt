package com.example.presentation.uiElement.components.items

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.chat.domain.model.DoctorModel
import com.example.chat.domain.model.MessageModel
import com.example.presentation.uiElement.components.composable.MessageView
import com.example.sharedui.uiElement.components.composable.TextBoldView
import com.example.sharedui.uiElement.components.composable.TextSemiBoldView
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme
import kotlin.reflect.KFunction3

@Composable
internal fun GuestUserMessageSection(
    dimen: CustomDimen,
    theme: CustomTheme,
    messageShape: Shape = RoundedCornerShape(
        bottomEnd = dimen.dimen_2.dp,
        topEnd = dimen.dimen_2.dp,
        bottomStart = dimen.dimen_2.dp,
        topStart = dimen.dimen_0.dp
    ),
    isStartMessage: Boolean = false,
    isEndMessage: Boolean = false,
    isEndChatMessage: Boolean = false,
    background: Color = theme.grayF2F7FB,
    messageColor: Color = theme.black,
    messageSize: Float = dimen.dimen_1_5,
    timeColor: Color = theme.gray797C7B,
    timeSize: Float = dimen.dimen_1_25,
    profileSize: Float = dimen.dimen_5,
    guestNameSize: Float = dimen.dimen_1_75,
    guestNameColor: Color = theme.black,
    modifier: Modifier = Modifier,
    messageModel: MessageModel,
    doctor: DoctorModel?,
    onOpenDownloadFile: KFunction3<String, String, String, Unit>
) {

    //create container here
    ConstraintLayout(
        modifier = modifier
            .padding(
                bottom = //if message is end chat message create padding from bottom equal 0
                if (isEndChatMessage) {
                    dimen.dimen_0.dp
                }//end if
                //if message is not end message create padding from bottom equal 6
                else if (!isEndMessage) {
                    dimen.dimen_0_75.dp
                }//end else
                else {
                    dimen.dimen_2.dp
                }//else
            )
    ) {
        //create ids for components here
        val (profileId, nameId, messageId) = createRefs()

        //create guides for components here
        val guideLineFromStart73P = createGuidelineFromStart(0.73f)

        //if message is start message in section create guest info
        if (isStartMessage) {

            //create guest profile here
            com.example.presentation.uiElement.components.composable.ProfileView(
                dimen = dimen,
                theme = theme,
                doctorModel = doctor,
                imageSize = profileSize,
                modifier = Modifier
                    .constrainAs(profileId) {
                        start.linkTo(parent.start)
                        top.linkTo(parent.top)
                    }
            )

            //create guest name here
            TextSemiBoldView(
                theme = theme,
                dimen = dimen,
                text = doctor?.doctorName ?: "",
                size = guestNameSize,
                fontColor = guestNameColor,
                modifier = Modifier
                    .constrainAs(nameId) {
                        start.linkTo(
                            profileId.end,
                            dimen.dimen_1_5.dp
                        )
                        top.linkTo(
                            parent.top,
                            dimen.dimen_0_75.dp
                        )
                    }
            )

        }//end if

        //create message box here
        ConstraintLayout(
            modifier = Modifier
                .constrainAs(messageId) {

                    start.linkTo(
                        parent.start,
                        (profileSize + dimen.dimen_2_25).dp
                    )
                    end.linkTo(guideLineFromStart73P)

                    //if message is first message in section
                    if (isStartMessage) {

                        top.linkTo(
                            nameId.bottom,
                            dimen.dimen_1_5.dp
                        )
                    }//end if

                    //else
                    else {
                        top.linkTo(parent.top)
                    }//end else
                    width = Dimension.fillToConstraints
                }
        ) {
            //create ids for components here
            val (messageContentId, timeId) = createRefs()

            Row(
                modifier = Modifier
                    .constrainAs(messageContentId) {
                        start.linkTo(parent.start)
                        top.linkTo(parent.top)
                    }
            ) {

                //create message view here
                MessageView(
                    theme = theme,
                    dimen = dimen,
                    background = background,
                    fileBackground = theme.grayB3D6F1,
                    message = messageModel,
                    messageColor = messageColor,
                    messageSize = messageSize,
                    messageShape = messageShape,
                    modifier = Modifier
                        .fillMaxWidth(),
                    onOpenDownloadFile = onOpenDownloadFile,
                )

            }//end Row

            //if this message is end message create text to show time
            if (isEndMessage) {

                //create text time here
                TextBoldView(
                    theme = theme,
                    dimen = dimen,
                    text = messageModel.time,
                    size = timeSize,
                    color = timeColor,
                    modifier = Modifier
                        .constrainAs(timeId) {
                            end.linkTo(
                                messageContentId.end,
                                dimen.dimen_1.dp
                            )
                            top.linkTo(
                                messageContentId.bottom,
                                dimen.dimen_1.dp
                            )
                        }
                )

            }//end if

        }//end Row

    }//end ConstraintLayout

}//end GuestUserMessageSection