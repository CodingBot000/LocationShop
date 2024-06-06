package com.codingbot.shop.domain.model

import com.google.gson.annotations.SerializedName

data class ReviewData(
    val id: Int,
    val hospital_id: Int,
    val surgeryId: List<Int>,
    val reviewImg: String,
    val userId: String,
    val reviewDesc: String,
    var productData: ProductData // 만들어지는 데이터
)

data class ReviewDatas(
    @SerializedName("reviews") val datas: List<ReviewData>
)
