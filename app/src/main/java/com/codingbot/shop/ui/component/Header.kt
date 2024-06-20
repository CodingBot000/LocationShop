package com.codingbot.shop.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.codingbot.shop.R
import com.codingbot.shop.ui.theme.CustomTheme

@Composable
fun MainHeader(
    title: String,
    onClickMenu: () -> Unit,
    trailingIcon: @Composable () -> Unit = {}
)
{
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp),

        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(id = R.drawable.menu_icon),
            contentDescription = null,
            modifier = Modifier
                .size(32.dp)
                .padding(start = 10.dp)
                .align(Alignment.CenterStart)
                .clickableSingle {
                    onClickMenu()
                },
            tint = CustomTheme.colors.textColorPrimary
        )

        Text(
            text = title,
            color = CustomTheme.colors.textColorPrimary,
            style = CustomTheme.typography.title1Bold,
            modifier = Modifier.align(Alignment.Center)
        )

        trailingIcon()
    }
}



@Composable
fun DetailHeader(
    title: String,
    backgroundColor: Color = CustomTheme.colors.bg,
    onClickBack: () -> Unit,
    trailingIcon: @Composable () -> Unit = {}
)
{
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .background(color = backgroundColor)
         ,
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(id = R.drawable.icon_arrow_left),
            contentDescription = null,
            modifier = Modifier
                .size(32.dp)
                .padding(start = 10.dp)
                .align(Alignment.CenterStart)
                .clickableSingle {
                    onClickBack()
                },
            tint = CustomTheme.colors.textColorPrimary
        )

        Text(
            text = title,
            color = CustomTheme.colors.textColorPrimary,
            style = CustomTheme.typography.title1Bold,
            modifier = Modifier.align(Alignment.Center)
        )
        Box(modifier = Modifier
            .align(Alignment.CenterEnd)) {
            trailingIcon()
        }
    }
}