package com.example.chat.pagination

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.chat.domain.model.MessageModel
import com.example.chat.domain.usecase.declarations.IGetPageMessageUseCase
import kotlinx.coroutines.delay
import java.util.LinkedList

class TotalChatMessagesDataSource(
    private val getPageMessageUseCase: IGetPageMessageUseCase,
    private val doctorId: Int
) : PagingSource<Int, MessageModel>() {

    override suspend fun load(
        params: LoadParams<Int>
    ): LoadResult<Int, MessageModel> {

        return try {

            //get number of current page
            val currentPageNumber = params.key ?: 1


            //get current page size
            val pageSize = params.loadSize

            //get current page data here
            val data = getPageMessageUseCase(
                page = currentPageNumber,
                perPage = 10,
                doctorId = doctorId
            )

            val temp = LinkedList<MessageModel>()

            for (count in 0 until (data.body?.size ?: 0)) {
                temp.add(data.body!![data.body!!.size - (count + 1)])
            }//end for

            //return current page here
            LoadResult.Page(
                data = temp,
                prevKey = if (data.lastPageNumber <= currentPageNumber) null else currentPageNumber.plus(1),
                nextKey = if (currentPageNumber == 1) null else currentPageNumber.minus(1),
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
        state: PagingState<Int, MessageModel>
    ): Int? = state.anchorPosition

}//end RemindersDataSource