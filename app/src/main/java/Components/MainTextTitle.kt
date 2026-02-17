import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.app.ui.theme.YongFontFamily
import com.example.app.ui.theme.testColor

@Composable
fun MainTextTitle(){
    Text(
        text = "Wafes of Food",
        fontFamily = YongFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 44.sp,
        color = testColor,
        modifier = Modifier
            .padding(top = 32.dp)
    )
}