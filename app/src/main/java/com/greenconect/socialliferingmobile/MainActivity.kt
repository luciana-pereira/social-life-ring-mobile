package com.greenconect.socialliferingmobile

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.greenconect.socialliferingmobile.service.ApiGreenConnect
import com.greenconect.socialliferingmobile.model.*
import com.greenconect.socialliferingmobile.ui.theme.DashboardActivity
import com.greenconect.socialliferingmobile.ui.theme.RegisterActivity
import com.greenconect.socialliferingmobile.ui.theme.SocialLifeRingMobileTheme

class MainActivity : ComponentActivity() {

    private lateinit var emailLogin: EditText
    private lateinit var passwordLogin: EditText
    private lateinit var buttonLogin: Button
    private lateinit var linkRegister: TextView
    private lateinit var buttonDecrease: Button
    private lateinit var buttonIncrease: Button
    private var textSize = 20f
    private val textSizeStep = 2f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initializeViews()
        setupLoginButton()
        setupRegisterLink()
        setupTextSizeControls()
    }

    private fun initializeViews() {
        emailLogin = findViewById(R.id.emailLogin)
        passwordLogin = findViewById(R.id.passwordLogin)
        buttonLogin = findViewById(R.id.buttonLogin)
        linkRegister = findViewById(R.id.linkRegister)
        buttonIncrease = findViewById(R.id.buttonIncreaseTextSize)
        buttonDecrease = findViewById(R.id.buttonDecreaseTextSize)
    }

    private fun setupLoginButton() {
        buttonLogin.setOnClickListener {
            val email = emailLogin.text.toString()
            val password = passwordLogin.text.toString()
            loginUser(email, password)
        }
    }

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://social-life-ring-api-a67f9fbe0125.herokuapp.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val apiService = retrofit.create(ApiGreenConnect::class.java)

    private fun loginUser(email: String, password: String) {
        val loginData = LoginData(email, password)
        val call = apiService.login(loginData)
        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful) {
                    navigateToDashboard()
                } else {
                    showLoginError()
                }
            }
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                // Erro de rede
            }
        })
    }

    private fun navigateToDashboard() {
        val intent = Intent(this, DashboardActivity::class.java)
        startActivity(intent)
    }

    private fun showLoginError() {
        Toast.makeText(baseContext, "E-mail ou senha incorreto!", Toast.LENGTH_SHORT).show()
    }

    private fun setupRegisterLink() {
        linkRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setupTextSizeControls() {
        buttonIncrease.setOnClickListener {
            textSize += textSizeStep
            adjustTextSize()
        }

        buttonDecrease.setOnClickListener {
            textSize -= textSizeStep
            adjustTextSize()
        }
    }

    private fun adjustTextSize() {
        if (textSize in 20f..28f) {
            emailLogin.textSize = textSize
            passwordLogin.textSize = textSize
            buttonLogin.textSize = textSize
            linkRegister.textSize = textSize
        } else {
            showTextSizeLimitError()
        }
    }

    private fun showTextSizeLimitError() {
        Toast.makeText(baseContext, "Opa! Este é o limite máximo para aumentar/diminuir o tamanho da letra para visualização.", Toast.LENGTH_SHORT).show()
    }
}
