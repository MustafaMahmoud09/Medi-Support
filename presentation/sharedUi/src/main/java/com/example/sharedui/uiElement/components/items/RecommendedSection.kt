package com.example.sharedui.uiElement.components.items

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.sharedui.R
import com.example.sharedui.uiElement.components.composable.TextNormalStartView
import com.example.sharedui.uiElement.components.composable.TextNormalView
import com.example.sharedui.uiElement.components.composable.TextSemiBoldView
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme

@Composable
fun RecommendedSection(
    dimen: CustomDimen,
    theme: CustomTheme,
    recommendedText: String = stringResource(
        R.string.recommended_reading
    ),
    recommendedColor: Color = theme.black,
    recommendedSize: Float = dimen.dimen_2,
    equationText: String,
    equationSize: Float = dimen.dimen_2,
    equationColor: Color = theme.black,
    responseText: String,
    responseColor: Color = theme.black,
    responseSize: Float = dimen.dimen_2,
    modifier: Modifier = Modifier
) {

    //create container here
    ConstraintLayout(
        modifier = modifier
    ) {
        //create ids for components here
        val (recommendedId, equationId, responseId) = createRefs()

        //create recommender text here
        TextSemiBoldView(
            theme = theme,
            dimen = dimen,
            text = recommendedText,
            size = recommendedSize,
            fontColor = recommendedColor,
            modifier = Modifier
                .constrainAs(recommendedId) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    width = Dimension.fillToConstraints
                }
        )

        //create equation text here
        TextNormalView(
            theme = theme,
            dimen = dimen,
            text = equationText,
            size = equationSize,
            weigh = 300,
            fontColor = equationColor,
            textAlign = null,
            modifier = Modifier
                .constrainAs(equationId) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(
                        recommendedId.bottom,
                        dimen.dimen_2_25.dp
                    )
                    width = Dimension.fillToConstraints
                }
        )

        //create response text here
        TextNormalStartView(
            theme = theme,
            dimen = dimen,
            text = responseText,
            size = responseSize,
            fontColor = responseColor,
            modifier = Modifier
                .constrainAs(responseId) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(
                        equationId.bottom,
                        dimen.dimen_2_25.dp
                    )
                }
        )

    }//end ConstraintLayout

}//end RecommendedSection