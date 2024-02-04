package com.example.bloodsuger.uiElement.components.items

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.bloodsuger.uiElement.components.composable.ColumnView
import com.example.sharedui.uiElement.components.composable.TextSemiBoldView
import com.example.sharedui.uiElement.components.modifier.appShadow
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme

@Composable
internal fun LazySliderSection(
    value: Float,
    onValueChanged: (Float) -> Unit,
    dimen: CustomDimen,
    theme: CustomTheme,
    shape: Shape = RoundedCornerShape(
        bottomStart = dimen.dimen_1_25.dp,
        bottomEnd = dimen.dimen_1_25.dp,
        topEnd = dimen.dimen_0.dp,
        topStart = dimen.dimen_0.dp
    ),
    elevation: Float = dimen.dimen_0_5,
    numberSelectedColor: Color = theme.redDark,
    numberSelectedSize: Float = dimen.dimen_2_5,
    unit: String,
    unitColor: Color = theme.black,
    unitSize: Float = dimen.dimen_2_25,
    startPoint: Int,
    endPoint: Int,
    modifier: Modifier = Modifier,
) {
    val lazyRowState: LazyListState = rememberLazyListState()

    //create box to create shadow
    Box(
        modifier = modifier
            .appShadow(
                shape = shape,
                elevation = elevation,
                ambientColor = theme.black,
                spotColor = theme.black,
            )
    ) {

        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .clip(
                    shape = shape
                )
                .background(
                    color = theme.background
                )
                .padding(
                    bottom = dimen.dimen_5.dp
                )
        ) {
            //create ids for components here
            val (numberSelectedId, lazyRowSliderId) = createRefs()

            //create data selected row here
            Row(
                modifier = Modifier
                    .constrainAs(numberSelectedId) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(
                            parent.top,
                            dimen.dimen_3.dp
                        )
                    },//end constrainAs
                verticalAlignment = Alignment.CenterVertically
            ) {

                //create number selected text here
                TextSemiBoldView(
                    theme = theme,
                    dimen = dimen,
                    text = "$value",
                    size = numberSelectedSize,
                    fontColor = numberSelectedColor
                )

                //create padding here
                Spacer(
                    modifier = Modifier
                        .width(
                            width = dimen.dimen_2.dp
                        )
                )

                //create unit here
                TextSemiBoldView(
                    theme = theme,
                    dimen = dimen,
                    text = unit,
                    size = unitSize,
                    fontColor = unitColor
                )

            }//end Row


            //create lazy row contain columns to represent lazy slider here
            LazyRow(
                state = lazyRowState,
                modifier = Modifier
                    .constrainAs(lazyRowSliderId) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(
                            numberSelectedId.bottom,
                            dimen.dimen_1_25.dp
                        )
                        width = Dimension.fillToConstraints
                    },
                contentPadding = PaddingValues(
                    horizontal = dimen.dimen_0_5.dp
                ),
            ) {
                //create column item here
                items(
                    count = (endPoint - startPoint) * 2 + 1,
                    key = { (it + (startPoint * 2f)) / 2f }
                ) {

                    ColumnView(
                        dimen = dimen,
                        theme = theme,
                        numberOfItem = (it + (startPoint * 2f)) / 2f
                    )

                }//end items

            }//end LazyRow

        }//end ConstraintLayout

    }//end Box

    //create launch effect to calculate new value to lazy slider here
    LaunchedEffect(
        key1 = lazyRowState.isScrollInProgress,
    ) {

        //define visible item here
        val visibleItemsInfo = lazyRowState.layoutInfo.visibleItemsInfo

        //if visible item list is not empty execute block
        if (visibleItemsInfo.isNotEmpty()) {

            //define center item value
            val newValue = visibleItemsInfo[visibleItemsInfo.size / 2].key as Float

            //change value by new value here
            onValueChanged(newValue)
        }//end if

    }//end Launch

}//end LazySliderSection
