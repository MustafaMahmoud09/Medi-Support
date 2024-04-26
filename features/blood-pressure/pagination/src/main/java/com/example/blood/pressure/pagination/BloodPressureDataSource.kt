package com.example.blood.pressure.pagination

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.blood.pressure.domain.model.SimpleBloodPressureModel
import com.example.blood.pressure.domain.usecase.declarations.IGetPageHistoryRecordUseCase

class BloodPressureDataSource(
    private val getPageHistoryRecordUseCase: IGetPageHistoryRecordUseCase,
) : PagingSource<Int, SimpleBloodPressureModel>() {

    override suspend fun load(
        params: LoadParams<Int>
    ): LoadResult<Int, SimpleBloodPressureModel> {

        return try {

            //get number of current page
            val currentPageNumber = params.key ?: 1

            //get current page size
            val pageSize = params.loadSize

            //get current page data here
            val data = getPageHistoryRecordUseCase(
                page = currentPageNumber,
                pageSize = 10
            )

            //return current page here
            LoadResult.Page(
                data = data.body ?: emptyList(),
                prevKey = if (currentPageNumber == 1) null else currentPageNumber.minus(1),
                nextKey = if (data.lastPageNumber == currentPageNumber) null else currentPageNumber.plus(
                    1
                )
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
        state: PagingState<Int, SimpleBloodPressureModel>
    ): Int? = null

}//end RemindersDataSource