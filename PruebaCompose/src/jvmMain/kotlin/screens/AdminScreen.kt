package screens

import Screen
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import controllers.NavController

@Composable
fun AdminScreen(
    navController: NavController
){
    MaterialTheme {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp, alignment = Alignment.CenterVertically),
            modifier = Modifier.fillMaxSize(),
        ) {
            Text(navController.currentScreen.value, style = MaterialTheme.typography.h5)
            Button(
                onClick = {
                    navController.navigate(Screen.LoginScreen.name)
                },
            ) {
                Text("Logout")
            }
        }
    }
}