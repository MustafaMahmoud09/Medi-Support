package com.example.online.booking.pagination

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.online.booking.domain.model.OnlineDoctorModel
import com.example.online.booking.domain.usecase.declarations.IGetTotalOnlineDoctorsUseCase

class TotalOnlineDoctorDataSource(
    private val getTotalOnlineDoctorsUseCase: IGetTotalOnlineDoctorsUseCase,
) : PagingSource<Int, OnlineDoctorModel>() {

    override suspend fun load(
        params: LoadParams<Int>
    ): LoadResult<Int, OnlineDoctorModel> {

        return try {

            //get number of current page
            val currentPageNumber = params.key ?: 1

            //get current page size
            val pageSize = params.loadSize

            //get current page data here
            val data = getTotalOnlineDoctorsUseCase(
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
        state: PagingState<Int, OnlineDoctorModel>
    ): Int? = state.anchorPosition

}//end RemindersDataSource