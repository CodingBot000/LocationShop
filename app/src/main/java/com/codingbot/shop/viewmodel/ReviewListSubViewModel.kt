package com.codingbot.shop.viewmodel

import com.codingbot.shop.core.common.Logger
import com.codingbot.shop.domain.model.ProductData
import com.codingbot.shop.domain.model.ReviewData
import com.codingbot.shop.data.repository.RepositoryReview
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

data class ReviewListSubSubUiState(
    val list: List<ReviewData>? = null,
    val data: ProductData? = null
)

sealed interface ReviewListSubIntent {
    data class ReviewListSubInfoList(val list: List<ReviewData>): ReviewListSubIntent
    data class GetProductData(val data: ProductData): ReviewListSubIntent
}

@HiltViewModel
class ReviewListSubViewModel @Inject constructor(
    private val repositoryReview: RepositoryReview
)
    : BaseViewModel<ReviewListSubSubUiState, ReviewListSubIntent>(ReviewListSubSubUiState())
{
    val logger = Logger("EventDescViewModel")

    fun getReviewListSubListData(id: Int) {
        repositoryReview.getReviewDataListBySurgery(id)?.let { reviewList ->
            execute(ReviewListSubIntent.ReviewListSubInfoList(reviewList))
        }
    }

    override suspend fun ReviewListSubSubUiState.reduce(intent: ReviewListSubIntent): ReviewListSubSubUiState =
        when (intent) {
            is ReviewListSubIntent.ReviewListSubInfoList -> copy(list = intent.list)
            is ReviewListSubIntent.GetProductData -> copy(data = intent.data)
        }
}
