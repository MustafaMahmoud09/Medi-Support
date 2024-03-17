package com.example.reminder.paginations

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.reminder.domaim.domain.model.reminder.ReminderPresentationModel
import com.example.reminder.domain.usecase.interfaces.IGetUserRemindersUseCase
import kotlinx.coroutines.flow.collectLatest

class RemindersDataSource(
    private val getUserRemindersUseCase: IGetUserRemindersUseCase,
) : PagingSource<Int, ReminderPresentationModel>() {

    override fun getRefreshKey(
        state: PagingState<Int, ReminderPresentationModel>
    ): Int? {

        return state.anchorPosition

    }//end getRefreshKey

    override suspend fun load(
        params: LoadParams<Int>
    ): LoadResult<Int, ReminderPresentationModel> {

        return try {

            //get number of current page
            val numberOfCurrentPage = params.key ?: 1

            //get current page size
            val pageSize = params.loadSize

            //get current page data here
            val data = getUserRemindersUseCase(
                page = numberOfCurrentPage,
                pageSize = pageSize
            )

            //return current page here
            LoadResult.Page(
                data = data,
                prevKey = if (numberOfCurrentPage == 1) null else numberOfCurrentPage.minus(1),
                nextKey = if (data.size < pageSize) null else numberOfCurrentPage.plus(1)
            )

        }//end try
        catch (ex: Exception) {

            //return exception error here
            LoadResult.Error(
                throwable = ex
            )

        }//end catch

    }//end load

}//end RemindersDataSource