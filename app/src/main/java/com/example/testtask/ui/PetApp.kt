package com.example.testtask.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.testtask.ui.main.MainScreen
import com.example.testtask.ui.petImage.PetImage

@Composable
fun PetApp(){
    val navController = rememberNavController()
    
    NavHost(
        navController = navController, 
        startDestination = "MainScreen"
    ){
        composable("MainScreen"){
            MainScreen(navController)
        }
        composable("PetImage"){
            PetImage(navController = navController)
        }
    }
}