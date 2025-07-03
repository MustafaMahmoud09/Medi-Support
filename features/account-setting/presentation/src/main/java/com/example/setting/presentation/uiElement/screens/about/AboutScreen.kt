package com.example.setting.presentation.uiElement.screens.about

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
import com.example.setting.presentation.uiElement.components.items.DeveloperSection
import com.example.setting.presentation.uiElement.components.items.EmailIconSection
import com.example.setting.presentation.uiElement.components.items.FeatureSection
import com.example.sharedui.R
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
                        dimen.dimen_3.dp
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
                        dimen.dimen_3.dp
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

                    item(
                       key = 1
                    ) {

                        FeatureSection(
                            theme = theme,
                            dimen = dimen,
                            icon = painterResource(
                                id = com.example.setting.presentation.R.drawable.feature_one
                            ),
                            text = stringResource(R.string.feature_one),
                            borderColor = theme.redDarkTR50
                        )

                    }//end items

                    item(
                        key = 2
                    ) {

                        FeatureSection(
                            theme = theme,
                            dimen = dimen,
                            icon = painterResource(
                                id = com.example.setting.presentation.R.drawable.feature_one
                            ),
                            text = stringResource(R.string.feature_two),
                            borderColor = theme.redDarkTR50
                        )

                    }//end items

                    item(
                        key = 3
                    ) {

                        FeatureSection(
                            theme = theme,
                            dimen = dimen,
                            icon = painterResource(
                                id = com.example.setting.presentation.R.drawable.feature_one
                            ),
                            text = stringResource(R.string.feature_three),
                            borderColor = theme.redDarkTR50
                        )

                    }//end items

                    item(
                        key = 4
                    ) {

                        FeatureSection(
                            theme = theme,
                            dimen = dimen,
                            icon = painterResource(
                                id = com.example.setting.presentation.R.drawable.feature_one
                            ),
                            text = stringResource(R.string.feature_four),
                            borderColor = theme.redDarkTR50
                        )

                    }//end items

                    item(
                        key = 5
                    ) {

                        FeatureSection(
                            theme = theme,
                            dimen = dimen,
                            icon = painterResource(
                                id = com.example.setting.presentation.R.drawable.feature_one
                            ),
                            text = stringResource(R.string.feature_five),
                            borderColor = theme.redDarkTR50
                        )

                    }//end items

                    item(
                        key = 6
                    ) {

                        FeatureSection(
                            theme = theme,
                            dimen = dimen,
                            icon = painterResource(
                                id = com.example.setting.presentation.R.drawable.feature_one
                            ),
                            text = stringResource(R.string.feature_six),
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

                    item(
                        key = 1
                    ){

                        DeveloperSection(
                            dimen = dimen,
                            theme = theme,
                            borderColor = theme.redDarkTR50,
                            teamName = stringResource(R.string.mobile_team),
                            developers = listOf(stringResource(R.string.mustafa_mahmoud))
                        )

                    }//end item

                    item(
                        key = 2
                    ){

                        DeveloperSection(
                            dimen = dimen,
                            theme = theme,
                            borderColor = theme.redDarkTR50,
                            teamName = stringResource(R.string.ai_team),
                            developers = listOf(
                                stringResource(R.string.mustafa_mahmoud),
                                stringResource(R.string.alaa_ali),
                                stringResource(R.string.nada_yakut),
                                stringResource(R.string.hossam_ezzat)
                            )
                        )

                    }//end item

                    item(
                        key = 3
                    ){

                        DeveloperSection(
                            dimen = dimen,
                            theme = theme,
                            borderColor = theme.redDarkTR50,
                            teamName = stringResource(R.string.back_end_team),
                            developers = listOf(
                                stringResource(R.string.alaa_ali),
                                stringResource(R.string.asmaa_gamal),
                                stringResource(R.string.nada_saied)
                            )
                        )

                    }//end item

                    item(
                        key = 4
                    ){

                        DeveloperSection(
                            dimen = dimen,
                            theme = theme,
                            borderColor = theme.redDarkTR50,
                            teamName = stringResource(R.string.front_end_team),
                            developers = listOf(
                                stringResource(R.string.nada_yakut),
                                stringResource(R.string.hossam_ezzat),
                                stringResource(R.string.nada_sobhy),
                                stringResource(R.string.rofayda_mohammed)
                            )
                        )

                    }//end item

                    item(
                        key = 5
                    ){

                        DeveloperSection(
                            dimen = dimen,
                            theme = theme,
                            borderColor = theme.redDarkTR50,
                            teamName = stringResource(R.string.ui_ux_team),
                            developers = listOf(
                                stringResource(R.string.rokaia_shereet),
                                stringResource(R.string.sara_elbadry)
                            )
                        )

                    }//end item

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
                        text = "+20-102-581-48",
                        fontFamily = robotoBold,
                        fontSize = dimen.dimen_1_75,
                        fontColor = theme.black,
                        icon = painterResource(
                            id = R.drawable.phone
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
                            id = R.drawable.email
                        ),
                        iconSize = dimen.dimen_1_75,
                        iconTint = theme.black
                    )
                }

            }//end item

        }//end child Lazy Column

    }//end ConstraintLayout

}//end AboutContent