package com.example.notification.pagination

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.notification.domain.model.NotificationModel
import com.example.notification.domain.usecase.declarations.IGetPageNotificationsUseCase

class NotificationDataSource(
    private val getPageNotificationsUseCase: IGetPageNotificationsUseCase,
) : PagingSource<Int, NotificationModel>() {

    override suspend fun load(
        params: LoadParams<Int>
    ): LoadResult<Int, NotificationModel> {

        return try {

            //get number of current page
            val currentPageNumber = params.key ?: 1

            //get current page size
            val pageSize = params.loadSize

            //get current page data here
            val data = getPageNotificationsUseCase(
                page = currentPageNumber
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
        state: PagingState<Int, NotificationModel>
    ): Int? = state.anchorPosition

}//end RemindersDataSource