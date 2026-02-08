package com.example.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.app.ui.theme.AppTheme
import com.example.app.ui.theme.YongFontFamily
import com.example.app.ui.theme.cornerColor
import com.example.app.ui.theme.startRed
import com.example.app.ui.theme.testColor
import kotlinx.coroutines.delay

@Composable

fun NextScreen(onSignUpClick:() -> Unit={}) {
    // Состояния для хранения текста, который вводит пользователь
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Основной контент (скроллируемый Column на случай маленьких экранов)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(90.dp))

            // Логотип
            Image(
                painter = painterResource(id = R.drawable.littlelogo),
                contentDescription = "Logo",
                modifier = Modifier.size(91.dp)
            )

            MainTextTitle()
            Spacer(modifier = Modifier.height(8.dp))
            DeleveryFavoriteFoodText()

            Spacer(modifier = Modifier.height(30.dp))

            Text(
                text = "Login to Your Account",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = startRed
            )

            Spacer(modifier = Modifier.height(30.dp))

            // Поля ввода (теперь они работают, так как привязаны к переменным email и password)
            CustomInputField(
                value = email,
                onValueChange = { email = it },
                placeholder = "Email or Phone Number"
            )
            Spacer(modifier = Modifier.height(16.dp))
            CustomInputField(
                value = password,
                onValueChange = { password = it },
                placeholder = "Password"
            )

            Text(
                text = "Or",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Gray,
                modifier = Modifier.padding(top = 20.dp)
            )

            Text(
                text = "Continue With",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic,
                color = Color.Black,
                fontFamily = YongFontFamily,
                modifier = Modifier.padding(top = 10.dp)
            )

            // Кнопки соцсетей (Row внутри Column)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 25.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
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

            Spacer(modifier = Modifier.height(35.dp))

            // ИСПОЛЬЗОВАНИЕ ВАШЕЙ ГРАДИЕНТНОЙ КНОПКИ
            GradientButton("Login"
            ){

            }

            Spacer(modifier = Modifier.height(15.dp))

            Text(
                text = "Don't Have Account?",
                color = startRed,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.clickable {
                    onSignUpClick()
                }
            )
        }

        // Футер, прижатый к самому низу
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

// Вспомогательные функции остаются без изменений (MainTextTitle, SocialLoginButton и т.д.)

@Composable
fun SocialLoginButton(
    text: String,
    iconRes: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .height(55.dp)
            .shadow(elevation = 4.dp, shape = RoundedCornerShape(12.dp)), // Тень и форма
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        onClick = onClick
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = iconRes),
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = text,
                color = Color.Black,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp
            )
        }
    }
}
@Composable
fun MainTextTitle(){
    Text(
        text = "Wafes of Food",
        fontFamily = YongFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 44.sp,
        color = testColor,
        modifier = Modifier
            .padding(top = 32.dp)
    )
}

@Composable
fun DeleveryFavoriteFoodText(){
    Text(
        text = "Deliever Favorite Food",
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        color = startRed,
    )
}
@Composable
fun CustomInputField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            // Создаем эффект тени и закругления
            .shadow(elevation = 8.dp, shape = RoundedCornerShape(15.dp)),
        color = Color.White,
        shape = RoundedCornerShape(15.dp)
    ) {
        TextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = {
                Text(text = placeholder, color = Color.LightGray)
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                disabledContainerColor = Color.White,
                focusedIndicatorColor = Color.Transparent, // Убираем полоску снизу
                unfocusedIndicatorColor = Color.Transparent,
            ),
            singleLine = true,
            modifier = Modifier.fillMaxSize()
        )
    }
}
@Composable
fun FooterText(){
    Text(
        text = "Design By" +
                " \n NeatRoots",
        color = startRed,
        fontFamily = YongFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp
    )
}