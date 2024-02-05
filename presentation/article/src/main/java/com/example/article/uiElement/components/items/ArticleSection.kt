package com.example.article.uiElement.components.items

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.article.uiElement.components.composable.EndIconButtonRadiusView
import com.example.sharedui.uiElement.components.composable.CropImageView
import com.example.sharedui.uiElement.components.composable.TextNormalView
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme

@Composable
internal fun ArticleSection(
    dimen: CustomDimen,
    theme: CustomTheme,
    borderSize: Float = dimen.dimen_0_125,
    borderColor: Color = theme.grayD7D7D7,
    shape: Shape = RoundedCornerShape(
        size = dimen.dimen_1_25.dp
    ),
    src: Painter,
    title: String,
    onClickOnButton: () -> Unit,
    buttonWidth: Float = dimen.dimen_15,
    buttonHeight: Float = dimen.dimen_3,
    modifier: Modifier = Modifier
) {

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
            .padding(
                end = dimen.dimen_1.dp,
            )
    ) {
        val (imageId, titleId, buttonId) = createRefs()
        val guideLineStart45P = createGuidelineFromStart(0.45f)

        TextNormalView(
            theme = theme,
            dimen = dimen,
            text = title,
            size = dimen.dimen_2,
            fontColor = theme.black,
            modifier = Modifier
                .constrainAs(titleId) {
                    start.linkTo(
                        guideLineStart45P,
                        dimen.dimen_1_25.dp
                    )
                    top.linkTo(
                        parent.top,
                        dimen.dimen_1_5.dp
                    )
                }
        )

        EndIconButtonRadiusView(
            dimen = dimen,
            theme = theme,
            icon = painterResource(
                id = com.example.sharedui.R.drawable.more_icon
            ),
            title = stringResource(
                com.example.sharedui.R.string.read_now
            ),
            onClick = onClickOnButton,
            modifier = Modifier
                .constrainAs(buttonId) {
                    start.linkTo(
                        guideLineStart45P,
                        dimen.dimen_1_25.dp
                    )
                    top.linkTo(
                        titleId.bottom,
                        dimen.dimen_1_75.dp
                    )
                    bottom.linkTo(
                        parent.bottom,
                        dimen.dimen_1_5.dp
                    )
                }
                .size(
                    width = buttonWidth.dp,
                    height = buttonHeight.dp
                )
        )

        CropImageView(
            painter = src,
            modifier = Modifier
                .constrainAs(imageId) {
                    start.linkTo(parent.start)
                    end.linkTo(guideLineStart45P)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    height = Dimension.fillToConstraints
                    width = Dimension.fillToConstraints
                }
        )

    }//end ConstraintLayout

}//end ArticleSection