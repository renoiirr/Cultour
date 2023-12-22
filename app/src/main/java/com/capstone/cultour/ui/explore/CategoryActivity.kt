package com.capstone.cultour.ui.explore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.capstone.cultour.R
import com.capstone.cultour.data.MainViewModel
import com.capstone.cultour.data.Result

import com.capstone.cultour.data.api.explore.RecommendationItem
import com.capstone.cultour.data.api.explore.RecommendationRequest
import com.capstone.cultour.data.api.explore.RecommendedPlaceItem
import com.capstone.cultour.data.api.home.PlacesItem
import com.capstone.cultour.data.api.login.LoginResult
import com.capstone.cultour.data.pref.UserModel
import com.capstone.cultour.data.pref.UserPreference
import com.capstone.cultour.databinding.ActivityCategoryBinding
import com.capstone.cultour.ui.ViewModelFactory
import com.capstone.cultour.ui.adapter.ListWisataAdapter
import com.capstone.cultour.ui.adapter.RecommendationAdapter
import com.capstone.cultour.ui.register.RegisterActivity
import com.capstone.cultour.ui.register.RegisterViewModel

class CategoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCategoryBinding
    private lateinit var userPreference: UserPreference
    private val viewModel by viewModels<CategoryViewModel> {
        ViewModelFactory.findInstance(this)
    }

    private var userId: Long = 1
    private var selectedCategory: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityCategoryBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val layoutManager = LinearLayoutManager(this)
        binding.rvRecommendation.layoutManager = layoutManager

        userPreference = UserPreference(this)
        val loginResult: LoginResult? = userPreference.gainUser()

        if (loginResult != null) {
            userId = loginResult.userId ?: 1
        }

        binding.btnBack.setOnClickListener {
            onBackPressed()
        }

        binding.fabMaps.setOnClickListener{
            startActivity(Intent(this, MapsActivity::class.java))
        }

        val categoryName = arrayOf(
            "Statues & Monuments",
            "Parks & Nature Attractions",
            "Tourist Attractions",
            "Historical Buildings",
            "Museum",
            "Religious Places"
        )
        val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, categoryName)

        val spinner = findViewById<Spinner>(R.id.spinner)
        spinner.adapter = arrayAdapter

        spinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                selectedCategory = categoryName[position]
            }
        }
        binding.makeaplan.setOnClickListener {
            if (selectedCategory.isNotBlank()){
                val reqList = listOf(RecommendationItem(userId, selectedCategory))
                viewModel.recommendCategory(RecommendationRequest(reqList)).observe(this){ output ->
                    when(output){
                        is Result.Loading -> {
                            binding.progressBar.visibility = View.VISIBLE
                        }
                        is Result.Success -> {
                            binding.progressBar.visibility = View.GONE
                            val response = output.data.recommendedPlace
                            if (response != null){
                                response.map { otp ->
                                    RecommendedPlaceItem(
                                        otp!!.latitude,
                                        otp.name,
                                        otp!!.rating,
                                        otp.category,
                                        otp.placeId,
                                        otp!!.longitude,
                                    )
                                    binding.rvRecommendation.adapter = RecommendationAdapter(response)
                                    binding.fabMaps.visibility = View.VISIBLE
                                }

                            }
                            Toast.makeText(this, output.data.message, Toast.LENGTH_SHORT).show()

                        }
                        is Result.Error -> {
                            binding.progressBar.visibility = View.GONE
                            Toast.makeText(this, "Error, please log out first", Toast.LENGTH_SHORT).show()
                        }
                    }

                }
            }
        }
    }





}
