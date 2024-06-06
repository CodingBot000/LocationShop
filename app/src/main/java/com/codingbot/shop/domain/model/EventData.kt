package com.codingbot.shop.domain.model

import com.google.gson.annotations.SerializedName

data class EventData(
    val id: Int,
    val eventDateFrom: String,
    val eventDateTo: String,
    val hospital_id: Int,
    val eventName: String,
    val eventImg: String,
    val surgeryIds: List<Int>,
    val desc: String
)


data class EventDatas(
    @SerializedName("events") val datas: List<EventData>
)
