package com.example.chat.pagination

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.chat.domain.model.ChatModel
import com.example.chat.domain.usecase.declarations.IGetPageChatsUseCase

class TotalChatDataSource(
    private val getPageChatUseCase: IGetPageChatsUseCase,
) : PagingSource<Int, ChatModel>() {

    override suspend fun load(
        params: LoadParams<Int>
    ): LoadResult<Int, ChatModel> {

        return try {

            //get number of current page
            val currentPageNumber = params.key ?: 1

            //get current page size
            val pageSize = params.loadSize

            //get current page data here
            val data = getPageChatUseCase(
                page = currentPageNumber,
                perPage = 10
            )

            //return current page here
            LoadResult.Page(
                data = data.body ?: emptyList(),
                prevKey = if (currentPageNumber == 1) null else currentPageNumber.minus(1),
                nextKey = if (data.lastPageNumber <= currentPageNumber) null else currentPageNumber.plus(1)
            )

        }//end try
        catch (ex: Exception) {

            //return exception error here
            LoadResult.Error(
                throwable = ex
            )

        }//end catch

    }//end load

    override fun getRefreshKey(
        state: PagingState<Int, ChatModel>
    ): Int? = state.anchorPosition

}//end RemindersDataSource