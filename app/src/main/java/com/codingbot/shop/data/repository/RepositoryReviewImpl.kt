package com.codingbot.shop.data.repository

import com.codingbot.shop.data.datasource.DataSourceCommon
import com.codingbot.shop.data.datasource.DataSourceReview
import com.codingbot.shop.domain.model.EventData
import com.codingbot.shop.domain.model.HomeBannerData
import com.codingbot.shop.domain.model.LocationChipData
import com.codingbot.shop.domain.model.ProductData
import com.codingbot.shop.domain.model.ProductDetailData
import com.codingbot.shop.domain.model.ReviewData
import com.codingbot.shop.domain.model.SurgeryData
import javax.inject.Inject

class RepositoryReviewImpl @Inject constructor(
    private val dataSource: DataSourceReview
): RepositoryReview {
    override fun getReviewDataListBySurgery(surgeryId: Int): List<ReviewData> {
        return dataSource.getReviewDataListBySurgery(surgeryId)
    }
}