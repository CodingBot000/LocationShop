package com.codingbot.shop.ui.screens.detail

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.codingbot.shop.core.common.imageLocalMapperTmpHospital
import com.codingbot.shop.ui.theme.Color
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.delay

@OptIn(ExperimentalPagerApi::class)
@Composable
fun SliderHospitalInfo(
    banners: List<String>,
    context: Context = LocalContext.current
) {

    val pagerState = rememberPagerState()
    LaunchedEffect(banners) {
        if (banners.isNotEmpty()) {
            while (true) {
                delay(4000)
                val nextPage = (pagerState.currentPage + 1) % (banners.size)
                pagerState.animateScrollToPage(nextPage)
            }
        }
    }

    Box(modifier = Modifier
        .fillMaxSize()
    )
    {

        HorizontalPager(
            count = banners.size,
            state = pagerState
        ) { page ->
            val data = banners[page]
            Box(
                modifier = Modifier.fillMaxWidth()
                    .height(250.dp)

            )
            {
                Image(
                    modifier = Modifier.fillMaxWidth()
                        .height(250.dp),
                    painter = painterResource(id = imageLocalMapperTmpHospital(data)),
                    contentScale = ContentScale.Crop,
                    contentDescription = null
                )
            }
        }
        if (banners.size > 1) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
//                    .align(Alignment.BottomCenter)
//                    .padding(bottom = 20.dp)
                    .padding(top =230.dp)
            ) {
                HorizontalTabs(
                    items = List(banners.size) { it },
                    pagerState = pagerState
                )
            }
        }

    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
private fun HorizontalTabs(
    items: List<Int>,
    pagerState: PagerState,
) {
    val dotRadius = 4.dp
    val dotSpacing = 8.dp

    Box(
        modifier = Modifier
            .height(dotRadius * 2)
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.align(Alignment.Center),
            horizontalArrangement = Arrangement.spacedBy(dotSpacing),
        ) {
            items?.forEachIndexed { index, _ ->
                Box(
                    modifier = Modifier
                        .size(dotRadius * 2)
                        .clip(CircleShape)
                        .background(
                            if (pagerState.currentPage == index) Color.Gray_70 else Color.Gray_20
                        ),
                )
            }
        }
    }
}
