package com.capstone.cultour.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.capstone.cultour.R
import com.capstone.cultour.data.dummy.WisataData
import com.capstone.cultour.databinding.ActivityMainBinding
import com.capstone.cultour.ui.adapter.ListWisataAdapter
import com.capstone.cultour.ui.explore.ExploreActivity
import com.capstone.cultour.ui.profile.ProfileActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

private lateinit var binding : ActivityMainBinding
private lateinit var recyclerView: RecyclerView
class MainActivity : AppCompatActivity() {
    private lateinit var bottomNavigationView: BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bindUIComponents()
        handleTabButtonPress()

        recyclerView = binding.rvPlace
        recyclerView.layoutManager = LinearLayoutManager(this)
        val wisataData = WisataData()
        recyclerView.adapter = ListWisataAdapter(wisataData.lokasiWisata)
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
        startActivity(intent)
        finish()}



    private fun bindUIComponents() {
        bottomNavigationView = findViewById(R.id.bottom_nav)
    }

}