package com.capstone.cultour.ui.explore

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.capstone.cultour.data.Result
import com.capstone.cultour.data.api.home.PlacesItem
import com.capstone.cultour.data.api.login.LoginResult
import com.capstone.cultour.data.pref.UserPreference
import com.capstone.cultour.databinding.ActivityRatingBinding
import com.capstone.cultour.ui.ViewModelFactory
import com.capstone.cultour.ui.adapter.RatingAdapter

class RatingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRatingBinding
    private lateinit var userPreference: UserPreference
    private val viewModel by viewModels<RatingViewModel> {
        ViewModelFactory.findInstance(application)
    }

    private var userId: Long = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRatingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val layoutManager = LinearLayoutManager(this)
        binding.rvPlace.layoutManager = layoutManager

        userPreference = UserPreference(this)
        val loginResult: LoginResult? = userPreference.gainUser()

        if (loginResult != null) {
            userId = loginResult.userId ?: 1
        }
        binding.btnBack.setOnClickListener {
            onBackPressed()
        }

        displayPlaces(viewModel)
    }

    private fun displayPlaces(ratingViewModel: RatingViewModel) {
        ratingViewModel.getPlaces().observe(this){places ->
            if (places != null){
                when(places) {
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
                                binding.rvPlace.adapter = RatingAdapter(info)
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


}