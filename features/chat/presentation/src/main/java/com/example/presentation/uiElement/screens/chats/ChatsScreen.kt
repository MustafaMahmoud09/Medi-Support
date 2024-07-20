@file:OptIn(ExperimentalFoundationApi::class)

package com.example.presentation.uiElement.screens.chats

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.chat.domain.model.ChatModel
import com.example.presentation.uiElement.components.items.ChatSection
import com.example.presentation.uiState.state.ChatsUiState
import com.example.presentation.uiState.viewModel.ChatsViewModel
import com.example.sharedui.R
import com.example.sharedui.uiElement.components.items.HeaderSection
import com.example.sharedui.uiElement.components.modifier.appDefaultContainer
import com.example.sharedui.uiElement.screen.BaseScreen
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.dimens.MediSupportAppDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme
import com.example.sharedui.uiElement.style.theme.MediSupportAppTheme
import kotlin.reflect.KFunction0

@Composable
internal fun ChatsScreen(
    viewModel: ChatsViewModel = hiltViewModel(),
    popChatNavGraph: () -> Unit,
    navigateToChatDestination: (Int) -> Unit
) {

    //collect screen state here
    val state = viewModel.state.collectAsState()

    ChatsContent(
        onClickOnBackButton = popChatNavGraph,
        onClickOnChat = navigateToChatDestination,
        uiState = state.value,
        chatsStatus = state.value.totalChatStatus?.collectAsLazyPagingItems(),
        chatsBackupStatus = state.value.chatsBackup?.collectAsLazyPagingItems(),
        onChatsBackupCreated = viewModel::onChatsBackupCreated
    )
}//end ChatsScreen

@Composable
private fun ChatsContent(
    dimen: CustomDimen = MediSupportAppDimen(),
    theme: CustomTheme = MediSupportAppTheme(),
    onClickOnBackButton: () -> Unit,
    onClickOnChat: (Int) -> Unit,
    uiState: ChatsUiState,
    chatsStatus: LazyPagingItems<ChatModel>?,
    chatsBackupStatus: LazyPagingItems<ChatModel>?,
    onChatsBackupCreated: KFunction0<Unit>
) {

    //create base screen to define navigation and status color
    BaseScreen(
        navigationColor = theme.background,
        statusColor = theme.background
    ) {

        //create container here
        ConstraintLayout(
            modifier = Modifier
                .appDefaultContainer(
                    color = theme.background
                )
        ) {
            //create ids for screen components here
            val (headerId, chatsId) = createRefs()

            //create header here
            HeaderSection(
                dimen = dimen,
                theme = theme,
                onClickOnBackButton = onClickOnBackButton,
                title = stringResource(
                    id = R.string.chat
                ),
                modifier = Modifier
                    .constrainAs(headerId) {
                        start.linkTo(
                            parent.start,
                            dimen.dimen_2.dp
                        )
                        end.linkTo(parent.end)
                        top.linkTo(
                            parent.top,
                            dimen.dimen_3.dp
                        )

                        width = Dimension.fillToConstraints
                    }
            )

            //if loading
            if (
                chatsStatus?.loadState?.refresh !is LoadState.NotLoading &&
                chatsBackupStatus?.loadState?.refresh !is LoadState.NotLoading
            ) {

                //create chats column here
                LazyColumn(
                    modifier = Modifier
                        .constrainAs(chatsId) {
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            bottom.linkTo(parent.bottom)
                            top.linkTo(
                                headerId.bottom,
                                dimen.dimen_2.dp
                            )
                            width = Dimension.fillToConstraints
                            height = Dimension.fillToConstraints
                        },
                    contentPadding = PaddingValues(
                        top = dimen.dimen_0_5.dp,
                        bottom = dimen.dimen_1_5.dp,
                        start = dimen.dimen_2_25.dp,
                        end = dimen.dimen_2_25.dp
                    ),
                    verticalArrangement = Arrangement.spacedBy(
                        space = dimen.dimen_1_5.dp
                    )
                ) {

                    //create chat items
                    items(
                        count = 10
                    ) {

                        //create single chat here
                        ChatSection(
                            dimen = dimen,
                            theme = theme,
                            onClick = {},
                            chat = uiState.chatPlaceHolder,
                            placeHolderState = true,
                            modifier = Modifier
                                .fillMaxWidth()
                        )

                    }//end items

                }//end LazyColumn

            }//end AnimatedVisibility


            //if not loading
            if (
                chatsStatus?.loadState?.refresh is LoadState.NotLoading ||
                chatsBackupStatus?.loadState?.refresh is LoadState.NotLoading
            ) {

                //create chats column here
                LazyColumn(
                    modifier = Modifier
                        .constrainAs(chatsId) {
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            bottom.linkTo(parent.bottom)
                            top.linkTo(
                                headerId.bottom,
                                dimen.dimen_2.dp
                            )
                            width = Dimension.fillToConstraints
                            height = Dimension.fillToConstraints
                        },
                    contentPadding = PaddingValues(
                        top = dimen.dimen_0_5.dp,
                        bottom = dimen.dimen_1_5.dp,
                        start = dimen.dimen_2_25.dp,
                        end = dimen.dimen_2_25.dp
                    ),
                    verticalArrangement = Arrangement.spacedBy(
                        space = dimen.dimen_1_5.dp
                    )
                ) {

                    val chats = if (chatsStatus?.loadState?.refresh is LoadState.NotLoading) {
                        chatsStatus
                    }//end if
                    else {
                        chatsBackupStatus
                    }//end else

                    //create chat items
                    chats?.let {
                        items(
                            count = it.itemCount,
                            key = {chats[it]!!.doctorId}
                        ) {

                            //create single chat here
                            chats[it]?.let { it1 ->
                                ChatSection(
                                    dimen = dimen,
                                    theme = theme,
                                    onClick = { onClickOnChat(it1.doctorId.toInt()) },
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .animateItemPlacement(),
                                    chat = it1,
                                )
                            }

                        }
                    }//end items

                }//end LazyColumn

            }//end Animated

        }//end ConstraintLayout

    }//end BaseScreen

    LaunchedEffect(
        key1 = chatsStatus?.loadState?.refresh
    ) {

        if (chatsStatus?.loadState?.refresh is LoadState.NotLoading) {
            onChatsBackupCreated()
        }//end if

    }//end LaunchedEffect

}//end ChatsContent