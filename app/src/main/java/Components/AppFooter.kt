import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.app.R

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