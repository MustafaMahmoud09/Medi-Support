package com.example.article.pagination

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.article.domain.model.TitleArticleModel
import com.example.article.domain.usecase.declarations.IGetPageArticlesUseCase

class ArticleDataSource(
    private val getPageArticlesUseCase: IGetPageArticlesUseCase,
) : PagingSource<Int, TitleArticleModel>() {

    override suspend fun load(
        params: LoadParams<Int>
    ): LoadResult<Int, TitleArticleModel> {

        return try {

            //get number of current page
            val currentPageNumber = params.key ?: 1

            //get current page size
            val pageSize = params.loadSize

            //get current page data here
            val data = getPageArticlesUseCase(
                page = currentPageNumber,
                pageSize = pageSize
            )

            //return current page here
            LoadResult.Page(
                data = data.body ?: emptyList(),
                prevKey = if (currentPageNumber == 1) null else currentPageNumber.minus(1),
                nextKey = if (data.lastPageNumber == currentPageNumber) null else currentPageNumber.plus(1)
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
        state: PagingState<Int, TitleArticleModel>
    ): Int? {
        return state.anchorPosition
    }//end getRefreshKey

}//end RemindersDataSource