@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.expensesage

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.expensesage.ui.components.NavBarGraph
import com.example.expensesage.ui.components.Navigations


//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun AppBar(
//    currentScreen: String?,
//    canNavigateBack: Boolean,
//    onNavIconPressed: () -> Unit,
//    modifier: Modifier = Modifier
//) {
//    val currentTitle: String = if (currentScreen == null || currentScreen.isEmpty()) "Expense Sage" else currentScreen.replaceFirstChar(Char::uppercase)
//    TopAppBar(
//        title = { Text(text = currentTitle) },
//        colors = TopAppBarDefaults.topAppBarColors(
//            containerColor = MaterialTheme.colorScheme.primaryContainer,
//        ),
//        modifier = modifier,
//        navigationIcon = {
//            if (canNavigateBack) {
//                IconButton(onClick = onNavIconPressed) {
//                    Icon(
//                        imageVector = Icons.Filled.ArrowBack,
//                        contentDescription = stringResource(id = R.string.back),
//                        tint = MaterialTheme.colorScheme.secondary
//                    )
//                }
//            }
//        }
//    )
//}

@Composable
fun ExpenseSageApp(
    navController: NavHostController = rememberNavController(),

    ) {
    val currentScreen = navController.currentBackStackEntryAsState()

    Scaffold(
//        topBar = {
//            AppBar(
//                currentScreen = currentScreen.value?.destination?.route,
//                canNavigateBack = false,
//                onNavIconPressed = { navController.navigateUp() }
//            )
//        },
        bottomBar = {
            BottomBar(navController = navController)
        }
    ) { innerPadding ->

        Box(modifier = Modifier.padding(innerPadding)) {
            NavBarGraph(
                navController = navController,
            )
        }

    }
}

@Composable
fun BottomBar(navController: NavController) {
    val screens = listOf(
        Navigations.Start,
        Navigations.Expenses,
        Navigations.Owed,
        Navigations.Due,
        Navigations.Summary,
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBar( containerColor = MaterialTheme.colorScheme.primary) {
        screens.forEach { screen ->
            NavigationBarItem(
                label = {
                    Text(
                        text = stringResource(screen.title),
                        fontSize = 8.sp,
                        lineHeight = 8.sp,
                    )
                },
                alwaysShowLabel = false,
                selected = currentRoute == screen.route,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.startDestinationId)
                        launchSingleTop = true
                    }
                },
                icon = { Icon(screen.icon, contentDescription = stringResource(screen.title),
                    tint = if (currentRoute == screen.route) MaterialTheme.colorScheme.surfaceTint else MaterialTheme.colorScheme.onPrimary) },
                colors = NavigationBarItemDefaults.colors(
                    unselectedTextColor = Color.Gray, selectedTextColor = MaterialTheme.colorScheme.onPrimary
                ),
            )
        }

    }
}



