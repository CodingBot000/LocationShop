package com.codingbot.shop.ui.screens.menu

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Chip
import androidx.compose.material.ChipDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.codingbot.shop.R
import com.codingbot.shop.core.common.Logger
import com.codingbot.shop.core.common.MenuCategoriesName
import com.codingbot.shop.core.common.Screen
import com.codingbot.shop.core.server.InitValue
import com.codingbot.shop.ui.component.clickableSingle
import com.codingbot.shop.ui.theme.CustomTheme
import com.codingbot.shop.viewmodel.MainViewModel

@Composable
fun MenuScreen(
    navController: NavController,
    mainViewModel: MainViewModel  = hiltViewModel(),

) {
    val logger = remember { Logger("MenuScreen", true, "[Screen]") }

    val uiState = mainViewModel.uiState.collectAsStateWithLifecycle()

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Icon(
            painter = painterResource(id = R.drawable.icon_close),
            contentDescription = null,
            modifier = Modifier
                .size(64.dp)
                .padding(end = 20.dp, top = 10.dp)
                .align(Alignment.TopEnd)
                .clickableSingle {
                    navController.popBackStack()
                },
            tint = CustomTheme.colors.black
        )

        Column(modifier = Modifier
            .padding(top = 40.dp, start = 20.dp)
            .fillMaxSize()
        )
        {
            uiState.value.menuList.forEachIndexed { index, item ->
                println("q qq LazyColumn index:$index item:${item}")
                MenuCell(
                    item = item,
                    onClick = { id ->
                        logger { "MenuCell id:$id"}
                        navController.navigate(

                            when (id) {
                                MenuCategoriesName.FAVORITE.value -> Screen.FavoriteScreen.route
                                MenuCategoriesName.EVENT.value -> Screen.EventMenuScreen.route
                                MenuCategoriesName.ABOUT_US.value -> Screen.AboutUsScreen.route
                                else -> if (1000 <= id) {
                                      // Surgical procedure
                                        Screen.RecommendSurgeryScreen.route(id)
                                    } else {
                                        // Cosmetic procedure
                                        Screen.TreatmentDetailDescScreen.route(id)
                                    }
                            }
                        )
                    },
                    onClickLocation = { locationNames ->
                        navController.navigate(Screen.LocationScreen.route(locationNames.name))
                    },
//                    onClickFolding = { headerText, isOpened ->
//                        mainViewModel.setMenuFolding(headerText, isOpened)
//                    }
                )
            }
        }
    }
}


@Composable
private fun MenuCell(
    item: SectionData,
    onClick: (Int) -> Unit,
    onClickLocation: (InitValue.LocationNames) -> Unit,
//    onClickFolding: (String, Boolean) -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()

    ) {
        Text(
            modifier = Modifier
                .padding(5.dp)
                .clickableSingle {
                    when (item.headerText) {
                        MenuTitle.FAVORITE.value -> {
                            onClick(MenuCategoriesName.FAVORITE.value)
                        }

                        MenuTitle.ABOUT_US.value -> {
                            onClick(MenuCategoriesName.ABOUT_US.value)
                        }

                        MenuTitle.EVENT.value -> {
                            onClick(MenuCategoriesName.EVENT.value)
                        }

                        else -> {
//                            onClickFolding(item.headerText, !item.isOpened)
                        }
                    }
                },
            text = item.headerText,
            color = CustomTheme.colors.black,
            style = CustomTheme.typography.title2Bold
        )
    SubMenuList(item.items,
        onClickSubItem = { sectionSubData ->
            if (item.headerText == "Location") {
                InitValue.LocationNames
                    .values()
                    .find { it ->
                        println("qq it.name:${it.name}  sectionSubData.subText:${sectionSubData.subText}")
                        it.name.lowercase() == sectionSubData.subText.lowercase()
                    }
                    ?.let {
                        onClickLocation(it)
                    }
            } else {
                onClick(sectionSubData.id)
            }

        })
    }
    Spacer(modifier = Modifier.width(10.dp))
}



@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun SubMenuList(
    subMenuList: List<SectionSubData>,
    onClickSubItem: (SectionSubData) -> Unit
)
{
    FlowRow(
        modifier = Modifier.padding(start = 20.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        subMenuList.forEach { data ->
            NameFilterChipContent(
                sectionSubData = data,
                onClickSubItem = { sectionSubData ->
                    onClickSubItem(sectionSubData)
                }
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
private fun NameFilterChipContent(
    sectionSubData: SectionSubData,
    onClickSubItem: (SectionSubData) -> Unit
) {
    Chip(
        shape = RoundedCornerShape(50.dp),
        colors = ChipDefaults.chipColors(
            backgroundColor = CustomTheme.colors.white,
            contentColor = Color.Black),
        border = BorderStroke(1.dp, CustomTheme.colors.black),
        onClick = {
            onClickSubItem(sectionSubData)
                  },
        content = {
            Text(text = sectionSubData.subText,
                color = CustomTheme.colors.textColorPrimary,
                style = CustomTheme.typography.bodyRegular,)
        })

}
