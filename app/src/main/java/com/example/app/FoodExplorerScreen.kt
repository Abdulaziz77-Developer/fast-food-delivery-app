package com.example.app

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
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
// Замени AppTheme на название темы из твоего файла ui.theme/Theme.kt
import com.example.app.ui.theme.AppTheme
import com.example.app.ui.theme.YongFontFamily
import com.example.app.ui.theme.startRed

@Composable
fun FoodExplorerScreen() {
    Scaffold(
        bottomBar = { CustomBottomMenu() },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /* Open Menu */ },
                containerColor = Color(0xFF9CCC65),
                shape = RoundedCornerShape(15.dp),
                modifier = Modifier.padding(bottom = 10.dp)
            ) {
                // Используй иконку меню
                Icon(
                    painter = painterResource(id = R.drawable.menuphoto),
                    contentDescription = null,
                    tint = Color.White
                )
            }
        },
        floatingActionButtonPosition = FabPosition.End
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFFBFBFB))
                .padding(padding)
        ) {
            // 1. Заголовок и колокольчик
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp, vertical = 20.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Explore Your Favorite Food",
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = YongFontFamily,
                        color = startRed,
                        modifier = Modifier.width(220.dp)
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.notification_bell),
                        contentDescription = null,
                        tint = Color(0xFF66BB6A),
                        modifier = Modifier.size(32.dp)
                    )
                }
            }

            // 2. Строка поиска
            item {
                OutlinedTextField(
                    value = "",
                    onValueChange = {},
                    placeholder = { Text("What do you want to order?", color = Color(0xFFD3A0A0)) },
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.search),
                            contentDescription = null,
                            tint = startRed
                        )
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
            }

            // 3. Карусель баннеров
            item {
                LazyRow(
                    contentPadding = PaddingValues(horizontal = 24.dp, vertical = 20.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(listOf(R.drawable.banner1, R.drawable.banner1)) { bannerId ->
                        Image(
                            painter = painterResource(id = bannerId),
                            contentDescription = null,
                            modifier = Modifier
                                .width(325.dp)
                                .height(160.dp)
                                .clip(RoundedCornerShape(20.dp)),
                            contentScale = ContentScale.FillBounds
                        )
                    }
                }
            }

            // 4. Заголовок "Popular"
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Popular", fontWeight = FontWeight.Bold, fontSize = 18.sp, color = startRed)
                    Text(text = "View More", fontSize = 12.sp, color = startRed)
                }
            }

            // 5. Список карточек
            items(getPopularFoodData()) { food ->
                FoodItemCard(food)
            }
        }
    }
}

@Composable
fun FoodItemCard(food: FoodItemData) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 8.dp),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(2.dp)
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
            Text(
                text = "$${food.price}",
                color = startRed,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 22.sp
            )
        }
    }
}

@Composable
fun CustomBottomMenu() {
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
            // Home
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .background(Color(0xFFE8F5E9), RoundedCornerShape(12.dp))
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Icon(painterResource(id = R.drawable.home), null, tint = Color(0xFF2E7D32))
                Text("Home", color = Color(0xFF2E7D32), fontSize = 12.sp)
            }
            // Остальные иконки из макета
            Icon(painterResource(id = R.drawable.shoppingcart), null, modifier = Modifier.size(28.dp))
            Icon(painterResource(id = R.drawable.caricon), null, modifier = Modifier.size(28.dp))
            Icon(painterResource(id = R.drawable.barmenu), null, modifier = Modifier.size(28.dp))
            Icon(painterResource(id = R.drawable.user1), null, modifier = Modifier.size(28.dp))
        }
    }
}

// Данные
data class FoodItemData(val name: String, val restaurant: String, val price: Int, val imageRes: Int)

fun getPopularFoodData() = listOf(
    FoodItemData("Herbal Pancake", "Warung Herbal", 7, R.drawable.menuphoto),
    FoodItemData("Fruit Salad", "Wijie Resto", 5, R.drawable.menuphoto),
    FoodItemData("Green Noodle", "Noodle Home", 15, R.drawable.menuphoto)
)

// ИСПРАВЛЕННОЕ ПРЕВЬЮ
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun FoodExplorerScreenPreview() {
    // ЗАМЕНИ AppTheme на свою тему, иначе будет Render Issue!
    AppTheme {
        FoodExplorerScreen()
    }
}