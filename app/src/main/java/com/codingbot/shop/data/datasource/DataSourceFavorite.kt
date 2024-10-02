package com.codingbot.shop.data.datasource

import com.codingbot.shop.domain.model.EventData
import com.codingbot.shop.domain.model.HomeBannerData
import com.codingbot.shop.domain.model.LocationChipData
import com.codingbot.shop.domain.model.ProductData
import com.codingbot.shop.domain.model.ProductDetailData
import com.codingbot.shop.domain.model.ReviewData
import com.codingbot.shop.domain.model.SurgeryData

interface DataSourceFavorite {
    fun getFavoriteStoredDatas(): List<ProductData>
    fun getFavoriteStoredData(id: Int): ProductData?
    fun addFavoriteStoredData(id: Int)
    fun addFavoriteStoredData(productData: ProductData)
    fun removeFavoriteStoredData(id: Int): Boolean
}