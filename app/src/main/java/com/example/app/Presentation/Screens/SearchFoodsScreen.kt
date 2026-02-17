package com.example.app.Presentation.Screens

import androidx.compose.foundation.Image
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.app.Presentation.ui.theme.AppTheme
import com.example.app.Presentation.ui.theme.startRed
import com.example.app.Presentation.ui.theme.YongFontFamily
import AppFooter
import com.example.app.R

// Класс данных
data class SearchFoodItem(
    val name: String,
    val restaurant: String,
    val price: Int,
    val imageRes: Int
)

@Composable
fun SearchFoodsScreen(
    onHomeClick: () -> Unit = {},
    onHistoryClick: () -> Unit = {},
    onCartClick: () -> Unit = {},
    onProfileClick: () -> Unit = {}
) {
    var currentScreen by remember { mutableStateOf("search_list") }
    var searchQuery by remember { mutableStateOf("") }

    // Список данных
    val allFood = getPopularSearchData()

    // Фильтрация: список обновляется сам, когда ты пишешь в поиск
    val filteredFood = remember(searchQuery) {
        if (searchQuery.isEmpty()) allFood
        else allFood.filter { it.name.contains(searchQuery, ignoreCase = true) }
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding(), // Поднимает над кнопками телефона
        bottomBar = {
            Surface(
                tonalElevation = 8.dp,
                shadowElevation = 12.dp,
                color = Color.White
            ) {
                AppFooter(
                    currentScreen = "search",
                    onHomeClick = onHomeClick,
                    onHistoryClick = onHistoryClick,
                    onCartClick = onCartClick,
                    onProfileClick = onProfileClick
                )
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color.White)
        ) {
            if (currentScreen == "search_list") {
                Column(modifier = Modifier.fillMaxSize()) {
                    Text(
                        text = "Search Food",
                        modifier = Modifier.padding(start = 24.dp, top = 24.dp, bottom = 12.dp),
                        fontSize = 24.sp,
                        fontFamily = YongFontFamily,
                        fontWeight = FontWeight.Bold,
                        color = startRed
                    )

                    // ПОЛЕ ПОИСКА
                    OutlinedTextField(
                        value = searchQuery,
                        onValueChange = { searchQuery = it },
                        placeholder = { Text("What do you want to order?", color = Color.LightGray, fontSize = 14.sp) },
                        leadingIcon = {
                            Icon(
                                painter = painterResource(id = R.drawable.search),
                                contentDescription = null,
                                tint = startRed,
                                modifier = Modifier.size(20.dp)
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 24.dp),
                        shape = RoundedCornerShape(15.dp),
                        singleLine = true,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedContainerColor = Color(0xFFFDECEC),
                            unfocusedContainerColor = Color(0xFFFDECEC),
                            focusedBorderColor = startRed,
                            unfocusedBorderColor = Color.Transparent,
                            cursorColor = startRed
                        )
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // СПИСОК (Исправлена ошибка PaddingValues)
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        // Исправленный вызов PaddingValues:
                        contentPadding = PaddingValues(start = 24.dp, end = 24.dp, bottom = 24.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(filteredFood) { food ->
                            SearchFoodCard(
                                food = food,
                                onClick = { currentScreen = "restaurant_details" }
                            )
                        }
                    }
                }
            } else {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(
                        "Restaurant Details Content",
                        modifier = Modifier.clickable { currentScreen = "search_list" },
                        color = startRed
                    )
                }
            }
        }
    }
}

@Composable
fun SearchFoodCard(food: SearchFoodItem, onClick: () -> Unit) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(15.dp),
        shadowElevation = 2.dp,
        color = Color.White
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = food.imageRes),
                contentDescription = null,
                modifier = Modifier
                    .size(64.dp)
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .padding(start = 16.dp)
                    .weight(1f)
            ) {
                Text(text = food.name, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Text(text = food.restaurant, color = Color.Gray, fontSize = 13.sp)
            }
            Text(
                text = "$${food.price}",
                color = startRed,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 18.sp
            )
        }
    }
}

fun getPopularSearchData() = listOf(
    SearchFoodItem("Herbal Pancake", "Warung Herbal", 7, R.drawable.menupicture),
    SearchFoodItem("Fruit Salad", "Wijie Resto", 5, R.drawable.menupicture),
    SearchFoodItem("Green Noddle", "Noodle Home", 15, R.drawable.menupicture)
)

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DiagnosticPreview() {
    AppTheme {
        SearchFoodsScreen()
    }
}