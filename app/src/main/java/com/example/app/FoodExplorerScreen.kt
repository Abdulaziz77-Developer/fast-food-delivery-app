package com.example.app

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import com.example.app.ui.theme.AppTheme
import com.example.app.ui.theme.YongFontFamily
import com.example.app.ui.theme.startRed

@Composable
fun FoodExplorerScreen(
    onHomeClick: () -> Unit,
    onPopularClick: () -> Unit,
    onFoodClick: (FoodItemData) -> Unit,
    onCartClick: () -> Unit,
    onHistoryClick: () -> Unit,
    onProfileClick: () -> Unit
) {
    Scaffold(
        bottomBar = {
            // ИСПРАВЛЕНО: Добавлен onCartClick, чтобы аргументы совпали с функцией AppFooter
            AppFooter(
                currentScreen = "food_explorer",
                onHomeClick = onHomeClick,
                onHistoryClick = onHistoryClick,
                onCartClick = onCartClick,    // ЭТОГО ПАРАМЕТРА НЕ ХВАТАЛО
                onProfileClick = onProfileClick
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /* Меню */ },
                containerColor = Color(0xFF9CCC65),
                shape = RoundedCornerShape(15.dp),
                modifier = Modifier.padding(bottom = 10.dp)
            ) {
                Icon(painterResource(id = R.drawable.menuphoto), null, tint = Color.White)
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFFBFBFB))
                .padding(padding)
        ) {
            item { HeaderSection() }
            item { SearchSection() }
            item { BannerSection() }
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp, vertical = 8.dp)
                        .clickable { onPopularClick() },
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Popular", fontWeight = FontWeight.Bold, fontSize = 18.sp, color = startRed)
                    Text(text = "View More", fontSize = 12.sp, color = startRed)
                }
            }
            items(getPopularFoodData()) { food ->
                FoodItemCard(food = food, onClick = { onFoodClick(food) })
            }
        }
    }
}

// --- Остальные функции (без изменений, но включены для работы файла) ---

@Composable
fun FoodItemCard(food: FoodItemData, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 8.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Row(modifier = Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = food.imageRes),
                contentDescription = null,
                modifier = Modifier.size(64.dp).clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )
            Column(modifier = Modifier.padding(start = 16.dp).weight(1f)) {
                Text(text = food.name, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Text(text = food.restaurant, color = Color.Gray, fontSize = 12.sp)
            }
            Text(text = "$${food.price}", color = startRed, fontWeight = FontWeight.ExtraBold, fontSize = 22.sp)
        }
    }
}

@Composable
fun HeaderSection() {
    Row(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp, vertical = 20.dp),
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
            painterResource(id = R.drawable.notification_bell),
            null,
            tint = Color(0xFF66BB6A),
            modifier = Modifier.size(32.dp)
        )
    }
}

@Composable
fun SearchSection() {
    OutlinedTextField(
        value = "",
        onValueChange = {},
        placeholder = { Text("What do you want to order?", color = Color(0xFFD3A0A0)) },
        leadingIcon = { Icon(painterResource(id = R.drawable.search), null, tint = startRed) },
        modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp),
        shape = RoundedCornerShape(15.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = Color(0xFFFDECEC),
            unfocusedContainerColor = Color(0xFFFDECEC),
            focusedBorderColor = Color.Transparent,
            unfocusedBorderColor = Color.Transparent
        )
    )
}

@Composable
fun BannerSection() {
    LazyRow(
        contentPadding = PaddingValues(horizontal = 24.dp, vertical = 20.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(listOf(R.drawable.banner1, R.drawable.banner1)) { bannerId ->
            Image(
                painter = painterResource(id = bannerId),
                contentDescription = null,
                modifier = Modifier.width(325.dp).height(160.dp).clip(RoundedCornerShape(20.dp)),
                contentScale = ContentScale.FillBounds
            )
        }
    }
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
    FoodItemData("Green Noodle", "Noodle Home", 15, R.drawable.itemfood2, "Description...")
)

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun FoodExplorerScreenPreview() {
    AppTheme {
        FoodExplorerScreen({}, {}, {}, {}, {}, {})
    }
}