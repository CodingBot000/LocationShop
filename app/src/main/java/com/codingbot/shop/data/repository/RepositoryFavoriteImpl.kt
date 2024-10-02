package com.codingbot.shop.data.repository

import com.codingbot.shop.data.datasource.DataSourceFavorite
import com.codingbot.shop.domain.model.ProductData
import javax.inject.Inject

class RepositoryFavoriteImpl @Inject constructor(
    private val dataSource: DataSourceFavorite
): RepositoryFavorite {
    override suspend fun removeFavoriteStoredData(id: Int): Boolean {
        return dataSource.removeFavoriteStoredData(id)
    }

    override suspend fun getFavoriteStoredDatas(): List<ProductData> {
        return dataSource.getFavoriteStoredDatas()
    }

    override suspend fun getFavoriteStoredData(id: Int): ProductData? {
        return dataSource.getFavoriteStoredData(id)
    }

    override suspend fun addFavoriteStoredData(id: Int) {
        return dataSource.addFavoriteStoredData(id)
    }

    override suspend fun addFavoriteStoredData(productData: ProductData) {
        return dataSource.addFavoriteStoredData(productData)
    }
}