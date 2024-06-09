package com.codingbot.shop.viewmodel

import com.codingbot.shop.core.common.Logger
import com.codingbot.shop.core.server.DumpServer
import com.codingbot.shop.core.server.InitValue
import com.codingbot.shop.domain.model.LocationChipData
import com.codingbot.shop.domain.model.ProductData
import com.codingbot.shop.domain.model.HomeBannerData
import com.codingbot.shop.ui.screens.menu.MenuTitle
import com.codingbot.shop.ui.screens.menu.SectionData
import com.codingbot.shop.ui.screens.menu.SectionSubData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

data class MainUiState(
    val bannerSliderListOld: MutableList<ProductData> = mutableListOf(),
    val bannerSliderList: MutableList<HomeBannerData> = mutableListOf(),
    val menuList: MutableList<SectionData> = mutableListOf(),
    val searchingList: MutableList<ProductData> = mutableListOf(),
    val locationChipDataList: MutableList<LocationChipData> = mutableListOf(),
    val region: String = InitValue.MENU_SUB_LOCATIONS[0]
)

sealed interface MainIntent {
    data class BannerSliderList(val bannerList: MutableList<HomeBannerData>): MainIntent
    data class BannerSliderListOld(val bannerListOld: MutableList<ProductData>): MainIntent
    data class MenuList(val menuList: MutableList<SectionData>): MainIntent
    data class SearchingList(val region: String, val searchingList: MutableList<ProductData>): MainIntent
    data class LocationChipDataList(val list: MutableList<LocationChipData>): MainIntent
}

@HiltViewModel
class MainViewModel @Inject constructor()
    : BaseViewModel<MainUiState, MainIntent>(MainUiState())
{
    val logger = Logger("SortingViewModel")
    var menuInitDataList: MutableList<SectionData> = mutableListOf()
//    var locationChipDataList: MutableList<LocationChipData> = mutableListOf<LocationChipData>()
//    lateinit var productDatasOrigin: ProductDatas


    init {
        initBannerSlider()
//        initHospitalDatas()
        initBannerSliderOld()
        initRegionDatas()
        initMenuData()
    }

    private fun initRegionDatas() {
//        InitValue.MENU_SUB_LOCATIONS.forEach {
//            locationChipDataList.add(LocationChipData(region = it))
//        }

        DumpServer.locationChipDataList[0].isSelected = true
        setRegion(DumpServer.locationChipDataList[0].region)
    }
//    private fun initHospitalDatas() {
//        productDatasOrigin = parseProductData(DataJson)
//    }
    private fun initMenuData(headerText: String = "", isOpened: Boolean = false) {

        menuInitDataList.clear()
        for ((index, sectionData) in InitValue.MENU_MAIN_CATEGORIES.withIndex())
        {
            when (sectionData) {
                MenuTitle.SURGICAL_PROCEDURE.value -> {
                    if (headerText == sectionData && !isOpened) {
                        menuInitDataList.add(
                            SectionData(
                                id = index,
                                headerText = sectionData,
                                items = emptyList<SectionSubData>())
                        )
                        continue
                    }


                        menuInitDataList.add(
                            SectionData(
                                id = index,
                                headerText = sectionData,
                                items = InitValue.MENU_SUB_SURGERY
//                                items = InitValue.MENU_SUB_SURGERY.mapIndexed { innerIndex, data
//                                    -> SectionSubData(id = innerIndex, subText = data)
//                                }
                            )
                        )
                }
                MenuTitle.COSMETIC_PROCEDURE.value -> {
                    if (headerText == sectionData && !isOpened) {
                        menuInitDataList.add(
                            SectionData(
                                id = index,
                                headerText = sectionData,
                                items = emptyList<SectionSubData>())
                        )
                        continue
                    }
                    menuInitDataList.add(
                        SectionData(
                            id = index,
                            headerText = sectionData,
                            items = InitValue.MENU_SUB_COSMETICS
//                            items = InitValue.MENU_SUB_COSMETICS.mapIndexed { innerIndex, data ->
//                                SectionSubData(id = innerIndex, subText = data)
//                            }
                        )
                    )
                }
                MenuTitle.LOCATION.value -> {
                    if (headerText == sectionData && !isOpened) {
                        menuInitDataList.add(
                            SectionData(
                                id = index,
                                headerText = sectionData,
                                items = emptyList<SectionSubData>())
                        )
                        continue
                    }

                    menuInitDataList.add(
                        SectionData(
                            id = index,
                            headerText = sectionData,
                            items = InitValue.MENU_SUB_LOCATIONS.map { SectionSubData(id = index, subText = it) }
                        )
                    )
                }

                MenuTitle.FAVORITE.value -> {
                    menuInitDataList.add(
                        SectionData(
                            id = index,
                            headerText = sectionData)
                    )
                }
                MenuTitle.EVENT.value -> {
                    menuInitDataList.add(
                        SectionData(
                            id = index,
                            headerText = sectionData)
                    )
                }
                MenuTitle.ABOUT_US.value -> {
                    menuInitDataList.add(
                        SectionData(
                            id = index,
                            headerText = sectionData)
                    )
                }
            }
        }
        for ((index, sectionData) in menuInitDataList.withIndex())
        {

            var newSectionSubData = sectionData.copy(sectionData.id, sectionData.headerText, sectionData.items, (sectionData.headerText == headerText))
            menuInitDataList[index] = newSectionSubData
        }


        execute(MainIntent.MenuList(menuInitDataList.toMutableList()))
    }

    fun setMenuFolding(headerText: String, isOpened: Boolean) {
        initMenuData(headerText, isOpened)
    }

    fun initBannerSlider() {
        execute(MainIntent.BannerSliderList(InitValue.getSurgeryList()))
    }
    fun initBannerSliderOld() {
        val bannerSliderList = mutableListOf<ProductData>()
        for (i in 0..8) {
            DumpServer.productDatasOrigin?.let { list ->
                bannerSliderList.add(list[i])
            }
        }

        execute(MainIntent.BannerSliderListOld(bannerSliderList.toMutableList()))
    }

    fun setRegion(currentRegion: String) {
        val datas = DumpServer.productDatasOrigin!!.filter {
          data -> data.region.equals(currentRegion, true)
        }

        DumpServer.locationChipDataList.forEachIndexed { index, locationChipData ->
            locationChipData.isSelected = (locationChipData.region ==  currentRegion)
            DumpServer.locationChipDataList[index] = locationChipData
        }

        execute(MainIntent.LocationChipDataList(DumpServer.locationChipDataList.toMutableList()))
        execute(MainIntent.SearchingList(currentRegion, datas.toMutableList()))
    }

    override suspend fun MainUiState.reduce(intent: MainIntent): MainUiState =
        when (intent) {
            is MainIntent.LocationChipDataList -> copy(locationChipDataList = intent.list)
            is MainIntent.BannerSliderList -> copy(bannerSliderList = intent.bannerList)
            is MainIntent.BannerSliderListOld -> copy(bannerSliderListOld = intent.bannerListOld)
            is MainIntent.MenuList -> copy(menuList = intent.menuList)
            is MainIntent.SearchingList -> copy(region = intent.region, searchingList = intent.searchingList)
        }
}