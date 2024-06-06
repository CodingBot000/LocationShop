package com.codingbot.shop.ui.component

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.codingbot.shop.R
import com.codingbot.shop.ui.theme.CustomTheme


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
//
//    enum class SNSIconType {

//    }

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
            .size(64.dp)
            .padding(end = 20.dp, top = 10.dp)
            .clickableSingle {
                onClickIcon(snsIconType)
            },
//        tint = CustomTheme.colors.white
    )

}