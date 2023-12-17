package com.capstone.cultour.ui.profile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.capstone.cultour.R
import com.capstone.cultour.databinding.ActivityProfileBinding
import com.capstone.cultour.ui.ViewModelFactory
import com.capstone.cultour.ui.login.LoginActivity
import com.capstone.cultour.ui.register.RegisterViewModel


class ProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding
    private val viewModel by viewModels<ProfileViewModel> {
        ViewModelFactory.findInstance(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityProfileBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnLogout.setOnClickListener{
        viewModel.saveSession("", this)
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }

}