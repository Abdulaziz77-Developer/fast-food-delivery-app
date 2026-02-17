import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
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
import com.example.app.ui.theme.YongFontFamily
import com.example.app.ui.theme.startRed

@Composable
fun DeliveryStatusCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(25.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column(modifier = Modifier.padding(15.dp)) {
            Text(
                text = "Call For Information",
                color = startRed,
                fontFamily = YongFontFamily,
                fontSize = 18.sp,
                modifier = Modifier.padding(bottom = 10.dp)
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.userprofile),
                    contentDescription = null,
                    modifier = Modifier.size(60.dp).clip(RoundedCornerShape(15.dp)),
                    contentScale = ContentScale.Crop
                )
                Column(modifier = Modifier.padding(start = 15.dp)) {
                    Text(text = "Mr Kemplas", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(painterResource(id = R.drawable.timeicon), null, tint = Color(0xFF66BB6A), modifier = Modifier.size(14.dp))
                        Text(text = " 20 minutes on the way", color = Color.Gray, fontSize = 12.sp)
                    }
                }
            }
            Spacer(modifier = Modifier.height(15.dp))
            Button(
                onClick = { /* Вызов */ },
                modifier = Modifier.fillMaxWidth().height(45.dp),
                shape = RoundedCornerShape(15.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF1FDF5))
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(painterResource(id = R.drawable.calllogo), null, tint = Color(0xFF66BB6A), modifier = Modifier.size(20.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "Call", color = Color(0xFF66BB6A), fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}