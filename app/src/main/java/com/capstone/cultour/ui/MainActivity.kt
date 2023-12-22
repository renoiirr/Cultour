package com.capstone.cultour.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.capstone.cultour.R
import com.capstone.cultour.data.MainViewModel
import com.capstone.cultour.data.Result
import com.capstone.cultour.data.api.home.PlacesItem
import com.capstone.cultour.data.dummy.WisataData
import com.capstone.cultour.data.pref.UserPreference
import com.capstone.cultour.data.retrofit.ApiConfig
import com.capstone.cultour.databinding.ActivityMainBinding
import com.capstone.cultour.ui.adapter.ListWisataAdapter
import com.capstone.cultour.ui.explore.ExploreActivity
import com.capstone.cultour.ui.profile.ProfileActivity
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {
    private val viewModel by viewModels<MainViewModel> {
        ViewModelFactory.findInstance(application)
    }
    private lateinit var pref: UserPreference
    private lateinit var binding: ActivityMainBinding
    private lateinit var bottomNavigationView: BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val layoutManager = LinearLayoutManager(this)
        binding.rvPlace.layoutManager = layoutManager

        displayPlaces(viewModel)

        bindUIComponents()
        handleTabButtonPress()

        pref = UserPreference(this)
        bottomNavigationView.selectedItemId = R.id.nav_home

    }

    private fun displayPlaces(mainViewModel: MainViewModel) {
        mainViewModel.getPlaces().observe(this) { places ->
            if (places != null) {
                when (places) {
                    is Result.Loading -> {}
                    is Result.Success -> {

                        val info = places.data.places
                        if (info != null) {
                            info.map { otp ->
                                PlacesItem(
                                    otp.addressFull,
                                    otp.image,
                                    otp.latitude,
                                    otp.name,
                                    otp.rating,
                                    otp.category,
                                    otp.longitude,
                                    otp.id,
                                    otp.placeId
                                )
                                binding.rvPlace.adapter = ListWisataAdapter(info)
                            }

                        }
                    }

                    is Result.Error -> {
                        Log.d("Error", "${places.error}")
                    }
                }
            }
        }
    }

    private fun handleTabButtonPress() {
        bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.nav_home -> {
                    return@setOnItemSelectedListener true
                }

                R.id.nav_explore -> {
                    loadActivities(ExploreActivity())
                    return@setOnItemSelectedListener true
                }

                R.id.nav_profile -> {
                    loadActivities(ProfileActivity())
                    return@setOnItemSelectedListener true
                }

            }
            return@setOnItemSelectedListener false
        }
    }


    private fun loadActivities(activity: AppCompatActivity) {
        val intent = Intent(applicationContext, activity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NO_ANIMATION
        startActivity(intent)
        finish()
    }


    private fun bindUIComponents() {
        bottomNavigationView = findViewById(R.id.bottom_nav)
    }

}