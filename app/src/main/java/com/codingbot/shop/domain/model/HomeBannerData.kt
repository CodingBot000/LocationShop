package com.codingbot.shop.domain.model

import com.google.gson.annotations.SerializedName

data class HomeBannerData(
    val id: Int,
    val name: String,
    val resId: Int,
    val desc: String
)


data class HomeBannerDatas(
    @SerializedName("surgerydatas") val datas: List<HomeBannerData>
)
