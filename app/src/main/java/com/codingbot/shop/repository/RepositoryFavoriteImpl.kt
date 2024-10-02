package com.codingbot.shop.repository

import com.codingbot.shop.datasource.DataSourceFavorite
import com.codingbot.shop.domain.model.ProductData
import javax.inject.Inject

class RepositoryFavoriteImpl @Inject constructor(
    val dataSourceFavorite: DataSourceFavorite
): RepositoryFavorite {
    override fun removeFavoriteStoredData(id: Int): Boolean {
        return dataSourceFavorite.removeFavoriteStoredData(id)
    }

    override fun getFavoriteStoredDatas(): List<ProductData> {
        return dataSourceFavorite.getFavoriteStoredDatas()
    }

    override fun getFavoriteStoredData(id: Int): ProductData? {
        return dataSourceFavorite.getFavoriteStoredData(id)
    }

    override fun addFavoriteStoredData(id: Int) {
        return dataSourceFavorite.addFavoriteStoredData(id)
    }

    override fun addFavoriteStoredData(productData: ProductData) {
        return dataSourceFavorite.addFavoriteStoredData(productData)
    }
}