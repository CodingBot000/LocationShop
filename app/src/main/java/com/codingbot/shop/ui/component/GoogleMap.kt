import android.content.Context
import android.content.Intent
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.net.Uri
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.codingbot.shop.core.common.Logger
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState

private fun getZoomSize(locationCnt: Int): Float {
    var zoom = 17f - locationCnt /2
    if (zoom < 11f) {
        zoom = 11f
    }
    return zoom
}

@Composable
fun MapView(searchQueries: List<String>)
{
    val logger = remember { Logger("MapView", true, "[Screen]") }

    val context = LocalContext.current
    var mapProperties by remember { mutableStateOf(MapProperties()) }
    var cameraPositionState by remember { mutableStateOf(CameraPositionState()) }
//    var markerState by remember { mutableStateOf(MarkerState(position = LatLng(0.0, 0.0))) }
    val markers = remember { mutableStateListOf<MarkerOptions>() }
    val fullAddresses = remember { mutableStateListOf<String>() }

    LaunchedEffect(searchQueries) {
//        val location = getLocationFromQuery(searchQuery, apiKey)
        markers.clear()
//        val location = getLocationFromAddress(context, searchQuery)
//        location?.let {
//            val latLng = LatLng(it.latitude, it.longitude)
//            cameraPositionState = CameraPositionState(
//                CameraPosition.fromLatLngZoom(latLng, ZOOM)
//            )
//            markerState = MarkerState(position = latLng)
//        }
        searchQueries.forEach { query ->
            logger { "map query:$query"}
            getLocationFromAddress(context, query)?.let { it ->
                val latLng = LatLng(it.latitude, it.longitude)
                logger { "map query :$query it.latitude:${it.latitude}  it.longitude:${it.longitude}"}
//                fullAddresses.add(it.first)

                markers.add(
                    MarkerOptions()
                        .position(latLng)
                        .title(query)
                        .snippet("Location of $query")
                )
            }
        }
        markers.firstOrNull()?.let {
            cameraPositionState = CameraPositionState(
                CameraPosition.fromLatLngZoom(it.position, getZoomSize(searchQueries.size))
            )
        }
    }

    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        properties = mapProperties,
        cameraPositionState = cameraPositionState
    ) {
        markers.forEachIndexed { index, markerOptions ->
            Marker(
                state = MarkerState(markerOptions.position),
                title = markerOptions.title,
                snippet = markerOptions.snippet,
                onClick = {
                    openGoogleMapsAppOrBrowser(context, markerOptions.position, searchQuery = fullAddresses[index])
                    true // 마커 클릭 이벤트를 소비했음을 나타냄
                }
            )
        }

    }
}


private fun openGoogleMapsAppOrBrowser(context: Context, latLng: LatLng, searchQuery: String) {
    println("openGoogleMapsAppOrBrowser searchQuery:$searchQuery")
    val uri = Uri.parse("geo:${latLng.latitude},${latLng.longitude}?q=${latLng.latitude},${latLng.longitude}(Label)")
    val intent = Intent(Intent.ACTION_VIEW, uri)
    intent.setPackage("com.google.android.apps.maps")
    if (intent.resolveActivity(context.packageManager) != null) {
        context.startActivity(intent)
    } else {
//        val webUri = Uri.parse("https://www.google.com/maps/search/?api=1&query=${latLng.latitude},${latLng.longitude}")
        val webUri = Uri.parse("https://www.google.com/maps/search/?api=1&query=$searchQuery")
        val webIntent = Intent(Intent.ACTION_VIEW, webUri)
        context.startActivity(webIntent)
    }
}

private fun getLocationFromAddress(context: Context, address: String ): Location? {
    val geocoder = Geocoder(context);
    val addresses:List<Address>
    var fullAddress: String = ""
    val resLocation = Location("");
    try {
        addresses = geocoder.getFromLocationName(address, 5)!!;
        if((addresses == null) || (addresses.size == 0)) {
            return null;
        }
        val addressLoc = addresses[0]
        fullAddress = addressLoc.getAddressLine(0)
        resLocation.latitude = addressLoc.latitude
        resLocation.longitude = addressLoc.longitude

    } catch (e: Exception) {
        e.printStackTrace();
    }
    return resLocation;
}
