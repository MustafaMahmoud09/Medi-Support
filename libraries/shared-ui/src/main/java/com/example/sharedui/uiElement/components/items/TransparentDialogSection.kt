@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.sharedui.uiElement.components.items

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.window.DialogProperties
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme

@Composable
fun TransparentDialogSection(
    onDismissRequest: () -> Unit,
    dimen: CustomDimen,
    theme: CustomTheme,
    logo: Painter,
    tint: Color,
    modifier: Modifier = Modifier
) {


    AlertDialog(
        onDismissRequest = onDismissRequest,
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        ),
        modifier = modifier
    ) {

        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = theme.transparent
                )
        ) {
            val (success) = createRefs()
            val guideFromStart25P = createGuidelineFromStart(.25f)
            val guideFromEnd25P = createGuidelineFromEnd(.25f)

            Icon(
                painter = logo,
                contentDescription = "",
                tint = tint,
                modifier = Modifier
                    .constrainAs(success) {
                        start.linkTo(guideFromStart25P)
                        end.linkTo(guideFromEnd25P)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        width = Dimension.fillToConstraints
                    }
                    .aspectRatio(1f)
            )

        }//end ConstraintLayout

    }//end Dialog

}//end DialogBackSection