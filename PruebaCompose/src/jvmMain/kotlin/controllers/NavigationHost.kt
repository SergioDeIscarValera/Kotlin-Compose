package controllers

import androidx.compose.runtime.Composable

class NavigationHost(
    val navController: NavController,
    val contents: @Composable NavigationGraphBuilder.() -> Unit
) {
    @Composable
    fun build(){
        NavigationGraphBuilder().renderContents()
    }

    inner class NavigationGraphBuilder(
        val navController: NavController = this@NavigationHost.navController
    ){
        @Composable
        fun renderContents(){
            this@NavigationHost.contents(this)
        }
    }
}

@Composable
fun NavigationHost.NavigationGraphBuilder.compostable(
    route: String,
    content: @Composable () -> Unit
){
    if (navController.currentScreen.value == route){
        content()
    }
}