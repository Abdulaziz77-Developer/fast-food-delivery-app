package com.example.app

import LocationScreen
import RestaurantDetailsScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.app.ui.theme.AppTheme
import com.example.app.ui.theme.YongFontFamily
import com.example.app.ui.theme.startRed
import com.example.app.ui.theme.testColor
import kotlinx.coroutines.delay

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

    // Хранилище для выбранной еды
    var selectedFood by remember { mutableStateOf<FoodItemData?>(null) }

    LaunchedEffect(Unit) {
        delay(2000)
        spleshScreen = false
    }

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
                    onHomeClick = { currentScreen = "restaurant_details" },
                    onPopularClick = { currentScreen = "search_foods" },
                    onFoodClick = { food ->
                        selectedFood = food
                        currentScreen = "food_details"
                    },
                    onCartClick = { currentScreen = "cart" }
                )
            }

            "food_details" -> {
                selectedFood?.let { food ->
                    FoodDetailsScreen(
                        food = food,
                        onBackClick = { currentScreen = "food_explorer" },
                        onAddToCartClick = { currentScreen = "cart" }
                    )
                }
            }

            "search_foods" -> {
                SearchFoodsScreen(onHomeClick = { currentScreen = "food_explorer" })
            }

            "restaurant_details" -> {
                RestaurantDetailsScreen(onBackClick = { currentScreen = "food_explorer" })
            }

            "cart" -> {
                CartScreen(
                    onBackClick = { currentScreen = "food_explorer" },
                    onHomeClick = { currentScreen = "food_explorer" },
                    // ТЕПЕРЬ ЭТО РАБОТАЕТ:
                    onProceedClick = { currentScreen = "edit_order" }
                )
            }

            // НОВЫЙ ЭКРАН: Редактирование и подтверждение заказа
            "edit_order" -> {
                EditOrderScreen(
                    onBackClick = { currentScreen = "cart" },
                    onPlaceOrderClick = {
                        // Здесь можно добавить экран "Успех" или вернуть на главную
                        currentScreen = "food_explorer"
                        println("Order placed successfully!")
                    }
                )
            }
        }
    }
}


@Composable
fun WavesOfFoodScreen(){
    Column(
        modifier= Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(
            modifier = Modifier.height(152.dp)
        )
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Wafes of food illustration",
            modifier = Modifier
                .size(200.dp)
                .padding(bottom = 16.dp)
        )
        Text(
            text = "Wafes of Food",
            fontFamily = YongFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 44.sp,
            color = testColor,
            modifier = Modifier
                .padding(bottom = 32.dp)
        )
        Text(
            text = "Deliever Favorite Food",
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            color = startRed,
        )
        Spacer(
            modifier = Modifier
                .height(300.dp)
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 50.dp),
            contentAlignment = Alignment.BottomCenter
        ) {
            FooterText()
        }
    }
}
@Composable
fun Homescreen(onNextClick: () -> Unit) { // Добавили аргумент для обработки нажатия
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(90.dp))
        Image(
            painter = painterResource(id = R.drawable.backkgroundstart1),
            contentDescription = "Wafes of food illustration",
            modifier = Modifier.size(333.dp)
        )
        Text(
            modifier = Modifier.padding(top = 20.dp),
            text = "Enjoy Restaurant Quality Meals at Home ",
            fontFamily = YongFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = startRed,
            textAlign = TextAlign.Center
        )

        // Используем weight(1f), чтобы прижать кнопку к низу автоматически
        Spacer(modifier = Modifier.height(260.dp))

        GradientButton("Next") {
            onNextClick() // Вызываем функцию перехода
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 50.dp),
            contentAlignment = Alignment.BottomCenter
        ) {
            FooterText()
        }
    }
}

@Composable
fun GradientButton(text: String,
            onClick: () -> Unit ) {
      Box(
          modifier = Modifier
              .background(
                  brush = Brush.horizontalGradient(
                      colors = listOf(
                          startRed,
                          testColor
                      )
                  ),
                  shape = RoundedCornerShape(10.dp)
              )
              .clickable {
                  onClick()
              }
              .padding(vertical = 16.dp, horizontal = 24.dp),
          contentAlignment = Alignment.Center
      ){
          Text(text = text, color = Color.White, fontSize = 18.sp, fontFamily = YongFontFamily)
      }
}
