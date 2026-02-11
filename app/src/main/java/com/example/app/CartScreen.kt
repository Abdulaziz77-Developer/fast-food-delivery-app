package com.example.app

import androidx.compose.animation.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
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

// Модель данных
data class CartItem(
    val id: Int,
    val name: String,
    val restaurant: String,
    val price: Int,
    val imageRes: Int,
    val quantity: Int = 0
)

@Composable
fun CartScreen(
    onBackClick: () -> Unit,
    onHomeClick: () -> Unit
) {
    // Начальное количество 0, чтобы увидеть анимацию появления панели
    val cartItems = remember {
        mutableStateListOf(
            CartItem(1, "Spacy fresh crab", "Waroenk kita", 35, R.drawable.menuphoto, 0),
            CartItem(2, "Spacy fresh crab", "Waroenk kita", 35, R.drawable.menuphoto, 0),
            CartItem(3, "Spacy fresh crab", "Waroenk kita", 35, R.drawable.menuphoto, 0)
        )
    }

    // Расчеты
    val subTotal = cartItems.sumOf { it.price * it.quantity }
    val totalQuantity = cartItems.sumOf { it.quantity }
    val deliveryCharge = if (totalQuantity > 0) 10 else 0
    val discount = if (totalQuantity > 0) 20 else 0
    val finalTotal = (subTotal + deliveryCharge - discount).coerceAtLeast(0)

    Scaffold(
        bottomBar = {
            CartBottomNavigationBar(
                onHomeClick = onHomeClick,
                onCartClick = { /* Мы уже здесь */ }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFFBFBFB))
                .padding(paddingValues = innerPadding)
        ) {
            Column(modifier = Modifier.fillMaxSize()) {

                // HEADER
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 24.dp, top = 20.dp, end = 24.dp, bottom = 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Explore Your Favorite Food",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = YongFontFamily,
                        color = startRed,
                        modifier = Modifier.width(220.dp)
                    )
                    Icon(
                        painterResource(id = R.drawable.notification_bell),
                        null,
                        tint = Color(0xFF66BB6A),
                        modifier = Modifier.size(28.dp)
                    )
                }

                // SEARCH
                OutlinedTextField(
                    value = "",
                    onValueChange = {},
                    placeholder = { Text("What do you want to order?", color = Color(0xFFD3A0A0), fontSize = 14.sp) },
                    leadingIcon = { Icon(painterResource(id = R.drawable.search), null, tint = startRed, modifier = Modifier.size(20.dp)) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp, vertical = 8.dp)
                        .height(56.dp),
                    shape = RoundedCornerShape(15.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = Color(0xFFFDECEC),
                        unfocusedContainerColor = Color(0xFFFDECEC),
                        focusedBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent
                    )
                )

                Text(
                    text = "Cart",
                    modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp),
                    textAlign = TextAlign.Center,
                    fontSize = 24.sp,
                    fontFamily = YongFontFamily,
                    color = startRed,
                    fontWeight = FontWeight.Bold
                )

                // Список товаров
                LazyColumn(
                    modifier = Modifier.weight(1f),
                    contentPadding = PaddingValues(horizontal = 24.dp, vertical = 10.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    itemsIndexed(cartItems) { index, item ->
                        CartItemCard(
                            item = item,
                            onPlusClick = { cartItems[index] = item.copy(quantity = item.quantity + 1) },
                            onMinusClick = { if (item.quantity > 0) cartItems[index] = item.copy(quantity = item.quantity - 1) },
                            onDeleteClick = { cartItems[index] = item.copy(quantity = 0) }
                        )
                    }
                }
            }

            // ПАНЕЛЬ С СУММОЙ (Появляется, если totalQuantity > 0)
            Box(modifier = Modifier.align(Alignment.BottomCenter)) {
                AnimatedVisibility(
                    visible = totalQuantity > 0,
                    enter = fadeIn() + slideInVertically(initialOffsetY = { it }),
                    exit = fadeOut() + slideOutVertically(targetOffsetY = { it })
                ) {
                    CartSummarySection(
                        subTotal = subTotal,
                        delivery = deliveryCharge,
                        discount = discount,
                        total = finalTotal
                    )
                }
            }
        }
    }
}

