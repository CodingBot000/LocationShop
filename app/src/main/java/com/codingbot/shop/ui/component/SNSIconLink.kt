package com.codingbot.shop.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.codingbot.shop.R


enum class SNSIconType {
    NONE, KAKAOTALK, TEL, HOMEPAGE, INSTAGRAM, FACEBOOK, BLOG, YOUTUBE, TIKTOK, SNAPCHAT, MAP
}

@Composable
fun SNSIconLink(
//    modifier: Modifier = Modifier,
    hyperlink: String,
    snsIconType: SNSIconType = SNSIconType.NONE,
    onClickIcon: (SNSIconType) -> Unit
) {
    val snsIconResId = remember {
        when(snsIconType) {
            SNSIconType.KAKAOTALK -> R.drawable.icon_sns_kakaotalk
            SNSIconType.TEL -> Icons.Filled.Call
            SNSIconType.HOMEPAGE -> Icons.Filled.Home
            SNSIconType.INSTAGRAM -> R.drawable.icon_sns_instagram
            SNSIconType.FACEBOOK -> R.drawable.icon_sns_facebook
            SNSIconType.BLOG -> R.drawable.icon_sns_blog
            SNSIconType.YOUTUBE -> R.drawable.icon_sns_youtube
            SNSIconType.TIKTOK -> R.drawable.icon_sns_tiktok
            SNSIconType.SNAPCHAT -> R.drawable.icon_sns_snapchat
            SNSIconType.MAP -> Icons.Filled.LocationOn
            else -> {
                R.drawable.sns_homepage
            }
        }
    }

    val modifier = Modifier
        .size(48.dp)
        .padding(end = 10.dp, top = 10.dp)
        .clip(shape = RoundedCornerShape(100.dp))
        .clickableSingle {
            onClickIcon(snsIconType)
        }
    if (snsIconResId is Int) {
        Image(
            painter = painterResource(id = snsIconResId),
            contentDescription = null,
            modifier = modifier
            )
    } else {
        Image(
            imageVector = snsIconResId as ImageVector,
            contentDescription = null,
            modifier = modifier,
        )
    }


}