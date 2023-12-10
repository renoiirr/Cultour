package com.capstone.cultour.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.capstone.cultour.R
import com.capstone.cultour.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_register)

        val emailRegister = binding.myemail.text
        val passwordRegister = binding.mypassword.text
        val repeatPassword = binding.repeatingPassword.text
        val username = binding.usernameEditText.text

        supportActionBar?.hide()


        binding.btnRegister.setOnClickListener {
            if (repeatPassword == passwordRegister) {
                startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
            }
            else{
                Toast.makeText(
                    this@RegisterActivity,
                    "Passwords does not match, please check again.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        binding.tvLoginHere.setOnClickListener {
            startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
        }
    }
}