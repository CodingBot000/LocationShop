package com.codingbot.shop.data.datasource

import com.codingbot.shop.domain.model.ReviewData

interface DataSourceReview {
    fun getReviewDataListBySurgery(surgeryId: Int): List<ReviewData>
}