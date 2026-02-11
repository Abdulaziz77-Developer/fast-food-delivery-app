package com.example.app

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
import com.example.app.ui.theme.AppTheme
import com.example.app.ui.theme.YongFontFamily
import com.example.app.ui.theme.startRed

@Composable
fun FoodDetailsScreen(
    food: FoodItemData,
    onBackClick: () -> Unit,
    onAddToCartClick: () -> Unit // Функция перехода в корзину
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 24.dp)
    ) {
        Spacer(modifier = Modifier.height(40.dp))

        // Верхняя панель
        Box(modifier = Modifier.fillMaxWidth()) {
            IconButton(
                onClick = onBackClick,
                modifier = Modifier.size(32.dp).align(Alignment.CenterStart)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.feedback),
                    contentDescription = "Back",
                    tint = Color(0xFFFFB74D)
                )
            }
            Text(
                text = food.name,
                modifier = Modifier.align(Alignment.Center),
                fontSize = 24.sp,
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
                .height(240.dp)
                .clip(RoundedCornerShape(20.dp)),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(text = "Short description", fontSize = 18.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = food.description,
            fontSize = 14.sp,
            color = Color.Gray,
            lineHeight = 20.sp
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

        Spacer(modifier = Modifier.weight(1f))

        // КНОПКА Add To Cart
        Button(
            onClick = { onAddToCartClick() }, // ВЫЗОВ ПЕРЕХОДА
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(15.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFDA332E)),
            elevation = ButtonDefaults.buttonElevation(defaultElevation = 8.dp)
        ) {
            Text(text = "Add To Cart", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(24.dp))
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
            onAddToCartClick = {} // Пустая функция для превью
        )
    }
}