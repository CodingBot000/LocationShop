package com.codingbot.shop.ui.component

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.codingbot.shop.core.common.imageLocalMapperTmpEvent
import com.codingbot.shop.domain.model.EventData


@Composable
fun EventCell(
    data: EventData,
    onClickEvent: (EventData) -> Unit,
    context: Context = LocalContext.current
)  {
    Row(modifier = Modifier
        .fillMaxWidth()
        .height(150.dp)
        .clickableSingle {
            onClickEvent(data)
        })
    {
        AsyncImage(
            model = ImageRequest
                .Builder(context)
                .data(imageLocalMapperTmpEvent(data.eventImg))
                .build(),
            contentDescription = null,
            modifier = Modifier
                .size(120.dp)
                .clip(shape = RoundedCornerShape(10.dp))
                .padding(horizontal = 10.dp),
            contentScale = ContentScale.Crop,
        )

        Column {
            Text(
                text = data.eventName
            )

        }
    }
}