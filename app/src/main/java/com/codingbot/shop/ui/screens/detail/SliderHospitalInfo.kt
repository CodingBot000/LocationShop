package com.codingbot.shop.ui.screens.detail

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
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
import com.codingbot.shop.R
import com.codingbot.shop.ui.theme.Color
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.delay

@OptIn(ExperimentalPagerApi::class)
@Composable
fun SliderHospitalInfo(
    context: Context = LocalContext.current,
    banners: List<String>,
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
                AsyncImage(
                    model = ImageRequest
                        .Builder(context)
                        .data(data)
                        .build(),
                    modifier = Modifier.fillMaxWidth()
                        .height(250.dp),
                    contentScale = ContentScale.Crop,
                    contentDescription = null,
                    error = painterResource(R.drawable.hospital_default)
                )
            }
        }
        if (banners.size > 1) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
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
            items.forEachIndexed { index, _ ->
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
