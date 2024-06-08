package com.codingbot.shop.ui.screens.detail

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.codingbot.shop.core.common.Logger
import com.codingbot.shop.domain.model.ProductDetailData
import com.codingbot.shop.ui.component.DetailHeader
import com.codingbot.shop.ui.component.RecommendMenu
import com.codingbot.shop.ui.component.SNSIconLink
import com.codingbot.shop.ui.component.SNSIconType
import com.codingbot.shop.ui.component.TabItem
import com.codingbot.shop.ui.component.TabsContent
import com.codingbot.shop.ui.component.clickableSingle
import com.codingbot.shop.ui.theme.CustomTheme
import com.codingbot.shop.viewmodel.DetailViewModel


@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    navController: NavController,
    id: Int,

    context: Context = LocalContext.current,
    detailViewModel: DetailViewModel = hiltViewModel(),
) {
    val logger = remember { Logger("DetailScreen", true, "[Screen]") }

    val uiState = detailViewModel.uiState.collectAsStateWithLifecycle()
    val tabs = remember { listOf(
        TabItem.HospitalInfo(id = id, navController = navController),
        TabItem.Event(id = id, navController = navController),
        TabItem.Reviews(id = id, navController = navController),
    ) }
    val pagerState = rememberPagerState(pageCount = {
        tabs.size
    })

    LaunchedEffect(key1 = Unit) {
        detailViewModel.getDetailData(id)
    }

    Box(
        modifier = Modifier
            .background(color = CustomTheme.colors.bg)
            .fillMaxSize(),
    )
    {
        Column(
            modifier = Modifier
    //            .background(color = CustomTheme.colors.bg)
                .fillMaxSize(),
        )
        {
            DetailHeader(
                title = uiState.value.productData?.productName ?: "",
                onClickBack = {
                    navController.popBackStack()
                },
                trailingIcon = {
                    Icon(
                        imageVector =
                        if (uiState.value.isFavorite) {
                            Icons.Filled.Favorite
                        } else {
                            Icons.Filled.FavoriteBorder
                        },
                        contentDescription = null,
                        modifier = Modifier
                            .size(46.dp)
                            .padding(end = 10.dp)
                            .clickableSingle {
                                detailViewModel.setFavorite(id, !uiState.value.isFavorite)
                            },
                        tint = CustomTheme.colors.orange60
                    )
                }
            )

            if (uiState.value.detailData == null) {
                Text(
                    modifier = Modifier
                        .fillMaxSize(),
                    text = "Failed Show Data",
                    color = CustomTheme.colors.black,
                    style = CustomTheme.typography.title1Bold,
                    textAlign = TextAlign.Center
                    )
                return
            }

            BoxWithConstraints {
                val screenHeight = maxHeight
                val scrollState = rememberScrollState()

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(state = scrollState)
                        .background(color = CustomTheme.colors.bg),
                    //            horizontalAlignment = Alignment.CenterHorizontally,
                )
                {
                    Box(
                        modifier = Modifier
                            .height(200.dp)
                            .fillMaxWidth()
                            .background(CustomTheme.colors.orange60),
                        contentAlignment = Alignment.Center
                    ) {
                        uiState.value.productData?.let { productData ->
                            if (productData.images.isNotEmpty()) {
                                SliderHospitalInfo(banners = productData.images)
                            }
                        }
                    }

                    Column(modifier = Modifier.height(screenHeight)) {
                        RecommendMenu(
                            tabs = tabs,
                            pagerState = pagerState,
                            onClickItem = {selectedItem ->

                            }
                        )
                        Box(modifier = Modifier
//                            .height(1700.dp)
                        ) {
                            TabsContent(tabs = tabs, pagerState = pagerState)
                        }
                    }
                }

//                LazyColumn(
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .background(color = CustomTheme.colors.bg),
//                    //            horizontalAlignment = Alignment.CenterHorizontally,
//                )
//                {
//                    item {
//                        Spacer(modifier = Modifier.padding(top = 20.dp))
//                    }
//
//                    item {
//                        uiState.value.productData?.let { productData ->
//                            if (productData.images.isNotEmpty()) {
//                                SliderHospitalInfo(productData.images)
//
////                                AsyncImage(
////                                    model = ImageRequest
////                                        .Builder(context)
////                                        .data(imageLocalMapperTmpHospital(productData.images[0]))
////                                        .build(),
////                                    contentDescription = null,
////                                    modifier = Modifier
////                                        .fillMaxWidth()
////                                        .height(300.dp)
////                                        .clip(shape = RoundedCornerShape(15.dp))
////                                        .padding(horizontal = 10.dp),
////                                    contentScale = ContentScale.Crop,
////                                )
//                            }
//                        }
//                    }
//                    item {
//                        Spacer(modifier = Modifier.padding(top = 10.dp))
//                    }
//
//                    stickyHeader {
//                        RecommendMenu(
//                            tabs = tabs,
//                            pagerState = pagerState,
//                            onClickItem = {selectedItem ->
//
//                            }
//                        )
//
//                    }
//                    item {
//                        Box(modifier = Modifier
//                            .height(1700.dp)
//                        ) {
//                            TabsContent(tabs = tabs, pagerState = pagerState)
//                        }
//                    }
////                    item {
//                        Text(
//                            modifier = Modifier
//                                .fillMaxWidth()
//                                .padding(top = 15.dp, start = 10.dp, end = 10.dp),
//                            text = uiState.value.detailData?.desc ?: "",
//                            color = CustomTheme.colors.black,
//                            style = CustomTheme.typography.caption2Regular,
//
//                            )
////                    }
////
////                    item {
////                        Text(
////                            modifier = Modifier
////                                .fillMaxWidth()
////                                .padding(top = 10.dp, bottom = 5.dp, start = 10.dp, end = 10.dp),
////                            text = uiState.value.detailData?.desc2 ?: "",
////                            color = CustomTheme.colors.black,
////                            style = CustomTheme.typography.caption2Regular,
////                        )
////                    }
////
////                    item {
////                        Spacer(modifier = Modifier.padding(bottom = 10.dp))
////                    }
////                    item {
////                        uiState.value.detailData?.let {
////                            Box(
////                                modifier = Modifier
////                                    .fillMaxWidth()
////                                    .height(300.dp)
////                                    .clip(shape = RoundedCornerShape(15.dp))
////                                    .padding(horizontal = 10.dp)
////                            ) {
////                                uiState.value.productData?.let { productData ->
////                                    MapView(arrayListOf(productData.searchQuery))
////                                }
////
////                            }
////                        }
////                    }
//                } // End of Lazy
                Column(
                    modifier = Modifier
                        .align(alignment = Alignment.CenterEnd)
                        .padding(end = 10.dp)
                ) {// Grid
                    uiState.value.detailData?.let {
                        FloatingSNSIcons(it)
                    }
                }
            } // end of Box
        }
    }
}


