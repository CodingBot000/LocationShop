package com.codingbot.shop.viewmodel

import com.codingbot.shop.core.common.Logger
import com.codingbot.shop.domain.model.ProductData
import com.codingbot.shop.domain.model.ProductDetailData
import com.codingbot.shop.domain.model.ProductDetailDescData
import com.codingbot.shop.data.repository.RepositoryCommon
import com.codingbot.shop.data.repository.RepositoryFavorite
import com.codingbot.shop.data.repository.RepositoryProductData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

data class DetailUiState(
    val productData: ProductData? = null,
    val detailData: ProductDetailData? = null,
    val detailDescData: ProductDetailDescData? = null,
    val isFavorite: Boolean = false
)

sealed interface DetailIntent {

    data class DetailData(val productData: ProductData?, val detailData: ProductDetailData?): DetailIntent
    data class FavoriteState(val isFavorite: Boolean): DetailIntent
}

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repositoryProductData: RepositoryProductData,
    private val repositoryFavorite: RepositoryFavorite
)
    : BaseViewModel<DetailUiState, DetailIntent>(DetailUiState())
{
    val logger = Logger("DetailViewModel")

    fun getDetailData(id: Int) {
        println("getDetailData: $id")
        val productData = repositoryProductData.getProductData(id)
        val detailData = repositoryProductData.getDetailDatasOrigin(id)

        if (productData == null || detailData == null)
            return

        execute(DetailIntent.DetailData(productData, detailData))
        getFavoriteState(id)


    }

    fun getFavoriteState(id: Int) {
        val data = repositoryFavorite.getFavoriteStoredData(id)
        val isWish = data?.wish ?: false
        execute(DetailIntent.FavoriteState(isWish))
    }

    fun setFavorite(id: Int, isFavorite: Boolean) {
        val data = repositoryFavorite.getFavoriteStoredData(id)

        data?.let {
            it.wish = isFavorite
            if (isFavorite) {
                if (repositoryFavorite.getFavoriteStoredData(it.id) == null) {
                    repositoryFavorite.addFavoriteStoredData(it)
                } else {

                }
            } else {
                if (!isFavorite) {
                    repositoryFavorite.removeFavoriteStoredData(it.id)
                }
            }
        } ?: run {
            if (isFavorite) {
                repositoryProductData.getProductOriginData(id)?.let {
                    repositoryFavorite.addFavoriteStoredData(it)
                }
            }
        }
        execute(DetailIntent.FavoriteState(isFavorite))
    }

    override suspend fun DetailUiState.reduce(intent: DetailIntent): DetailUiState =
        when (intent) {
            is DetailIntent.DetailData -> {
                copy(productData = intent.productData,  detailData = intent.detailData)
            }
            is DetailIntent.FavoriteState -> copy(isFavorite = intent.isFavorite)
        }
}