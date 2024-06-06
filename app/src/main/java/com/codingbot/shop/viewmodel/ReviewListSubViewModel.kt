package com.codingbot.shop.viewmodel

import com.codingbot.shop.core.common.Logger
import com.codingbot.shop.core.server.DumpServer
import com.codingbot.shop.core.server.DumpServer.eventDataList
import com.codingbot.shop.core.server.DumpServer.productDatasOrigin
import com.codingbot.shop.core.server.DumpServer.reviewDataList
import com.codingbot.shop.domain.model.EventData
import com.codingbot.shop.domain.model.ProductData
import com.codingbot.shop.domain.model.ReviewData
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
class ReviewListSubViewModel @Inject constructor()
    : BaseViewModel<ReviewListSubSubUiState, ReviewListSubIntent>(ReviewListSubSubUiState())
{
    val logger = Logger("EventDescViewModel")

    fun getReviewListSubListData(id: Int) {
//        val list = mutableListOf<ProductData>()
//        reviewDataList?.let {reviewList ->
//
//        }
        val list = reviewDataList


//                productData.surgeries
//                    .any { surgeryId -> surgeryId == id }
//            }?.toMutableList() ?: mutableListOf()
//
//            productDatasOrigin?.let { productList ->
//                productList.map { productData ->
//                    if (productData.id == id) {
//                        list.add(productData)
//                    }
//                }
//             }
        DumpServer.getReviewDataListBySurgery(id)?.let { reviewList ->
            execute(ReviewListSubIntent.ReviewListSubInfoList(reviewList))
        }
//        reviewDataList?.let { list ->
//            execute(ReviewListSubIntent.ReviewListSubInfoList(list))
//        }
    }

    fun getProductData(id: Int) {

    }

    override suspend fun ReviewListSubSubUiState.reduce(intent: ReviewListSubIntent): ReviewListSubSubUiState =
        when (intent) {
            is ReviewListSubIntent.ReviewListSubInfoList -> copy(list = intent.list)
            is ReviewListSubIntent.GetProductData -> copy(data = intent.data)
        }
}
