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

data class HospitalInfoSubUiState(
    val productData: ProductData? = null,
    val detailData: ProductDetailData? = null,
    val detailDescData: ProductDetailDescData? = null,

//    val isFavorite: Boolean = false
)

sealed interface HospitalInfoSubIntent {

    data class DetailData(val productData: ProductData?, val detailData: ProductDetailData?): HospitalInfoSubIntent
//    data class FavoriteState(val isFavorite: Boolean): HospitalInfoSubIntent
}

@HiltViewModel
class HospitalInfoSubViewModel @Inject constructor()
    : BaseViewModel<HospitalInfoSubUiState, HospitalInfoSubIntent>(HospitalInfoSubUiState())
{
    val logger = Logger("HospitalInfoSubViewModel")

    fun getDetailData(id: Int) {
        println("getDetailData: $id")
        val productData = productDatasOrigin?.find { it.id == id }
        val detailData = detailDatasOrigin?.find { it.id == id }

        if (productData == null || detailData == null)
            return

        execute(HospitalInfoSubIntent.DetailData(productData, detailData))
//        getFavoriteState(id)


    }

//    fun getFavoriteState(id: Int) {
//        val data = DumpServer.getFavoriteStoredData(id)
//        val isWish = data?.wish ?: false
//        execute(HospitalInfoSubIntent.FavoriteState(isWish))
//    }

//    fun setFavorite(id: Int, isFavorite: Boolean) {
//        val data = DumpServer.getFavoriteStoredData(id)
//
//        data?.wish = isFavorite
//        data?.let {
//            it.wish = isFavorite
//
//            if (isFavorite) {
//                if (DumpServer.getFavoriteStoredData(it.id) == null) {
//                    DumpServer.addFavoriteStoredData(it)
//                } else {
//
//                }
//            } else {
//                if (!isFavorite) {
//                    DumpServer.removeFavoriteStoredData(it.id)
//                }
//            }
//        } ?: run {
//            if (isFavorite) {
//                DumpServer.getProductOriginData(id)?.let {
//                    DumpServer.addFavoriteStoredData(it)
//                }
//            }
//        }
//        execute(HospitalInfoSubIntent.FavoriteState(isFavorite))
//    }

    override suspend fun HospitalInfoSubUiState.reduce(intent: HospitalInfoSubIntent): HospitalInfoSubUiState =
        when (intent) {
            is HospitalInfoSubIntent.DetailData -> {

                intent.detailData?.let {it ->
                 it.apply {


//                    println("qq qq DetailViewModel reduce tel ${tel}")
//                    println("qq qq DetailViewModel reduce tel  blog ${blog}")
//                    println("qq qq DetailViewModel reduce tel  instagram ${instagram}")
//                    println("qq qq DetailViewModel reduce tel  homepage ${homepage}")
//                    println("qq qq DetailViewModel reduce tel  youtube ${youtube}")
//                    println("qq qq DetailViewModel reduce tel  facebook ${facebook}")
                }
                }
                copy(productData = intent.productData,  detailData = intent.detailData)
            }
//            is HospitalInfoSubIntent.FavoriteState -> copy(isFavorite = intent.isFavorite)
        }
}