package com.ucb.ucbtest.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ucb.ucbtest.gitalias.GitaliasUI
import com.ucb.ucbtest.login.LoginUI
import com.ucb.ucbtest.takephoto.TakePhotoUI

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.LoginScreen.route,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None },
        popEnterTransition = { EnterTransition.None },
        popExitTransition = { ExitTransition.None }

    ) {
        composable(Screen.MenuScreen.route) {
            LoginUI(
                onSuccess = {
                    navController.navigate(Screen.GitaliasScreen.route)
                }
            )
        }

        composable(Screen.GitaliasScreen.route) {
            GitaliasUI()
        }

        composable(Screen.TakePhotoScreen.route) {
            TakePhotoUI()
        }

        composable(Screen.LoginScreen.route) {
            LoginUI(
                onSuccess = {
                    navController.navigate(Screen.GitaliasScreen.route)
                }
            )
        }

    }

}