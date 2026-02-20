package com.example.app.Presentation.Screens

import CustomInputField
import DeleveryFavoriteFoodText
import FooterText
import GradientButton
import MainTextTitle
import SocialLoginButton
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.app.R
import com.example.app.Presentation.ui.theme.startRed
import com.example.app.Presentation.ui.theme.YongFontFamily
import com.example.app.presentation.auth.AuthViewModel

// Импортируй свою ViewModel (проверь путь, если он другой)
// import com.example.app.presentation.auth.AuthViewModel

@Composable
fun NextScreen(
    onSignUpClick: () -> Unit = {},
    onLoginSuccess: () -> Unit = {}, // Добавил колбэк при успешном входе
    viewModel: AuthViewModel = hiltViewModel() // ПОДКЛЮЧАЕМ HILT VIEWMODEL
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val scrollState = rememberScrollState()
    val context = LocalContext.current

    // Наблюдаем за состоянием из ViewModel
    val state = viewModel.state.value

    // Слушаем ошибки или успешный вход
    LaunchedEffect(state.error) {
        if (state.error.isNotBlank()) {
            Toast.makeText(context, state.error, Toast.LENGTH_SHORT).show()
        }
    }

    LaunchedEffect(state.isSuccess) {
        if (state.isSuccess) {
            onLoginSuccess()
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color.White
    ) { innerPadding ->
        Box(modifier = Modifier.fillMaxSize()) { // Обертка для индикатора загрузки
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .background(Color.White)
                    .verticalScroll(scrollState)
                    .padding(horizontal = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(40.dp))

                Image(
                    painter = painterResource(id = R.drawable.littlelogo),
                    contentDescription = "Logo",
                    modifier = Modifier.size(80.dp)
                )

                MainTextTitle()
                Spacer(modifier = Modifier.height(4.dp))
                DeleveryFavoriteFoodText()

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "Login to Your Account",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = startRed
                )

                Spacer(modifier = Modifier.height(20.dp))

                CustomInputField(
                    value = email,
                    onValueChange = { email = it },
                    placeholder = "Email or Phone Number"
                )
                Spacer(modifier = Modifier.height(12.dp))
                CustomInputField(
                    value = password,
                    onValueChange = { password = it },
                    placeholder = "Password"
                )

                Text(
                    text = "Or",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Gray,
                    modifier = Modifier.padding(top = 12.dp)
                )

                Text(
                    text = "Continue With",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Italic,
                    color = Color.Black,
                    fontFamily = YongFontFamily,
                    modifier = Modifier.padding(top = 4.dp)
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    SocialLoginButton(
                        text = "Facebook",
                        iconRes = R.drawable.facebookicon,
                        onClick = { /* Логика Facebook */ },
                        modifier = Modifier.weight(1f)
                    )
                    SocialLoginButton(
                        text = "Google",
                        iconRes = R.drawable.googleicon,
                        onClick = { /* Логика Google */ },
                        modifier = Modifier.weight(1f)
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                // ИСПОЛЬЗУЕМ СЕРВИС ПРИ НАЖАТИИ
                GradientButton("Login") {
                    if (email.isNotBlank() && password.isNotBlank()) {
                        viewModel.login(email, password)
                    } else {
                        Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show()
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Don't Have Account?",
                    color = startRed,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.clickable { onSignUpClick() }
                )

                Spacer(modifier = Modifier.height(40.dp))

                FooterText()

                Spacer(modifier = Modifier.height(20.dp))
            }

            // ИНДИКАТОР ЗАГРУЗКИ (поверх всего контента)
            if (state.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                    color = startRed
                )
            }
        }
    }
}