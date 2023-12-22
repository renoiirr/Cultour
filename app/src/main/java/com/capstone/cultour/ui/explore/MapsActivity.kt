package com.capstone.cultour.ui.explore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.capstone.cultour.R

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.capstone.cultour.databinding.ActivityMapsBinding
import com.google.android.gms.maps.model.LatLngBounds

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        mMap.uiSettings.isZoomControlsEnabled = true
        mMap.uiSettings.isIndoorLevelPickerEnabled = true
        mMap.uiSettings.isCompassEnabled = true
        mMap.uiSettings.isMapToolbarEnabled = true
        addManyMarker()
    }
    data class BusStop(
        val name: String,
        val latitude: Double,
        val longitude: Double
    )
    private val boundsBuilder = LatLngBounds.Builder()
    private fun addManyMarker() {
        val busStop = listOf(
            BusStop("Kenpark Bus Stop", -7.252737, 112.796098),
            BusStop("Veteran Bus Stop", -7.2395512, 112.737511),
            BusStop("Pasar Turi Bus Stop", -7.246075, 112.736937),
            BusStop("Halte Siwalankerto",-7.3370483,	112.7290539),
            BusStop("Blauran Bus Stop",-7.25553,	112.7341),
            BusStop("Urip Sumoharjo 2 Bus Stop",-7.2739349,	112.7421892),
            BusStop("Darmo Bus Stop",	-7.2893295,	112.7392396),
            BusStop("Terminal Intermoda Joyoboyo",	-7.2989842,	112.7364092),
            BusStop("Rajawali Bus Stop",	-7.234562,	112.732036),
            BusStop("Basra Bus Stop",	-7.2647258,	112.7411374),
            )
        busStop.forEach{busstop ->
            val latLng = LatLng(busstop.latitude, busstop.longitude)
            mMap.addMarker(MarkerOptions().position(latLng).title(busstop.name))
            boundsBuilder.include(latLng)
        }

        val bounds: LatLngBounds = boundsBuilder.build()
        mMap.animateCamera(
            CameraUpdateFactory.newLatLngBounds(
                bounds,
                resources.displayMetrics.widthPixels,
                resources.displayMetrics.heightPixels,
                300
            )
        )
    }

}