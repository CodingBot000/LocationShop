package com.codingbot.shop.ui.screens.recommend

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
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
import com.codingbot.shop.core.common.Logger
import com.codingbot.shop.core.common.Screen
import com.codingbot.shop.core.common.imageLocalMapperTmpHospital
import com.codingbot.shop.domain.model.ProductData
import com.codingbot.shop.ui.component.clickableSingle
import com.codingbot.shop.ui.theme.CustomTheme
import com.codingbot.shop.viewmodel.HospitalListSubViewModel


@Composable
fun HospitalListSubScreen(
    navController: NavController,
    id: Int,
    context: Context = LocalContext.current,
    hospitalSubViewModel: HospitalListSubViewModel = hiltViewModel(),
) {
    val logger = remember { Logger("FavoriteScreen", true, "[Screen]") }

    val uiState = hospitalSubViewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = Unit) {
        hospitalSubViewModel.getHospitalListData(id)
    }
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 10.dp)

    ) {
        items(uiState.value.list?.size ?: 0) { index ->
            val data = uiState.value.list!![index]
            HospitalInfoCell(
                data = data,
                onClick = { hospitalId ->
                    navController.navigate(
                        Screen.DetailScreen.route(hospitalId))
                }
            )
        }
    } // End of Lazy
}

@Composable
private fun HospitalInfoCell(
    data: ProductData,
    onClick: (Int) -> Unit
) {
    Column(modifier =
        Modifier.fillMaxWidth()
            .clickableSingle {
                onClick(data.id)
            }
    )
    {
        Image(
            painter = painterResource(id = imageLocalMapperTmpHospital(data.images[0])),
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(bottom = 10.dp)
                .clip(shape = RoundedCornerShape(15.dp)),
            contentScale = ContentScale.Crop,
            contentDescription = null
        )

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 5.dp),
            text = data.productName,
            color = CustomTheme.colors.black,
            style = CustomTheme.typography.bodyRegular,
            textAlign = TextAlign.Center
        )
    }
}