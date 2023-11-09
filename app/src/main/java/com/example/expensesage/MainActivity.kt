package com.example.expensesage

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
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
        ) {
            if (it) {
                // Permission is granted
                Log.d("MainActivity", "Permission granted")
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
            } else {
                // Permission denied
                Log.d("MainActivity", "Permission denied")
                setContent {
                    ExpenseSageTheme {
                        Surface(modifier = Modifier.fillMaxSize()) {
                            PermissionDenied()
                        }
                    }
                }
            }
        }

        launcher.launch(android.Manifest.permission.POST_NOTIFICATIONS)



    }
}

@Composable
fun PermissionDenied() {
    val context = LocalContext.current
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(text = "This app needs permission to run. Please grant permission in the settings.", style = MaterialTheme.typography.displaySmall)
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            modifier = Modifier
                .width(200.dp)
                .height(50.dp),
            onClick = {
                val intent =
                    Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                        data = Uri.fromParts("package", context.packageName, null)
                    }
                startActivity(context, intent, null)
            },
        ) {
            Text("Go to settings", fontSize = 20.sp)
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
