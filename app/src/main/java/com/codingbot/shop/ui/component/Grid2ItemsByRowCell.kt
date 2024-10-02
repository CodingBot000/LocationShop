package com.codingbot.shop.ui.component

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.codingbot.shop.R
import com.codingbot.shop.ui.theme.CustomTheme

@Composable
fun Grid2ItemsByRowCell(
    id: Int,
    imgUrl: String,
    descString: String,
    onClick: (Int) -> Unit,
    context: Context = LocalContext.current
) {
    Column(
        modifier = Modifier.clickableSingle {
            onClick(id)
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
            error = painterResource(R.drawable.load_waiting)
        )
        Text(
            modifier = Modifier
                .padding(horizontal = 3.dp)
                .fillMaxWidth(),
            text = descString,
            color = CustomTheme.colors.black,
            style = CustomTheme.typography.title3Bold,
            textAlign = TextAlign.Center
        )
    }
}

