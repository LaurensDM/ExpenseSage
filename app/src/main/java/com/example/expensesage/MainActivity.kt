package com.example.expensesage

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.expensesage.ui.theme.ExpenseSageTheme

class MainActivity : ComponentActivity() {

    /**
     * viewModel that is used to store the state of the app.
     */

    /**
     * onCreate function that sets the content of the app to the ExpenseSageApp composable.
     *
     *                 val workRequest = OneTimeWorkRequestBuilder<BudgetWorker>()
     * //            .setConstraints(
     * //                Constraints.Builder()
     * //                    .setRequiredNetworkType(NetworkType.CONNECTED)
     * //                    .build()
     * //            )
     *                     .build()
     *                 WorkManager.getInstance(this).enqueueUniqueWork("BudgetWorker", ExistingWorkPolicy.REPLACE, workRequest)
     *
     * @param savedInstanceState The saved instance state of the app
     */
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val launcher = registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) {}

        launcher.launch(android.Manifest.permission.POST_NOTIFICATIONS)

        setContent {
            ExpenseSageTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    val windowSize = calculateWindowSizeClass(this)
                    ExpenseSageApp(windowSize = windowSize.widthSizeClass)
                }
            }
        }
    }
}

/**
 * Composable that displays what the UI of the app looks like in light theme in the design tab.
 *
 */
@Preview(showSystemUi = true)
@Composable
fun ExpensePreview() {
    ExpenseSageTheme(darkTheme = false) {
        ExpenseSageApp(windowSize = WindowWidthSizeClass.Compact)
    }
}

/**
 * Composable that displays what the UI of the app looks like in dark theme in the design tab.
 */
@Preview
@Composable
fun ExpenseDarkThemePreview() {
    ExpenseSageTheme(darkTheme = true) {
        ExpenseSageApp(windowSize = WindowWidthSizeClass.Compact)
    }
}
