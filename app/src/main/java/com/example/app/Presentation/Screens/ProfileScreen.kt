package com.example.app.Presentation.Screens

import AppFooter
import ProfileField
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.app.Presentation.theme.YongFontFamily
import com.example.app.Presentation.theme.startRed
import com.example.app.Presentation.theme.AppTheme

@Composable
fun ProfileScreen(
    onHomeClick: () -> Unit = {},
    onHistoryClick: () -> Unit = {},
    onCartClick: () -> Unit = {},
    onProfileClick: () -> Unit = {}
) {
    // 1. Создаем состояние прокрутки
    val scrollState = rememberScrollState()

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding(),
        bottomBar = {
            Surface(
                shadowElevation = 8.dp,
                color = Color.White
            ) {
                AppFooter(
                    currentScreen = "profile",
                    onHomeClick = onHomeClick,
                    onHistoryClick = onHistoryClick,
                    onCartClick = onCartClick,
                    onProfileClick = onProfileClick
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .statusBarsPadding()
                // 2. Добавляем отступ Scaffold и горизонтальные отступы
                .padding(bottom = innerPadding.calculateBottomPadding())
                .padding(horizontal = 24.dp)
        ) {
            // Заголовок остается статичным (не улетает при скролле)
            Text(
                text = "Hello, lorem ipsum",
                fontFamily = YongFontFamily,
                color = startRed,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 24.dp, bottom = 24.dp)
            )

            // 3. Оборачиваем поля в Column с вертикальным скроллом
            Column(
                modifier = Modifier
                    .weight(1f) // Занимает всё доступное место
                    .verticalScroll(scrollState), // ВКЛЮЧАЕМ СКРОЛЛ
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                ProfileField(label = "Name", value = "lorem ipsum")
                ProfileField(label = "Address", value = "Lorem ipsum is placeholder text commonly used in the graphic and visual arts...")
                ProfileField(label = "Email", value = "lorem ipsum@gmail.com")
                ProfileField(label = "Phone", value = "123456789")
                ProfileField(label = "Password", value = "***********", isPassword = true)

                // Можно добавить еще полей для теста скролла
                ProfileField(label = "Bio", value = "Food lover and tech enthusiast")

                Spacer(modifier = Modifier.height(20.dp))
            }

            // 4. Кнопка всегда "плавает" над футером, не перекрывая текст
            Button(
                onClick = { /* Save Action */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                shape = RoundedCornerShape(15.dp),
                border = BorderStroke(1.dp, Color(0xFFEEEEEE)),
                elevation = ButtonDefaults.buttonElevation(4.dp)
            ) {
                Text(
                    text = "Save Information",
                    color = startRed,
                    fontWeight = FontWeight.Bold,
                    fontFamily = YongFontFamily,
                    fontSize = 16.sp
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ProfileScreenPreview() {
    AppTheme {
        ProfileScreen()
    }
}