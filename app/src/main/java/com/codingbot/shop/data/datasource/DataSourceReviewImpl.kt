package com.codingbot.shop.data.datasource

import com.codingbot.shop.core.server.DumpServer
import com.codingbot.shop.domain.model.EventData
import com.codingbot.shop.domain.model.HomeBannerData
import com.codingbot.shop.domain.model.LocationChipData
import com.codingbot.shop.domain.model.ProductData
import com.codingbot.shop.domain.model.ProductDetailData
import com.codingbot.shop.domain.model.ReviewData
import com.codingbot.shop.domain.model.SurgeryData
import javax.inject.Inject

class DataSourceReviewImpl @Inject constructor(): DataSourceReview {
    override fun getReviewDataListBySurgery(surgeryId: Int): List<ReviewData> {
        return DumpServer.getReviewDataListBySurgery(surgeryId)
    }
}