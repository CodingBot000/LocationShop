package com.codingbot.shop.viewmodel

import androidx.lifecycle.viewModelScope
import com.codingbot.shop.core.common.Logger
import com.codingbot.shop.core.server.InitValue
import com.codingbot.shop.ui.screens.menu.MenuTitle
import com.codingbot.shop.ui.screens.menu.SectionData
import com.codingbot.shop.ui.screens.menu.SectionSubData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

data class MenuUiState(
    val menuList: MutableList<SectionData> = mutableListOf(),
)

sealed interface MenuIntent {
    data class MenuList(val menuList: MutableList<SectionData>): MenuIntent
}

@HiltViewModel
class MenuViewModel @Inject constructor()
    : BaseViewModel<MenuUiState, MenuIntent>(MenuUiState())
{
    val logger = Logger("MenuViewModel")
    var menuInitDataList: MutableList<SectionData> = mutableListOf()

    init {
        viewModelScope.launch {
            initMenuData()
        }
    }

    private suspend fun initMenuData(headerText: String = "", isOpened: Boolean = false) {
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


        execute(MenuIntent.MenuList(menuInitDataList.toMutableList()))
    }

    override suspend fun MenuUiState.reduce(intent: MenuIntent): MenuUiState =
        when (intent) {
            is MenuIntent.MenuList -> copy(menuList = intent.menuList)
        }
}