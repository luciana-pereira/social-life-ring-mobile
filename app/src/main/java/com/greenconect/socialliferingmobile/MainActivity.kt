package com.greenconect.socialliferingmobile

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
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

    private fun loginUser(email: String, password: String) {
        //criar autenticação de dois fatores no bakend
    }
}
