package com.codingbot.shop.data.datasource

import com.codingbot.shop.domain.model.EventData
import com.codingbot.shop.domain.model.HomeBannerData
import com.codingbot.shop.domain.model.LocationChipData
import com.codingbot.shop.domain.model.ProductData
import com.codingbot.shop.domain.model.ProductDetailData
import com.codingbot.shop.domain.model.ReviewData
import com.codingbot.shop.domain.model.SurgeryData

interface DataSourceFavorite {
    suspend fun getFavoriteStoredDatas(): List<ProductData>
    suspend fun getFavoriteStoredData(id: Int): ProductData?
    suspend fun addFavoriteStoredData(id: Int)
    suspend fun addFavoriteStoredData(productData: ProductData)
    suspend fun removeFavoriteStoredData(id: Int): Boolean
}