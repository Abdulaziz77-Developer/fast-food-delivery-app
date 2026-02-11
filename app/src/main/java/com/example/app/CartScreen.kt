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
    onHomeClick: () -> Unit,
    onProceedClick: () -> Unit,
    onHistoryClick: () -> Unit,
    onCartClick: () -> Unit,
    onProfileClick: () -> Unit
) {
    val cartItems = remember {
        mutableStateListOf(
            CartItem(1, "Spacy fresh crab", "Waroenk kita", 35, R.drawable.menuphoto, 0),
            CartItem(2, "Spacy fresh crab", "Waroenk kita", 35, R.drawable.menuphoto, 0),
            CartItem(3, "Spacy fresh crab", "Waroenk kita", 35, R.drawable.menuphoto, 0)
        )
    }

    // 1. Состояние для показа окна
    var showDialog by remember { mutableStateOf(false) }
    // 2. Специальный триггер клика (изначально 0)
    var clickTrigger by remember { mutableIntStateOf(0) }

    // LaunchedEffect сработает ТОЛЬКО при изменении clickTrigger (т.е. при нажатии на +)
    // При первом запуске (когда clickTrigger = 0) он не покажет окно.
    LaunchedEffect(clickTrigger) {
        if (clickTrigger > 0) {
            showDialog = true
        }
    }

    val subTotal = cartItems.sumOf { it.price * it.quantity }
    val totalQuantity = cartItems.sumOf { it.quantity }
    val deliveryCharge = if (totalQuantity > 0) 10 else 0
    val discount = if (totalQuantity > 0) 20 else 0
    val finalTotal = (subTotal + deliveryCharge - discount).coerceAtLeast(0)

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            confirmButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text("OK", color = startRed, fontWeight = FontWeight.Bold)
                }
            },
            title = { Text("Добавлено", color = startRed, fontFamily = YongFontFamily) },
            text = { Text("Вы успешно увеличили количество товара!") },
            containerColor = Color.White,
            shape = RoundedCornerShape(20.dp)
        )
    }

    Scaffold(
        bottomBar = {
            AppFooter(
                currentScreen = "cart",
                onHomeClick = onHomeClick,
                onHistoryClick = onHistoryClick,
                onCartClick = onCartClick,
                onProfileClick = onProfileClick
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFFBFBFB))
                .padding(innerPadding)
        ) {
            // Header
            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp, vertical = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Explore Your Favorite Food", fontSize = 24.sp, fontWeight = FontWeight.Bold, fontFamily = YongFontFamily, color = startRed, modifier = Modifier.width(220.dp))
                Icon(painterResource(id = R.drawable.notification_bell), null, tint = Color(0xFF66BB6A), modifier = Modifier.size(28.dp))
            }

            // Search
            OutlinedTextField(
                value = "", onValueChange = {},
                placeholder = { Text("What do you want to order?", color = Color(0xFFD3A0A0)) },
                leadingIcon = { Icon(painterResource(id = R.drawable.search), null, tint = startRed) },
                modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp),
                shape = RoundedCornerShape(15.dp),
                colors = OutlinedTextFieldDefaults.colors(focusedContainerColor = Color(0xFFFDECEC), unfocusedContainerColor = Color(0xFFFDECEC), focusedBorderColor = Color.Transparent, unfocusedBorderColor = Color.Transparent)
            )

            Text("Cart", modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp), textAlign = TextAlign.Center, fontSize = 24.sp, fontFamily = YongFontFamily, color = startRed, fontWeight = FontWeight.Bold)

            LazyColumn(
                modifier = Modifier.weight(1f),
                contentPadding = PaddingValues(horizontal = 24.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                itemsIndexed(cartItems) { index, item ->
                    CartItemCard(
                        item = item,
                        onPlusClick = {
                            cartItems[index] = item.copy(quantity = item.quantity + 1)
                            // Увеличиваем триггер, чтобы LaunchedEffect сработал
                            clickTrigger++
                        },
                        onMinusClick = { if (item.quantity > 0) cartItems[index] = item.copy(quantity = item.quantity - 1) },
                        onDeleteClick = { cartItems[index] = item.copy(quantity = 0) }
                    )
                }
            }

            AnimatedVisibility(visible = totalQuantity > 0) {
                CartSummarySection(subTotal, deliveryCharge, discount, finalTotal, onProceedClick)
            }
        }
    }
}

@Composable
fun CartItemCard(item: CartItem, onPlusClick: () -> Unit, onMinusClick: () -> Unit, onDeleteClick: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(22.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Row(modifier = Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
            Image(painter = painterResource(id = item.imageRes), contentDescription = null, modifier = Modifier.size(64.dp).clip(RoundedCornerShape(15.dp)), contentScale = ContentScale.Crop)
            Column(modifier = Modifier.padding(start = 16.dp).weight(1f)) {
                Text(item.name, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Text(item.restaurant, color = Color.Gray, fontSize = 12.sp)
                Text("$ ${item.price}", color = startRed, fontWeight = FontWeight.Bold, fontSize = 18.sp)
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Surface(modifier = Modifier.size(26.dp).clickable { onMinusClick() }, shape = RoundedCornerShape(8.dp), color = Color(0xFFE8F5E9)) {
                    Icon(painterResource(id = R.drawable.minusicon), null, tint = startRed, modifier = Modifier.padding(6.dp))
                }
                Text("${item.quantity}", modifier = Modifier.padding(horizontal = 8.dp), fontWeight = FontWeight.Bold)
                Surface(modifier = Modifier.size(26.dp).clickable { onPlusClick() }, shape = RoundedCornerShape(8.dp), color = startRed) {
                    Icon(painterResource(id = R.drawable.addicon), null, tint = Color.White, modifier = Modifier.padding(6.dp))
                }
            }
        }
    }
}

@Composable
fun CartSummarySection(subTotal: Int, delivery: Int, discount: Int, total: Int, onProceedClick: () -> Unit) {
    Card(modifier = Modifier.fillMaxWidth().padding(24.dp), shape = RoundedCornerShape(25.dp), colors = CardDefaults.cardColors(containerColor = startRed)) {
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
            Button(onClick = onProceedClick, modifier = Modifier.fillMaxWidth().height(50.dp), shape = RoundedCornerShape(15.dp), colors = ButtonDefaults.buttonColors(containerColor = Color.White)) {
                Text("Proceed", color = startRed, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
fun SummaryLine(label: String, value: String) {
    Row(modifier = Modifier.fillMaxWidth().padding(vertical = 2.dp), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(label, color = Color.White, fontSize = 14.sp)
        Text(value, color = Color.White, fontSize = 14.sp)
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CartScreenPreview() {
    AppTheme {
        CartScreen({}, {}, {}, {}, {}, {})
    }
}