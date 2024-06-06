package com.codingbot.shop.ui.component

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.TextUnit

@Composable
fun CallHyperlinkText(
    modifier: Modifier = Modifier,
    fullText: String,
    linkTextColored: List<String>,
    linkTextColor: Color = Color.Blue,
    linkTextFontWeight: FontWeight = FontWeight.Medium,
    linkTextDecoration: TextDecoration = TextDecoration.Underline,
    hyperlinks: List<String>,
    fontSize: TextUnit = TextUnit.Unspecified
) {
    HyperlinkText(
        modifier = modifier,
        fullText = fullText,
        linkTextColored = linkTextColored,
        linkTextColor = linkTextColor,
        linkTextFontWeight = linkTextFontWeight,
        linkTextDecoration = linkTextDecoration,
        hyperlinks = hyperlinks,
        fontSize = fontSize,
        isLinkType = LINK_TYPE_TEL
    )
}

val LINK_TYPE_TEL = "TEL"
val LINK_TYPE_URL = "URL"

@Composable
fun HyperlinkText(
    modifier: Modifier = Modifier,
    fullText: String,
    linkTextColored: List<String>,
    linkTextColor: Color = Color.Blue,
    linkTextFontWeight: FontWeight = FontWeight.Medium,
    linkTextDecoration: TextDecoration = TextDecoration.Underline,
    hyperlinks: List<String> = listOf(),
    fontSize: TextUnit = TextUnit.Unspecified,
    isLinkType: String = LINK_TYPE_URL
) {
    val annotatedString = buildAnnotatedString {
        append(fullText)
        linkTextColored.forEachIndexed { index, link ->
            val startIndex = fullText.indexOf(link)
            val endIndex = startIndex + link.length
            addStyle(
                style = SpanStyle(
                    color = linkTextColor,
                    fontSize = fontSize,
                    fontWeight = linkTextFontWeight,
                    textDecoration = linkTextDecoration
                ),
                start = startIndex,
                end = endIndex
            )
            addStringAnnotation(
                tag = isLinkType,
                annotation = hyperlinks[index],
                start = startIndex,
                end = endIndex
            )
        }
        addStyle(
            style = SpanStyle(
                fontSize = fontSize
            ),
            start = 0,
            end = fullText.length
        )
    }

    val uriHandler = LocalUriHandler.current
    val context = LocalContext.current
    ClickableText(
        modifier = modifier,
        text = annotatedString,
        onClick = {
            annotatedString
                .getStringAnnotations(isLinkType, it, it)
                .firstOrNull()?.let { stringAnnotation ->
                    if (isLinkType == LINK_TYPE_URL) {
                        uriHandler.openUri(stringAnnotation.item)
                    } else {
                        context.startActivity(
                            Intent(
                                "android.intent.action.DIAL",
                                Uri.parse("tel:${stringAnnotation.item}")
                            )
                        )
                    }
                }
        }
    )
}