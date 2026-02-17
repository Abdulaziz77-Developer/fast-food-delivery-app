package com.example.app.presentation.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.app.R
import com.example.app.Presentation.ui.theme.AppTheme
import com.example.app.Presentation.ui.theme.startRed
import com.example.app.Presentation.ui.theme.YongFontFamily


// Импорты твоих компонентов (убедись, что они верны)
import AppFooter
import FoodItemCard
import com.example.app.presentation.components.BannerSection

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FoodExplorerScreen(
    onHomeClick: () -> Unit = {},
    onPopularClick: () -> Unit = {},
    onFoodClick: (FoodItemData) -> Unit = {},
    onCartClick: () -> Unit = {},
    onHistoryClick: () -> Unit = {},
    onProfileClick: () -> Unit = {},
    onNotificationClick: () -> Unit = {}
) {
    // Состояние для поиска (теперь вводить текст МОЖНО)
    var searchQuery by remember { mutableStateOf("") }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding(), // ГАРАНТИЯ: Поднимает всё над кнопками телефона
        bottomBar = {
            Surface(
                tonalElevation = 8.dp,
                shadowElevation = 15.dp, // Сделал тень побольше для четкости
                color = Color.White
            ) {
                AppFooter(
                    currentScreen = "food_explorer",
                    onHomeClick = onHomeClick,
                    onHistoryClick = onHistoryClick,
                    onCartClick = onCartClick,
                    onProfileClick = onProfileClick
                )
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /* Открыть меню категорий */ },
                containerColor = Color(0xFF9CCC65),
                shape = RoundedCornerShape(15.dp),
                modifier = Modifier.padding(bottom = 70.dp) // Чуть выше, чтобы не мешать футеру
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.menuphoto),
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFFBFBFB))
                .padding(innerPadding)
        ) {
            // Заголовок
            HeaderSection(onNotificationClick = onNotificationClick)

            // Секция поиска (передаем состояние)
            SearchSection(
                value = searchQuery,
                onValueChange = { searchQuery = it }
            )

            Spacer(modifier = Modifier.height(15.dp))

            // Твоя карусель
            BannerSection()

            // Список Popular
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(bottom = 20.dp)
            ) {
                stickyHeader {
                    Surface(
                        color = Color(0xFFFBFBFB),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 24.dp, vertical = 12.dp)
                                .clickable { onPopularClick() },
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Popular",
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp,
                                color = startRed,
                                fontFamily = YongFontFamily
                            )
                            Text(
                                text = "View More",
                                fontSize = 14.sp,
                                color = Color(0xFF66BB6A),
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }
                }

                items(getPopularFoodData()) { food ->
                    FoodItemCard(food = food, onClick = { onFoodClick(food) })
                }
            }
        }
    }
}

@Composable
fun HeaderSection(onNotificationClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 24.dp, end = 24.dp, top = 20.dp, bottom = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Explore Your\nFavorite Food",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = YongFontFamily,
            color = startRed,
            lineHeight = 32.sp,
            modifier = Modifier.weight(1f)
        )

        Surface(
            modifier = Modifier
                .size(45.dp)
                .clickable { onNotificationClick() },
            shape = RoundedCornerShape(12.dp),
            color = Color(0xFFFDECEC)
        ) {
            Box(contentAlignment = Alignment.Center) {
                Icon(
                    painter = painterResource(id = R.drawable.notification_bell),
                    contentDescription = null,
                    tint = Color(0xFF66BB6A),
                    modifier = Modifier.size(26.dp)
                )
            }
        }
    }
}

@Composable
fun SearchSection(value: String, onValueChange: (String) -> Unit) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text("What do you want to order?", color = Color(0xFFD3A0A0), fontSize = 14.sp) },
        leadingIcon = { Icon(painterResource(id = R.drawable.search), null, tint = startRed, modifier = Modifier.size(20.dp)) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
        shape = RoundedCornerShape(15.dp),
        singleLine = true,
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = Color(0xFFFDECEC),
            unfocusedContainerColor = Color(0xFFFDECEC),
            focusedBorderColor = startRed, // Подсветим при вводе
            unfocusedBorderColor = Color.Transparent,
            cursorColor = startRed
        )
    )
}

// Данные и Превью
data class FoodItemData(
    val name: String,
    val restaurant: String,
    val price: Int,
    val imageRes: Int,
    val description: String
)

fun getPopularFoodData() = listOf(
    FoodItemData("Herbal Pancake", "Warung Herbal", 7, R.drawable.menuphoto, "Description..."),
    FoodItemData("Fruit Salad", "Wijie Resto", 5, R.drawable.itemfood1, "Description..."),
    FoodItemData("Green Noodle", "Noodle Home", 15, R.drawable.itemfood2, "Description..."),
    FoodItemData("Green Noodle", "Noodle Home", 15, R.drawable.itemfood2, "Description..."),
)

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun FoodExplorerScreenPreview() {
    AppTheme {
        // Пустые лямбды для превью
        FoodExplorerScreen()
    }
}