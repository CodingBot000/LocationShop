package com.codingbot.shop.core.common

import com.codingbot.shop.domain.model.EventData
import com.codingbot.shop.domain.model.EventDatas
import com.codingbot.shop.domain.model.ProductData
import com.codingbot.shop.domain.model.ProductDatas
import com.codingbot.shop.domain.model.ProductDetailData
import com.codingbot.shop.domain.model.ProductDetailDatas
import com.codingbot.shop.domain.model.ProductDetailDescData
import com.codingbot.shop.domain.model.ProductDetailDescDatas
import com.codingbot.shop.domain.model.ReviewData
import com.codingbot.shop.domain.model.ReviewDatas
import com.codingbot.shop.domain.model.SurgeryData
import com.codingbot.shop.domain.model.SurgeryDatas
import com.google.gson.Gson

object GsonAdapter {
    private val gson = Gson()

    fun parseProductData(jsonString: String): List<ProductData> {
        return gson.fromJson(jsonString, ProductDatas::class.java).datas
    }

    fun parseDetailData(jsonString: String): List<ProductDetailData> {
        return gson.fromJson(jsonString, ProductDetailDatas::class.java).datas
    }

    fun parseDetailDescData(jsonString: String): List<ProductDetailDescData> {
        return gson.fromJson(jsonString, ProductDetailDescDatas::class.java).datas
    }

    fun parseEventData(jsonString: String): List<EventData> {
        return gson.fromJson(jsonString, EventDatas::class.java).datas
    }

    fun parseReviewData(jsonString: String): List<ReviewData> {
        return gson.fromJson(jsonString, ReviewDatas::class.java).datas.toMutableList()
    }

    fun parseSurgeryData(jsonString: String): List<SurgeryData> {
        return gson.fromJson(jsonString, SurgeryDatas::class.java).datas
    }

}

