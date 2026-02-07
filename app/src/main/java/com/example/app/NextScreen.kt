package com.example.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.app.ui.theme.AppTheme
import com.example.app.ui.theme.YongFontFamily
import com.example.app.ui.theme.cornerColor
import com.example.app.ui.theme.startRed
import com.example.app.ui.theme.testColor
import kotlinx.coroutines.delay

@Composable
@Preview(showBackground = true)
fun NextScreen(){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(90.dp))
      Image(
          painter = painterResource(id = R.drawable.littlelogo),
          contentDescription = "Wafes of food illustration",
          modifier = Modifier
              .size(91.dp)
      )
        MainTextTitle()
        Spacer(modifier = Modifier.height(20.dp))
        DeleveryFavoriteFoodText()
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Login to Your Account ",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = startRed,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(40.dp))
        NameInput()
        Spacer(modifier = Modifier.height(20.dp))
        PasswordInput()
        Text(
            text = "Or",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Gray,
            modifier = Modifier.padding(top = 20.dp)
        )
        Text(
            text = "Continue With",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Italic,
            color = Color.Black,
            fontFamily = YongFontFamily,
            modifier = Modifier.padding(top = 10.dp)
        )
        Spacer(modifier = Modifier.height(380.dp))
        FooterText()
    }
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Bottom

    ) {

    }
}


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

@Composable
fun DeleveryFavoriteFoodText(){
    Text(
        text = "Deliever Favorite Food",
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        color = startRed,
    )
}

@Composable
fun NameInput(){
    var text by remember { mutableStateOf("") }
    OutlinedTextField(
        value = text,
        onValueChange = { newText -> text = newText},
        placeholder = { Text(text = "Enter your login")},
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(15.dp),
        colors = TextFieldDefaults.colors(
            focusedTextColor = cornerColor,
            unfocusedTextColor = cornerColor,
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
        )
    )
}
@Composable
fun PasswordInput(){
    var text by remember { mutableStateOf("") }
    OutlinedTextField(
        value = text,
        onValueChange = { newText -> text = newText},
        placeholder = { Text(text = "Enter your login")},
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(15.dp),
        colors = TextFieldDefaults.colors(
            focusedTextColor = cornerColor,
            unfocusedTextColor = cornerColor,
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
        )
    )
}

@Composable
fun FooterText(){
    Text(
        text = "Design By" +
                " \n NeatRoots",
        color = startRed,
        fontFamily = YongFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp
    )
}