import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.app.R
import com.example.app.ui.theme.YongFontFamily

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
