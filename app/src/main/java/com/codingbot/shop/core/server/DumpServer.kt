package com.codingbot.shop.core.server

import com.codingbot.shop.core.common.GsonAdapter
import com.codingbot.shop.core.server.jsondata.DetailHospitalInfoDescJson
import com.codingbot.shop.core.server.jsondata.EventDataJson
import com.codingbot.shop.core.server.jsondata.HospitalDataJson
import com.codingbot.shop.core.server.jsondata.HospitalDetailJson
import com.codingbot.shop.core.server.jsondata.ReviewDataJson
import com.codingbot.shop.core.server.jsondata.SurgeryDataJson
import com.codingbot.shop.domain.model.EventData
import com.codingbot.shop.domain.model.HomeBannerData
import com.codingbot.shop.domain.model.LocationChipData
import com.codingbot.shop.domain.model.ProductData
import com.codingbot.shop.domain.model.ProductDetailData
import com.codingbot.shop.domain.model.ReviewData
import com.codingbot.shop.domain.model.SurgeryData

object DumpServer {

    /**
     * 상품리스트 json Data parsing 원본 서버에 있는 원시데이터라 가정합니다
     */
    private var productDatasOrigin: List<ProductData> = mutableListOf()

    /**
     * 지역 선택 chip 아이콘 데이터
     */
    private var locationChipDataList: MutableList<LocationChipData> = mutableListOf<LocationChipData>()

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
    private var eventDataList: List<EventData>? = null

    /**
     * ReviewData
     */
    private var reviewDataList: List<ReviewData>? = null

    /**
     * SurgeryData
     */
    private var surgeryDataList: List<SurgeryData>? = null

    fun init() {
        productDatasOrigin = GsonAdapter.parseProductData(HospitalDataJson)
        initRegionDatas()
        initDetailDatas()

        eventDataList = GsonAdapter.parseEventData(EventDataJson)
        reviewDataList = GsonAdapter.parseReviewData(ReviewDataJson)
        surgeryDataList = GsonAdapter.parseSurgeryData(SurgeryDataJson)

    }

    fun getBannerSlideData(): List<HomeBannerData> {

        val bannerSliderList = mutableListOf<HomeBannerData>()
        for (i in 0..8) {
            productDatasOrigin?.let { list ->
                bannerSliderList.add(
                    HomeBannerData(
                        id = list[i].id,
                         name = list[i].productName,
                         urlImg=  list[i].images[0],
                        desc = list[i].productName,
                    ))
            }
        }
        return bannerSliderList
    }

    fun getNewBeautyDatas() : List<ProductData> {
        val list = mutableListOf<ProductData>()
        productDatasOrigin?.let { productDataList ->
            for (i in 0..8) {
                list.add(productDataList[i])
            }
        }
        return list
    }

    fun getSurgeryList() =
        surgeryDataList

    private fun initRegionDatas() {
        locationChipDataList.clear()
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
    fun getDetailDatasOrigin(id: Int) =
        detailDatasOrigin?.find { it.id == id }

    fun getFavoriteStoredDatas() = favoriteStoredDatas
    fun getFavoriteStoredData(id: Int) = favoriteStoredDatas.find { it -> it.id == id}
    fun addFavoriteStoredData(id: Int) {
        getFavoriteStoredData(id)?.let { data ->
            favoriteStoredDatas.add(data)
        }
    }

    fun getHospitalListByLocation(currentRegion: String): List<ProductData> {
        setLocationPosition(currentRegion)
        return productDatasOrigin.filter { data ->
            data.region.equals(currentRegion, true)
        }.toList()
    }

    fun getEventDataAllList() = eventDataList ?: emptyList()

    fun getEventDataListById(id: Int): List<EventData> {
        val eventList = mutableListOf<EventData>()
        eventDataList?.forEach { eventData ->
            eventData.surgeryIds.find {
                    surgeryId -> surgeryId == id
            }?.let {
                eventList.add(eventData)
            }
        }
        return eventList
    }

    fun getEventDataById(id: Int) =
        eventDataList?.find { it.id == id }

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
                 getProductData(surgeryId)?.let {data ->
                     reviewData.productData = data
                     newReviewDatas.add(reviewData)
                }
            }
        }
        return newReviewDatas.toList()
    }

    fun getHospitalDataListBySurgery(surgeryId: Int): List<ProductData> =
        productDatasOrigin
            ?.filter { productData ->
                productData.surgeries
                    .any { surgery_Id -> surgery_Id == surgeryId }
            }?.toMutableList() ?: mutableListOf()

    fun getLocationChipDataList() = locationChipDataList

    fun initLocationChipDataList(): String {
        val initLocationName = InitValue.LocationNames.APGUJEONG.value
        setLocationPosition(initLocationName)
        return initLocationName
    }

    fun setLocationPosition(currentRegion: String): List<LocationChipData>{
        locationChipDataList.forEachIndexed { index, locationChipData ->
            locationChipData.isSelected = (locationChipData.region ==  currentRegion)
            locationChipDataList[index] = locationChipData
        }
        return locationChipDataList
    }
}
