package com.example.auth.presentation.uiElement.components.items

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.sharedui.uiElement.components.composable.TextBoldBlueView
import com.example.sharedui.uiElement.components.composable.TextNormalView
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme

@Composable
internal fun WelcomeSection(
    theme: CustomTheme,
    dimen: CustomDimen,
    image: Painter,
    title: String,
    message: String,
    modifier: Modifier = Modifier
) {

    ConstraintLayout(
        modifier = modifier
            .background(
                color = theme.background
            )
    ) {
        val (headerImage, header) = createRefs()
        val guideLineTop665P = createGuidelineFromTop(.665f)

        Image(
            painter = image,
            contentDescription = "welcome",
            modifier = Modifier
                .constrainAs(headerImage) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(guideLineTop665P)
                    width = Dimension.fillToConstraints
                    height = Dimension.fillToConstraints
                },
        )

        LazyColumn(
            modifier = Modifier
                .constrainAs(header) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(headerImage.bottom)
                    bottom.linkTo(parent.bottom)
                    width = Dimension.fillToConstraints
                    height = Dimension.fillToConstraints
                },
            contentPadding = PaddingValues(
                vertical  = dimen.dimen_1_5.dp,
                horizontal = dimen.dimen_4.dp
            ),
            verticalArrangement = Arrangement.spacedBy(
                dimen.dimen_3.dp
            )
        ) {

            item(
                key = 1
            ) {

                Box(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ){

                    TextBoldBlueView(
                        theme = theme,
                        dimen = dimen,
                        text = title,
                        size = dimen.dimen_2_5,
                    )

                }//end Box

            }//end item

            item(
                key = 2
            ) {

                Box(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ){

                    TextNormalView(
                        theme = theme,
                        dimen = dimen,
                        text = message,
                        size =dimen.dimen_2_25
                    )

                }//end Box

            }//end item

        }//end LazyColumn


    }//end ConstraintLayout

}//end WelcomeSection