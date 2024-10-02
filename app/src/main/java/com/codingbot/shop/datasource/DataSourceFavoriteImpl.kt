package com.codingbot.shop.datasource

import com.codingbot.shop.core.server.DumpServer
import com.codingbot.shop.domain.model.ProductData
import javax.inject.Inject

class DataSourceFavoriteImpl @Inject constructor(): DataSourceFavorite {

    override fun removeFavoriteStoredData(id: Int): Boolean {
        return DumpServer.removeFavoriteStoredData(id)
    }

    override fun getFavoriteStoredDatas(): List<ProductData> {
        return DumpServer.getFavoriteStoredDatas()
    }

    override fun getFavoriteStoredData(id: Int): ProductData? {
        return DumpServer.getFavoriteStoredData(id)
    }

    override fun addFavoriteStoredData(id: Int) {
        return DumpServer.addFavoriteStoredData(id)
    }

    override fun addFavoriteStoredData(productData: ProductData) {
        return DumpServer.addFavoriteStoredData(productData)
    }
}