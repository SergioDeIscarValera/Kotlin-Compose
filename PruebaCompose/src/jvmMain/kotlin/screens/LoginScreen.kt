package screens

import Screen
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import controllers.NavController
import controllers.UserController
import enums.UserRol
import models.User

@Composable
fun LoginScreen(
    navController: NavController,
    controller: UserController
) {
    var user by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val buttonEnable = user.isNotEmpty() && password.isNotEmpty()
    var userLogged: User? by remember { mutableStateOf(null) }

    MaterialTheme {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp, alignment = Alignment.CenterVertically),
            modifier = Modifier.fillMaxSize(),
        ) {
            Text(navController.currentScreen.value, style = MaterialTheme.typography.h5)
            OutlinedTextField(
                user,
                onValueChange = {user = it},
                label = { Text("User") }
            )
            var passVisible by remember { mutableStateOf(false) }
            OutlinedTextField(
                password,
                onValueChange = {password = it},
                label = { Text("Password") },
                visualTransformation = if (passVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    IconToggleButton(checked = passVisible, onCheckedChange = {passVisible = it}) {
                        Icon(
                            imageVector = if (passVisible) Icons.Filled.VisibilityOff else Icons.Filled.Visibility,
                            contentDescription = null
                        )
                    }
                }
            )
            Button(
                onClick = {
                    userLogged = controller.login(user, password)
                    if (userLogged != null){
                        navController.navigate(
                            when(userLogged?.rol){
                                UserRol.ADMINISTRADOR -> Screen.AdminScreen.name
                                UserRol.CLIENTE -> Screen.ClientScreen.name
                                else -> {""}
                            }
                        )
                    }
                },
                enabled = buttonEnable,
            ) {
                Text("Login")
            }
            if (userLogged == null){
                Text("User not logged", color = Color.Red)
            }
        }
    }
}