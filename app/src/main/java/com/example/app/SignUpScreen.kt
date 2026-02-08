package com.example.app

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.app.ui.theme.YongFontFamily
import com.example.app.ui.theme.startRed

@Composable
@Preview(showBackground = true)
fun SignUpScreen(
    onAlreadyHaveAccountClick: () -> Unit = {},
    onSignUpSuccess: () -> Unit = {} // Добавили параметр успеха для перехода на LocationScreen
) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(40.dp))

            Image(
                painter = painterResource(id = R.drawable.littlelogo),
                contentDescription = "Logo",
                modifier = Modifier.size(91.dp)
            )

            MainTextTitle()

            Text(
                text = "Sign Up Here",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = startRed,
                modifier = Modifier.padding(top = 16.dp)
            )

            Spacer(modifier = Modifier.height(30.dp))

            // Поле Имя
            AuthInputField(
                value = name,
                onValueChange = { name = it },
                placeholder = "Name",
                leadingIcon = R.drawable.user
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Поле Email
            AuthInputField(
                value = email,
                onValueChange = { email = it },
                placeholder = "Email or Phone Number",
                leadingIcon = R.drawable.email
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Поле Пароль
            AuthInputField(
                value = password,
                onValueChange = { password = it },
                placeholder = "Password",
                leadingIcon = R.drawable.lock,
                isPassword = true
            )

            Text(
                text = "or",
                fontSize = 16.sp,
                color = Color.Gray,
                modifier = Modifier.padding(top = 20.dp)
            )

            Text(
                text = "Sign Up With",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                fontFamily = YongFontFamily,
                modifier = Modifier.padding(top = 10.dp)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                SocialLoginButton(
                    text = "Facebook",
                    iconRes = R.drawable.facebookicon,
                    onClick = { /* gRPC FB Sign Up */ },
                    modifier = Modifier.weight(1f)
                )
                SocialLoginButton(
                    text = "Google",
                    iconRes = R.drawable.googleicon,
                    onClick = { /* gRPC Google Sign Up */ },
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(30.dp))

            // Кнопка Create Account с переходом
            GradientButton(
                text = "Create Account",
                onClick = {
                    // В будущем здесь будет вызов gRPC Python Backend
                    onSignUpSuccess() // Вызываем переход на LocationScreen
                }
            )

            Spacer(modifier = Modifier.height(15.dp))

            Text(
                text = "Already Have An Account?",
                color = startRed,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.clickable { onAlreadyHaveAccountClick() }
            )
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 20.dp),
            contentAlignment = Alignment.BottomCenter
        ) {
            FooterText()
        }
    }
}

@Composable
fun AuthInputField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    leadingIcon: Int,
    isPassword: Boolean = false
) {
    var passwordVisible by remember { mutableStateOf(false) }

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .shadow(elevation = 8.dp, shape = RoundedCornerShape(15.dp)),
        color = Color.White,
        shape = RoundedCornerShape(15.dp)
    ) {
        TextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = { Text(text = placeholder, color = Color.LightGray) },
            leadingIcon = {
                Image(
                    painter = painterResource(id = leadingIcon),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
            },
            trailingIcon = {
                if (isPassword) {
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Image(
                            painter = painterResource(id = R.drawable.eyelogo),
                            contentDescription = null,
                            modifier = Modifier
                                .size(24.dp)
                                .alpha(if (passwordVisible) 1f else 0.5f)
                        )
                    }
                }
            },
            visualTransformation = if (isPassword && !passwordVisible) PasswordVisualTransformation() else VisualTransformation.None,
            keyboardOptions = if (isPassword) KeyboardOptions(keyboardType = KeyboardType.Password) else KeyboardOptions.Default,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            ),
            singleLine = true,
            modifier = Modifier.fillMaxSize()
        )
    }
}