package com.capstone.cultour.ui.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.capstone.cultour.data.Result
import com.capstone.cultour.databinding.ActivityRegisterBinding
import com.capstone.cultour.ui.ViewModelFactory

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private val viewModel by viewModels<RegisterViewModel> {
        ViewModelFactory.findInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val valName = binding.usernameEditText.text
        val valEmail = binding.myemail.text
        val valPassword = binding.mypassword.text

        supportActionBar?.hide()

        binding.btnRegister.setOnClickListener {
            val signupPassword = valPassword.toString()

            if (signupPassword?.length!! < 8) {
                binding.btnRegister.error = "Minimal harus 8 karakter"
            } else {

                viewModel.registerUser(
                    valName.toString(),
                    valEmail.toString(),
                    signupPassword
                ).observe(this) { output ->
                    when (output) {
                        is Result.Loading -> {}
                        is Result.Success -> {
                            binding.progressBar.visibility = View.GONE
                            val response = output.data
                            Toast.makeText(this, response.message, Toast.LENGTH_SHORT).show()
                        }

                        is Result.Error -> {
                            Toast.makeText(this, "Sign Up Failed", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this, RegisterActivity::class.java))
                        }
                    }
                }
            }
        }
    }
}