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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.codingbot.shop.R
import com.codingbot.shop.domain.model.EventData
import com.codingbot.shop.ui.theme.CustomTheme


@Composable
fun EventCell(
    data: EventData,
    onClickEvent: (EventData) -> Unit,
    context: Context = LocalContext.current
)  {
    Row(modifier = Modifier
        .fillMaxWidth()
        .height(140.dp)
        .padding(horizontal = 10.dp)
        .clickableSingle {
            onClickEvent(data)
        })
    {
        AsyncImage(
            model = ImageRequest
                .Builder(context)
                .data(data.eventImg)
                .placeholder(R.drawable.default_beauty_surgery)
                .build(),
            contentDescription = null,
            modifier = Modifier
                .size(120.dp)
                .clip(shape = RoundedCornerShape(10.dp))
                .padding(end = 10.dp),
            contentScale = ContentScale.Crop,
            error = painterResource(R.drawable.load_waiting)
        )

        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = data.eventName,
                color = CustomTheme.colors.black,
                style = CustomTheme.typography.bodyBold,
            )
            Text(
                modifier = Modifier.fillMaxWidth().padding(bottom = 5.dp),
                text = "Duration: ${data.eventDateFrom} ~ ${data.eventDateTo}",
                color = CustomTheme.colors.black,
                style = CustomTheme.typography.captionRegular,
                textAlign = TextAlign.End
            )
            Text(
                text =data.desc,
                color = CustomTheme.colors.black,
                maxLines = 4,
                overflow = TextOverflow.Ellipsis,
                style = CustomTheme.typography.caption2Regular,
            )
        }
    }
}