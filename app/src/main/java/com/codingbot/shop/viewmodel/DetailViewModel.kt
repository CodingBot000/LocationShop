package com.codingbot.shop.viewmodel

import com.codingbot.shop.core.server.DumpServer.detailDatasOrigin
import com.codingbot.shop.core.common.Logger
import com.codingbot.shop.core.server.DumpServer
import com.codingbot.shop.core.server.DumpServer.productDatasOrigin
import com.codingbot.shop.domain.model.ProductData
import com.codingbot.shop.domain.model.ProductDetailData
import com.codingbot.shop.domain.model.ProductDetailDescData
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
class DetailViewModel @Inject constructor()
    : BaseViewModel<DetailUiState, DetailIntent>(DetailUiState())
{
    val logger = Logger("DetailViewModel")

    fun getDetailData(id: Int) {
        println("getDetailData: $id")
        val productData = productDatasOrigin?.find { it.id == id }
        val detailData = detailDatasOrigin?.find { it.id == id }

        if (productData == null || detailData == null)
            return

        execute(DetailIntent.DetailData(productData, detailData))
        getFavoriteState(id)


    }

    fun getFavoriteState(id: Int) {
        val data = DumpServer.getFavoriteStoredData(id)
        val isWish = data?.wish ?: false
        execute(DetailIntent.FavoriteState(isWish))
    }

    fun setFavorite(id: Int, isFavorite: Boolean) {
        val data = DumpServer.getFavoriteStoredData(id)

//        data?.wish = isFavorite
        data?.let {
            it.wish = isFavorite
            val test  =DumpServer.getFavoriteStoredDatas()
            val test2  =DumpServer.getFavoriteStoredData(it.id)
            if (isFavorite) {
                if (DumpServer.getFavoriteStoredData(it.id) == null) {
                    DumpServer.addFavoriteStoredData(it)
                } else {

                }
            } else {
                if (!isFavorite) {
                    DumpServer.removeFavoriteStoredData(it.id)
                }
            }
        } ?: run {
            if (isFavorite) {
                DumpServer.getProductOriginData(id)?.let {
                    DumpServer.addFavoriteStoredData(it)
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