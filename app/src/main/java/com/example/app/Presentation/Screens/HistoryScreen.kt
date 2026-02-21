package com.example.app.Presentation.Screens

import AppFooter
import DeliveryStatusCard
import HistoryOrderCard
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.app.R
import com.example.app.Presentation.theme.AppTheme
import com.example.app.Presentation.theme.YongFontFamily
import com.example.app.Presentation.theme.startRed

// Модель данных
data class HistoryItem(
    val name: String,
    val restaurant: String,
    val price: Int,
    val imageRes: Int
)

@Composable
fun HistoryScreen(
    onHomeClick: () -> Unit = {},
    onHistoryClick: () -> Unit = {},
    onCartClick: () -> Unit = {},
    onProfileClick: () -> Unit = {}
) {
    val historyList = listOf(
        HistoryItem("Spacy fresh crab", "Waroenk kita", 35, R.drawable.menuphoto),
        HistoryItem("Spacy fresh crab", "Waroenk kita", 35, R.drawable.menuphoto),
        HistoryItem("Spacy fresh crab", "Waroenk kita", 35, R.drawable.menuphoto)
    )

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding(), // 1. ФИКС: Поднимаем всё приложение над кнопками навигации
        bottomBar = {
            // Оборачиваем в Surface, чтобы футер имел фон и не просвечивал
            Surface(
                tonalElevation = 8.dp,
                shadowElevation = 12.dp,
                color = Color.White
            ) {
                AppFooter(
                    currentScreen = "history",
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
                .background(Color(0xFFF8F9FF))
                // 2. ФИКС: statusBarsPadding() отодвигает от верхней челки
                .statusBarsPadding()
                .padding(bottom = innerPadding.calculateBottomPadding())
                .padding(horizontal = 20.dp)
        ) {
            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Hello, lorem ipsum",
                fontSize = 24.sp,
                fontFamily = YongFontFamily,
                color = startRed,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(20.dp))

            DeliveryStatusCard()

            Spacer(modifier = Modifier.height(20.dp))

            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(15.dp),
                contentPadding = PaddingValues(bottom = 20.dp)
            ) {
                items(historyList) { item ->
                    HistoryOrderCard(item)
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HistoryPreview() {
    AppTheme {
        HistoryScreen()
    }
}