package com.example.app.Presentation.Screens

import EditInputField
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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



@Composable
fun EditOrderScreen(
    onBackClick: () -> Unit = {},
    onPlaceOrderClick: () -> Unit = {}
) {
    var name by remember { mutableStateOf("lorem ipsum") }
    var address by remember { mutableStateOf("Lorem ipsum is placeholder text commonly used...") }
    var phone by remember { mutableStateOf("123456789") }

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .statusBarsPadding() // ФИКС: Отступ от верхних иконок (время, батарея)
            .navigationBarsPadding() // ФИКС: Отступ от нижних кнопок телефона
            .padding(horizontal = 24.dp)
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        // Back Button & Title
        Box(modifier = Modifier.fillMaxWidth()) {
            IconButton(
                onClick = onBackClick,
                modifier = Modifier
                    .size(40.dp)
                    .background(Color(0xFFFDECEC), RoundedCornerShape(12.dp))
                    .align(Alignment.CenterStart)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.feedback), // Используем рабочую иконку назад
                    contentDescription = "Back",
                    tint = Color(0xFFFFB200),
                    modifier = Modifier.size(24.dp)
                )
            }

            Text(
                text = "Edit Order",
                modifier = Modifier.align(Alignment.Center),
                fontSize = 24.sp,
                fontFamily = YongFontFamily,
                color = startRed,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Область ввода с прокруткой (чтобы клавиатура не закрывала поля)
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(scrollState)
        ) {
            EditInputField(label = "Name", value = name, onValueChange = { name = it })
            Spacer(modifier = Modifier.height(16.dp))
            EditInputField(label = "Address", value = address, onValueChange = { address = it })
            Spacer(modifier = Modifier.height(16.dp))
            EditInputField(label = "Phone", value = phone, onValueChange = { phone = it })

            Spacer(modifier = Modifier.height(24.dp))

            // Payment Method Section
            Text(
                text = "Payment Method",
                color = Color.Gray,
                fontSize = 14.sp,
                modifier = Modifier.padding(start = 8.dp, bottom = 8.dp)
            )

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFFBFBFB)),
                border = BorderStroke(1.dp, Color(0xFFEEEEEE))
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 20.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Cash on Delivery", color = Color.Black, fontWeight = FontWeight.Medium)
                    Image(
                        painter = painterResource(id = R.drawable.banner1),
                        contentDescription = null,
                        modifier = Modifier.height(30.dp)
                    )
                }
            }
        }

        // КНОПКА Place My Order (Всегда над кнопками телефона)
        Button(
            onClick = onPlaceOrderClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .padding(vertical = 4.dp),
            shape = RoundedCornerShape(15.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.White),
            border = BorderStroke(1.dp, Color(0xFFF0F0F0)),
            elevation = ButtonDefaults.buttonElevation(defaultElevation = 6.dp)
        ) {
            Text(
                text = "Place My Order",
                color = startRed,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                fontFamily = YongFontFamily
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun EditOrderPreview() {
    AppTheme {
        EditOrderScreen()
    }
}