package com.codingbot.shop.data.repository

import com.codingbot.shop.domain.model.ProductData

interface RepositoryFavorite {
    suspend fun getFavoriteStoredDatas(): List<ProductData>
    suspend fun getFavoriteStoredData(id: Int): ProductData?
    suspend fun addFavoriteStoredData(id: Int)
    suspend fun addFavoriteStoredData(productData: ProductData)
    suspend fun removeFavoriteStoredData(id: Int): Boolean

}