import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.app.Presentation.ui.theme.startRed
import com.example.app.Presentation.ui.theme.YongFontFamily


@Composable
fun FooterText() {
    Text(
        text = "Design By \n  NeatRoots",
        color = startRed,
        fontFamily = YongFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
        textAlign = TextAlign.Center,
        modifier = Modifier.padding(bottom = 12.dp)
    )
}