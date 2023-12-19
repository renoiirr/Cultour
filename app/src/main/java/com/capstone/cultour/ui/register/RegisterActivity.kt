package com.capstone.cultour.ui.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.capstone.cultour.data.Result
import com.capstone.cultour.data.pref.UserModel
import com.capstone.cultour.databinding.ActivityRegisterBinding
import com.capstone.cultour.ui.ViewModelFactory
import com.capstone.cultour.ui.login.LoginActivity

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

        binding.btnLogin.setOnClickListener{
            startActivity(Intent(this, LoginActivity::class.java))
        }
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
                            sendToLogin(UserModel(valEmail.toString(), valPassword.toString()))
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

    private fun sendToLogin(data: UserModel) {
        val intent = Intent(this, LoginActivity::class.java)
        intent.putExtra("extra_email_username", data)
        startActivity(intent)
        finish()
    }
}