package com.damanhour.Graduation.medisupport.ui.uiElement.components.items

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.sharedui.R
import com.example.sharedui.uiElement.components.composable.TextBoldView
import com.example.sharedui.uiElement.components.composable.TextSemiBoldView
import com.example.sharedui.uiElement.components.modifier.clickableWithoutHover
import com.example.sharedui.uiElement.navigation.data.MenuData
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme

@Composable
fun DropDownMenuPagerSection(
    dimen: CustomDimen,
    theme: CustomTheme,
    menus: Array<MenuData>,
    textSelectedSize: Float = dimen.dimen_2_5,
    textItemSize: Float = dimen.dimen_1_75,
    dropDownMenuWidth: Float = dimen.dimen_15,
    currentPage: Int,
    menusExpanded: Boolean,
    onDropMenusExpandedChanged: () -> Unit,
    modifier: Modifier = Modifier,
) {

    if (menus.isNotEmpty()) {

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
                    .clickableWithoutHover {
                        onDropMenusExpandedChanged()
                    }
            )

            TextBoldView(
                theme = theme,
                dimen = dimen,
                text = menus[currentPage].title,
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
                    expanded = menusExpanded,
                    onDismissRequest = onDropMenusExpandedChanged,
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


                    menus.forEach { item ->

                        DropdownMenuItem(
                            text = {
                                TextSemiBoldView(
                                    theme = theme,
                                    dimen = dimen,
                                    text = item.title,
                                    size = textItemSize,
                                    fontColor = theme.black,
                                )
                            },
                            onClick = item.onClick
                        )

                    }//end forEach

                }//end DropdownMenu

            }//end Box

        }//end ConstraintLayout

    }//end if

}//end DropDownMenuSection