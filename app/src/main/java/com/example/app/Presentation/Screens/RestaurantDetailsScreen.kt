import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.app.R
import com.example.app.Presentation.theme.AppTheme
import com.example.app.Presentation.theme.YongFontFamily
import com.example.app.Presentation.theme.startRed

@Composable
fun RestaurantDetailsScreen(onBackClick: () -> Unit) {
    val menuItems = listOf("Noodle", "Salad", "Burger", "Herbal Pan Cake", "Momos")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 24.dp)
    ) {
        Spacer(modifier = Modifier.height(40.dp))

        // Кнопка назад и Название ресторана
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = onBackClick,
                modifier = Modifier
                    .size(32.dp)
                    .background(Color(0xFFFFB74D).copy(alpha = 0.2f), CircleShape)
            ) {
                // Используем painterResource для иконки из drawable
                Icon(
                    painter = painterResource(id = R.drawable.feedback), // ЗАМЕНИ на имя своей иконки назад
                    contentDescription = "Back",
                    tint = Color(0xFFFFB74D),
                    modifier = Modifier.size(20.dp)
                )
            }
            Text(
                text = "Restaurant Name",
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center,
                fontSize = 24.sp,
                fontFamily = YongFontFamily,
                color = startRed,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Главное изображение ресторана
        Image(
            painter = painterResource(id = R.drawable.menupicture),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clip(RoundedCornerShape(15.dp)),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Описание
        Text(
            text = "Short description",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Text(
            text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad",
            fontSize = 14.sp,
            color = Color.Black.copy(alpha = 0.7f),
            lineHeight = 20.sp
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Заголовок Меню
        Text(
            text = "Menu",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = YongFontFamily,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Список меню
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(menuItems) { item ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(6.dp)
                                .background(Color.Black, CircleShape)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = item, fontSize = 16.sp)
                    }

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "See Details",
                            color = startRed.copy(alpha = 0.6f),
                            fontSize = 14.sp,
                            modifier = Modifier.clickable { /* Логика */ }
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        // Используем painterResource для иконки Checkbox
                        Icon(
                            painter = painterResource(id = R.drawable.cheked), // ЗАМЕНИ на имя иконки галочки
                            contentDescription = null,
                            modifier = Modifier.size(20.dp),
                            tint = Color.Black
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RestaurantPreview() {
    AppTheme {
        RestaurantDetailsScreen(onBackClick = {})
    }
}