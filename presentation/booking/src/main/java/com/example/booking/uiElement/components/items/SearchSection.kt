package com.example.booking.uiElement.components.items

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.sharedui.R
import com.example.sharedui.uiElement.components.composable.BasicTextFieldView
import com.example.sharedui.uiElement.components.composable.TextSemiBoldView
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme

@Composable
internal fun SearchSection(
    dimen: CustomDimen,
    theme: CustomTheme,
    key: String,
    hint: String,
    hintColor: Color = theme.grayDark646464,
    icon: Painter = painterResource(
        id = R.drawable.search_icon
    ),
    iconTint: Color = theme.grayDark646464,
    onChange: (String) -> Unit,
    height: Float = dimen.dimen_5_5,
    shape: Shape = RoundedCornerShape(
        percent = 90
    ),
    borderWidth: Float = dimen.dimen_0_125,
    borderColor: Color = theme.grayC1C7CD,
    onFocus: MutableState<Boolean>,
    focusRequester: FocusRequester,
    modifier: Modifier = Modifier,
) {

    //create container here
    ConstraintLayout(
        modifier = modifier
            .height(
                height = height.dp
            )
            .clip(
                shape = shape
            )
            .border(
                width = borderWidth.dp,
                color = borderColor,
                shape = shape
            )
    ) {
        val (searchIconId, keyId, hintId) = createRefs()

        //create search icon here
        Icon(
            painter = icon,
            contentDescription = "search icon",
            tint = iconTint,
            modifier = Modifier
                .size(
                    size = dimen.dimen_2_25.dp
                )
                .constrainAs(searchIconId) {
                    start.linkTo(
                        parent.start,
                        dimen.dimen_2.dp
                    )
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
        )

        //check key empty or no
        //if empty draw hint
        if (key.isEmpty()) {

            //create hint here
            TextSemiBoldView(
                theme = theme,
                dimen = dimen,
                text = hint,
                size = dimen.dimen_2,
                fontColor = hintColor,
                maxLines = 1,
                modifier = Modifier
                    .constrainAs(hintId) {
                        start.linkTo(
                            searchIconId.end,
                            dimen.dimen_1.dp
                        )
                        end.linkTo(
                            parent.end,
                            dimen.dimen_1.dp
                        )
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        width = Dimension.fillToConstraints
                    }
            )

        }//end if

        //create text input here
        BasicTextFieldView(
            dimen = dimen,
            theme = theme,
            input = key,
            onChange = onChange,
            textColor = hintColor,
            modifier = Modifier
                .constrainAs(keyId) {
                    start.linkTo(
                        searchIconId.end,
                        dimen.dimen_1.dp
                    )
                    end.linkTo(
                        parent.end,
                        dimen.dimen_1.dp
                    )
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    width = Dimension.fillToConstraints
                }
                .onFocusChanged {
                    onFocus.value = it.isFocused
                }
                .focusRequester(focusRequester)
        )

    }//end ConstraintLayout

}//end SearchSection