package com.codingbot.shop.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
            SNSIconType.KAKAOTALK -> R.drawable.sns_kakaotalk
            SNSIconType.TEL -> R.drawable.sns_tel
            SNSIconType.HOMEPAGE -> R.drawable.sns_homepage
            SNSIconType.INSTAGRAM -> R.drawable.sns_instagram
            SNSIconType.FACEBOOK -> R.drawable.sns_facebook
            SNSIconType.BLOG -> R.drawable.sns_blog
            SNSIconType.YOUTUBE -> R.drawable.sns_youtube
            SNSIconType.TIKTOK -> R.drawable.sns_tiktok
            SNSIconType.SNAPCHAT -> R.drawable.sns_snapchat
            SNSIconType.MAP -> R.drawable.sns_map
            else -> {
                R.drawable.sns_homepage
            }
        }
    }

    Image(
        painter = painterResource(id = snsIconResId),
        contentDescription = null,
        modifier = Modifier
            .size(48.dp)
            .padding(end = 10.dp, top = 10.dp)
            .clip(shape = RoundedCornerShape(100.dp))
            .clickableSingle {
                onClickIcon(snsIconType)
            },

    )

}