@Composable
fun ColumnScope.FloatingSNSIcons(
    datas: ProductDetailData
) {
    Column(
        modifier = Modifier
    ) {

        datas.let { data ->
            SNSIconType.entries.forEach { snsType ->
                val (linkType, hyperText) =
                    getSNSInfo(
                        snsType = snsType,
                        data = data)
                if (hyperText.isNotEmpty() && linkType != SNSIconType.NONE) {
                    val uriHandler = LocalUriHandler.current
                    val context = LocalContext.current
                    SNSIconLink(
                        hyperlink = hyperText,
                        snsIconType = linkType,
                        onClickIcon = {snsIconType ->
//                            if (snsIconType == SNSIconType.MAP) {
//                                uriHandler.openUri(hyperText)  // 일단외부로 내보내기
//                            }
                            if (snsIconType == SNSIconType.TEL) {
                                context.startActivity(
                                    Intent(
                                        "android.intent.action.DIAL",
                                        Uri.parse("tel:${hyperText}")
                                    )
                                )
                            } else {
                                uriHandler.openUri(hyperText)
                            }
                        }
                    )
                }
            }
        }
    }
}


private fun getSNSInfo(
    snsType: SNSIconType = SNSIconType.NONE,
    data: ProductDetailData
): Pair<SNSIconType, String>
{
    var hyperText = ""
    var linkType = SNSIconType.NONE
    if (snsType == SNSIconType.KAKAOTALK && data.kakaotalk.isNotEmpty()) {
        hyperText = data.kakaotalk
        linkType = SNSIconType.KAKAOTALK
    } else if (snsType == SNSIconType.TEL && data.tel.isNotEmpty()) {
        hyperText = data.tel
        linkType = SNSIconType.TEL
    } else if (snsType == SNSIconType.HOMEPAGE && data.homepage.isNotEmpty()) {
        hyperText = data.homepage
        linkType = SNSIconType.HOMEPAGE
    } else if (snsType == SNSIconType.BLOG && data.blog.isNotEmpty()) {
        hyperText = data.blog
        linkType = SNSIconType.BLOG
    } else if (snsType == SNSIconType.FACEBOOK && data.facebook.isNotEmpty()) {
        hyperText = data.facebook
        linkType = SNSIconType.FACEBOOK
    } else if (snsType == SNSIconType.INSTAGRAM && data.instagram.isNotEmpty()) {
        hyperText = data.instagram
        linkType = SNSIconType.INSTAGRAM
    } else if (snsType == SNSIconType.SNAPCHAT && data.snapchat.isNotEmpty()) {
        hyperText = data.snapchat
        linkType = SNSIconType.SNAPCHAT
    } else if (snsType == SNSIconType.TIKTOK && data.tiktok.isNotEmpty()) {
        hyperText = data.tiktok
        linkType = SNSIconType.TIKTOK
    } else if (snsType == SNSIconType.YOUTUBE && data.youtube.isNotEmpty()) {
        hyperText = data.youtube
        linkType = SNSIconType.YOUTUBE
//                } else if (snsType == SNSIconType.MAP && data.map.isNotEmpty()) {
//                    hyperText = data.map
//                    linkType = SNSIconType.MAP

    } else {
        // NONE
        hyperText = ""
        linkType = SNSIconType.NONE
    }
    return Pair(linkType, hyperText)
}