import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.app.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomBottomNavigation(
    onHomeClick: () -> Unit,
    onHistoryClick: () -> Unit,
    onCartClick: () -> Unit,
    onProfileClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .clip(RoundedCornerShape(topStart = 25.dp, topEnd = 25.dp)),
        color = Color.White,
        shadowElevation = 10.dp
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painterResource(id = R.drawable.home),
                null,
                modifier = Modifier.size(28.dp).clickable { onHomeClick() }
            )

            BadgedBox(badge = { Badge { Text("7") } }) {
                Icon(
                    painterResource(id = R.drawable.shoppingcart),
                    null,
                    modifier = Modifier.size(28.dp).clickable { onCartClick() }
                )
            }

            // Кнопка Search (активная)
            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE8F5E9)),
                shape = RoundedCornerShape(12.dp),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(painterResource(id = R.drawable.search), null, tint = Color(0xFF2E7D32), modifier = Modifier.size(20.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Search", color = Color(0xFF2E7D32), fontSize = 14.sp)
                }
            }

            Icon(
                painterResource(id = R.drawable.barmenu),
                null,
                modifier = Modifier.size(28.dp).clickable { onHistoryClick() }
            )
            Icon(
                painterResource(id = R.drawable.user1),
                null,
                modifier = Modifier.size(28.dp).clickable { onProfileClick() }
            )
        }
    }
}