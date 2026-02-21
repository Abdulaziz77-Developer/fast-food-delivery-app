import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.app.Presentation.theme.YongFontFamily
import com.example.app.Presentation.theme.startRed
import com.example.app.Presentation.theme.testColor

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
