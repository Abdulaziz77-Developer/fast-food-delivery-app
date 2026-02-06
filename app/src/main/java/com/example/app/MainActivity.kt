package com.example.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.app.ui.theme.AppTheme
import com.example.app.ui.theme.YongFontFamily
import com.example.app.ui.theme.startRed
import com.example.app.ui.theme.testColor

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    WavesOfFoodScreen()
                }
            }
        }
    }
}

@Composable

@Preview(showBackground = true)
fun WavesOfFoodScreen(){
    Column(
        modifier= Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(
            modifier = Modifier.height(152.dp)
        )
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Wafes of food illustration",
            modifier = Modifier
                .size(200.dp)
                .padding(bottom = 16.dp)
        )
        Text(
            text = "Wafes of Food",
            fontFamily = YongFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 44.sp,
            color = testColor,
            modifier = Modifier
                .padding(bottom = 32.dp)
        )
        Text(
            text = "Deliever Favorite Food",
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            color = startRed,
        )
        Spacer(
            modifier = Modifier
                .height(300.dp)
        )
        Text(
            text = "Design By \n" +
                    "NeatRoots",
            color = startRed,
            fontFamily = YongFontFamily,
            fontSize = 18.sp
        )
    }
}