package com.codingbot.shop.data.repository

import com.codingbot.shop.data.datasource.DataSourceFavorite
import com.codingbot.shop.domain.model.ProductData
import javax.inject.Inject

class RepositoryFavoriteImpl @Inject constructor(
    private val dataSource: DataSourceFavorite
): RepositoryFavorite {
    override fun removeFavoriteStoredData(id: Int): Boolean {
        return dataSource.removeFavoriteStoredData(id)
    }

    override fun getFavoriteStoredDatas(): List<ProductData> {
        return dataSource.getFavoriteStoredDatas()
    }

    override fun getFavoriteStoredData(id: Int): ProductData? {
        return dataSource.getFavoriteStoredData(id)
    }

    override fun addFavoriteStoredData(id: Int) {
        return dataSource.addFavoriteStoredData(id)
    }

    override fun addFavoriteStoredData(productData: ProductData) {
        return dataSource.addFavoriteStoredData(productData)
    }
}