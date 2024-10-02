package com.codingbot.shop.ui.screens.favorite

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.codingbot.shop.R
import com.codingbot.shop.core.common.Logger
import com.codingbot.shop.core.common.Screen
import com.codingbot.shop.domain.model.ProductData
import com.codingbot.shop.ui.component.DetailHeader
import com.codingbot.shop.ui.component.clickableSingle
import com.codingbot.shop.ui.theme.CustomTheme
import com.codingbot.shop.viewmodel.FavoriteViewModel


@Composable
fun FavoriteScreen(
    context: Context = LocalContext.current,
    navController: NavController,
    favoriteViewModel: FavoriteViewModel = hiltViewModel(),
) {
    val logger = remember { Logger("FavoriteScreen", true, "[Screen]") }

    val uiState = favoriteViewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = Unit) {

    }
    Column(
        modifier = Modifier
            .background(color = CustomTheme.colors.bg)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    )
    {
        DetailHeader(
            title = "Favorite",
            onClickBack = {
                navController.popBackStack()
            }
        )

        LazyVerticalGrid(
            modifier = Modifier
                .fillMaxSize()
                .background(CustomTheme.colors.bg)
                .padding(start = 10.dp, end = 10.dp),
            horizontalArrangement = Arrangement.spacedBy(14.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            state = rememberLazyGridState(),
            columns = GridCells.Fixed(2),
            content = {
                items(uiState.value.favoriteDatas.size) { index ->
                    val data = uiState.value.favoriteDatas[index]
                    FavoriteCell(
                        data = data,
                        imgUrl = data.images[0],
                        onClickFavoriteCell = { id ->
                            navController.navigate(
                                Screen.DetailScreen.route(
                                    id = id,
                                )
                            )
                        }
                    )
                }
            }
        )
    }
}

@Composable
private fun FavoriteCell(
    context: Context = LocalContext.current,
    imgUrl: String,
    data: ProductData,
    onClickFavoriteCell:(Int) -> Unit
) {
    Column(
        modifier = Modifier.clickableSingle {
            onClickFavoriteCell(data.id)
        }
    ) {
        AsyncImage(
            model = ImageRequest
                .Builder(context)
                .data(imgUrl)
                .placeholder(R.drawable.default_hospital)
                .build(),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .aspectRatio(1f)
                .clip(shape = RoundedCornerShape(15.dp)),
            contentScale = ContentScale.Crop,
            error = painterResource(R.drawable.default_hospital)
        )
        Text(
            modifier = Modifier
                .padding(horizontal = 3.dp)
                .fillMaxWidth(),
            text = data.productName,
            color = CustomTheme.colors.black,
            style = CustomTheme.typography.bodyRegular,
            textAlign = TextAlign.Center
        )
        Text(
            modifier = Modifier
                .padding(horizontal = 3.dp)
                .fillMaxWidth(),
            text = "[${data.region}]",
            color = CustomTheme.colors.black,
            style = CustomTheme.typography.bodyRegular,
            textAlign = TextAlign.Center
        )
    }
}