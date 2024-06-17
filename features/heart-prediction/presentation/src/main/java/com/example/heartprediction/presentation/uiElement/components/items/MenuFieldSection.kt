package com.example.heartprediction.presentation.uiElement.components.items

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.heartprediction.presentation.uiElement.data.MenuData
import com.example.heartprediction.presentation.uiState.state.FieldDataSelected
import com.example.sharedui.R
import com.example.sharedui.uiElement.components.composable.TextNormalView
import com.example.sharedui.uiElement.components.composable.TextSemiBoldView
import com.example.sharedui.uiElement.components.modifier.appBorder
import com.example.sharedui.uiElement.components.modifier.clickableWithoutHover
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme
import kotlin.reflect.KFunction2

@Composable
internal fun MenuFieldSection(
    dimen: CustomDimen,
    theme: CustomTheme,
    title: String,
    titleColorOnFocus: Color = theme.black,
    titleColorOnNotFocus: Color = theme.grayD7D7D7,
    inputColorOnFocus: Color = theme.grayC1C7CD,
    inputColorOnNotFocus: Color = theme.grayLight,
    inputSize: Float = dimen.dimen_2,
    value: String,
    height: Float = dimen.dimen_6_5,
    contentCenter: Boolean = true,
    borderWidth: Float = dimen.dimen_0_125,
    borderColorOnFocus: Color = theme.redDark,
    borderColorOnNotFocus: Color = theme.grayD7D7D7,
    shape: Shape = RoundedCornerShape(
        size = dimen.dimen_1_25.dp
    ),
    menus: List<MenuData>,
    onClickOnMenuItem: KFunction2<Int, String, Unit>,
    numberField: Int,
    numberFieldIsFocusNow: Int,
    backgroundOnFocus: Color = theme.background,
    backgroundOnNotFocus: Color = theme.grayLightF2F2F2,
    onDropMenusExpandedChanged: () -> Unit,
    menusExpanded: Boolean = false,
    modifier: Modifier = Modifier,
    dataSelected: FieldDataSelected,
) {
    //create container here
    Column(
        modifier = modifier
    ) {

        //create title here
        TextNormalView(
            theme = theme,
            dimen = dimen,
            text = title,
            size = dimen.dimen_1_75,
            fontColor = if (numberField == numberFieldIsFocusNow) titleColorOnFocus else titleColorOnNotFocus
        )

        //create padding here
        Spacer(
            modifier = Modifier
                .height(dimen.dimen_0_75.dp)
        )

        //create field here
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .height(height.dp)
                .appBorder(
                    borderWidth = borderWidth,
                    borderColor = if (numberField == numberFieldIsFocusNow) borderColorOnFocus else borderColorOnNotFocus,
                    shape = shape
                )
                .background(
                    color = if (numberField == numberFieldIsFocusNow) backgroundOnFocus else backgroundOnNotFocus
                )
                .padding(
                    horizontal = dimen.dimen_2.dp
                ),
        ) {
            //create ids for components here
            val (iconSelectedId, hintId) = createRefs()

            if (numberField == numberFieldIsFocusNow) {

                Icon(
                    painter = if (menusExpanded) painterResource(
                        id = R.drawable.drag_menu_icon
                    ) else painterResource(
                        id = R.drawable.drop_menu_icon
                    ),
                    contentDescription = "drop icon",
                    tint = theme.redDark,
                    modifier = Modifier
                        .size(
                            dimen.dimen_3.dp
                        )
                        .constrainAs(iconSelectedId) {
                            end.linkTo(parent.end)
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                        }
                        .clickableWithoutHover {
                            onDropMenusExpandedChanged()
                        }
                )
            }//end if

            //create hint here
            TextNormalView(
                theme = theme,
                dimen = dimen,
                text = if (dataSelected.id == -1) {
                    value
                } else {
                     dataSelected.name
                },
                size = inputSize,
                textAlign = null,
                maxLines = 1,
                defineLine = true,
                fontColor = if (numberField == numberFieldIsFocusNow) inputColorOnFocus else inputColorOnNotFocus,
                modifier = Modifier
                    .constrainAs(hintId) {
                        start.linkTo(parent.start)
                        //if keyboard type is number create field end before operation
                        if (numberField == numberFieldIsFocusNow) {

                            end.linkTo(
                                iconSelectedId.start,
                                dimen.dimen_1.dp
                            )
                        } else {
                            end.linkTo(parent.end)
                        }
                        if (contentCenter) {
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                        }
                        //else make hint in top
                        else {
                            top.linkTo(
                                parent.top,
                                dimen.dimen_2.dp
                            )
                        }
                        width = Dimension.fillToConstraints
                    }
            )

        }//end ConstraintLayout

        if (numberField == numberFieldIsFocusNow) {


            DropdownMenu(
                expanded = menusExpanded,
                onDismissRequest = onDropMenusExpandedChanged,
                modifier = Modifier
                    .background(
                        color = theme.background,
                        shape = RectangleShape
                    )
                    .width(
                        (LocalConfiguration.current.screenWidthDp - dimen.dimen_4).dp
                    )
            ) {


                menus.forEach { item ->

                    DropdownMenuItem(
                        text = {
                            TextSemiBoldView(
                                theme = theme,
                                dimen = dimen,
                                text = item.name,
                                size = dimen.dimen_1_75,
                                fontColor = theme.black,
                            )
                        },
                        onClick = {
                            onClickOnMenuItem(item.id, item.name)
                        }
                    )

                }//end forEach

            }//end DropdownMenu

        }//emd IF

    }//end Column


}//end InputMenuFieldSection