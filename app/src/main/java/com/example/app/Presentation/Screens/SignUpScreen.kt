package com.example.app.Presentation.Screens

import FooterText
import GradientButton
import MainTextTitle
import SocialLoginButton
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.app.R
import com.example.app.Presentation.theme.YongFontFamily
import com.example.app.Presentation.theme.startRed

@Composable
fun SignUpScreen(
    onAlreadyHaveAccountClick: () -> Unit = {},
    onSignUpSuccess: () -> Unit = {}
) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val scrollState = rememberScrollState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color.White
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color.White)
                .verticalScroll(scrollState)
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // 1. Отступ сверху
            Spacer(modifier = Modifier.height(40.dp))

            // 2. Логотип (уменьшен для изящности)
            Image(
                painter = painterResource(id = R.drawable.littlelogo),
                contentDescription = "Logo",
                modifier = Modifier.size(80.dp)
            )

            MainTextTitle()

            // 3. Заголовок (уменьшен шрифт)
            Text(
                text = "Sign Up Here",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = startRed,
                modifier = Modifier.padding(top = 12.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            // 4. Поля ввода (используем AuthInputField)
            AuthInputField(
                value = name,
                onValueChange = { name = it },
                placeholder = "Name",
                leadingIcon = R.drawable.user
            )

            Spacer(modifier = Modifier.height(12.dp))

            AuthInputField(
                value = email,
                onValueChange = { email = it },
                placeholder = "Email or Phone Number",
                leadingIcon = R.drawable.email
            )

            Spacer(modifier = Modifier.height(12.dp))

            AuthInputField(
                value = password,
                onValueChange = { password = it },
                placeholder = "Password",
                leadingIcon = R.drawable.lock,
                isPassword = true
            )

            // 5. Разделитель
            Text(
                text = "or",
                fontSize = 14.sp,
                color = Color.Gray,
                modifier = Modifier.padding(top = 16.dp)
            )

            Text(
                text = "Sign Up With",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                fontFamily = YongFontFamily,
                modifier = Modifier.padding(top = 4.dp)
            )

            // 6. Социальные кнопки
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                SocialLoginButton(
                    text = "Facebook",
                    iconRes = R.drawable.facebookicon,
                    onClick = { /* gRPC FB */ },
                    modifier = Modifier.weight(1f)
                )
                SocialLoginButton(
                    text = "Google",
                    iconRes = R.drawable.googleicon,
                    onClick = { /* gRPC Google */ },
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // 7. Кнопка создания аккаунта
            GradientButton(
                text = "Create Account",
                onClick = { onSignUpSuccess() }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // 8. Ссылка на логин
            Text(
                text = "Already Have An Account?",
                color = startRed,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.clickable { onAlreadyHaveAccountClick() }
            )

            // 9. Футер
            Spacer(modifier = Modifier.height(40.dp))
            FooterText()
            Spacer(modifier = Modifier.height(20.dp))
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
            .height(56.dp) // Чуть уменьшил высоту для компактности
            .shadow(elevation = 4.dp, shape = RoundedCornerShape(15.dp)),
        color = Color.White,
        shape = RoundedCornerShape(15.dp)
    ) {
        TextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = { Text(text = placeholder, color = Color.LightGray, fontSize = 14.sp) },
            leadingIcon = {
                Image(
                    painter = painterResource(id = leadingIcon),
                    contentDescription = null,
                    modifier = Modifier.size(20.dp)
                )
            },
            trailingIcon = {
                if (isPassword) {
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Image(
                            painter = painterResource(id = R.drawable.eyelogo),
                            contentDescription = null,
                            modifier = Modifier
                                .size(20.dp)
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
                disabledIndicatorColor = Color.Transparent
            ),
            singleLine = true,
            modifier = Modifier.fillMaxSize()
        )
    }
}