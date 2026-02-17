import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import com.example.app.ui.theme.YongFontFamily
import com.example.app.ui.theme.startRed

@Composable
fun NotificationRow(
    iconRes: Int,
    message: String,
    iconColor: Color,
    onClick: () -> Unit // ДОБАВИЛИ ПАРАМЕТР
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() } // ТЕПЕРЬ СТРОКА КЛИКАБЕЛЬНА
            .padding(vertical = 4.dp)
    ) {
        // Иконка статуса
        Icon(
            painter = painterResource(id = iconRes),
            contentDescription = null,
            tint = iconColor,
            modifier = Modifier.size(35.dp)
        )

        Spacer(modifier = Modifier.width(16.dp))

        // Текст сообщения
        Text(
            text = message,
            fontSize = 15.sp,
            color = startRed,
            fontFamily = YongFontFamily,
            fontWeight = FontWeight.Medium
        )
    }
}