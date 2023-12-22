package com.capstone.cultour.ui.login

import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.capstone.cultour.data.pref.UserModel
import com.capstone.cultour.databinding.ActivityLoginBinding
import com.capstone.cultour.ui.MainActivity
import com.capstone.cultour.data.Result
import com.capstone.cultour.ui.ViewModelFactory
import com.capstone.cultour.ui.register.RegisterActivity

class LoginActivity : AppCompatActivity() {
private val viewModel by viewModels<LoginViewModel> {
    ViewModelFactory.findInstance(this)
}
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val btnLogin = binding.btnLogin
        val loginEmail = binding.myemail
        val loginPassword = binding.mypassword
        val registerBtn = binding.tvRegisterHere

        isAlreadyLogin(this)

        supportActionBar?.hide()
        if (Build.VERSION.SDK_INT >= 33) {
            val data = intent.getParcelableExtra("extra_email_username", UserModel::class.java)
            if (data != null) {
                loginUser(data.email.toString(), data.password.toString())
            }
        } else {
            val data = intent.getParcelableExtra<UserModel>("extra_email_username")
            if (data != null) {
                loginUser(data.email.toString(), data.password.toString())
            }
        }

        btnLogin.setOnClickListener {
            if (loginPassword.text?.isEmpty() == true) {
                loginPassword.error = "Please fill it with the correct data"
            }

            if (loginEmail.text?.isEmpty() == true) {
                loginEmail.error = "Please fill it with the correct data"
            }
            if (loginPassword.error == null && loginEmail.error == null) {
                loginUser(loginEmail.text.toString(), loginPassword.text.toString())

            }
        }

        registerBtn.setOnClickListener {
            startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
        }
    }

    private fun isAlreadyLogin(context: Context) {
        viewModel.getSession(context).observe(this) { token ->
            if (token != null) {
                if (token.userId.toString().isNotBlank() && token.name.toString()
                        .isNotBlank() && token.token.toString().isNotBlank()
                ) {
                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }
    }
    private fun loginUser(email: String, password: String){
        viewModel.loginUser(email, password).observe(this){ output ->
            if (output != null){
                when (output){
                    is Result.Loading ->{
                        binding.progressBar.visibility = View.VISIBLE
                    }
                    is Result.Success ->{
                        binding.progressBar.visibility = View.GONE
                        val info = output.data
                        if(info.loginResult?.token != null){
                            viewModel.saveSession(info.loginResult, this)
                        }
                        val mainActivity = Intent(this@LoginActivity, MainActivity::class.java)
                        startActivity(mainActivity)
                        finish()
                    }
                    is Result.Error ->{
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(this, output.error, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

}