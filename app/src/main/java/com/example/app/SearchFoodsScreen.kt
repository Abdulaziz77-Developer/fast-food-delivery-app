package com.example.app
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.app.ui.theme.AppTheme
import com.example.app.ui.theme.YongFontFamily
import com.example.app.ui.theme.startRed

// Исправлено: Уникальное имя модели, чтобы не было ошибок дублирования
data class SearchFoodItem(
    val name: String,
    val restaurant: String,
    val price: Int,
    val imageRes: Int
)

@Composable
fun SearchFoodsScreen() {
    var currentScreen by remember { mutableStateOf("search_list") }

    if (currentScreen == "search_list") {
        SearchListContent(onFoodClick = { currentScreen = "restaurant_details" })
    } else {
        RestaurantDetailsContent(onBackClick = { currentScreen = "search_list" })
    }
}

@Composable
fun SearchListContent(onFoodClick: () -> Unit) {
    Scaffold(
        bottomBar = { CustomBottomNavigation(onHomeClick = onFoodClick) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(innerPadding) // Используем PaddingValues от Scaffold
        ) {
            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(
                value = "",
                onValueChange = {},
                placeholder = { Text("What do you want to order?", color = Color(0xFFD3A0A0)) },
                leadingIcon = {
                    Icon(painterResource(id = R.drawable.search), null, tint = startRed)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                shape = RoundedCornerShape(15.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color(0xFFFDECEC),
                    unfocusedContainerColor = Color(0xFFFDECEC),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )

            Text(
                text = "Popular",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = YongFontFamily,
                color = startRed,
                modifier = Modifier
                    .padding(24.dp)
                    .align(Alignment.CenterHorizontally)
            )

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                // ИСПРАВЛЕНО: `PaddingValues` теперь использует правильные параметры
                contentPadding = PaddingValues(start = 24.dp, end = 24.dp, bottom = 20.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(getPopularSearchData()) { food ->
                    FoodCard(food, onClick = onFoodClick)
                }
            }
        }
    }
}

@Composable
fun FoodCard(food: SearchFoodItem, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(4.dp)
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
            Column(modifier = Modifier.padding(start = 16.dp).weight(1f)) {
                Text(text = food.name, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Text(text = food.restaurant, color = Color.Gray, fontSize = 12.sp)
            }
            Column(horizontalAlignment = Alignment.End) {
                Text(text = "$${food.price}", color = startRed, fontWeight = FontWeight.Bold, fontSize = 22.sp)
                Icon(painterResource(id = R.drawable.cheked), null, modifier = Modifier.size(20.dp))
            }
        }
    }
}

@Composable
fun RestaurantDetailsContent(onBackClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 24.dp)
    ) {
        Spacer(modifier = Modifier.height(40.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(
                onClick = onBackClick,
                modifier = Modifier
                    .size(32.dp)
                    .background(Color(0xFFFFB74D).copy(0.2f), CircleShape)
            ) {
                Icon(painterResource(id = R.drawable.feedback), null, tint = Color(0xFFFFB74D))
            }
            Text(
                text = "Restaurant Name",
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center,
                fontSize = 24.sp,
                fontFamily = YongFontFamily,
                color = startRed,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Image(
            painter = painterResource(id = R.drawable.menupicture),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clip(RoundedCornerShape(15.dp)),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(text = "Short description", fontSize = 18.sp, fontWeight = FontWeight.Bold)
        Text(
            text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
            fontSize = 14.sp, color = Color.Gray, lineHeight = 20.sp
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(text = "Menu", fontSize = 20.sp, fontWeight = FontWeight.Bold, fontFamily = YongFontFamily)

        val menuItems = listOf("Noddle", "Salad", "Burger", "Herbal Pan Cake", "Momos")
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.padding(top = 16.dp).fillMaxSize()
        ) {
            items(menuItems) { item ->
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(text = "• $item", fontSize = 16.sp)
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(text = "See Details", color = startRed.copy(0.6f), fontSize = 12.sp)
                        Spacer(modifier = Modifier.width(8.dp))
                        Icon(painterResource(id = R.drawable.cheked), null, modifier = Modifier.size(18.dp))
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomBottomNavigation(onHomeClick: () -> Unit) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .clip(RoundedCornerShape(topStart = 25.dp, topEnd = 25.dp)),
        color = Color.White,
        shadowElevation = 10.dp
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painterResource(id = R.drawable.home),
                null,
                modifier = Modifier
                    .size(28.dp)
                    .clickable { onHomeClick() }
            )

            BadgedBox(badge = { Badge { Text("7") } }) {
                Icon(painterResource(id = R.drawable.shoppingcart), null, modifier = Modifier.size(28.dp))
            }

            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE8F5E9)),
                shape = RoundedCornerShape(12.dp),
                // ИСПРАВЛЕНО: Добавлены .dp
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(painterResource(id = R.drawable.search), null, tint = Color(0xFF2E7D32), modifier = Modifier.size(20.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Search", color = Color(0xFF2E7D32), fontSize = 14.sp)
                }
            }

            Icon(painterResource(id = R.drawable.barmenu), null, modifier = Modifier.size(28.dp))
            Icon(painterResource(id = R.drawable.user1), null, modifier = Modifier.size(28.dp))
        }
    }
}

fun getPopularSearchData() = listOf(
    SearchFoodItem("Herbal Pancake", "Warung Herbal", 7, R.drawable.menupicture),
    SearchFoodItem("Fruit Salad", "Wijie Resto", 5, R.drawable.menupicture),
    SearchFoodItem("Green Noddle", "Noodle Home", 15, R.drawable.menupicture)
)

// Это дополнительное превью. Если основное не работает, проверь это.
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DiagnosticPreview() {
    // Используем стандартную MaterialTheme вместо AppTheme для проверки
    MaterialTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color.White
        ) {
            SearchFoodsScreen()
        }
    }
}