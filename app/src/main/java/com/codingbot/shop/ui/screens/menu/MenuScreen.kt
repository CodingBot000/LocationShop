package com.codingbot.shop.ui.screens.menu

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
    val scrollState = rememberScrollState()


    Box(
        modifier = Modifier
            .fillMaxSize()
//            .background(color = CustomTheme.colors.black)
//            .alpha(alpha = 0.3f)
    ) {

        Box(
            modifier = Modifier
//                .alpha(alpha = 0.1f)
                .fillMaxSize()
                .background(color = CustomTheme.colors.black)
        )

        Icon(
            painter = painterResource(id = R.drawable.menu_icon),
            contentDescription = null,
            modifier = Modifier
                .size(64.dp)
                .padding(end = 20.dp, top = 10.dp)
                .align(Alignment.TopEnd)
                .clickableSingle {
                    navController.popBackStack()
                },
            tint = CustomTheme.colors.white
        )


        Column(modifier = Modifier
            .padding(top = 40.dp, start = 20.dp)
            .fillMaxSize()
        )
        {

//            ExpandableList(
//                sections = mainViewModel.menuData
//            )

            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(10.dp)
            )
            {

                itemsIndexed(uiState.value.menuList,
                    key = { index, item -> "$index _$item" })
                { _, item ->
                    MenuCell(
                        item = item,
                        onClick = { id ->
                            logger { "MenuCell id:$id"}
                            navController.navigate(
                                when (id) {
                                    MenuCategoriesName.FAVORITE.value -> Screen.FavoriteScreen.route
                                    MenuCategoriesName.EVENT.value -> Screen.EventMenuScreen.route
                                    MenuCategoriesName.ABOUT_US.value -> Screen.AboutUsScreen.route
                                    else -> Screen.TreatmentDetailDescScreen.route(id)
                                }
                            )
                        },
                        onClickLocation = { locationNames ->
                            navController.navigate(Screen.LocationScreen.route(locationNames.name))
                        },
                        onClickFolding = { headerText, isOpened ->
                            mainViewModel.setMenuFolding(headerText, isOpened)
                        }
                    )
                }


            }
        }
    }
}


@Composable
private fun MenuCell(
    item: SectionData,
    onClick: (Int) -> Unit,
    onClickLocation: (InitValue.LocationNames) -> Unit,
    onClickFolding: (String, Boolean) -> Unit
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
                                onClickFolding(item.headerText, !item.isOpened)
                            }
                        }
                    },
                text = item.headerText,
                color = CustomTheme.colors.white,
                style = CustomTheme.typography.title2Bold
            )
            if (item.isOpened) {
                repeat(item.items.size) { index ->
                    Text(
                        modifier = Modifier
                            .padding(start = 20.dp, top = 5.dp)
                            .clickableSingle {
                                if (item.headerText == "Location") {
                                    InitValue.LocationNames.values()
                                        .find {it ->
                                            println("qq it.name:${it.name}  item.items[index].subText:${item.items[index].subText}")
                                            it.name.lowercase() == item.items[index].subText.lowercase()
                                        }
                                        ?.let {
                                            onClickLocation(it)
                                        }
                                } else {
                                    onClick(item.items[index].id)
                                }
                            },
                        text = item.items[index].subText.toString(),
                        color = CustomTheme.colors.white,
                        style = CustomTheme.typography.bodyRegular
                    )
                }
            }
        }
//    }
    Spacer(modifier = Modifier.width(10.dp))
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SideMenu(
    drawerState: DrawerState = rememberDrawerState(initialValue = androidx.compose.material3.DrawerValue.Closed)
) {
//    val drawerState = rememberDrawerState(initialValue = androidx.compose.material3.DrawerValue.Closed)
//    val drawerState = rememberDrawerState(DrawerValue.Closed)
//    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val drawerMinOffsetAbs = -with(LocalDensity.current) { (-360).dp.toPx() }.dp
    val width = 300.dp

    ModalNavigationDrawer(
        drawerState = drawerState,
        gesturesEnabled = true,
        modifier = Modifier,
        drawerContent = {
            Box(
                modifier = Modifier
                    .background(CustomTheme.colors.elementBarBackground)
                    .width(width)
                    .fillMaxHeight()
                    .clickableSingle {

                    }
            ) {
                Text("Drawer title", modifier = Modifier.padding(16.dp))
            }
        }
    ) {
        Box(
            modifier =  Modifier
                .offset(x = (drawerState.offset.value.dp + drawerMinOffsetAbs) * (width/drawerMinOffsetAbs))
//                .background(color = CustomTheme.colors.black)
//                .alpha(0.5f)
        ) {
            Text("Screen title",
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}