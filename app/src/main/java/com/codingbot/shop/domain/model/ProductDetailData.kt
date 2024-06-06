package com.codingbot.shop.domain.model

import com.google.gson.annotations.SerializedName

data class ProductDetailData(
    val id: Int,
    val tel: String,
    val kakaotalk: String,
    val homepage: String,
    val instagram: String,
    val facebook: String,
    val blog: String,
    val youtube: String,
    val tiktok: String,
    val snapchat: String,
    val map: String,
    val searchQuery: String,
    var detailDesc: ProductDetailDescData,
)

data class ProductDetailDatas(
    @SerializedName("details") val datas: List<ProductDetailData>
)


data class ProductDetailDescData(
    val id: Int,
    val doctors: List<String>,
    val descAddress: String,
    val openingHour: String,
    val facilities: String,
    val etc: String
)

data class ProductDetailDescDatas(
    @SerializedName("detailsdesc") val datas: List<ProductDetailDescData>
)
