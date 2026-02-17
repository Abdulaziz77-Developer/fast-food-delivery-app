import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.app.R
import com.example.app.Presentation.ui.theme.startRed
import com.example.app.Presentation.ui.theme.YongFontFamily


@Composable
fun RestaurantDetailsContent(onBackClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 24.dp)
    ) {
        Spacer(modifier = Modifier.height(40.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(
                onClick = onBackClick,
                modifier = Modifier
                    .size(32.dp)
                    .background(Color(0xFFFFB74D).copy(0.2f), CircleShape)
            ) {
                Icon(painterResource(id = R.drawable.feedback), null, tint = Color(0xFFFFB74D))
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

        Text(text = "Short description", fontSize = 18.sp, fontWeight = FontWeight.Bold)
        Text(
            text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
            fontSize = 14.sp, color = Color.Gray, lineHeight = 20.sp
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(text = "Menu", fontSize = 20.sp, fontWeight = FontWeight.Bold, fontFamily = YongFontFamily)

        val menuItems = listOf("Noddle", "Salad", "Burger", "Herbal Pan Cake", "Momos")
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.padding(top = 16.dp).fillMaxSize()
        ) {
            items(menuItems) { item ->
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(text = "• $item", fontSize = 16.sp)
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(text = "See Details", color = startRed.copy(0.6f), fontSize = 12.sp)
                        Spacer(modifier = Modifier.width(8.dp))
                        Icon(painterResource(id = R.drawable.cheked), null, modifier = Modifier.size(18.dp))
                    }
                }
            }
        }
    }
}
