package com.example.app.Presentation.Screens

import FooterText
import GradientButton
import LocationScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.app.R
import com.example.app.presentation.screens.CartScreen
import com.example.app.presentation.screens.FoodExplorerScreen
import com.example.app.presentation.screens.FoodItemData
import kotlinx.coroutines.delay
import com.example.app.Presentation.ui.theme.testColor
import com.example.app.Presentation.ui.theme.AppTheme
import com.example.app.Presentation.ui.theme.startRed
import com.example.app.Presentation.ui.theme.YongFontFamily


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                MainScreen()
            }
        }
    }
}




@Composable
fun MainScreen() {
    var spleshScreen by remember { mutableStateOf(true) }
    var currentScreen by remember { mutableStateOf("home") }

    var selectedFood by remember { mutableStateOf<FoodItemData?>(null) }

    LaunchedEffect(Unit) {
        delay(2000)
        spleshScreen = false
    }

    val navigateToHome = { currentScreen = "food_explorer" }
    val navigateToHistory = { currentScreen = "history" }
    val navigateToCart = { currentScreen = "cart" }
    val navigateToProfile = { currentScreen = "profile" }
    val navigateToNotifications = { currentScreen = "notification" }

    if (spleshScreen) {
        WavesOfFoodScreen()
    } else {
        when (currentScreen) {
            "home" -> Homescreen(onNextClick = { currentScreen = "login" })

            "login" -> NextScreen(onSignUpClick = { currentScreen = "signup" })

            "signup" -> SignUpScreen(
                onAlreadyHaveAccountClick = { currentScreen = "login" },
                onSignUpSuccess = { currentScreen = "location" }
            )

            "location" -> LocationScreen(onCitySelected = { currentScreen = "food_explorer" })

            "food_explorer" -> {
                FoodExplorerScreen(
                    onHomeClick = navigateToHome,
                    onPopularClick = { currentScreen = "search_foods" },
                    onFoodClick = { food ->
                        selectedFood = food
                        currentScreen = "food_details"
                    },
                    onCartClick = navigateToCart,
                    onHistoryClick = navigateToHistory,
                    onProfileClick = navigateToProfile,
                    onNotificationClick = navigateToNotifications
                )
            }

            "food_details" -> {
                selectedFood?.let { food ->
                    FoodDetailsScreen(
                        food = food,
                        onBackClick = navigateToHome,
                        onAddToCartClick = navigateToCart
                    )
                }
            }

            "search_foods" -> {
                SearchFoodsScreen(
                    onHomeClick = navigateToHome,
                    onHistoryClick = navigateToHistory,
                    onCartClick = navigateToCart,
                    onProfileClick = navigateToProfile
                )
            }

            "cart" -> {
                CartScreen(
                    onBackClick = navigateToHome,
                    onHomeClick = navigateToHome,
                    onProceedClick = { currentScreen = "edit_order" },
                    onHistoryClick = navigateToHistory,
                    onCartClick = navigateToCart,
                    onProfileClick = navigateToProfile,
                    onNotificationClick = navigateToNotifications
                )
            }

            "edit_order" -> {
                EditOrderScreen(
                    onBackClick = navigateToCart,
                    onPlaceOrderClick = { currentScreen = "congrats" }
                )
            }

            "congrats" -> {
                CongratsScreen(onGoHomeClick = navigateToHome)
            }

            "history" -> {
                HistoryScreen(
                    onHomeClick = navigateToHome,
                    onCartClick = navigateToCart,
                    onHistoryClick = navigateToHistory,
                    onProfileClick = navigateToProfile
                )
            }

            "profile" -> {
                ProfileScreen(
                    onHomeClick = navigateToHome,
                    onCartClick = navigateToCart,
                    onHistoryClick = navigateToHistory,
                    onProfileClick = navigateToProfile
                )
            }
            "notification" -> {
                NotificationScreen(
                    onBackClick = { currentScreen = "food_explorer" },
                    onNotificationClick = { currentScreen = "feedback" }
                )
            }

            // НОВЫЙ ЭКРАН ОТЗЫВА
            "feedback" -> {
                FeedbackScreen(
                    onBackClick = { currentScreen = "notification" },
                    onSendClick = { currentScreen = "food_explorer" }
                )
            }
        }
    }
}

@Composable
fun WavesOfFoodScreen() {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color.White
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.weight(1f))

            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = null,
                modifier = Modifier.size(200.dp)
            )

            Text(
                text = "Waves of Food",
                fontFamily = YongFontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 40.sp,
                color = testColor
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Deliver Favorite Food",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = startRed,
            )

            Spacer(modifier = Modifier.weight(1.5f))


            FooterText()
        }
    }
}
@Composable
fun Homescreen(onNextClick: () -> Unit) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color.White
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding) // Защита от наезжания на кнопки Mi Phone
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // 1. Гибкий отступ сверху
            Spacer(modifier = Modifier.weight(1f))

            // 2. Иллюстрация (делаем размер адаптивным через fillMaxWidth)
            Image(
                painter = painterResource(id = R.drawable.backkgroundstart1),
                contentDescription = "Illustration",
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .aspectRatio(1f),
                contentScale = ContentScale.Fit
            )

            // 3. Текст заголовка
            Text(
                modifier = Modifier.padding(top = 32.dp),
                text = "Enjoy Restaurant Quality Meals at Home",
                fontFamily = YongFontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp, // Чуть увеличил для читабельности
                color = startRed,
                textAlign = TextAlign.Center,
                lineHeight = 28.sp
            )

            // 4. ГИБКИЙ ОТСТУП (вместо 260.dp)
            // Он будет "выталкивать" кнопку вниз, подстраиваясь под высоту экрана
            Spacer(modifier = Modifier.weight(1.5f))

            // 5. Кнопка
            GradientButton("Next") {
                onNextClick()
            }

            // 6. Футер (всегда в самом низу с небольшим отступом)
            Spacer(modifier = Modifier.height(24.dp))
            FooterText()
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
