package com.example.bmi.pagination

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.bmi.domain.model.SimpleBMIModel
import com.example.bmi.domain.usecase.declarations.IGetPageHistoryRecordsUseCase

class BMIDataSource(
    private val getPageHistoryRecordsUseCase: IGetPageHistoryRecordsUseCase,
) : PagingSource<Int, SimpleBMIModel>() {

    override suspend fun load(
        params: LoadParams<Int>
    ): LoadResult<Int, SimpleBMIModel> {

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
                nextKey = if (data.lastPageNumber <= currentPageNumber) null else currentPageNumber.plus(
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
        state: PagingState<Int, SimpleBMIModel>
    ): Int? = null

}//end RemindersDataSource