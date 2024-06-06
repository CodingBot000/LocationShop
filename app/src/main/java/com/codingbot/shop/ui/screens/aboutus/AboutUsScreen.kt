package com.codingbot.shop.ui.screens.aboutus

import android.content.Context
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.codingbot.shop.core.common.Logger
import com.codingbot.shop.ui.component.DetailHeader
import com.codingbot.shop.ui.theme.CustomTheme
import com.codingbot.shop.viewmodel.TreatmentDetailDescViewModel


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AboutUsScreen(
    navController: NavController
) {
    val logger = remember { Logger("AboutUsScreen", true, "[Screen]") }

    Column(
        modifier = Modifier
            .background(color = CustomTheme.colors.bg)
            .fillMaxSize(),
    )
    {
        DetailHeader(
            title = "About Us",
            onClickBack = {
                navController.popBackStack()
            }
        )

        LazyColumn(
            modifier = Modifier
                .background(color = CustomTheme.colors.bg)
                .fillMaxSize()
                .padding(horizontal = 10.dp),
            //            .weight(weight = 1f, fill = false),
            horizontalAlignment = Alignment.CenterHorizontally,
        )
        {
            item {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp, bottom = 5.dp),
                    color = CustomTheme.colors.black,
                    style = CustomTheme.typography.title3Bold,
                    text = "Purpose of BeuatyU is to make all process simple for you.",
                    textAlign = TextAlign.Center
                )
            }

            item {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 5.dp),
                    color = CustomTheme.colors.black,
                    style = CustomTheme.typography.bodyRegular,
                    text = """
                  1) Reservation : 
                  users can search and make reservation(clinics and plastic surgery).
                     BeautyU provide information of Beauty specialist’s profile and service they offer.
                     BeuatyU make all this process simple.
        
                  2) Information : BeautyU provide newly beauty trend, make-up tips, skin-care methods and other useful informations.
                    Popular beauty celebrity can provide ideal products, tips and know-how in BeautyU . Users can make there own decision based on informations beauty experts directly provide.
        
                  3) Cummunation : Users can share their vivid experience and know-hows. Users can communicate and share their various knowledge within BeautyU.
        
                  4) Events and Discounts : BeautyU provide newly events and various information of discounts. BeautyU also provide gifts for users.
        
                  5) Customized for individuals : BeautyU provide customized information based on their interest and perference.
        
                  6) Channel : BeautyU connect to channel directly to hospitals and clinics. Users can ask what they are curious about. 
                    """.trimIndent()
                )
            }

            item {
                Spacer(modifier = Modifier.padding(bottom = 20.dp))
            }
        }
    }
}
