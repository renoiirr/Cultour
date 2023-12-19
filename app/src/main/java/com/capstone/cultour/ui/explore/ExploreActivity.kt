package com.capstone.cultour.ui.explore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.capstone.cultour.R
import com.capstone.cultour.databinding.ActivityExploreBinding
import com.capstone.cultour.databinding.ActivityMainBinding
import com.capstone.cultour.ui.MainActivity
import com.capstone.cultour.ui.profile.ProfileActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class ExploreActivity : AppCompatActivity() {
    private lateinit var binding: ActivityExploreBinding
    private lateinit var bottomNavigationView: BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityExploreBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        bindUIComponents()
        handleTabButtonPress()
        bottomNavigationView.selectedItemId = R.id.nav_explore
    }

    private fun handleTabButtonPress() {
        bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.nav_home -> {
                    loadActivities(MainActivity())
                    return@setOnItemSelectedListener true
                }

                R.id.nav_explore -> {
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
        finish()}

    private fun bindUIComponents() {
        bottomNavigationView = findViewById(R.id.bottom_nav)
    }
}