@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.expensesage

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.WarningAmber
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.expensesage.data.Expense
import com.example.expensesage.ui.AppViewModelProvider
import com.example.expensesage.ui.components.Create
import com.example.expensesage.ui.components.Details
import com.example.expensesage.ui.components.Edit
import com.example.expensesage.ui.components.NavBarGraph
import com.example.expensesage.ui.utils.ModalType
import com.example.expensesage.ui.utils.NavigationType
import com.example.expensesage.ui.utils.Navigations
import com.example.expensesage.ui.utils.screens
import com.example.expensesage.ui.viewModels.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * Top app bar for the app
 *
 * @param currentScreen the current screen
 * @param navigationType the type of navigation required
 * @param onNavIconPressed the action to perform when the navigation icon is pressed
 * @param onBackIconPressed the action to perform when the back icon is pressed
 * @param modifier the modifier to apply to this layout node
 * @param scrollBehavior the scroll behavior of the app bar
 * @param drawerState the state of the drawer
 * @param scope the coroutine scope
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
    currentScreen: Navigations,
    navigationType: NavigationType,
    onNavIconPressed: () -> Unit,
    onBackIconPressed: () -> Unit,
    scrollBehavior: TopAppBarScrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(),
    drawerState: DrawerState,
    scope: CoroutineScope,
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(id = currentScreen.title),
                color = MaterialTheme.colorScheme.onPrimary,
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
        ),
        modifier = Modifier.fillMaxWidth(),
        navigationIcon = {
            if (navigationType == NavigationType.NAVIGATION_RAIL && currentScreen.route != Navigations.Settings.route) {
                IconButton(onClick = {
                    scope.launch {
                        drawerState.open()
                    }
                }) {
                    Icon(
                        imageVector = Icons.Filled.Menu,
                        contentDescription = stringResource(id = R.string.back),
                        tint = MaterialTheme.colorScheme.onPrimary,
                    )
                }
            } else if (currentScreen.route == Navigations.Settings.route || currentScreen.route == Navigations.Edit.route) {
                IconButton(onClick = onBackIconPressed) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(id = Navigations.Settings.title),
                        tint = MaterialTheme.colorScheme.onPrimary,
                    )
                }
            }
        },
        actions = {
            if (currentScreen.route != Navigations.Settings.route) {
                IconButton(onClick = onNavIconPressed) {
                    Icon(
                        imageVector = Navigations.Settings.icon,
                        contentDescription = stringResource(id = Navigations.Settings.title),
                        tint = MaterialTheme.colorScheme.onPrimary,
                    )
                }
            }
        },
        scrollBehavior = scrollBehavior,
    )
}

/**
 * Main app
 *
 * @param navController the navController that handles the navigation
 * @param windowSize the size of the window
 * @param viewModel the viewModel that holds the data
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpenseSageApp(
    navController: NavHostController = rememberNavController(),
    windowSize: WindowWidthSizeClass,
    viewModel: MainViewModel = viewModel(factory = AppViewModelProvider.Factory),
) {
    // Get current back stack entry
    val backStackEntry by navController.currentBackStackEntryAsState()
    // Get the name of the current screen
    val currentScreen =
        if (backStackEntry?.destination?.route?.contains("Edit") == true) {
            Navigations.Edit
        } else {
            Navigations.valueOf(
                backStackEntry?.destination?.route ?: Navigations.Start.name,
            )
        }

    val scrollBehavior =
        TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())

    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val snackbarHostState = remember { SnackbarHostState() }

    val navigationType: NavigationType = when (windowSize) {
        WindowWidthSizeClass.Compact -> {
            NavigationType.BOTTOM_NAVIGATION
        }

        WindowWidthSizeClass.Medium -> {
            NavigationType.NAVIGATION_RAIL
        }

        WindowWidthSizeClass.Expanded -> {
            NavigationType.NAVIGATION_RAIL
        }

        else -> {
            NavigationType.BOTTOM_NAVIGATION
        }
    }

    BackHandler(enabled = true, onBack = {
        if (drawerState.isOpen) {
            scope.launch {
                drawerState.close()
            }
        } else {
            navController.popBackStack()
        }
    })

    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerContent(
                navController = navController,
                drawerState = drawerState,
                scope = scope,
            )
        },
        drawerState = drawerState,
        gesturesEnabled = drawerState.isOpen,
        scrimColor = Color.Transparent,
    ) {
        Scaffold(
            modifier = Modifier
                .nestedScroll(scrollBehavior.nestedScrollConnection)
                .fillMaxWidth(),
            topBar = {
                AppBar(
                    currentScreen = currentScreen,
                    navigationType = navigationType,
                    onNavIconPressed = { navController.navigate(Navigations.Settings.route) },
                    onBackIconPressed = { navController.popBackStack() },
                    scrollBehavior = scrollBehavior,
                    drawerState = drawerState,
                    scope = scope,
                )
            },
            snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
            bottomBar = {
                if (navigationType == NavigationType.BOTTOM_NAVIGATION) {
                    BottomBar(navController = navController)
                }
            },
        ) { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                NavBarGraph(
                    navController = navController,
                    showModal = { expense, isOwed, modalType ->
                        viewModel.showModal(expense, isOwed, modalType)
                    },
                    showAlert = { onConfirm, title, onCancel ->
                        viewModel.showAlert(onConfirm, title, onCancel)
                    },
                )
                ExpenseDialog()
                ExpenseAlert()

            }
        }
    }
}

/**
 * Alert dialog for the app
 *
 */
