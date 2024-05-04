package com.example.offline.booking.pagination

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.offline.booking.domain.model.OfflineDoctorModel
import com.example.offline.booking.domain.usecase.declarations.IGetTotalOfflineDoctorsUseCase

class TotalOfflineDoctorDataSource(
    private val getTotalOfflineDoctorsUseCase: IGetTotalOfflineDoctorsUseCase,
) : PagingSource<Int, OfflineDoctorModel>() {

    override suspend fun load(
        params: LoadParams<Int>
    ): LoadResult<Int, OfflineDoctorModel> {

        return try {

            //get number of current page
            val currentPageNumber = params.key ?: 1

            //get current page size
            val pageSize = params.loadSize

            //get current page data here
            val data = getTotalOfflineDoctorsUseCase(
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
        state: PagingState<Int, OfflineDoctorModel>
    ): Int? = state.anchorPosition

}//end RemindersDataSource