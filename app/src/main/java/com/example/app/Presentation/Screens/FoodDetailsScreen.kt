package com.example.app.Presentation.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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
import com.example.app.R
import com.example.app.presentation.screens.FoodItemData
import com.example.app.Presentation.theme.AppTheme
import com.example.app.Presentation.theme.YongFontFamily
import com.example.app.Presentation.theme.startRed

@Composable
fun FoodDetailsScreen(
    food: FoodItemData,
    onBackClick: () -> Unit,
    onAddToCartClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            // 1. ФИКС ВЕРХА: Отодвигаем от челки и времени
            .statusBarsPadding()
            // 2. ФИКС НИЗА: Поднимаем кнопку над системными кнопками Android
            .navigationBarsPadding()
            .padding(horizontal = 24.dp)
    ) {
        // Убрали Spacer(40.dp), так как statusBarsPadding уже дал нужный отступ
        Spacer(modifier = Modifier.height(16.dp))

        // Верхняя панель
        Box(modifier = Modifier.fillMaxWidth()) {
            IconButton(
                onClick = onBackClick,
                modifier = Modifier
                    .size(45.dp)
                    .background(Color(0xFFFDECEC), RoundedCornerShape(12.dp)) // Сделал кнопку назад красивее
                    .align(Alignment.CenterStart)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.feedback),
                    contentDescription = "Back",
                    tint = Color(0xFFFFB74D),
                    modifier = Modifier.size(20.dp)
                )
            }
            Text(
                text = "Details", // Обычно здесь пишут просто Details, а имя еды ниже
                modifier = Modifier.align(Alignment.Center),
                fontSize = 22.sp,
                fontFamily = YongFontFamily,
                color = startRed,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Картинка
        Image(
            painter = painterResource(id = food.imageRes),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(260.dp)
                .clip(RoundedCornerShape(25.dp)),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Название и описание
        Text(
            text = food.name,
            fontSize = 26.sp,
            fontFamily = YongFontFamily,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(text = "Short description", fontSize = 18.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = food.description,
            fontSize = 14.sp,
            color = Color.Gray,
            lineHeight = 22.sp
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(text = "Ingredients", fontSize = 18.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = " • Strawberry\n • Cream\n • wheat",
            fontSize = 14.sp,
            color = Color.Black,
            lineHeight = 24.sp
        )

        // Этот Spacer(weight(1f)) выталкивает кнопку в самый низ,
        // но navigationBarsPadding не даст ей уйти за границы
        Spacer(modifier = Modifier.weight(1f))

        // КНОПКА Add To Cart (Теперь она всегда над кнопками телефона)
        Button(
            onClick = onAddToCartClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(15.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFDA332E)),
            elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp)
        ) {
            Text(
                text = "Add To Cart",
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }

        // Небольшой отступ от самого края экрана для красоты
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun FoodDetailsPreview() {
    AppTheme {
        val mockFood = FoodItemData(
            name = "Herbal Pancake",
            restaurant = "Warung Herbal",
            price = 7,
            imageRes = R.drawable.menuphoto,
            description = "Delicious and crispy herbal pancakes made with fresh mint and honey. A perfect healthy start to your day."
        )
        FoodDetailsScreen(
            food = mockFood,
            onBackClick = {},
            onAddToCartClick = {}
        )
    }
}