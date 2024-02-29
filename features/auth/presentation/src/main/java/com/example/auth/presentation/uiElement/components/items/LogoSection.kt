package com.example.auth.presentation.uiElement.components.items


import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.auth.presentation.R
import com.example.sharedui.uiElement.components.composable.TextNormalRedView
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme


@Composable
internal fun LogoSection(
    theme: CustomTheme,
    dimen: CustomDimen,
    modifier: Modifier = Modifier
) {

    ConstraintLayout(
        modifier = modifier
    ) {
        val (mediText, heartIcon, supportText) = createRefs()

        TextNormalRedView(
            theme = theme,
            dimen = dimen,
            text = stringResource(
                com.example.sharedui.R.string.medi
            ),
            size = dimen.dimen_4,
            modifier = Modifier
                .constrainAs(mediText) {
                    start.linkTo(parent.start)
                    bottom.linkTo(parent.bottom)
                    top.linkTo(parent.top)
                }
        )

        Icon(
            painter = painterResource(
                id = R.drawable.heart_icon
            ),
            contentDescription = "heart icon",
            modifier = Modifier
                .constrainAs(heartIcon) {
                    start.linkTo(
                        mediText.end,
                        dimen.dimen_0_5.dp
                    )
                    top.linkTo(mediText.top)
                    bottom.linkTo(mediText.bottom)
                    height = Dimension.fillToConstraints
                }
                .aspectRatio(.55f),
            tint = theme.redIcon
        )

        TextNormalRedView(
            theme = theme,
            dimen = dimen,
            text = stringResource(
                com.example.sharedui.R.string.support
            ),
            size = dimen.dimen_4,
            modifier = Modifier
                .constrainAs(supportText) {
                    bottom.linkTo(parent.bottom)
                    top.linkTo(parent.top)
                    start.linkTo(
                        heartIcon.end,
                        dimen.dimen_0_5.dp
                    )
                }
        )

    }//end ConstraintLayout

}//end LogoSection