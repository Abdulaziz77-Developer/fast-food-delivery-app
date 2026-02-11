package com.example.app

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.app.ui.theme.AppTheme
import com.example.app.ui.theme.YongFontFamily
import com.example.app.ui.theme.startRed

@Composable
fun NotificationScreen(
    onBackClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 24.dp, vertical = 40.dp)
    ) {
        // Кнопка назад (Оранжевая стрелка в светлом квадрате)
        Box(
            modifier = Modifier
                .size(45.dp)
                .background(
                    color = Color(0xFFFFEFD5), // Светло-оранжевый фон
                    shape = RoundedCornerShape(15.dp)
                )
                .clickable { onBackClick() },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.feedback), // Убедись, что иконка есть в drawable
                contentDescription = "Back",
                tint = Color(0xFFDAA520), // Золотистый цвет стрелки
                modifier = Modifier.size(24.dp)
            )
        }

        Spacer(modifier = Modifier.height(30.dp))

        // Заголовок (Hello, lorem ipsum)
        Text(
            text = "Hello, lorem ipsum",
            fontSize = 24.sp,
            fontFamily = YongFontFamily,
            color = startRed,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(40.dp))

        // Список уведомлений
        Column(verticalArrangement = Arrangement.spacedBy(30.dp)) {

            // 1. Отмена заказа
            NotificationRow(
                iconRes = R.drawable.sadface,
                message = "Your order has been Canceled Successfully",
                iconColor = Color(0xFFFFA07A) // Оранжево-красный
            )

            // 2. Курьер взял заказ
            NotificationRow(
                iconRes = R.drawable.caricon,
                message = "Order has been taken by the driver",
                iconColor = Color(0xFF66BB6A) // Зеленый
            )

            // 3. Заказ успешно размещен
            NotificationRow(
                iconRes = R.drawable.chek_icon,
                message = "Congrats Your Order Placed",
                iconColor = Color(0xFF66BB6A) // Зеленый
            )
        }
    }
}

@Composable
fun NotificationRow(
    iconRes: Int,
    message: String,
    iconColor: Color
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        // Иконка статуса
        Icon(
            painter = painterResource(id = iconRes),
            contentDescription = null,
            tint = iconColor,
            modifier = Modifier.size(35.dp)
        )

        Spacer(modifier = Modifier.width(16.dp))

        // Текст сообщения
        Text(
            text = message,
            fontSize = 15.sp,
            color = startRed,
            fontFamily = YongFontFamily,
            fontWeight = FontWeight.Medium
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun NotificationScreenPreview() {
    AppTheme {
        NotificationScreen(onBackClick = {})
    }
}