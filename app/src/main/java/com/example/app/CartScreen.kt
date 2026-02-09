package com.example.app

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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

// Модель для элемента корзины
data class CartItem(
    val id: Int,
    val name: String,
    val restaurant: String,
    val price: Int,
    val imageRes: Int,
    var quantity: Int = 1
)

@Composable
fun CartScreen(onBackClick: () -> Unit) {
    // Временный список данных
    val cartItems = remember {
        mutableStateListOf(
            CartItem(1, "Spacy fresh crab", "Waroenk kita", 35, R.drawable.menuphoto),
            CartItem(2, "Spacy fresh crab", "Waroenk kita", 35, R.drawable.menuphoto),
            CartItem(3, "Spacy fresh crab", "Waroenk kita", 35, R.drawable.menuphoto)
        )
    }

    Box(modifier = Modifier.fillMaxSize().background(Color(0xFFFBFBFB))) {
        Column(modifier = Modifier.fillMaxSize()) {

            // Заголовок страницы
            Text(
                text = "Cart",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 40.dp, bottom = 10.dp),
                textAlign = TextAlign.Center,
                fontSize = 24.sp,
                fontFamily = YongFontFamily,
                color = startRed,
                fontWeight = FontWeight.Bold
            )

            // Список товаров в корзине
            LazyColumn(
                modifier = Modifier.weight(1f),
                contentPadding = PaddingValues(horizontal = 24.dp, vertical = 10.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(cartItems) { item ->
                    CartItemCard(item)
                }
            }

            // Нижний блок с итогами (Красная карточка)
            CartSummarySection()
        }
    }
}

@Composable
fun CartItemCard(item: CartItem) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(22.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = item.imageRes),
                contentDescription = null,
                modifier = Modifier
                    .size(64.dp)
                    .clip(RoundedCornerShape(15.dp)),
                contentScale = ContentScale.Crop
            )

            Column(modifier = Modifier.padding(start = 16.dp).weight(1f)) {
                Text(text = item.name, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Text(text = item.restaurant, color = Color.Gray, fontSize = 12.sp)
                Text(text = "$ ${item.price}", color = startRed, fontWeight = FontWeight.Bold, fontSize = 18.sp)
            }

            // Кнопки управления количеством
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Минус
                Surface(
                    modifier = Modifier.size(26.dp).clickable { /* логика - */ },
                    shape = RoundedCornerShape(8.dp),
                    color = Color(0xFFE8F5E9)
                ) {
                    Icon(painterResource(id = R.drawable.minusicon), null, tint = startRed, modifier = Modifier.padding(6.dp))
                }

                Text(text = "${item.quantity}", fontWeight = FontWeight.Bold)

                // Плюс
                Surface(
                    modifier = Modifier.size(26.dp).clickable { /* логика + */ },
                    shape = RoundedCornerShape(8.dp),
                    color = startRed
                ) {
                    Icon(painterResource(id = R.drawable.addicon), null, tint = Color.White, modifier = Modifier.padding(6.dp))
                }

                // Иконка удаления (корзина)
                Icon(
                    painter = painterResource(id = R.drawable.deleteicon),
                    contentDescription = null,
                    modifier = Modifier.padding(start = 8.dp).size(24.dp).clickable { /* удалить */ }
                )
            }
        }
    }
}

@Composable
fun CartSummarySection() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp),
        shape = RoundedCornerShape(25.dp),
        colors = CardDefaults.cardColors(containerColor = startRed)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            SummaryLine("Sub-Total", "120 $")
            SummaryLine("Delivery Charge", "10 $")
            SummaryLine("Discount", "20 $")

            Spacer(modifier = Modifier.height(12.dp))

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text("Total", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.ExtraBold)
                Text("150 $", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.ExtraBold)
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Белая кнопка Proceed
            Button(
                onClick = { /* Оформить заказ */ },
                modifier = Modifier.fillMaxWidth().height(56.dp),
                shape = RoundedCornerShape(15.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.White)
            ) {
                Text(text = "Proceed", color = startRed, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            }
        }
    }
}

@Composable
fun SummaryLine(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 2.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(label, color = Color.White, fontSize = 14.sp)
        Text(value, color = Color.White, fontSize = 14.sp)
    }
}

@Preview(showBackground = true,showSystemUi = true)
@Composable
fun CartPreview() {
    AppTheme {
        CartScreen(onBackClick = {})
    }
}