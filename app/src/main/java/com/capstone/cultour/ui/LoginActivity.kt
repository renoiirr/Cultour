package com.capstone.cultour.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.capstone.cultour.R
import com.capstone.cultour.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val btnLogin = binding.btnLogin
        val loginEmail = binding.myemail
        val loginPassword = binding.mypassword
        val registerBtn = binding.tvRegisterHere


        btnLogin.setOnClickListener {
            if (loginPassword.text?.isEmpty() == true) {
                loginPassword.error = "Please fill it with the correct data"
            }

            if (loginEmail.text?.isEmpty() == true) {
                loginEmail.error = "Please fill it with the correct data"
            }
            if (loginPassword.error == null && loginEmail.error == null) {
//                loginUser(loginEmail.text.toString(), loginPassword.text.toString())

                startActivity(Intent(this@LoginActivity, MainActivity::class.java))
            }
        }

        registerBtn.setOnClickListener {
            startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
        }
    }

}