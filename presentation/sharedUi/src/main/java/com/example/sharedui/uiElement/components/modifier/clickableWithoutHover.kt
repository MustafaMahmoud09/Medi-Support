package com.example.sharedui.uiElement.components.modifier

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier

@Stable
fun Modifier.clickableWithoutHover(
    onClick: () -> Unit
) = then(
    clickable(
        interactionSource = MutableInteractionSource(),
        indication = null
    ) {
        onClick()
    }//end clickable
)