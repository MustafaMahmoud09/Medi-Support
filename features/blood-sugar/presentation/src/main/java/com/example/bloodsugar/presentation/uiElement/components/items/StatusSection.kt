package com.example.bloodsugar.presentation.uiElement.components.items

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.blood.sugar.domain.model.StatusModel
import com.example.bloodsugar.presentation.uiElement.components.composable.CheckBoxView
import com.example.sharedui.uiElement.components.composable.TextNormalView
import com.example.sharedui.uiElement.components.modifier.appBorder
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme
import com.google.accompanist.placeholder.placeholder

@Composable
internal fun StatusSection(
    dimen: CustomDimen,
    theme: CustomTheme,
    shape: Shape = RoundedCornerShape(
        size = dimen.dimen_1_25.dp
    ),
    borderWidth: Float = dimen.dimen_0_125,
    borderColor: Color = theme.redDark,
    statusSize: Float = dimen.dimen_2,
    statusColor: Color = theme.hintIconBottom,
    checkBoxSize: Float = dimen.dimen_2_25,
    checkBoxColor: Color = theme.redDark,
    checkBoxBorderWidth: Float = dimen.dimen_0_125,
    checkBoxShape: Shape = CircleShape,
    modifier: Modifier = Modifier,
    placeHolderState: Boolean = false,
    status: StatusModel,
    numberItemSelected: Int,
    onChanged: (Int) -> Unit,
    placeHolderColor: Color = theme.grayLight,
) {

    //create container here
    ConstraintLayout(
        modifier = modifier
            .clip(
                shape = shape
            )
            .placeholder(
                visible = placeHolderState,
                color = placeHolderColor
            )
            .border(
                width = borderWidth.dp,
                color = borderColor,
                shape = shape
            )
            .padding(
                vertical = dimen.dimen_1_75.dp,
                horizontal = dimen.dimen_2.dp
            )
    ) {
        //create ids for components here
        val (statusId, checkBoxId) = createRefs()

        //create check box here
        CheckBoxView(
            dimen = dimen,
            theme = theme,
            value = numberItemSelected == status.id,
            onChanged = {onChanged(status.id)},
            size = checkBoxSize,
            color = checkBoxColor,
            borderWidth = checkBoxBorderWidth,
            shape = checkBoxShape,
            modifier = Modifier
                .constrainAs(checkBoxId){
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
        )

        //create status title here
        TextNormalView(
            theme = theme,
            dimen = dimen,
            text = status.status,
            size = statusSize,
            fontColor = statusColor,
            textAlign = null,
            modifier = Modifier
                .constrainAs(statusId){
                    start.linkTo(parent.start)
                    end.linkTo(
                        checkBoxId.start,
                        dimen.dimen_1.dp
                    )
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    width = Dimension.fillToConstraints
                    height = Dimension.fillToConstraints
                }
        )

    }//end ConstraintLayout

}//end StatusSection