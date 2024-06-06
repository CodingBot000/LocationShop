package com.codingbot.shop.core.server

import com.codingbot.shop.core.common.GsonAdapter
import com.codingbot.shop.core.server.jsondata.DetailHospitalInfoDescJson
import com.codingbot.shop.core.server.jsondata.EventDataJson
import com.codingbot.shop.core.server.jsondata.HospitalDataJson
import com.codingbot.shop.core.server.jsondata.HospitalDetailJson
import com.codingbot.shop.core.server.jsondata.ReviewDataJson
import com.codingbot.shop.core.server.jsondata.SurgeryDataJson
import com.codingbot.shop.domain.model.EventData
import com.codingbot.shop.domain.model.LocationChipData
import com.codingbot.shop.domain.model.ProductData
import com.codingbot.shop.domain.model.ProductDetailData
import com.codingbot.shop.domain.model.ReviewData
import com.codingbot.shop.domain.model.SurgeryData

object DumpServer {

    /**
     * 상품리스트 json Data parsing 원본
     */
    var productDatasOrigin: List<ProductData>? = null

    /**
     * 지역 선택 chip 아이콘 데이터
     */
    var locationChipDataList: MutableList<LocationChipData> = mutableListOf<LocationChipData>()

    /**
     * 제품 디테일데이터 원본
     */
//    var productDetailDatasOrigin: List<MenuDetailData>? = null

    /**
     * 병원 디테일데이터 기본정보 - 전화, SNS등
     */
    var detailDatasOrigin: List<ProductDetailData>? = null

    /**
     * 프로덕트 찜목록
     */
    private val favoriteStoredDatas: MutableList<ProductData> = mutableListOf()

    /**
     * EventData
     */
    var eventDataList: List<EventData>? = null

    /**
     * ReviewData
     */
    var reviewDataList: List<ReviewData>? = null

    /**
     * SurgeryData
     */
    var surgeryDataList: List<SurgeryData>? = null

    fun init() {
        productDatasOrigin = GsonAdapter.parseProductData(HospitalDataJson)
        initRegionDatas()

        initDetailDatas()

//        productDetailDatasOrigin = GsonAdapter.parseMenuDetailData(MenuDetailData)
        eventDataList = GsonAdapter.parseEventData(EventDataJson)
        reviewDataList = GsonAdapter.parseReviewData(ReviewDataJson)
        surgeryDataList = GsonAdapter.parseSurgeryData(SurgeryDataJson)


    }

    private fun initRegionDatas() {
        InitValue.MENU_SUB_LOCATIONS.forEach {
            locationChipDataList.add(LocationChipData(region = it))
        }
    }
    private fun initDetailDatas() {
        detailDatasOrigin = GsonAdapter.parseDetailData(HospitalDetailJson)
        val detailDescs = GsonAdapter.parseDetailDescData(DetailHospitalInfoDescJson)

        detailDatasOrigin?.let { originData ->
            var tmpIdx = 0
            val originSize = originData.size
            originData.forEachIndexed { index, _ ->
                if (tmpIdx < originSize) {
                    tmpIdx = 0
                }
                originData[index].detailDesc = detailDescs[tmpIdx]
                tmpIdx++
            }
        }
    }

    fun getProductOriginData(id: Int) =
        productDatasOrigin?.let {
            it.find { data -> data.id == id}
        }

    fun getFavoriteStoredDatas() = favoriteStoredDatas
    fun getFavoriteStoredData(id: Int) = favoriteStoredDatas.find { it -> it.id == id}
    fun addFavoriteStoredData(id: Int) {
        getFavoriteStoredData(id)?.let { data ->
            favoriteStoredDatas.add(data)
        }
    }
    fun addFavoriteStoredData(productData: ProductData) {
        favoriteStoredDatas.add(productData)
    }
    fun removeFavoriteStoredData(id: Int) {
        favoriteStoredDatas.removeIf { it.id == id }
    }

    fun getProductData(id: Int) =
        productDatasOrigin?.find { data -> data.id == id}

    fun getReviewDataListBySurgery(surgeryId: Int): List<ReviewData> {
        val newReviewDatas = mutableListOf<ReviewData>()
        reviewDataList?.forEach { reviewData ->
            reviewData.surgeryId.find { id -> id == surgeryId }
                ?.let {
                 DumpServer.getProductData(surgeryId)?.let {data ->
                     reviewData.productData = data
                     newReviewDatas.add(reviewData)
                }
            }
        }
        return newReviewDatas.toList()
    }
}
