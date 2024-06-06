package com.codingbot.shop.domain.model

import com.google.gson.annotations.SerializedName

data class SurgeryData(
    val id: Int,
    val surgeryName: String,
    val surgeryImgs: List<String>,
    val surgeryDesc: String,
)

data class SurgeryDatas(
    @SerializedName("surgery") val datas: List<SurgeryData>
)
