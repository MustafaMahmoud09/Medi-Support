package com.example.online.booking.pagination

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.online.booking.domain.model.OnlineBookingModel
import com.example.online.booking.domain.usecase.declarations.IGetPageOnlineBookingsUseCase

class OnlineBookingDataSource(
    private val getPageOnlineBookingsUseCase: IGetPageOnlineBookingsUseCase,
) : PagingSource<Int, OnlineBookingModel>() {

    override suspend fun load(
        params: LoadParams<Int>
    ): LoadResult<Int, OnlineBookingModel> {

        return try {

            //get number of current page
            val currentPageNumber = params.key ?: 1

            //get current page size
            val pageSize = params.loadSize

            //get current page data here
            val data = getPageOnlineBookingsUseCase(
                page = currentPageNumber,
                pageSize = 10
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
        state: PagingState<Int, OnlineBookingModel>
    ): Int? = state.anchorPosition

}//end RemindersDataSource