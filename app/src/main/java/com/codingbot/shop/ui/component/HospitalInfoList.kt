package com.codingbot.shop.ui.component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.codingbot.shop.core.common.Screen
import com.codingbot.shop.core.common.imageLocalMapperTmpHospital
import com.codingbot.shop.domain.model.ProductData

@Composable
fun HospitalInfoList(
    list: List<ProductData>,
    onClick: (Int) -> Unit
) {
    LazyColumn(
        modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 10.dp)
    ) {
        items(list.size) { index ->
            val data = list[index]
            HospitalInfoCell(
                id = data.id,
                descString = data.productName,
                resImgId = imageLocalMapperTmpHospital(data.images[0]),
                onClick = { hospitalId ->
                    onClick(hospitalId)
                }
            )
        }
    } // End of Lazy
}

