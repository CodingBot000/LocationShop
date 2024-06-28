package com.codingbot.shop.ui.screens.main

import android.content.Context
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.codingbot.shop.domain.model.HomeBannerData
import com.codingbot.shop.ui.component.clickableSingle
import com.codingbot.shop.ui.theme.Color
import com.codingbot.shop.ui.theme.CustomTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.delay

@OptIn(ExperimentalPagerApi::class)
@Composable
fun SliderBanner(
    context: Context = LocalContext.current,
    banners: List<HomeBannerData>,
    onClick: (HomeBannerData) -> Unit)
{

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
                    .height(300.dp)
                    .clickableSingle {
                        onClick(data)
                    }
            )
            {
                AsyncImage(
                    model = ImageRequest
                        .Builder(context)
                        .data(data.urlImg)
                        .build(),
                    modifier = Modifier.fillMaxWidth()
                        .height(300.dp),
                    contentScale = ContentScale.Crop,
                    contentDescription = null
                )


                Text(
                    text = data.name,
                    color = CustomTheme.colors.black,
                    style = CustomTheme.typography.bodyRegular,
                    textAlign = TextAlign.Right,
                    modifier = Modifier.align(alignment = Alignment.BottomEnd)
                        .background(color = CustomTheme.colors.orange60)
                        .padding(horizontal = 10.dp)
                        .clip(shape = RoundedCornerShape(topStart = 15.dp))
                )

            }
        }
        Box(modifier = Modifier
            .fillMaxSize()
            .align(Alignment.BottomCenter)
            .padding(bottom = 20.dp)
        ) {
        HorizontalTabs(
            items = List(banners.size) { it },
            pagerState = pagerState
        )
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
