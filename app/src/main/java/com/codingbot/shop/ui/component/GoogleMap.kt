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
//fun MapView(searchQuery: String, apiKey: String) {
//fun MapView(searchQuery: String) {
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
//@Composable
////fun MapView(searchQuery: String, apiKey: String) {
//fun MapView(searchQuery: String) {
////fun MapView(searchQueries: List<String>)
//    val context = LocalContext.current
//    var mapProperties by remember { mutableStateOf(MapProperties()) }
//    var cameraPositionState by remember { mutableStateOf(CameraPositionState()) }
//    var markerState by remember { mutableStateOf(MarkerState(position = LatLng(0.0, 0.0))) }
//
//    LaunchedEffect(searchQuery) {
////        val location = getLocationFromQuery(searchQuery, apiKey)
//        val location = getLocationFromAddress(context, searchQuery)
//        location?.let {
//            val latLng = LatLng(it.latitude, it.longitude)
//            cameraPositionState = CameraPositionState(
//                CameraPosition.fromLatLngZoom(latLng, ZOOM)
//            )
//            markerState = MarkerState(position = latLng)
//        }
//    }
//
//    GoogleMap(
//        modifier = Modifier.fillMaxSize(),
//        properties = mapProperties,
//        cameraPositionState = cameraPositionState
//    ) {
//        Marker(
//            state = markerState,
//            title = searchQuery,
//            snippet = "Location of $searchQuery",
//            onClick = {
//                openGoogleMapsAppOrBrowser(context, markerState.position)
//                true // 마커 클릭 이벤트를 소비했음을 나타냄
//            }
//        )
//    }
//}
//
//
//private fun openGoogleMapsAppOrBrowser(context: Context, latLng: LatLng) {
//    val uri = Uri.parse("geo:${latLng.latitude},${latLng.longitude}?q=${latLng.latitude},${latLng.longitude}(Label)")
//    val intent = Intent(Intent.ACTION_VIEW, uri)
//    intent.setPackage("com.google.android.apps.maps")
//    if (intent.resolveActivity(context.packageManager) != null) {
//        context.startActivity(intent)
//    } else {
//        val webUri = Uri.parse("https://www.google.com/maps/search/?api=1&query=${latLng.latitude},${latLng.longitude}")
//        val webIntent = Intent(Intent.ACTION_VIEW, webUri)
//        context.startActivity(webIntent)
//    }
//}
//
//
//private fun getLocationFromAddress(context: Context, address: String ): Location? {
//    val geocoder = Geocoder(context);
//    val addresses:List<Address>
//    val resLocation = Location("");
//    try {
//        addresses = geocoder.getFromLocationName(address, 5)!!;
//        if((addresses == null) || (addresses.size == 0)) {
//            return null;
//        }
//        val addressLoc = addresses[0]
//
//        resLocation.latitude = addressLoc.latitude
//        resLocation.longitude = addressLoc.longitude
//
//    } catch (e: Exception) {
//        e.printStackTrace();
//    }
//    return resLocation;
//}
//


//
//@Composable
//fun GoogleMapScreen(
//    context: Context = LocalContext.current,
//    searchText: String,
//    latitude: Double,
//    longitude: Double
//) {
//    val cameraPositionState = rememberCameraPositionState {
////        position = CameraPosition.fromLatLngZoom(latLng, 17f)
//        null
//    }
//
//
//    var myMarkerState = rememberMarkerState {
//        null
//    }
//    LaunchedEffect(key1 = Unit) {
//        val location = getLocationFromAddress(context, searchText);
//        showCurrentLocation(location, cameraPositionState);
//    }
//
//
////    val context = LocalContext.current
////    val latLng = getMyLocation(context)
////    val dataStore = DataStoreModule(context)
//    val latLng = LatLng(latitude, longitude)
//    var savedLatitude by remember {
//        mutableDoubleStateOf(0.0)
//    }
//    var savedLongitude by remember {
//        mutableDoubleStateOf(0.0)
//    }
//
//
////    LaunchedEffect(Unit) {
////        dataStore.getLatitude.collect {
////            savedLatitude = it
////        }
////    }
////
////    LaunchedEffect(Unit) {
////        dataStore.getLongitude.collect {
////            savedLongitude = it
////        }
////    }
//
//
//    val uiSettings = remember {
//        MapUiSettings(myLocationButtonEnabled = true)
//    }
//    val properties by remember {
//        mutableStateOf(MapProperties(isMyLocationEnabled = true))
//    }
//    GoogleMap(
//        modifier = Modifier.fillMaxSize(),
//        cameraPositionState = cameraPositionState,
//        properties = properties,
//        uiSettings = uiSettings
//    ) {
//        if (savedLatitude != 0.0 && savedLongitude != 0.0) {
//            Marker(
//                state = MarkerState(position = LatLng(savedLatitude, savedLongitude)),
//            )
//        }
//    }
//}
//

//
//private fun showCurrentLocation(location: Location?, cameraPositionState: CameraPositionState) {
//    location?.let {
//        val curPoint = LatLng(location.latitude, location.longitude)
//    //    val msg = "Latitude : ${curPoint.latitude}\nLongitude : ${curPoint.longitude}"
//    //    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
//        cameraPositionState.position = CameraPosition.fromLatLngZoom(curPoint, 17f)
//
//        // 화면 확대, 숫자가 클수록 확대
////        map.animateCamera(CameraUpdateFactory.newLatLngZoom(curPoint, 15f))
//
//        // 마커 찍기
//        val targetLocation = Location("").apply {
//            latitude = location.latitude
//            longitude = location.longitude
//        }
//        showMyMarker(targetLocation)
//    }
//}
//
//private fun showMyMarker(location: Location) {
//    if (myMarker == null) {
//        myMarker = MarkerOptions()
//        myMarker.position(LatLng(location.latitude, location.longitude))
//        myMarker.title("◎ 내위치\n")
//        myMarker.snippet("여기가 어디지?")
//        myMarker.icon(BitmapDescriptorFactory.fromResource(R.drawable.mylocation))
//        map.addMarker(myMarker)
//    }
//}
//lat/lng: (35.1368049,129.1000598)
