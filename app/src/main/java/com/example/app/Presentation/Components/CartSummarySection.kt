import SummaryLine
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.app.Presentation.theme.startRed

@Composable
fun CartSummarySection(subTotal: Int, delivery: Int, discount: Int, total: Int, onProceedClick: () -> Unit) {
    Card(modifier = Modifier.fillMaxWidth().padding(24.dp), shape = RoundedCornerShape(25.dp), colors = CardDefaults.cardColors(containerColor = startRed)) {
        Column(modifier = Modifier.padding(20.dp)) {
            SummaryLine("Sub-Total", "$subTotal $")
            SummaryLine("Delivery Charge", "$delivery $")
            SummaryLine("Discount", "$discount $")
            Spacer(modifier = Modifier.height(12.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text("Total", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.ExtraBold)
                Text("$total $", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.ExtraBold)
            }
            Spacer(modifier = Modifier.height(15.dp))
            Button(onClick = onProceedClick, modifier = Modifier.fillMaxWidth().height(50.dp), shape = RoundedCornerShape(15.dp), colors = ButtonDefaults.buttonColors(containerColor = Color.White)) {
                Text("Proceed", color = startRed, fontWeight = FontWeight.Bold)
            }
        }
    }
}