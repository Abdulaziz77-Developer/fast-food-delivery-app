package com.example.app

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.app.ui.theme.AppTheme
import com.example.app.ui.theme.YongFontFamily
import com.example.app.ui.theme.startRed

@Composable
fun EditOrderScreen(
    onBackClick: () -> Unit,
    onPlaceOrderClick: () -> Unit // This is the navigation trigger
) {
    // State for the input fields
    var name by remember { mutableStateOf("lorem ipsum") }
    var address by remember { mutableStateOf("Lorem ipsum is placeholder text commonly used...") }
    var phone by remember { mutableStateOf("123456789") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 24.dp)
    ) {
        Spacer(modifier = Modifier.height(24.dp))

        // Back Button
        IconButton(
            onClick = onBackClick,
            modifier = Modifier.size(32.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.backkgroundstart1),
                contentDescription = "Back",
                tint = Color(0xFFFFB200)
            )
        }

        Text(
            text = "Edit",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontSize = 28.sp,
            fontFamily = YongFontFamily,
            color = startRed,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Input Fields
        EditInputField(label = "Name", value = name, onValueChange = { name = it })
        EditInputField(label = "Address", value = address, onValueChange = { address = it }, isSingleLine = false)
        EditInputField(label = "Phone", value = phone, onValueChange = { phone = it })

        Spacer(modifier = Modifier.height(16.dp))

        // Payment Method Section
        Text(
            text = "Payment Method",
            color = Color.Gray,
            fontSize = 14.sp,
            modifier = Modifier.padding(start = 8.dp, bottom = 8.dp)
        )

        Card(
            modifier = Modifier.fillMaxWidth().height(80.dp),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFFBFBFB)),
            border = BorderStroke(1.dp, Color(0xFFEEEEEE))
        ) {
            Row(
                modifier = Modifier.fillMaxSize().padding(horizontal = 20.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Cash on Delivery", color = Color.Black, fontWeight = FontWeight.Medium)
                Image(
                    painter = painterResource(id = R.drawable.banner1),
                    contentDescription = null,
                    modifier = Modifier.height(30.dp)
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        // Place My Order Button
        Button(
            onClick = onPlaceOrderClick, // Now triggers navigation to CongratsScreen
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .padding(bottom = 8.dp),
            shape = RoundedCornerShape(15.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.White),
            elevation = ButtonDefaults.buttonElevation(defaultElevation = 6.dp)
        ) {
            Text(
                text = "Place My Order",
                color = startRed,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditInputField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    isSingleLine: Boolean = true
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFFBFBFB)),
        border = BorderStroke(1.dp, Color(0xFFEEEEEE))
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = label,
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp,
                modifier = Modifier.width(70.dp),
                color = Color.Black
            )

            TextField(
                value = value,
                onValueChange = onValueChange,
                modifier = Modifier.weight(1f),
                singleLine = isSingleLine,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                textStyle = TextStyle(fontSize = 14.sp, color = Color.Gray)
            )

            Icon(
                painter = painterResource(id = R.drawable.editicon),
                contentDescription = null,
                modifier = Modifier.size(18.dp),
                tint = Color.Black
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun EditOrderPreview() {
    AppTheme {
        EditOrderScreen(
            onBackClick = {},
            onPlaceOrderClick = {}
        )
    }
}