package com.example.app

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.app.ui.theme.YongFontFamily
import com.example.app.ui.theme.startRed
import com.example.app.R
import com.example.app.ui.theme.AppTheme

@Composable
fun ProfileScreen(
    onHomeClick: () -> Unit,
    onHistoryClick: () -> Unit,
    onCartClick: () -> Unit, // ДОБАВЛЕНО: теперь MainScreen не будет ругаться
    onProfileClick: () -> Unit
) {
    Scaffold(
        bottomBar = {
            AppFooter(
                currentScreen = "profile",
                onHomeClick = onHomeClick,
                onHistoryClick = onHistoryClick,
                onCartClick = onCartClick, // ПЕРЕДАЕМ В ФУТЕР
                onProfileClick = onProfileClick
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(innerPadding)
                .padding(horizontal = 24.dp)
        ) {
            Text(
                text = "Hello, lorem ipsum",
                fontFamily = YongFontFamily,
                color = startRed,
                fontSize = 28.sp,
                modifier = Modifier.padding(top = 40.dp, bottom = 24.dp)
            )

            ProfileField(label = "Name", value = "lorem ipsum")
            ProfileField(label = "Address", value = "Lorem ipsum is placeholder text commonly used in the graphic...")
            ProfileField(label = "Email", value = "lorem ipsum@gmail.com")
            ProfileField(label = "Phone", value = "123456789")
            ProfileField(label = "Password", value = "***********", isPassword = true)

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = { /* Save Action */ },
                modifier = Modifier.fillMaxWidth().height(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                shape = RoundedCornerShape(15.dp),
                border = BorderStroke(1.dp, Color(0xFFEEEEEE)),
                elevation = ButtonDefaults.buttonElevation(2.dp)
            ) {
                Text(text = "Save Information", color = startRed, fontWeight = FontWeight.Bold, fontFamily = YongFontFamily)
            }
        }
    }
}

@Composable
fun ProfileField(label: String, value: String, isPassword: Boolean = false) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .background(Color.White, RoundedCornerShape(15.dp))
            .border(1.dp, Color(0xFFF3F3F3), RoundedCornerShape(15.dp))
            .padding(16.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = label, fontWeight = FontWeight.Bold, fontSize = 14.sp, fontFamily = YongFontFamily)
                Text(text = value, color = Color.Gray, fontSize = 13.sp, lineHeight = 18.sp)
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                if (isPassword) {
                    Icon(painterResource(id = R.drawable.eyelogo), null, modifier = Modifier.size(20.dp).padding(end = 8.dp), tint = Color.Gray)
                }
                Icon(painterResource(id = R.drawable.editicon), null, modifier = Modifier.size(20.dp), tint = Color.Black)
            }
        }
    }
}

// ОБНОВЛЕННЫЙ FOOTER: Добавлен onCartClick
@Composable
fun AppFooter(
    currentScreen: String,
    onHomeClick: () -> Unit,
    onHistoryClick: () -> Unit,
    onCartClick: () -> Unit, // ДОБАВЛЕНО
    onProfileClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .clip(RoundedCornerShape(topStart = 25.dp, topEnd = 25.dp)),
        color = Color.White,
        shadowElevation = 15.dp
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Home
            IconButton(onClick = onHomeClick) {
                Icon(painterResource(id = R.drawable.home), null, modifier = Modifier.size(28.dp),
                    tint = if (currentScreen == "home" || currentScreen == "food_explorer") Color(0xFF2E7D32) else Color.Gray)
            }

            // Cart (Теперь кликабельно!)
            IconButton(onClick = onCartClick) {
                Icon(painterResource(id = R.drawable.shoppingcart), null, modifier = Modifier.size(28.dp),
                    tint = if (currentScreen == "cart") Color(0xFF2E7D32) else Color.Gray)
            }

            // Delivery
            IconButton(onClick = { /* Track */ }) {
                Icon(painterResource(id = R.drawable.caricon), null, modifier = Modifier.size(28.dp))
            }

            // History
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .background(if (currentScreen == "history") Color(0xFFE8F5E9) else Color.Transparent)
                    .clickable { onHistoryClick() }
                    .padding(8.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(painterResource(id = R.drawable.barmenu), null, modifier = Modifier.size(28.dp),
                        tint = if (currentScreen == "history") Color(0xFF2E7D32) else Color.Gray)
                    if (currentScreen == "history") {
                        Text(" History", color = Color(0xFF2E7D32), fontSize = 12.sp)
                    }
                }
            }

            // Profile
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .background(if (currentScreen == "profile") Color(0xFFE8F5E9) else Color.Transparent)
                    .clickable { onProfileClick() }
                    .padding(8.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(painterResource(id = R.drawable.user1), null, modifier = Modifier.size(28.dp),
                        tint = if (currentScreen == "profile") Color(0xFF2E7D32) else Color.Gray)
                    if (currentScreen == "profile") {
                        Text(" Profile", color = Color(0xFF2E7D32), fontSize = 12.sp)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ProfileScreenPreview() {
    AppTheme {
        ProfileScreen(onHomeClick = {}, onHistoryClick = {}, onCartClick = {}, onProfileClick = {})
    }
}