package com.codingbot.shop.ui.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import com.codingbot.shop.ui.theme.CustomTheme

object AnnotationMaker {


    @Composable
    fun getWebLinkMaker(): AnnotatedString {
        return buildAnnotatedString {
            append("By joining, you agree to the ")

            pushStringAnnotation(tag = "policy", annotation = "https://google.com/policy")
            withStyle(style = SpanStyle(color = CustomTheme.colors.orange60)) {
                append("privacy policy")
            }
            pop()

            append(" and ")

            pushStringAnnotation(tag = "terms", annotation = "https://google.com/terms")

            withStyle(style = SpanStyle(color = CustomTheme.colors.textColorPrimary)) {
                append("terms of use")
            }

            pop()
        }
    }

}