@Composable
fun ExpenseAlert(
    viewModel: MainViewModel = viewModel(factory = AppViewModelProvider.Factory),
) {
    if (viewModel.isAlertShown) {
        AlertDialog(
            onDismissRequest = {
                viewModel.alertOnCancel()
                viewModel.onDialogDismiss()
                               },
            confirmButton = {
                Button(
                    onClick = {
                        viewModel.alertOnConfirm()
                        viewModel.onDialogDismiss()
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        contentColor = MaterialTheme.colorScheme.onSurface,
                    ),
                ) {
                    Text(text = "Confirm")
                }
            },
            dismissButton = {
                Button(
                    onClick = {
                        viewModel.alertOnCancel()
                        viewModel.onDialogDismiss()
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.inverseOnSurface,
                        contentColor = MaterialTheme.colorScheme.onSurface,
                    ),
                ) {
                    Text(text = "Cancel")
                }
            },
            title = {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(Icons.Outlined.WarningAmber, contentDescription = "Alert")
                    Text(
                        text = viewModel.alertTitle,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth(),
                    )
                }
            },
            modifier = Modifier.padding(16.dp),
            shape = MaterialTheme.shapes.large,
            properties = DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = true),
            containerColor = MaterialTheme.colorScheme.surface,

            )
    }
}

/**
 * Dialog for the app
 *
 *
 */
@Composable
fun ExpenseDialog(
    viewModel: MainViewModel = viewModel(factory = AppViewModelProvider.Factory),
) {
    if (viewModel.isDialogShown) {
        Dialog(
            onDismissRequest = { viewModel.onDialogDismiss() },
            properties = DialogProperties(
                dismissOnBackPress = true,
                dismissOnClickOutside = true,
            ),
        ) {
            when (viewModel.currentModalType) {
                ModalType.DETAIL -> {
                    Details(
                        viewModel.selectedExpense,
                    ) { viewModel.onDialogDismiss() }
                }

                ModalType.EDIT -> {
                    Edit(
                        viewModel.selectedExpense,
                        { viewModel.onDialogDismiss() },
                    )
                }

                else -> {
                    Create(
                        onDialogDismiss = { viewModel.onDialogDismiss() },
                        isOwed = viewModel.isOwed,
                    )
                }
            }
        }
    }
}

/**
 * Bottom navigation bar for the app
 *
 * @param navController the navController that handles the navigation
 */
@Composable
fun BottomBar(navController: NavController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBar(containerColor = MaterialTheme.colorScheme.primary) {
        screens.forEach { screen ->
//            Badge
            NavigationBarItem(
                label = {
                    Text(
                        text = stringResource(screen.title),
                        fontSize = 9.sp,
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
                icon = {
                    Icon(
                        screen.icon,
                        contentDescription = stringResource(screen.title),
                        tint = if (currentRoute == screen.route) MaterialTheme.colorScheme.surfaceTint else MaterialTheme.colorScheme.onPrimary,
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    unselectedTextColor = Color.Gray,
                    selectedTextColor = MaterialTheme.colorScheme.onPrimary,
                ),
            )
        }
    }
}

/**
 * Modal drawer content for navigation drawer
 *
 * @param navController the navController that handles the navigation
 * @param drawerState the state of the drawer
 * @param scope the coroutine scope
 */
@Composable
fun ModalDrawerContent(
    navController: NavHostController,
    drawerState: DrawerState,
    scope: CoroutineScope,
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    ModalDrawerSheet(
//        drawerContainerColor = if (drawerState.isClosed) Color.Transparent else MaterialTheme.colorScheme.surface
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(
                space = 12.dp,
                alignment = Alignment.CenterVertically,
            ),
            modifier = Modifier.fillMaxHeight(),
        ) {
            screens.forEach { screen ->
                NavigationDrawerItem(
                    label = {
                        Text(
                            text = stringResource(screen.title),
                            fontSize = 16.sp,
                            lineHeight = 8.sp,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth(),
                        )
                    },
                    selected = currentRoute == screen.route,
                    onClick = {
                        navController.navigate(screen.route) {
                            popUpTo(navController.graph.startDestinationId)
                            launchSingleTop = true
                        }
                        scope.launch { drawerState.close() }
                    },
                    icon = {
                        Icon(
                            screen.icon,
                            contentDescription = stringResource(screen.title),
                            tint = if (currentRoute == screen.route) MaterialTheme.colorScheme.surfaceTint else MaterialTheme.colorScheme.secondary,
                        )
                    },
                    colors = NavigationDrawerItemDefaults.colors(
                        unselectedTextColor = Color.Gray,
                        selectedTextColor = MaterialTheme.colorScheme.onPrimary,
                    ),
                )
            }
        }
    }
}
