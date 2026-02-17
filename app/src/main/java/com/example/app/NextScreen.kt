package com.example.app

import CustomInputField
import DeleveryFavoriteFoodText
import FooterText
import GradientButton
import MainTextTitle
import SocialLoginButton
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.app.ui.theme.YongFontFamily
import com.example.app.ui.theme.startRed

@Composable
fun NextScreen(onSignUpClick: () -> Unit = {}) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val scrollState = rememberScrollState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color.White
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color.White)
                // Добавляем вертикальный скролл, чтобы на маленьких экранах всё влезло
                .verticalScroll(scrollState)
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // 1. ОТСТУП СВЕРХУ (Чтобы логотип не прилипал к краю)
            Spacer(modifier = Modifier.height(40.dp))

            // 2. ЛОГОТИП (Немного уменьшил для элегантности)
            Image(
                painter = painterResource(id = R.drawable.littlelogo),
                contentDescription = "Logo",
                modifier = Modifier.size(80.dp)
            )

            // 3. ЗАГОЛОВКИ (MainTextTitle и Delevery должны быть компактными)
            MainTextTitle()
            Spacer(modifier = Modifier.height(4.dp))
            DeleveryFavoriteFoodText()

            Spacer(modifier = Modifier.height(20.dp))

            // 4. ТЕКСТ LOGIN (Уменьшил шрифт)
            Text(
                text = "Login to Your Account",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = startRed
            )

            Spacer(modifier = Modifier.height(20.dp))

            // 5. ПОЛЯ ВВОДА
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

            // 6. БЛОК "OR CONTINUE WITH" (Сделал более компактным)
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

            // 7. КНОПКИ СОЦСЕТЕЙ
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

            // 8. КНОПКА ЛОГИНА
            GradientButton("Login") {
                // Логика входа
            }

            Spacer(modifier = Modifier.height(16.dp))

            // 9. ССЫЛКА НА РЕГИСТРАЦИЮ
            Text(
                text = "Don't Have Account?",
                color = startRed,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.clickable { onSignUpClick() }
            )

            // 10. ОТСТУП ПЕРЕД ФУТЕРОМ
            // Используем фиксированный Spacer, так как weight плохо работает внутри скролла
            Spacer(modifier = Modifier.height(40.dp))

            // 11. ФУТЕР (Теперь он будет виден внизу после скролла)
            FooterText()

            // Финальный отступ снизу
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}