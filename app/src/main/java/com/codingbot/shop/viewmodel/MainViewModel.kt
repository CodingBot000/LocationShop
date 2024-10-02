package com.codingbot.shop.viewmodel

import com.codingbot.shop.core.common.Logger
import com.codingbot.shop.core.server.InitValue
import com.codingbot.shop.domain.model.LocationChipData
import com.codingbot.shop.domain.model.ProductData
import com.codingbot.shop.domain.model.HomeBannerData
import com.codingbot.shop.data.repository.RepositoryCommon
import com.codingbot.shop.data.repository.RepositoryProductData
import com.codingbot.shop.ui.screens.menu.MenuTitle
import com.codingbot.shop.ui.screens.menu.SectionData
import com.codingbot.shop.ui.screens.menu.SectionSubData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

data class MainUiState(
    val bannerSliderList: MutableList<HomeBannerData> = mutableListOf(),
    val newBeautyDataList: MutableList<ProductData> = mutableListOf(),
    val menuList: MutableList<SectionData> = mutableListOf(),
    val searchingList: MutableList<ProductData> = mutableListOf(),
    val locationChipDataList: MutableList<LocationChipData> = mutableListOf(),
    val region: String = InitValue.MENU_SUB_LOCATIONS[0]
)

sealed interface MainIntent {
    data class BannerSliderList(val bannerList: MutableList<HomeBannerData>): MainIntent
    data class NewBeautyDataList(val beautyDataList: MutableList<ProductData>): MainIntent
    data class MenuList(val menuList: MutableList<SectionData>): MainIntent
    data class SearchingList(val region: String, val searchingList: MutableList<ProductData>): MainIntent
    data class LocationChipDataList(val list: MutableList<LocationChipData>): MainIntent
}

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repositoryProductData: RepositoryProductData,
    val repositoryCommon: RepositoryCommon
)
    : BaseViewModel<MainUiState, MainIntent>(MainUiState())
{
    val logger = Logger("SortingViewModel")
    var menuInitDataList: MutableList<SectionData> = mutableListOf()

    init {
        initBannerSlider()
        initNewBeautyDatas()
        initRegionDatas()
        initMenuData()
    }

    private fun initRegionDatas() {
        val initLocationName = repositoryCommon.initLocationChipDataList()
        setLocation(initLocationName)
    }

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
        val list = repositoryCommon.getBannerSlideData()
        execute(MainIntent.BannerSliderList(list.toMutableList()))
    }

    fun initNewBeautyDatas() {
        val list = repositoryProductData.getNewBeautyDatas()
        execute(MainIntent.NewBeautyDataList(list.toMutableList()))
    }

    fun setLocation(currentRegion: String) {
        val dataList = repositoryProductData.getHospitalListByLocation(currentRegion)
        val locationChipDataList = repositoryCommon.getLocationChipDataList()
        execute(MainIntent.LocationChipDataList(locationChipDataList.toMutableList()))
        execute(MainIntent.SearchingList(currentRegion, dataList.toMutableList()))
    }

    override suspend fun MainUiState.reduce(intent: MainIntent): MainUiState =
        when (intent) {
            is MainIntent.BannerSliderList -> copy(bannerSliderList = intent.bannerList)
            is MainIntent.NewBeautyDataList -> copy(newBeautyDataList = intent.beautyDataList)
            is MainIntent.LocationChipDataList -> copy(locationChipDataList = intent.list)
            is MainIntent.MenuList -> copy(menuList = intent.menuList)
            is MainIntent.SearchingList -> copy(region = intent.region, searchingList = intent.searchingList)
        }
}