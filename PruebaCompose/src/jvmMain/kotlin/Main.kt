import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AdminPanelSettings
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.VerifiedUser
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import controllers.*
import repositories.UserRepository
import screens.AdminScreen
import screens.ClienteScreen
import screens.LoginScreen

private val userController = UserController(UserRepository())

@Composable
@Preview
fun App() {
    val screens = Screen.values().toList()
    val navController by rememberNavController(Screen.LoginScreen.name)
    val currentScreen by remember {
        navController.currentScreen
    }

    MaterialTheme {
        Surface(
            modifier = Modifier.background(color = MaterialTheme.colors.background)
        ) {
            Box(
                modifier = Modifier.fillMaxSize()
            ){
                /*NavigationRail(
                    modifier = Modifier.align(Alignment.CenterStart).fillMaxSize()
                ){
                    screens.forEach {
                        NavigationRailItem(
                            selected = currentScreen == it.name,
                            icon = {
                                Icon(
                                    imageVector = it.icon,
                                    contentDescription = it.label
                                )
                            },
                            label = {
                                Text(it.label)
                            },
                            alwaysShowLabel = false,
                            onClick = {
                                navController.navigate(it.name)
                            }
                        )
                    }
                }*/

                Box(
                    modifier = Modifier.fillMaxSize()
                ){
                    CustomNavigationHost(navController = navController)
                }
            }
        }
    }
}

@Composable
@Preview
fun adminView(){
    MaterialTheme {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp, alignment = Alignment.CenterVertically),
            modifier = Modifier.fillMaxSize(),
        ) {
            Text("Admin:", style = MaterialTheme.typography.h5)
        }
    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication, title = "Prueba Compose") {
        App()
    }
}

enum class Screen(
    val label: String,
    val icon: ImageVector
){
    LoginScreen(
        label = "Login",
        icon = Icons.Filled.Home
    ),
    AdminScreen(
        label = "AdminView",
        icon = Icons.Filled.AdminPanelSettings
    ),
    ClientScreen(
        label = "ClienteView",
        icon = Icons.Filled.VerifiedUser
    )
}

@Composable
fun CustomNavigationHost(
    navController: NavController
){
    NavigationHost(navController){
        compostable(Screen.LoginScreen.name){
            LoginScreen(navController, userController)
        }
        compostable(Screen.AdminScreen.name){
            AdminScreen(navController)
        }
        compostable(Screen.ClientScreen.name){
            ClienteScreen(navController)
        }
    }.build()
}