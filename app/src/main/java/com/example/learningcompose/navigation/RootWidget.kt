package com.example.learningcompose.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.learningcompose.ui.theme.LearningComposeTheme

@Composable
fun RootWidget() {
    val navController = rememberNavController()

    val initialArgument = "What if this is wrong?"

    NavHost(
        navController = navController,
        startDestination = "$COMPOSE_A_ROUTE/{start}"
    ) {
        composable(route = "$COMPOSE_A_ROUTE/{start}") {
            ComposeA(initialArgument) {
                navController.navigate("$COMPOSE_B_ROUTE/Mahmood/24")
            }
        }
        composable(
            route = "$COMPOSE_B_ROUTE/{name}/{age}",
            arguments = listOf(
                navArgument("name") { type = NavType.StringType },
                navArgument("age") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val name = backStackEntry.arguments?.getString("name") ?: "NoArgs"
            val age = backStackEntry.arguments?.getInt("age") ?: "NoArgs"
            ComposeB("$name $age") {
                navController.navigate(COMPOSE_C_ROUTE)
            }
        }
        composable(route = COMPOSE_C_ROUTE) {
            ComposeC {
                navController.popBackStack()
                    navController.popBackStack()
            }
        }
    }

}


@Preview
@Composable
fun RootWidgetPreview() {
    LearningComposeTheme {
        RootWidget()
    }
}


const val COMPOSE_A_ROUTE = "compose_a"
const val COMPOSE_B_ROUTE = "compose_b"
const val COMPOSE_C_ROUTE = "compose_c"

fun String.withArgs(vararg args: String): String {
    return buildString {
        append(this)
        args.forEach {
            append("/$it")
        }
    }
}
