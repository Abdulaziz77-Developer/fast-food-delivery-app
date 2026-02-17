import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.app.Presentation.ui.theme.startRed


@Composable
fun DeleveryFavoriteFoodText(){
    Text(
        text = "Deliever Favorite Food",
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        color = startRed,
    )
}