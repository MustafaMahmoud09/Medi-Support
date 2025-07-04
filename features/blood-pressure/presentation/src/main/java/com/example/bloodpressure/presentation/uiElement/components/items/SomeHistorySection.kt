package com.example.bloodpressure.presentation.uiElement.components.items

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.blood.pressure.domain.model.SimpleBloodPressureModel
import com.example.sharedui.R
import com.example.sharedui.uiElement.components.composable.TextNormalView
import com.example.sharedui.uiElement.components.composable.TextSemiBoldView
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme

@Composable
fun SomeHistorySection(
    dimen: CustomDimen,
    theme: CustomTheme,
    roundSize: Float = dimen.dimen_0_75,
    borderColor: Color = theme.redDark,
    onClickSeeAll: () -> Unit,
    historyRecords: List<SimpleBloodPressureModel>,
    modifier: Modifier = Modifier,
) {

    ConstraintLayout(
        modifier = modifier
            .background(
                color = theme.background
            )
            .clip(
                shape = RoundedCornerShape(
                    roundSize.dp
                )
            )
            .border(
                width = dimen.dimen_0_125.dp,
                color = borderColor,
                shape = RoundedCornerShape(
                    roundSize.dp
                )
            )
            .padding(
                bottom = dimen.dimen_2.dp
            )
    ) {
        val (historyTextId, seeAllTextId, historiesId, historiesEmptyId) = createRefs()

        TextNormalView(
            theme = theme,
            dimen = dimen,
            text = stringResource(
                com.example.sharedui.R.string.history
            ),
            fontColor = theme.black,
            size = dimen.dimen_2,
            modifier = Modifier
                .constrainAs(historyTextId) {
                    top.linkTo(
                        parent.top,
                        dimen.dimen_2_25.dp
                    )
                    start.linkTo(
                        parent.start,
                        dimen.dimen_2.dp
                    )
                },
        )

        TextNormalView(
            theme = theme,
            dimen = dimen,
            text = stringResource(
                com.example.sharedui.R.string.see_all
            ),
            fontColor = theme.black,
            size = dimen.dimen_2,
            modifier = Modifier
                .constrainAs(seeAllTextId) {
                    top.linkTo(
                        parent.top,
                        dimen.dimen_2_25.dp
                    )
                    end.linkTo(
                        parent.end,
                        dimen.dimen_2.dp
                    )
                }
                .clickable(
                    interactionSource = remember {
                        MutableInteractionSource()
                    },
                    indication = null
                ) {
                    onClickSeeAll()
                },
        )


        AnimatedVisibility(
            visible = historyRecords.isEmpty(),
            enter = fadeIn(
                animationSpec = tween(
                    durationMillis = 50
                )
            ),
            exit = fadeOut(
                animationSpec = tween(
                    durationMillis = 50
                )
            ),
            modifier = Modifier
                .constrainAs(historiesEmptyId) {
                    start.linkTo(
                        parent.start,
                        dimen.dimen_1.dp
                    )
                    end.linkTo(
                        parent.end,
                        dimen.dimen_1.dp
                    )
                    top.linkTo(
                        seeAllTextId.bottom,
                        dimen.dimen_2_5.dp
                    )
                    width = Dimension.fillToConstraints
                }
        ) {


            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = dimen.dimen_1_5.dp,
                    )
                    .padding(
                        top = dimen.dimen_2.dp,
                        bottom = dimen.dimen_2_5.dp
                    ),
                contentAlignment = Alignment.Center
            ) {


                TextSemiBoldView(
                    theme = theme,
                    dimen = dimen,
                    text = stringResource(
                        R.string.there_are_no_items_to_display
                    ),
                    size = dimen.dimen_1_75,
                    fontColor = theme.grayLight,
                )

            }//end Box

        }//end AnimatedVisibility


        AnimatedVisibility(
            visible = historyRecords.isNotEmpty(),
            enter = fadeIn(
                animationSpec = tween(
                    durationMillis = 50
                )
            ),
            exit = fadeOut(
                animationSpec = tween(
                    durationMillis = 50
                )
            ),
            modifier = Modifier
                .constrainAs(historiesId) {
                    start.linkTo(
                        parent.start,
                        dimen.dimen_1.dp
                    )
                    end.linkTo(
                        parent.end,
                        dimen.dimen_1.dp
                    )
                    top.linkTo(
                        seeAllTextId.bottom,
                        dimen.dimen_2_5.dp
                    )
                    width = Dimension.fillToConstraints
                },
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(
                    dimen.dimen_2.dp
                )
            ) {


                for (element in historyRecords) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                horizontal = dimen.dimen_1_5.dp
                            ),
                        contentAlignment = Alignment.Center
                    ) {

                        SingleHistorySection(
                            dimen = dimen,
                            theme = theme,
                            modifier = Modifier
                                .fillMaxWidth(),
                            record = element,
                        )

                    }//end Box

                }//end if

            }//end Column

        }//end AnimatedVisibility

    }//end ConstraintLayout

}//end SomeHistorySection