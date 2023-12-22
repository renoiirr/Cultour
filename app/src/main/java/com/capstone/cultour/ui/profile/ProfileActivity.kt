package com.capstone.cultour.ui.profile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.capstone.cultour.R
import com.capstone.cultour.data.api.login.LoginResult
import com.capstone.cultour.data.pref.UserPreference
import com.capstone.cultour.databinding.ActivityProfileBinding
import com.capstone.cultour.ui.MainActivity
import com.capstone.cultour.ui.ViewModelFactory
import com.capstone.cultour.ui.explore.ExploreActivity
import com.capstone.cultour.ui.login.LoginActivity
import com.capstone.cultour.ui.register.RegisterViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView


class ProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var userPreference: UserPreference
    private val viewModel by viewModels<ProfileViewModel> {
        ViewModelFactory.findInstance(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityProfileBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        userPreference = UserPreference(this)

        val loginResult: LoginResult? = userPreference.gainUser()

        if (loginResult != null){
            binding.tvUsername.text = loginResult.name
            binding.tvUserid.text = loginResult.userId.toString()
        }

        bindUIComponents()
        handleTabButtonPress()
        bottomNavigationView.selectedItemId = R.id.nav_profile




        binding.btnLogout.setOnClickListener{
        viewModel.saveSession(LoginResult("",null,""), this)
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }

    private fun bindUIComponents() {
        bottomNavigationView = findViewById(R.id.bottom_nav)
    }
    private fun handleTabButtonPress() {
        bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.nav_home -> {
                    loadActivities(MainActivity())
                    return@setOnItemSelectedListener true
                }

                R.id.nav_explore -> {
                    loadActivities(ExploreActivity())
                    return@setOnItemSelectedListener true
                }

                R.id.nav_profile -> {

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

}