import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.app.R
import com.example.app.getPopularSearchData
import com.example.app.ui.theme.YongFontFamily
import com.example.app.ui.theme.startRed

@Composable
fun SearchListContent(
    onFoodClick: () -> Unit,
    onHomeClick: () -> Unit,
    onHistoryClick: () -> Unit,
    onCartClick: () -> Unit,
    onProfileClick: () -> Unit
) {
    Scaffold(
        bottomBar = {
            CustomBottomNavigation(
                onHomeClick = onHomeClick,
                onHistoryClick = onHistoryClick,
                onCartClick = onCartClick,
                onProfileClick = onProfileClick
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(innerPadding)
        ) {
            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(
                value = "",
                onValueChange = {},
                placeholder = { Text("What do you want to order?", color = Color(0xFFD3A0A0)) },
                leadingIcon = {
                    Icon(painterResource(id = R.drawable.search), null, tint = startRed)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                shape = RoundedCornerShape(15.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color(0xFFFDECEC),
                    unfocusedContainerColor = Color(0xFFFDECEC),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )

            Text(
                text = "Popular",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = YongFontFamily,
                color = startRed,
                modifier = Modifier
                    .padding(24.dp)
                    .align(Alignment.CenterHorizontally)
            )

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(start = 24.dp, end = 24.dp, bottom = 20.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(getPopularSearchData()) { food ->
                    FoodCard(food, onClick = onFoodClick)
                }
            }
        }
    }
}
