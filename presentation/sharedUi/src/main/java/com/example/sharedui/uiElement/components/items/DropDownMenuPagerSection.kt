@file:OptIn(ExperimentalFoundationApi::class)

package com.example.sharedui.uiElement.components.items

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.PopupProperties
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.sharedui.R
import com.example.sharedui.uiElement.components.composable.TextBoldView
import com.example.sharedui.uiElement.components.composable.TextSemiBoldView
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme
import kotlinx.coroutines.launch

@Composable
fun DropDownMenuPagerSection(
    dimen: CustomDimen,
    theme: CustomTheme,
    options: List<String>,
    textSelectedSize: Float = dimen.dimen_2_5,
    textItemSize: Float = dimen.dimen_1_75,
    pagerState: PagerState,
    dropDownMenuWidth: Float = dimen.dimen_15,
    modifier: Modifier = Modifier
) {
    val coroutineScope = rememberCoroutineScope()

    if (options.isNotEmpty()) {

        var expanded by rememberSaveable {
            mutableStateOf(false)
        }
        var itemSelected by rememberSaveable {
            mutableStateOf(options[0])
        }

        ConstraintLayout(
            modifier = modifier
        ) {
            val (textSelectedId, iconSelectedId, dropDownMenuId) = createRefs()

            Icon(
                painter = painterResource(
                    id = R.drawable.drop_icon
                ),
                contentDescription = "drop icon",
                modifier = Modifier
                    .size(
                        dimen.dimen_3.dp
                    )
                    .constrainAs(iconSelectedId) {
                        end.linkTo(parent.end)
                        top.linkTo(parent.top)
                    }
                    .clickable(
                        interactionSource = remember {
                            MutableInteractionSource()
                        },
                        indication = null
                    ) {
                        expanded = !expanded
                    }
            )

            TextBoldView(
                theme = theme,
                dimen = dimen,
                text = itemSelected,
                size = textSelectedSize,
                modifier = Modifier
                    .constrainAs(textSelectedId) {
                        start.linkTo(
                            parent.start,
                            dimen.dimen_1.dp
                        )
                        end.linkTo(
                            iconSelectedId.start,
                            dimen.dimen_1.dp
                        )
                        top.linkTo(parent.top)
                        width = Dimension.fillToConstraints
                    }
            )

            Box(
                modifier = Modifier
                    .constrainAs(dropDownMenuId) {
                        end.linkTo(parent.end)
                        top.linkTo(
                            iconSelectedId.bottom,
                            dimen.dimen_1.dp
                        )
                    }
                    .clip(
                        shape = RectangleShape
                    )
            ) {

                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    offset = DpOffset((-1 * dropDownMenuWidth).dp, dimen.dimen_0.dp),
                    modifier = Modifier
                        .background(
                            color = theme.white,
                            shape = RectangleShape
                        )
                        .width(
                            dropDownMenuWidth.dp
                        ),
                ) {

                    for (count in options.indices)

                        DropdownMenuItem(
                            text = {
                                TextSemiBoldView(
                                    theme = theme,
                                    dimen = dimen,
                                    text = options[count],
                                    size = textItemSize,
                                    fontColor = theme.black,
                                )
                            },
                            onClick = {
                                coroutineScope.launch {

                                    if (pagerState.currentPage != count) {
                                        pagerState.scrollToPage(
                                            page = count
                                        )
                                        itemSelected = options[count]
                                    }//end if

                                }//end launch
                            }//end onClick
                        )

                }//end for

            }//end DropdownMenu

        }//end Box

    }//end ConstraintLayout

}//end DropDownMenuSection