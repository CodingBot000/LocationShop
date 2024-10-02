package com.codingbot.shop.data.repository

import com.codingbot.shop.domain.model.ProductData

interface RepositoryFavorite {
    fun getFavoriteStoredDatas(): List<ProductData>
    fun getFavoriteStoredData(id: Int): ProductData?
    fun addFavoriteStoredData(id: Int)
    fun addFavoriteStoredData(productData: ProductData)
    fun removeFavoriteStoredData(id: Int): Boolean

}