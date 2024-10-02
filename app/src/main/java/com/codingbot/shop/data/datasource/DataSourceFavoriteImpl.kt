package com.codingbot.shop.data.datasource

import com.codingbot.shop.core.server.DumpServer
import com.codingbot.shop.domain.model.ProductData
import javax.inject.Inject

class DataSourceFavoriteImpl @Inject constructor(): DataSourceFavorite {

    override suspend fun removeFavoriteStoredData(id: Int): Boolean {
        return DumpServer.removeFavoriteStoredData(id)
    }

    override suspend fun getFavoriteStoredDatas(): List<ProductData> {
        return DumpServer.getFavoriteStoredDatas()
    }

    override suspend fun getFavoriteStoredData(id: Int): ProductData? {
        return DumpServer.getFavoriteStoredData(id)
    }

    override suspend fun addFavoriteStoredData(id: Int) {
        return DumpServer.addFavoriteStoredData(id)
    }

    override suspend fun addFavoriteStoredData(productData: ProductData) {
        return DumpServer.addFavoriteStoredData(productData)
    }
}