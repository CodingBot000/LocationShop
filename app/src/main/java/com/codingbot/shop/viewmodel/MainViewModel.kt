package com.codingbot.shop.viewmodel

import androidx.lifecycle.viewModelScope
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
import kotlinx.coroutines.launch
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

    init {
        viewModelScope.launch {
            initBannerSlider()
            initNewBeautyDatas()
            initRegionDatas()
//            initMenuData()
        }
    }

    private suspend fun initRegionDatas() {
        val initLocationName = repositoryCommon.initLocationChipDataList()
        setLocation(initLocationName)
    }

    suspend fun initBannerSlider() {
        val list = repositoryCommon.getBannerSlideData()
        execute(MainIntent.BannerSliderList(list.toMutableList()))
    }

    suspend fun initNewBeautyDatas() {
        val list = repositoryProductData.getNewBeautyDatas()
        execute(MainIntent.NewBeautyDataList(list.toMutableList()))
    }

    fun setLocation(currentRegion: String) {
        viewModelScope.launch {
            val dataList = repositoryProductData.getHospitalListByLocation(currentRegion)
            val locationChipDataList = repositoryCommon.getLocationChipDataList()
            execute(MainIntent.LocationChipDataList(locationChipDataList.toMutableList()))
            execute(MainIntent.SearchingList(currentRegion, dataList.toMutableList()))
        }
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