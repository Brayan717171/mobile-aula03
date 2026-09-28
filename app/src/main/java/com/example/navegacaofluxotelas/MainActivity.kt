package com.example.navegacaofluxotelas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.navegacaofluxotelas.screens.LoginScreen
import com.example.navegacaofluxotelas.screens.MenuScreen
import com.example.navegacaofluxotelas.screens.PedidosScreen
import com.example.navegacaofluxotelas.screens.PerfilScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            NavHost(
                navController = navController,
                startDestination = "login" // Tela inicial
            ) {
                // Rota simples
                composable(route = "login") { LoginScreen(navController) }

                // Rota simples para o Menu
                composable(route = "menu") { MenuScreen(navController) }

                // Rota com parâmetros obrigatórios (nome e idade)
                composable(
                    route = "perfil/{nome}/{idade}",
                    arguments = listOf(
                        navArgument("nome") { type = NavType.StringType },
                        navArgument("idade") { type = NavType.IntType }
                    )
                ) {
                    val nome = it.arguments?.getString("nome")
                    val idade = it.arguments?.getInt("idade")
                    PerfilScreen(navController, nome!!, idade!!)
                }

                // Rota com parâmetro opcional (estilo query string)
                composable(
                    route = "pedidos?numeroPedido={numeroPedido}",
                    arguments = listOf(
                        navArgument("numeroPedido") {
                            type = NavType.StringType
                            defaultValue = "Sem pedidos"
                        }
                    )
                ) {
                    val numeroPedido = it.arguments?.getString("numeroPedido")
                    PedidosScreen(navController, numeroPedido!!)
                }
            }
        }
    }
}