package com.example.app

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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

// Модель данных (без изменений)
data class HistoryItem(
    val name: String,
    val restaurant: String,
    val price: Int,
    val imageRes: Int
)

@Composable
fun HistoryScreen(
    onHomeClick: () -> Unit,
    onHistoryClick: () -> Unit,
    onCartClick: () -> Unit,    // ДОБАВЛЕНО: Теперь MainScreen увидит этот параметр
    onProfileClick: () -> Unit
) {
    val historyList = listOf(
        HistoryItem("Spacy fresh crab", "Waroenk kita", 35, R.drawable.menuphoto),
        HistoryItem("Spacy fresh crab", "Waroenk kita", 35, R.drawable.menuphoto),
        HistoryItem("Spacy fresh crab", "Waroenk kita", 35, R.drawable.menuphoto)
    )

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            // ИСПОЛЬЗУЕМ УНИВЕРСАЛЬНЫЙ FOOTER С 4 ПАРАМЕТРАМИ
            AppFooter(
                currentScreen = "history",
                onHomeClick = onHomeClick,
                onHistoryClick = onHistoryClick,
                onCartClick = onCartClick, // ПЕРЕДАЕМ КЛИК КОРЗИНЫ
                onProfileClick = onProfileClick
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF8F9FF))
                .padding(innerPadding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp)
            ) {
                Spacer(modifier = Modifier.height(40.dp))

                Text(
                    text = "Hello, lorem ipsum",
                    fontSize = 24.sp,
                    fontFamily = YongFontFamily,
                    color = startRed,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )

                Spacer(modifier = Modifier.height(20.dp))

                DeliveryStatusCard()

                Spacer(modifier = Modifier.height(20.dp))

                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(15.dp),
                    contentPadding = PaddingValues(bottom = 20.dp)
                ) {
                    items(historyList) { item ->
                        HistoryOrderCard(item)
                    }
                }
            }
        }
    }
}

// Вспомогательные функции (DeliveryStatusCard и HistoryOrderCard) оставляем как у тебя
@Composable
fun DeliveryStatusCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(25.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column(modifier = Modifier.padding(15.dp)) {
            Text(
                text = "Call For Information",
                color = startRed,
                fontFamily = YongFontFamily,
                fontSize = 18.sp,
                modifier = Modifier.padding(bottom = 10.dp)
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.userprofile),
                    contentDescription = null,
                    modifier = Modifier.size(60.dp).clip(RoundedCornerShape(15.dp)),
                    contentScale = ContentScale.Crop
                )
                Column(modifier = Modifier.padding(start = 15.dp)) {
                    Text(text = "Mr Kemplas", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(painterResource(id = R.drawable.timeicon), null, tint = Color(0xFF66BB6A), modifier = Modifier.size(14.dp))
                        Text(text = " 20 minutes on the way", color = Color.Gray, fontSize = 12.sp)
                    }
                }
            }
            Spacer(modifier = Modifier.height(15.dp))
            Button(
                onClick = { /* Вызов */ },
                modifier = Modifier.fillMaxWidth().height(45.dp),
                shape = RoundedCornerShape(15.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF1FDF5))
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(painterResource(id = R.drawable.calllogo), null, tint = Color(0xFF66BB6A), modifier = Modifier.size(20.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "Call", color = Color(0xFF66BB6A), fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@Composable
fun HistoryOrderCard(item: HistoryItem) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(1.dp)
    ) {
        Row(modifier = Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = item.imageRes),
                contentDescription = null,
                modifier = Modifier.size(60.dp).clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )
            Column(modifier = Modifier.weight(1f).padding(horizontal = 12.dp)) {
                Text(text = item.name, fontWeight = FontWeight.Bold, fontSize = 15.sp)
                Text(text = item.restaurant, color = Color.Gray, fontSize = 12.sp)
                Text(text = "$ ${item.price}", color = startRed, fontWeight = FontWeight.Bold, fontSize = 18.sp)
            }
            Button(
                onClick = { /* Повторить */ },
                colors = ButtonDefaults.buttonColors(containerColor = startRed),
                shape = RoundedCornerShape(12.dp),
                contentPadding = PaddingValues(horizontal = 15.dp, vertical = 5.dp)
            ) {
                Text(text = "Buy Again", fontSize = 12.sp, color = Color.White)
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HistoryPreview() {
    AppTheme {
        HistoryScreen(onHomeClick = {}, onHistoryClick = {}, onCartClick = {}, onProfileClick = {})
    }
}