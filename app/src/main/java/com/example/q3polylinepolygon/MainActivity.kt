package com.example.q3polylinepolygon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.JointType
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Polygon
import com.google.maps.android.compose.Polyline
import com.google.maps.android.compose.rememberCameraPositionState

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                PolylinePolygonScreen()
            }
        }
    }
}

@Composable
fun PolylinePolygonScreen() {
    val trailPoints = remember {
        listOf(
            LatLng(37.4220, -122.0841),
            LatLng(37.4230, -122.0860),
            LatLng(37.4245, -122.0875),
            LatLng(37.4260, -122.0865),
            LatLng(37.4270, -122.0845)
        )
    }

    val parkPoints = remember {
        listOf(
            LatLng(37.4212, -122.0882),
            LatLng(37.4238, -122.0892),
            LatLng(37.4255, -122.0870),
            LatLng(37.4240, -122.0835),
            LatLng(37.4215, -122.0848)
        )
    }

    var polylineColor by remember { mutableStateOf(Color.Red) }
    var polygonFillColor by remember { mutableStateOf(Color(0x6600AA00)) }
    var polygonStrokeColor by remember { mutableStateOf(Color.Green) }
    var polylineWidth by remember { mutableStateOf(12f) }
    var polygonStrokeWidth by remember { mutableStateOf(6f) }
    var infoText by remember {
        mutableStateOf("Tap the trail or park overlay to view information.")
    }
    var mapLoaded by remember { mutableStateOf(false) }

    val cameraPositionState = rememberCameraPositionState()

    if (mapLoaded) {
        LaunchedEffect(Unit) {
            cameraPositionState.move(
                CameraUpdateFactory.newLatLngZoom(
                    LatLng(37.4240, -122.0860),
                    15f
                )
            )
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState,
            uiSettings = MapUiSettings(zoomControlsEnabled = true),
            onMapLoaded = {
                mapLoaded = true
            }
        ) {
            Polyline(
                points = trailPoints,
                color = polylineColor,
                width = polylineWidth,
                jointType = JointType.ROUND,
                clickable = true,
                onClick = {
                    infoText =
                        "Hiking Trail: this polyline represents the hiking trail. Width = $polylineWidth"
                }
            )

            Polygon(
                points = parkPoints,
                fillColor = polygonFillColor,
                strokeColor = polygonStrokeColor,
                strokeWidth = polygonStrokeWidth,
                clickable = true,
                onClick = {
                    infoText =
                        "Park Area: this polygon highlights an area of interest. Border width = $polygonStrokeWidth"
                }
            )
        }

        Card(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(text = infoText)

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Button(onClick = { polylineColor = Color.Red }) {
                        Text("Trail Red")
                    }
                    Button(onClick = { polylineColor = Color.Blue }) {
                        Text("Trail Blue")
                    }
                    Button(onClick = { polylineColor = Color.Magenta }) {
                        Text("Trail Purple")
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Button(onClick = {
                        polygonFillColor = Color(0x6600AA00)
                        polygonStrokeColor = Color.Green
                    }) {
                        Text("Park Green")
                    }
                    Button(onClick = {
                        polygonFillColor = Color(0x66FFD700)
                        polygonStrokeColor = Color(0xFFFF8C00)
                    }) {
                        Text("Park Yellow")
                    }
                    Button(onClick = {
                        polygonFillColor = Color(0x661E90FF)
                        polygonStrokeColor = Color.Blue
                    }) {
                        Text("Park Blue")
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Button(onClick = { polylineWidth += 4f }) {
                        Text("Trail Width +")
                    }
                    Button(onClick = {
                        if (polylineWidth > 4f) {
                            polylineWidth -= 4f
                        }
                    }) {
                        Text("Trail Width -")
                    }
                    Button(onClick = { polygonStrokeWidth += 2f }) {
                        Text("Park Border +")
                    }
                    Button(onClick = {
                        if (polygonStrokeWidth > 2f) {
                            polygonStrokeWidth -= 2f
                        }
                    }) {
                        Text("Park Border -")
                    }
                }
            }
        }
    }
}