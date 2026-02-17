import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.app.R
import com.example.app.presentation.screens.CartItem
import com.example.app.Presentation.ui.theme.startRed

@Composable
fun CartItemCard(item: com.example.app.presentation.screens.CartItem, onPlusClick: () -> Unit, onMinusClick: () -> Unit, onDeleteClick: () -> Unit) {
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