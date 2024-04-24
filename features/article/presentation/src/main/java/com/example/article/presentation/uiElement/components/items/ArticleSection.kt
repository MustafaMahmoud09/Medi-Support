package com.example.article.presentation.uiElement.components.items

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.article.domain.model.TitleArticleModel
import com.example.article.presentation.uiElement.components.composable.EndIconButtonRadiusView
import com.example.sharedui.R
import com.example.sharedui.uiElement.components.composable.ServerLoadImageView
import com.example.sharedui.uiElement.components.composable.TextNormalView
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme
import com.google.accompanist.placeholder.placeholder

@Composable
internal fun ArticleSection(
    dimen: CustomDimen,
    theme: CustomTheme,
    borderSize: Float = dimen.dimen_0_125,
    borderColor: Color = theme.grayD7D7D7,
    shape: Shape = RoundedCornerShape(
        size = dimen.dimen_1_25.dp
    ),
    article: TitleArticleModel,
    onClickOnButton: (Long) -> Unit,
    buttonWidth: Float = dimen.dimen_15,
    buttonHeight: Float = dimen.dimen_3,
    placeHolderState: Boolean = false,
    placeHolderColor: Color = theme.grayLight,
    modifier: Modifier = Modifier
) {

    ConstraintLayout(
        modifier = modifier
            .clip(
                shape = shape
            )
//            .placeholder(
//                visible = placeHolderState,
//                color = placeHolderColor
//            )
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
            text = article.title,
            size = dimen.dimen_2,
            fontColor = theme.black,
            textAlign = null,
            maxLines = 1,
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
                    end.linkTo(
                        parent.end,
                        dimen.dimen_1_25.dp
                    )
                    width = Dimension.fillToConstraints
                }
                .placeholder(
                    visible = placeHolderState,
                    color = placeHolderColor
                )
        )

        EndIconButtonRadiusView(
            dimen = dimen,
            theme = theme,
            icon = painterResource(
                id = R.drawable.more_icon
            ),
            title = stringResource(
                R.string.read_now
            ),
            onClick = { onClickOnButton(article.id) },
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
                .placeholder(
                    visible = placeHolderState,
                    color = placeHolderColor
                )
        )

        if (placeHolderState) {

            Spacer(
                modifier = Modifier
                    .constrainAs(imageId) {
                        start.linkTo(parent.start)
                        end.linkTo(guideLineStart45P)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        height = Dimension.fillToConstraints
                        width = Dimension.fillToConstraints
                    }
                    .placeholder(
                        visible = placeHolderState,
                        color = placeHolderColor
                    )
            )

        }//end if
        else {

            ServerLoadImageView(
                dimen = dimen,
                theme = theme,
                imageUrl = article.image,
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

        }//end else

    }//end ConstraintLayout

}//end ArticleSection