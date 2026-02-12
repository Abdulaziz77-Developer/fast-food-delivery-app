package com.example.app

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
import com.example.app.ui.theme.AppTheme
import com.example.app.ui.theme.YongFontFamily
import com.example.app.ui.theme.startRed

@Composable
fun FeedbackScreen(
    onBackClick: () -> Unit,
    onSendClick: () -> Unit
) {
    // Состояние для текста отзыва
    var feedbackText by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(24.dp)
    ) {
        // Кнопка назад и Заголовок
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.feedback), // Используй свою иконку назад
                contentDescription = "Back",
                tint = Color(0xFFF9A825), // Оранжевый цвет как на скрине
                modifier = Modifier
                    .size(32.dp)
                    .clickable { onBackClick() }
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = "Write Your Feedback Here",
                fontSize = 20.sp,
                fontFamily = YongFontFamily,
                color = startRed,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(40.dp))

        // Поле ввода отзыва
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp),
            shape = RoundedCornerShape(22.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(6.dp)
        ) {
            Column(
                modifier = Modifier.padding(20.dp)
            ) {
                Text(
                    text = "Short description",
                    color = startRed,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    fontFamily = YongFontFamily
                )

                TextField(
                    value = feedbackText,
                    onValueChange = { feedbackText = it },
                    placeholder = {
                        Text(
                            "Lorem ipsum dolor sit amet, consectetur adipiscing elit...",
                            color = startRed.copy(alpha = 0.5f),
                            fontSize = 14.sp
                        )
                    },
                    modifier = Modifier.fillMaxSize(),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    )
                )
            }
        }

        Spacer(modifier = Modifier.height(40.dp))

        // Кнопка Send
        Button(
            onClick = onSendClick,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .width(180.dp)
                .height(55.dp),
            shape = RoundedCornerShape(15.dp),
            colors = ButtonDefaults.buttonColors(containerColor = startRed)
        ) {
            Text(
                text = "Send",
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = YongFontFamily
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FeedbackScreenPreview() {
    AppTheme {
        FeedbackScreen(onBackClick = {}, onSendClick = {})
    }
}