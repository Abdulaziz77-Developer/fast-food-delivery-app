package com.example.app.Presentation.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.app.R
import com.example.app.Presentation.theme.AppTheme
import com.example.app.Presentation.theme.YongFontFamily
import com.example.app.Presentation.theme.startRed

@Composable
fun CongratsScreen(
    onGoHomeClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Заголовки
            Text(
                text = "Congrats",
                fontSize = 32.sp,
                fontFamily = YongFontFamily,
                color = startRed,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Your Order Placed",
                fontSize = 22.sp,
                fontFamily = YongFontFamily,
                color = startRed,
                fontWeight = FontWeight.Medium
            )

            Spacer(modifier = Modifier.height(50.dp))

            // Иллюстрация с галочкой и точками
            Box(
                modifier = Modifier.size(200.dp),
                contentAlignment = Alignment.Center
            ) {
                // Декоративные точки (как на скриншоте)
                Box(modifier = Modifier.size(12.dp).offset(x = (-60).dp, y = (-50).dp).background(Color(0xFF66BB6A), CircleShape))
                Box(modifier = Modifier.size(8.dp).offset(x = 70.dp, y = (-30).dp).background(Color(0xFF66BB6A), CircleShape))
                Box(modifier = Modifier.size(10.dp).offset(x = (-70).dp, y = 40.dp).background(Color(0xFF66BB6A), CircleShape))
                Box(modifier = Modifier.size(6.dp).offset(x = 60.dp, y = 50.dp).background(Color(0xFF66BB6A), CircleShape))

                // Основной зеленый круг
                Surface(
                    modifier = Modifier.size(140.dp),
                    shape = CircleShape,
                    color = Color(0xFF58E38F)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(
                            painter = painterResource(id = R.drawable.chek_icon),
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(70.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(60.dp))

            // Кнопка Go Home
            Button(
                onClick = onGoHomeClick,
                modifier = Modifier
                    .width(180.dp)
                    .height(55.dp),
                shape = RoundedCornerShape(15.dp),
                colors = ButtonDefaults.buttonColors(containerColor = startRed)
            ) {
                Text(
                    text = "Go Home",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CongratsScreenPreview() {
    AppTheme {
        CongratsScreen(
            onGoHomeClick = {
                // This is just a preview, so we don't need real logic here
                println("Go Home clicked in Preview")
            }
        )
    }
}