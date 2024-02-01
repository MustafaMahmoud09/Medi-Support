package com.example.sharedui.uiElement.components.items

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.sharedui.uiElement.components.composable.IconButtonView
import com.example.sharedui.uiElement.components.composable.TextBoldView
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme

@Composable
fun HeaderSection(
    dimen: CustomDimen,
    theme: CustomTheme,
    onClickOnBackButton: () -> Unit,
    backButtonTint: Color = theme.black,
    title: String,
    titleSize: Float = dimen.dimen_2_25,
    titleColor: Color = theme.black,
    modifier: Modifier
) {

    //create container here
    ConstraintLayout(
        modifier = modifier
    ) {
        //create ids for components here
        val (backButtonId, titleId) = createRefs()

        //create back button here
        IconButtonView(
            dimen = dimen,
            theme = theme,
            onClick = onClickOnBackButton,
            tint = backButtonTint,
            modifier = Modifier
                .constrainAs(backButtonId) {
                    start.linkTo(parent.start)
                }//end constrainAs
        )

        //create title here
        TextBoldView(
            theme = theme,
            dimen = dimen,
            text = title,
            size = titleSize,
            color = titleColor,
            modifier = Modifier
                .constrainAs(titleId) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }//end constrainAs
        )

    }//end ConstraintLayout

}//end HeaderSection