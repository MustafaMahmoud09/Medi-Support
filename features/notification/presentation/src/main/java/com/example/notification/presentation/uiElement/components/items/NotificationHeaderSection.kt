package com.example.notification.presentation.uiElement.components.items

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.sharedui.uiElement.components.composable.TextBoldView
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme

@Composable
internal fun NotificationHeaderSection(
    dimen: CustomDimen,
    theme: CustomTheme,
    title: String,
    titleSize: Float = dimen.dimen_2_25,
    titleColor: Color = theme.black,
    readTitle: String,
    readTitleColor: Color = theme.blackLight353535,
    readTitleSize: Float = (dimen.dimen_1_5 + dimen.dimen_0_125),
    readCheckBoxSize: Float = dimen.dimen_1_5,
    readCheckColor: Color = theme.redDark,
    readCheckBoxBorderWidth: Float = dimen.dimen_0_125,
    readCheckBoxBorderShape: Shape = RectangleShape,
    readCheckBoxState: Boolean,
    readOnCheckBoxChanged: () -> Unit,
    modifier: Modifier = Modifier
) {

    //create container here
    ConstraintLayout(
        modifier = modifier
    ) {
        //create ids for components here
        val (titleId, readSectionId) = createRefs()

        //create title here
        TextBoldView(
            theme = theme,
            dimen = dimen,
            text = title,
            size = titleSize,
            color = titleColor,
            modifier = Modifier
                .constrainAs(titleId) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                }
        )

        //create readSection here
        ReadNotificationsSection(
            dimen = dimen,
            theme = theme,
            title = readTitle,
            checkBoxState = readCheckBoxState,
            onCheckBoxChanged = readOnCheckBoxChanged,
            titleSize = readTitleSize,
            titleColor = readTitleColor,
            checkBoxSize = readCheckBoxSize,
            checkColor = readCheckColor,
            checkBoxBorderWidth = readCheckBoxBorderWidth,
            checkBoxBorderShape = readCheckBoxBorderShape,
            modifier = Modifier
                .constrainAs(readSectionId) {
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                }
        )

    }//end ConstraintLayout

}//end NotificationHeaderSection