@Composable
fun CartItemCard(
    item: CartItem,
    onPlusClick: () -> Unit,
    onMinusClick: () -> Unit,
    onDeleteClick: () -> Unit
) {
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
                modifier = Modifier.size(64.dp).clip(RoundedCornerShape(15.dp)),
                contentScale = ContentScale.Crop
            )

            Column(modifier = Modifier.padding(start = 16.dp).weight(1f)) {
                Text(text = item.name, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Text(text = item.restaurant, color = Color.Gray, fontSize = 12.sp)
                Text(text = "$ ${item.price}", color = startRed, fontWeight = FontWeight.Bold, fontSize = 18.sp)
            }

            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Surface(
                    modifier = Modifier.size(26.dp).clickable { onMinusClick() },
                    shape = RoundedCornerShape(8.dp),
                    color = Color(0xFFE8F5E9)
                ) {
                    Icon(painterResource(id = R.drawable.minusicon), null, tint = startRed, modifier = Modifier.padding(6.dp))
                }

                Text(text = "${item.quantity}", fontWeight = FontWeight.Bold)

                Surface(
                    modifier = Modifier.size(26.dp).clickable { onPlusClick() },
                    shape = RoundedCornerShape(8.dp),
                    color = startRed
                ) {
                    Icon(painterResource(id = R.drawable.addicon), null, tint = Color.White, modifier = Modifier.padding(6.dp))
                }

                Icon(
                    painter = painterResource(id = R.drawable.deleteicon),
                    contentDescription = null,
                    modifier = Modifier.padding(start = 8.dp).size(24.dp).clickable { onDeleteClick() }
                )
            }
        }
    }
}

@Composable
fun CartSummarySection(subTotal: Int, delivery: Int, discount: Int, total: Int) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp, vertical = 10.dp),
        shape = RoundedCornerShape(25.dp),
        colors = CardDefaults.cardColors(containerColor = startRed)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            SummaryLine("Sub-Total", "$subTotal $")
            SummaryLine("Delivery Charge", "$delivery $")
            SummaryLine("Discount", "$discount $")

            Spacer(modifier = Modifier.height(12.dp))

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text("Total", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.ExtraBold)
                Text("$total $", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.ExtraBold)
            }

            Spacer(modifier = Modifier.height(15.dp))

            Button(
                onClick = { /* Proceed */ },
                modifier = Modifier.fillMaxWidth().height(50.dp),
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

@Composable
fun CartBottomNavigationBar(onHomeClick: () -> Unit, onCartClick: () -> Unit) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .clip(RoundedCornerShape(topStart = 25.dp, topEnd = 25.dp)),
        color = Color.White,
        shadowElevation = 8.dp
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.clickable { onHomeClick() }.padding(8.dp)
            ) {
                Icon(painterResource(id = R.drawable.home), null, tint = Color(0xFF2E7D32))
                Text("Home", color = Color(0xFF2E7D32), fontSize = 12.sp)
            }
            Box(
                modifier = Modifier
                    .size(45.dp)
                    .background(Color(0xFFE8F5E9), RoundedCornerShape(12.dp))
                    .clickable { onCartClick() },
                contentAlignment = Alignment.Center
            ) {
                Icon(painterResource(id = R.drawable.shoppingcart), null, tint = Color(0xFF2E7D32))
            }
            Icon(painterResource(id = R.drawable.caricon), null, modifier = Modifier.size(24.dp))
            Icon(painterResource(id = R.drawable.barmenu), null, modifier = Modifier.size(24.dp))
            Icon(painterResource(id = R.drawable.user1), null, modifier = Modifier.size(24.dp))
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CartPreview() {
    AppTheme {
        CartScreen(onBackClick = {}, onHomeClick = {})
    }
}