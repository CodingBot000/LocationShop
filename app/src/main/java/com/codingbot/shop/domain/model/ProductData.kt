package com.codingbot.shop.domain.model

import com.google.gson.annotations.SerializedName

data class ProductData(
    val id: Int,
    val productName: String,
    val searchQuery: String,
    val region: String,
    val images: List<String>,
    val surgeries: List<Int>,
    var wish: Boolean = false,
)

data class ProductDatas(
    @SerializedName("hospitals") val datas: List<ProductData>
)

