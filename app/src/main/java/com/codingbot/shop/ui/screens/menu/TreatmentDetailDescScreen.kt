package com.codingbot.shop.ui.screens.menu

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.codingbot.shop.core.common.Logger
import com.codingbot.shop.core.common.imageLocalMapperTmpDoctors
import com.codingbot.shop.core.common.imageLocalMapperTmpHospital
import com.codingbot.shop.core.common.imageLocalMapperTmpSurgery
import com.codingbot.shop.ui.component.DetailHeader
import com.codingbot.shop.ui.theme.CustomTheme
import com.codingbot.shop.viewmodel.TreatmentDetailDescViewModel


@Composable
fun TreatmentDetailDescScreen(
    navController: NavController,
    id: Int,
    context: Context = LocalContext.current,
    treatmentDetailDescViewModel: TreatmentDetailDescViewModel = hiltViewModel(),
) {
    val logger = remember { Logger("DetailScreen", true, "[Screen]") }

    val uiState = treatmentDetailDescViewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = Unit) {
        treatmentDetailDescViewModel.getDetailData(id)
    }
    Column(
        modifier = Modifier
            .background(color = CustomTheme.colors.bg)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    )
    {
        DetailHeader(
            title = uiState.value.detailData?.surgeryName ?: "",
            onClickBack = {
                navController.popBackStack()
            }
        )
        if (uiState.value.detailData == null) {
            return
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(color = CustomTheme.colors.bg),
//            horizontalAlignment = Alignment.CenterHorizontally,
        )
        {
            item {
                Spacer(modifier = Modifier.padding(top = 10.dp))
            }

            item {
                uiState.value.detailData?.surgeryImgs?.let {
                    Image(
                        painter = painterResource(id = imageLocalMapperTmpSurgery(uiState.value.detailData!!.surgeryImgs[0])),
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(1f)
                            .padding(10.dp),
                        contentScale = ContentScale.Crop,
                        contentDescription = "surgeryDevice image"
                    )
                }
            }

            item {
                Spacer(modifier = Modifier.padding(top = 10.dp))
            }

            item {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 15.dp, bottom = 5.dp, start = 10.dp, end = 10.dp),
                    text = uiState.value.detailData?.surgeryDesc ?: "",
                    color = CustomTheme.colors.black,
                    style = CustomTheme.typography.bodyRegular,

                    )
            }

        }
    }
}
