package com.example.app.presentation.screens

import AppFooter
import CartItemCard
import CartSummarySection
import androidx.compose.animation.*
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.app.R
import com.example.app.Presentation.theme.AppTheme
import com.example.app.Presentation.theme.YongFontFamily
import com.example.app.Presentation.theme.startRed

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
    onBackClick: () -> Unit = {},
    onHomeClick: () -> Unit = {},
    onProceedClick: () -> Unit = {},
    onHistoryClick: () -> Unit = {},
    onCartClick: () -> Unit = {},
    onProfileClick: () -> Unit = {},
    onNotificationClick: () -> Unit = {}
) {
    val cartItems = remember {
        mutableStateListOf(
            CartItem(1, "Spacy fresh crab", "Waroenk kita", 35, R.drawable.menuphoto, 0),
            CartItem(2, "Spacy fresh crab", "Waroenk kita", 35, R.drawable.menuphoto, 0),
            CartItem(3, "Spacy fresh crab", "Waroenk kita", 35, R.drawable.menuphoto, 0)
        )
    }

    var showDialog by remember { mutableStateOf(false) }

    // ФИКС: Состояние для текста поиска
    var cartSearchQuery by remember { mutableStateOf("") }

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
            text = { Text("Вы успешно добавили товар в корзину!") },
            containerColor = Color.White,
            shape = RoundedCornerShape(20.dp)
        )
    }

    Scaffold(
        modifier = Modifier.fillMaxSize().navigationBarsPadding(),
        bottomBar = {
            Surface(shadowElevation = 8.dp, color = Color.White) {
                AppFooter(
                    currentScreen = "cart",
                    onHomeClick = onHomeClick,
                    onHistoryClick = onHistoryClick,
                    onCartClick = onCartClick,
                    onProfileClick = onProfileClick
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFFBFBFB))
                .statusBarsPadding()
                .padding(bottom = innerPadding.calculateBottomPadding())
        ) {
            // HEADER
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp, end = 24.dp, top = 24.dp, bottom = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Explore Your\nFavorite Food",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = YongFontFamily,
                    color = startRed,
                    lineHeight = 34.sp,
                    modifier = Modifier.weight(1f)
                )

                Surface(
                    modifier = Modifier
                        .size(48.dp)
                        .clickable { onNotificationClick() },
                    shape = RoundedCornerShape(15.dp),
                    color = Color(0xFFFDECEC)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(
                            painter = painterResource(id = R.drawable.notification_bell),
                            contentDescription = null,
                            tint = Color(0xFF66BB6A),
                            modifier = Modifier.size(28.dp)
                        )
                    }
                }
            }

            // ПОИСК (ИСПРАВЛЕН: Теперь текст вводится)
            OutlinedTextField(
                value = cartSearchQuery,
                onValueChange = { cartSearchQuery = it },
                placeholder = { Text("What do you want to order?", color = Color(0xFFD3A0A0), fontSize = 15.sp) },
                leadingIcon = {
                    Icon(
                        painterResource(id = R.drawable.search),
                        null,
                        tint = startRed,
                        modifier = Modifier.size(22.dp)
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .padding(horizontal = 24.dp),
                shape = RoundedCornerShape(15.dp),
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color(0xFFFDECEC),
                    unfocusedContainerColor = Color(0xFFFDECEC),
                    focusedBorderColor = startRed, // Сделал границу заметной при фокусе
                    unfocusedBorderColor = Color.Transparent,
                    cursorColor = startRed
                )
            )

            Text(
                text = "Order Details",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp, bottom = 12.dp),
                textAlign = TextAlign.Center,
                fontSize = 24.sp,
                fontFamily = YongFontFamily,
                color = startRed,
                fontWeight = FontWeight.Bold
            )

            // Список корзины
            LazyColumn(
                modifier = Modifier.weight(1f),
                contentPadding = PaddingValues(start = 24.dp, end = 24.dp, bottom = 24.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                itemsIndexed(cartItems) { index, item ->
                    CartItemCard(
                        item = item,
                        onPlusClick = {
                            if (item.quantity == 0) showDialog = true
                            cartItems[index] = item.copy(quantity = item.quantity + 1)
                        },
                        onMinusClick = {
                            if (item.quantity > 0) cartItems[index] = item.copy(quantity = item.quantity - 1)
                        },
                        onDeleteClick = { cartItems[index] = item.copy(quantity = 0) }
                    )
                }
            }

            // Блок итогов
            AnimatedVisibility(
                visible = totalQuantity > 0,
                enter = slideInVertically(initialOffsetY = { it }) + fadeIn(),
                exit = slideOutVertically(targetOffsetY = { it }) + fadeOut()
            ) {
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    color = Color.White,
                    shadowElevation = 20.dp,
                    shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp)
                ) {
                    CartSummarySection(subTotal, deliveryCharge, discount, finalTotal, onProceedClick)
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CartScreenPreview() {
    AppTheme {
        CartScreen()
    }
}