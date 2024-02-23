package com.damanhour.Graduation.medisupport.ui.uiElement.components.items

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.sharedui.uiElement.components.composable.TextSemiBoldView
import com.example.sharedui.uiElement.components.modifier.clickableWithoutHover
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme

@Composable
internal fun TabSection(
    dimen: CustomDimen,
    theme: CustomTheme,
    title: String,
    titleSize: Float,
    selectedTitleColor: Color,
    unselectedTitleColor: Color,
    unselectedTabHeight: Float,
    selectedTabHeight: Float,
    unselectedShape: Shape,
    selectedShape: Shape,
    unselectedColor: Color,
    selectedColor: Color,
    numberOfItem: Int,
    selectedItem: Int,
    onClickOnTab: () -> Unit,
    modifier: Modifier = Modifier,
) {

    //create container here
    ConstraintLayout(
        modifier = modifier
            .clickableWithoutHover {
                onClickOnTab()
            }
    ) {
        //create ids for components here
        val (titleId, shapeId) = createRefs()

        //create title here
        TextSemiBoldView(
            theme = theme,
            dimen = dimen,
            text = title,
            size = titleSize,
            fontColor = if (numberOfItem == selectedItem) selectedTitleColor else unselectedTitleColor,
            modifier = Modifier
                .constrainAs(titleId) {
                    top.linkTo(parent.top)
                    start.linkTo(
                        parent.start,
                        dimen.dimen_1.dp
                    )
                    end.linkTo(
                        parent.end,
                        dimen.dimen_1.dp
                    )
                    width = Dimension.preferredWrapContent
                }
        )

        //create shape box here
        Box(
            modifier = Modifier
                .constrainAs(shapeId) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(
                        titleId.bottom,
                        dimen.dimen_1_75.dp
                    )
                    width = Dimension.fillToConstraints
                },
            contentAlignment = Alignment.Center
        ) {

            //create unselected shape here
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(
                        shape = unselectedShape
                    )
                    .height(
                        height = unselectedTabHeight.dp
                    )
                    .background(
                        color = unselectedColor
                    )
            )

            //if number of item equal selected item create selected shape
            if (selectedItem == numberOfItem) {

                //create selected shape here
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(
                            shape = selectedShape
                        )
                        .height(
                            height = selectedTabHeight.dp
                        )
                        .background(
                            color = selectedColor
                        )
                )

            }//end if

            //else create transparent shape
            else {

                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(
                            shape = unselectedShape
                        )
                        .height(
                            height = selectedTabHeight.dp
                        )
                        .background(
                            color = theme.transparent
                        )
                )

            }//end else

        }//end Box

    }//end ConstraintLayout

}//end TabSection