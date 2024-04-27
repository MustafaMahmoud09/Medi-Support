package com.example.blood.sugar.pagination

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.blood.sugar.domain.model.SimpleBloodSugarModel
import com.example.blood.sugar.domain.usecase.declarations.IGetPageHistoryRecordsUseCase

class BloodSugarDataSource(
    private val getPageHistoryRecordsUseCase: IGetPageHistoryRecordsUseCase,
) : PagingSource<Int, SimpleBloodSugarModel>() {

    override suspend fun load(
        params: LoadParams<Int>
    ): LoadResult<Int, SimpleBloodSugarModel> {

        return try {

            //get number of current page
            val currentPageNumber = params.key ?: 1

            //get current page size
            val pageSize = params.loadSize

            //get current page data here
            val data = getPageHistoryRecordsUseCase(
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
        state: PagingState<Int, SimpleBloodSugarModel>
    ): Int? = null

}//end RemindersDataSource