package com.example.setting.uiElement.screens.about

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.setting.R
import com.example.setting.uiElement.components.items.DeveloperSection
import com.example.setting.uiElement.components.items.EmailIconSection
import com.example.setting.uiElement.components.items.FeatureSection
import com.example.sharedui.uiElement.components.composable.IconButtonView
import com.example.sharedui.uiElement.components.composable.LineView
import com.example.sharedui.uiElement.components.composable.MultiColorTextView
import com.example.sharedui.uiElement.components.composable.TextBoldView
import com.example.sharedui.uiElement.components.items.IconTextSection
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.dimens.MediSupportAppDimen
import com.example.sharedui.uiElement.style.robotoBold
import com.example.sharedui.uiElement.style.theme.CustomTheme
import com.example.sharedui.uiElement.style.theme.MediSupportAppTheme


@Composable
internal fun AboutScreen(popAboutDestination: () -> Unit) {

    AboutContent(
        onClickBack = popAboutDestination
    )
}//end AboutScreen

@Composable
private fun AboutContent(
    theme: CustomTheme = MediSupportAppTheme(),
    dimen: CustomDimen = MediSupportAppDimen(),
    onClickBack: () -> Unit
) {

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = theme.background
            )
    ) {
        val (backButton, title, content) = createRefs()

        IconButtonView(
            dimen = dimen,
            theme = theme,
            onClick = onClickBack,
            modifier = Modifier
                .constrainAs(backButton) {
                    start.linkTo(
                        parent.start,
                        dimen.dimen_2.dp
                    )
                    top.linkTo(
                        parent.top,
                        dimen.dimen_4.dp
                    )
                }//end constrainAs
        )

        TextBoldView(
            theme = theme,
            dimen = dimen,
            text = stringResource(
                id = com.example.sharedui.R.string.about_us
            ),
            size = dimen.dimen_2_25,
            modifier = Modifier
                .constrainAs(title) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(
                        parent.top,
                        dimen.dimen_4.dp
                    )
                }//end constrainAs
        )

        LazyColumn(
            modifier = Modifier
                .constrainAs(content) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(
                        title.bottom,
                        dimen.dimen_2.dp
                    )
                    bottom.linkTo(parent.bottom)
                    width = Dimension.fillToConstraints
                    height = Dimension.fillToConstraints
                },
            contentPadding = PaddingValues(
                top = dimen.dimen_2.dp,
                bottom = dimen.dimen_2.dp,
            ),
            verticalArrangement = Arrangement.spacedBy(
                dimen.dimen_2.dp
            )
        ) {

            item(
                key = 1
            ) {

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = dimen.dimen_4.dp
                        )
                ) {

                    MultiColorTextView(
                        dimen = dimen,
                        theme = theme,
                        parentText = stringResource(
                            id = com.example.sharedui.R.string.about_info
                        ),
                        subTexts = arrayOf(
                            stringResource(
                                com.example.sharedui.R.string.medisupport
                            )
                        ),
                        subTextsColors = arrayOf(theme.redDark),
                        parentTextColor = theme.hintIconBottom,
                        modifier = Modifier
                            .fillMaxWidth()
                    )

                }//end Box

            }//end item

            item(
                key = 2
            ) {

                LineView(
                    dimen = dimen,
                    theme = theme,
                    color = theme.grayLight,
                    modifier = Modifier
                        .fillMaxWidth()
                )

            }//end item

            item(
                key = 3
            ) {

                TextBoldView(
                    theme = theme,
                    dimen = dimen,
                    text = stringResource(
                        com.example.sharedui.R.string.our_features
                    ),
                    size = dimen.dimen_1_75,
                    color = theme.redDark,
                    modifier = Modifier
                        .fillMaxWidth()
                )

            }//end item

            item(
                key = 4
            ) {

                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentPadding = PaddingValues(
                        horizontal = dimen.dimen_2.dp
                    ),
                    horizontalArrangement = Arrangement.spacedBy(
                        dimen.dimen_1_5.dp
                    )
                ) {

                    items(10) {

                        FeatureSection(
                            theme = theme,
                            dimen = dimen,
                            icon = painterResource(
                                id = R.drawable.test
                            ),
                            text = "Monitoring the health status of patients with hypertension and diabetes by entering some data about the disease and displaying the results in the form of diagrams. Additionally, determining the patient's BMI by entering their weight and height and providing medical advice to assist them.",
                            borderColor = theme.redDarkTR50
                        )

                    }//end items

                }//end LazyRow

            }//end item

            item(
                key = 5
            ) {

                LineView(
                    dimen = dimen,
                    theme = theme,
                    color = theme.grayLight,
                    modifier = Modifier
                        .fillMaxWidth()
                )

            }//end item

            item(
                key = 6
            ) {

                TextBoldView(
                    theme = theme,
                    dimen = dimen,
                    text = stringResource(
                        com.example.sharedui.R.string.our_developer
                    ),
                    size = dimen.dimen_1_75,
                    color = theme.redDark,
                    modifier = Modifier
                        .fillMaxWidth()
                )

            }//end item

            item(
                key = 7
            ) {

                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentPadding = PaddingValues(
                        horizontal = dimen.dimen_2.dp
                    ),
                    horizontalArrangement = Arrangement.spacedBy(
                        dimen.dimen_1_5.dp
                    )
                ) {

                    items(10) {

                        DeveloperSection(
                            dimen = dimen,
                            theme = theme,
                            borderColor = theme.redDarkTR50,
                            teamName = "Front-end Team",
                            developers = listOf("Nada Yakout", "Nada Sobhy", "Hossam Ezat")
                        )

                    }//end items

                }//end LazyRow

            }//end item

            item(
                key = 8
            ) {

                LineView(
                    dimen = dimen,
                    theme = theme,
                    color = theme.grayLight,
                    modifier = Modifier
                        .fillMaxWidth()
                )

            }//end item

            item(
                key = 9
            ) {

                Box(
                    modifier = Modifier
                        .padding(
                            horizontal = dimen.dimen_2.dp
                        )
                ) {

                    IconTextSection(
                        theme = theme,
                        dimen = dimen,
                        text = "+000-213-553-22",
                        fontFamily = robotoBold,
                        fontSize = dimen.dimen_1_75,
                        fontColor = theme.black,
                        icon = painterResource(
                            id = com.example.sharedui.R.drawable.phone
                        ),
                        iconSize = dimen.dimen_1_75,
                        iconTint = theme.green2BEF4A
                    )
                }

            }//end item

            item(
                key = 10
            ) {

                Box(
                    modifier = Modifier
                        .padding(
                            horizontal = dimen.dimen_2.dp
                        )
                ) {

                    EmailIconSection(
                        theme = theme,
                        dimen = dimen,
                        text = "MediaSupport@gmail.com",
                        fontFamily = robotoBold,
                        fontSize = dimen.dimen_1_75,
                        icon = painterResource(
                            id = com.example.sharedui.R.drawable.email
                        ),
                        iconSize = dimen.dimen_1_75,
                        iconTint = theme.black
                    )
                }

            }//end item

        }//end child Lazy Column

    }//end ConstraintLayout

}//end AboutContent