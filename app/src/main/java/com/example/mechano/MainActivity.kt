package com.example.mechano

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.*
import android.location.LocationRequest.Builder
import android.media.audiofx.BassBoost
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.Gravity
import android.widget.*
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.core.view.GravityCompat.apply
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest.PRIORITY_HIGH_ACCURACY
import com.google.android.gms.location.LocationRequest.create
import com.google.android.gms.location.LocationServices
import com.google.android.material.navigation.NavigationView
import java.net.URI.create
import java.util.*
import java.util.concurrent.TimeUnit
import java.util.jar.Manifest

class MainActivity : AppCompatActivity() {
    private var PERMISSION_REQUEST_ACCESS_FINE_LOCATION = 100

    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient

    private lateinit var locationRequest: LocationRequest
    private lateinit var locationCallback: LocationCallback
    private var currentLocation: Location? = null
    var count = 0
    var drawer_layout : DrawerLayout? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        setContentView(R.layout.requestmechanic)

        val Help = findViewById<Button>(R.id.button2)
        Help.setOnClickListener {
            val intent= Intent(this, HelpActivity::class.java)
            startActivity(intent)
        }

        val bookings = findViewById<Button>(R.id.button3)
        bookings.setOnClickListener {
            val intent= Intent(this, Mybookings::class.java)
            startActivity(intent)
        }
//        val request = findViewById<Button>(R.id.materialButton)
//        request.setOnClickListener {
//            val intent= Intent(this, CreateServiceRequest::class.java)
//            startActivity(intent)
//        }

        val menu = findViewById<ImageButton>(R.id.imageView6)
//        menu.setOnClickListener({
//
//        })

        drawer_layout = findViewById<DrawerLayout>(R.id.drawer_layout_requestMechanic)
        var nav_layout = findViewById<NavigationView>(R.id.nv_requestmechanic)

        drawer_layout?.closeDrawer(GravityCompat.START)

        val toogle_btn = findViewById<ImageView>(R.id.toogle_bar)
        toogle_btn.setOnClickListener {
            Toast.makeText(this, "button is clicked", Toast.LENGTH_LONG).show()
            drawer_layout?.openDrawer(GravityCompat.END)
        }
        val requestMechanic = findViewById<Button>(R.id.requestButton)

        requestMechanic.setOnClickListener {
            Toast.makeText(this, "button is clicked", Toast.LENGTH_LONG).show()
            var intent = Intent(this, CreateServiceRequest::class.java)
            startActivity(intent)
        }

    }

//    override fun Onbac(){
//        if (drawer_layout?.isDrawerOpen(GravityCompat.START) == true){
//            drawer_layout?.closeDrawer(GravityCompat.END)
//        }else{
//            super.onBackPressed()
//        }
//    }

    override fun onResume() {
        super.onResume()
        getLocation()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_REQUEST_ACCESS_FINE_LOCATION) {
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                getLocation()
            }
        }
    }
    private fun isLocationEnabled(): Boolean {
        var locationManager: LocationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER
        )
    }

    private fun requestPermission(){
        ActivityCompat.requestPermissions(
            this,
            arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
            PERMISSION_REQUEST_ACCESS_FINE_LOCATION)
    }


    private fun getLocation() {
        val LocationText = findViewById<TextView>(R.id.locationView)
            if (ActivityCompat.checkSelfPermission(
                    this,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(
                    this,
                    android.Manifest.permission.ACCESS_FINE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED) {
                if (isLocationEnabled()) {
                fusedLocationProviderClient.lastLocation.addOnCompleteListener(this) { task ->
                    val location: Location? = task.result;
                    if (location != null) {
                        val geocoder = Geocoder(this, Locale.getDefault())
                        val list: List<Address> =
                            geocoder.getFromLocation(location.latitude, location.longitude, 1)
//                        LocationText.text = "${list[0].getAddressLine(0)}"
                        LocationText.text = "${list[0].locality + " , " +list[0].adminArea}"
                        Log.d("Location","Address\n${list[0].getAddressLine(0)}" )
                    }
                    Log.d("Location","location values -2" )
                }
            } else {
                if (count == 0){
                    Toast.makeText(this, "Please turn on location", Toast.LENGTH_LONG).show()
                    val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                    startActivity(intent)
                    count+=1
                }else{
                    //wrong text is coming , need to check
                    LocationText.text = getString(R.string.locationError)
                }
            }
        } else {
            requestPermission()
        }
    }
}