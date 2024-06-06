package com.codingbot.shop.ui.screens.recommend

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
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
import com.codingbot.shop.core.common.Logger
import com.codingbot.shop.core.common.Screen
import com.codingbot.shop.core.common.imageLocalMapperTmpHospital
import com.codingbot.shop.core.common.imageLocalMapperTmpReview
import com.codingbot.shop.domain.model.ProductData
import com.codingbot.shop.domain.model.ReviewData
import com.codingbot.shop.ui.component.clickableSingle
import com.codingbot.shop.ui.theme.Color
import com.codingbot.shop.ui.theme.CustomTheme
import com.codingbot.shop.viewmodel.ReviewListSubViewModel


@Composable
fun ReviewListSubScreen(
    navController: NavController,
    id: Int,
    context: Context = LocalContext.current,
    reviewListSubViewModel: ReviewListSubViewModel = hiltViewModel(),
) {
    val logger = remember { Logger("ReviewSubListScreen", true, "[Screen]") }

    val uiState = reviewListSubViewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = Unit) {
        reviewListSubViewModel.getReviewListSubListData(id)

    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 10.dp)

    ) {
        items(uiState.value.list?.size ?: 0) { index ->
            val reviewData = uiState.value.list!![index]
//            reviewListSubViewModel.getProductData(reviewData.hospital_id)

            ReviewInfoCell(
                reviewData = reviewData,
                onClickGotoHospital = { hospitalId ->
                    navController.navigate(
                        Screen.DetailScreen.route(hospitalId))
                }
            )
        }
    } // End of Lazy
}


@Composable
private fun ReviewInfoCell(
    reviewData: ReviewData,
    onClickGotoHospital: (Int) -> Unit
) {
    Column(modifier =
        Modifier.fillMaxWidth()
    )
    {

        if (!reviewData.reviewImg.isNullOrEmpty()) {
            Image(
                painter = painterResource(id = imageLocalMapperTmpReview(reviewData.reviewImg)),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(bottom = 10.dp)
                    .clip(shape = RoundedCornerShape(15.dp)),
                contentScale = ContentScale.Crop,
                contentDescription = null
            )
        }
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 5.dp),
            text = reviewData.reviewDesc,
            color = CustomTheme.colors.black,
            style = CustomTheme.typography.bodyRegular,

        )

        Row(modifier = Modifier
            .fillMaxWidth()
            .height(50.dp))
        {
            Text(
                text = reviewData.userId,
                color = CustomTheme.colors.orange60,
                style = CustomTheme.typography.bodyRegular,

            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 5.dp)
                    .clickableSingle {
                        onClickGotoHospital(reviewData.hospital_id)
                    },
                text = "Hospitals Shortcut -> ${reviewData.productData.productName}",
                color = CustomTheme.colors.black,
                style = CustomTheme.typography.bodyRegular,
                textAlign = TextAlign.End
            )
        }

        Spacer(modifier = Modifier.padding(bottom = 5.dp))
        Divider(modifier = Modifier.fillMaxWidth()
            .height(1.dp)
            .background(Color.Gray_20))
        Spacer(modifier = Modifier.padding(bottom = 5.dp))
    }
}