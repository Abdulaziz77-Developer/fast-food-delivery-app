import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import com.example.app.HistoryItem
import com.example.app.ui.theme.startRed

@Composable
fun HistoryOrderCard(item: HistoryItem) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(1.dp)
    ) {
        Row(modifier = Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = item.imageRes),
                contentDescription = null,
                modifier = Modifier.size(60.dp).clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )
            Column(modifier = Modifier.weight(1f).padding(horizontal = 12.dp)) {
                Text(text = item.name, fontWeight = FontWeight.Bold, fontSize = 15.sp)
                Text(text = item.restaurant, color = Color.Gray, fontSize = 12.sp)
                Text(text = "$ ${item.price}", color = startRed, fontWeight = FontWeight.Bold, fontSize = 18.sp)
            }
            Button(
                onClick = { /* Повторить */ },
                colors = ButtonDefaults.buttonColors(containerColor = startRed),
                shape = RoundedCornerShape(12.dp),
                contentPadding = PaddingValues(horizontal = 15.dp, vertical = 5.dp)
            ) {
                Text(text = "Buy Again", fontSize = 12.sp, color = Color.White)
            }
        }
    }
}