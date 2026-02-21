import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CustomInputField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            // Создаем эффект тени и закругления
            .shadow(elevation = 8.dp, shape = RoundedCornerShape(15.dp)),
        color = Color.White,
        shape = RoundedCornerShape(15.dp)
    ) {
        TextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = {
                Text(text = placeholder, color = Color.LightGray)
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                disabledContainerColor = Color.White,
                focusedIndicatorColor = Color.Transparent, // Убираем полоску снизу
                unfocusedIndicatorColor = Color.Transparent,
            ),
            singleLine = true,
            modifier = Modifier.fillMaxSize()
        )
    }
}