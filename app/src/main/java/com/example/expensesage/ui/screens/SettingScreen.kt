package com.example.expensesage.ui.screens

import android.media.MediaPlayer
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Stop
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.expensesage.R
import com.example.expensesage.ui.AppViewModelProvider
import com.example.expensesage.ui.MainViewModel
import com.example.expensesage.ui.utils.CurrencyVisualTransformation
import com.example.expensesage.ui.viewModels.SettingsViewModel

/**
 * Composable that displays the setting screen of the app
 *
 */
@Composable
fun SettingScreen(
    viewModel: MainViewModel,
    settingsViewModel: SettingsViewModel = viewModel(factory = AppViewModelProvider.Factory),
) {
    val mContext = LocalContext.current
    val mMediaPlayer = MediaPlayer.create(mContext, R.raw.rockmusic)
    mMediaPlayer.setVolume(50f, 50f)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        PocketMoney(settingsViewModel)
        Row {
            Text(
                modifier = Modifier.padding(vertical = 12.dp),
                text = "Music:",
                style = MaterialTheme.typography.labelLarge,
                textAlign = TextAlign.Center,
                lineHeight = 64.sp,
            )
            IconButton(onClick = { mMediaPlayer.start() }) {
                Icon(Icons.Default.PlayArrow, contentDescription = "", Modifier.size(64.dp))
            }

            // IconButton for Pause Action
            IconButton(onClick = { mMediaPlayer.stop() }) {
                Icon(Icons.Default.Stop, contentDescription = "", Modifier.size(64.dp))
            }
        }
    }
}

/**
 * Composable that displays the pocket money field
 *
 */
@Composable
fun PocketMoney(settingsViewModel: SettingsViewModel) {
//    val keyboardController = LocalSoftwareKeyboardController.current
    val moneyAvailable by settingsViewModel.getMoneyAvailable().collectAsState()
    var edit by rememberSaveable { mutableStateOf(true) }
    val focusRequester = remember { FocusRequester() }
    var text: String by remember { mutableStateOf(moneyAvailable.toString()) }

    DisposableEffect(moneyAvailable) {
        text = if (moneyAvailable == 0.0) {
            ""
        } else {
            moneyAvailable.toString()
        }
        onDispose { /* Dispose logic if needed */ }
    }

    Row(
        modifier = Modifier.padding(16.dp),
    ) {
        TextField(
//            prefix = { Text(text = "$") },
            modifier = Modifier
                .focusRequester(focusRequester)
                .width(164.dp),
            label = { Text(text = "Pocket Money") },
            placeholder = { Text(text = "Enter your pocket money") },
            value = text,
            onValueChange = {
                text = it
            },
            singleLine = true,
            readOnly = edit,
            enabled = !edit,
            leadingIcon = {
                Icon(
                    Icons.Filled.AttachMoney,
                    contentDescription = "Localized description",
                )
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
            visualTransformation = CurrencyVisualTransformation(),
            keyboardActions = KeyboardActions(
                onDone = {
                    val money: Double = if (text == "" || text == "0") {
                        0.0
                    } else {
                        text.toDouble() / 100
                    }
                    edit = !edit
                    settingsViewModel.onMoneyChange(money)
                },
            ),
        )

        IconButton(onClick = {
            edit = !edit
            focusRequester.requestFocus()
        }) {
            Icon(Icons.Default.Edit, contentDescription = "", Modifier.size(20.dp))
        }
    }
}
