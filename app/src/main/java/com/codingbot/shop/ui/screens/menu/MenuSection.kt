package com.codingbot.shop.ui.screens.menu

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.toMutableStateMap
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.codingbot.shop.ui.theme.Color

enum class MenuTitle(val value: String) {
    SURGICAL_PROCEDURE("Surgical procedure"),
    COSMETIC_PROCEDURE("Cosmetic procedure"),
    LOCATION("Location"),
    FAVORITE("Favorite"),
    EVENT("Event"),
    ABOUT_US("About Us")
}

data class SectionData(val id: Int, val headerText: String, val items: List<SectionSubData> = emptyList(), val isOpened: Boolean = false)
data class SectionSubData(val id: Int, val subText: String = "", val isMenuEnable: Boolean = true )
