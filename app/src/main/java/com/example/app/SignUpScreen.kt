package com.example.app

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.app.ui.theme.YongFontFamily
import com.example.app.ui.theme.startRed

@Composable
@Preview(showBackground = true)
fun SignUpScreen(onAlreadyHaveAccountClick: () -> Unit ={}) {
    // Состояния для новых полей
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(40.dp))

            // Твой логотип
            Image(
                painter = painterResource(id = R.drawable.littlelogo),
                contentDescription = "Logo",
                modifier = Modifier.size(91.dp)
            )

            MainTextTitle() // Используем твой компонент заголовка

            Text(
                text = "Sign Up Here",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = startRed,
                modifier = Modifier.padding(top = 16.dp)
            )

            Spacer(modifier = Modifier.height(30.dp))

            // Поля ввода (используем твой CustomInputField)
            CustomInputField(value = name, onValueChange = { name = it }, placeholder = "Name")
            Spacer(modifier = Modifier.height(16.dp))
            CustomInputField(value = email, onValueChange = { email = it }, placeholder = "Email or Phone Number")
            Spacer(modifier = Modifier.height(16.dp))
            CustomInputField(value = password, onValueChange = { password = it }, placeholder = "Password")

            Text(
                text = "or",
                fontSize = 16.sp,
                color = Color.Gray,
                modifier = Modifier.padding(top = 20.dp)
            )

            Text(
                text = "Sign Up With",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                fontFamily = YongFontFamily,
                modifier = Modifier.padding(top = 10.dp)
            )

            // Кнопки соцсетей (твой SocialLoginButton)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                SocialLoginButton(
                    text = "Facebook",
                    iconRes = R.drawable.facebookicon,
                    onClick = { /* gRPC FB Sign Up */ },
                    modifier = Modifier.weight(1f)
                )
                SocialLoginButton(
                    text = "Google",
                    iconRes = R.drawable.googleicon,
                    onClick = { /* gRPC Google Sign Up */ },
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(30.dp))

            // Твоя кнопка с градиентом
            GradientButton(
                text = "Create Account",
                onClick = {
                    // Здесь будет логика регистрации через gRPC на Python бэкенд
                }
            )

            Spacer(modifier = Modifier.height(15.dp))

            // Ссылка на экран логина
            Text(
                text = "Already Have An Account?",
                color = startRed,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.clickable { onAlreadyHaveAccountClick() }
            )
        }

        // Твой футер в самом низу
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 20.dp),
            contentAlignment = Alignment.BottomCenter
        ) {
            FooterText()
        }
    }
}