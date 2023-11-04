package com.example.expensesage.ui.screens

import android.media.MediaPlayer
import android.util.Log
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Stop
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
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
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.expensesage.R
import com.example.expensesage.data.currencyList
import com.example.expensesage.ui.AppViewModelProvider
import com.example.expensesage.ui.components.CurrencyIcon
import com.example.expensesage.ui.utils.CurrencyVisualTransformation
import com.example.expensesage.ui.viewModels.SettingsViewModel
import com.example.expensesage.ui.viewModels.SnackBarType
import kotlinx.coroutines.flow.StateFlow

/**
 * Composable that displays the setting screen of the app
 *
 */
@Composable
fun SettingScreen(
    showSnackBar: (message: String, snackBarType: SnackBarType) -> Unit,
    settingsViewModel: SettingsViewModel = viewModel(factory = AppViewModelProvider.Factory),
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(enabled = true, state = ScrollState(0)),
        verticalArrangement = Arrangement.spacedBy(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        PocketMoney(
            showSnackBar,
            moneyAvailable = settingsViewModel.moneyAvailable,
            updateMoneyAvailable = { settingsViewModel.updateMoneyAvailable(it) },
            changeMoneyAvailable = { settingsViewModel.changeMoneyAvailable() },
        )
        MonthlyBudget(
            showSnackBar = showSnackBar,
            monthlyBudget = settingsViewModel.monthlyBudget,
            updateMonthlyBudget = { settingsViewModel.updateMonthlyBudget(it) },
            changeMonthlyBudget = { settingsViewModel.changeMonthlyBudget() },
        )
        CurrencySelect(
            onSelect = {
                settingsViewModel.changeCurrency(it)
            },
            currentCurrency = settingsViewModel.getCurrency(),
            showSnackBar = showSnackBar,
        )
        MusicPlayer()
    }
}

/**
 * Composable that displays the pocket money field
 *
 */
@Composable
fun PocketMoney(
    showSnackBar: (message: String, snackBarType: SnackBarType) -> Unit,
    moneyAvailable: String,
    updateMoneyAvailable: (String) -> Unit,
    changeMoneyAvailable: () -> Unit,
) {
//    val keyboardController = LocalSoftwareKeyboardController.current

    var edit by rememberSaveable { mutableStateOf(true) }
    val focusRequester = remember { FocusRequester() }

    Row(
        modifier = Modifier.padding(start = 48.dp),
        horizontalArrangement = Arrangement.End,
    ) {
        TextField(
            modifier = Modifier
                .focusRequester(focusRequester)
                .width(164.dp),
            label = { Text(text = "Pocket Money") },
            placeholder = { Text(text = "Enter your pocket money") },
            value = moneyAvailable,
            onValueChange = {
                updateMoneyAvailable(it)
            },
            singleLine = true,
            readOnly = edit,
            enabled = !edit,
            leadingIcon = {
                CurrencyIcon()
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
            visualTransformation = CurrencyVisualTransformation(),
            keyboardActions = KeyboardActions(
                onDone = {
                    edit = !edit
                    changeMoneyAvailable()
                    showSnackBar("Changed money to $moneyAvailable", SnackBarType.SUCCESS)
                },
            ),
        )

        IconButton(onClick = {
            edit = !edit
            focusRequester.requestFocus()
        }) {
            Icon(Icons.Default.Edit, contentDescription = "Edit", Modifier.size(20.dp))
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun MonthlyBudget(
    showSnackBar: (message: String, snackBarType: SnackBarType) -> Unit,
    monthlyBudget: String,
    updateMonthlyBudget: (String) -> Unit,
    changeMonthlyBudget: () -> Unit,
) {

    val keyboardController = LocalSoftwareKeyboardController.current

    Row(
        modifier = Modifier.padding(16.dp),
    ) {
        TextField(
            modifier = Modifier
                .width(164.dp),
            label = { Text(text = "Monthly Budget") },
            placeholder = { Text(text = "Enter your pocket money") },
            value = monthlyBudget,
            onValueChange = {
                updateMonthlyBudget(it)
            },
            singleLine = true,
            leadingIcon = {
                CurrencyIcon()
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
            visualTransformation = CurrencyVisualTransformation(),
            keyboardActions = KeyboardActions(
                onDone = {
                    changeMonthlyBudget()
                    showSnackBar("Changed monthly budget to $monthlyBudget", SnackBarType.SUCCESS)
                    keyboardController?.hide()
                },
            ),
        )
    }
}

@Composable
fun CurrencySelect(
    onSelect: (String) -> Unit,
    currentCurrency: StateFlow<String>,
    showSnackBar: (message: String, snackBarType: SnackBarType) -> Unit
) {
//    val scope = rememberCoroutineScope()
    var expanded by rememberSaveable { mutableStateOf(false) }
    val currency by currentCurrency.collectAsState()

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
    ) {
        Text(
            text = "Currency",
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 16.dp),
            style = MaterialTheme.typography.labelLarge,
        )

        Box() {
            Button(onClick = { expanded = !expanded }) {
                Text(text = currency)
                Icon(Icons.Filled.ExpandMore, contentDescription = "Expand")
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                offset = DpOffset(16.dp, 0.dp),
            ) {
                currencyList.forEach {
                    DropdownMenuItem(text = { Text(text = it) }, onClick = {
                        onSelect(it)
//                        var double = 0.0
//                        scope.launch {
//                            double = apiViewModel.getRate(it)
//                            Log.d("Dropdown", "CurrencyRate: $double")
//                        }
                        Log.i("Dropdown", "$it selected")
                        expanded = false
                        showSnackBar("Changed currency to $it", SnackBarType.SUCCESS)
                    })
                }
            }
        }
    }
}

@Composable
fun MusicPlayer() {
    val mContext = LocalContext.current
    val mMediaPlayer = MediaPlayer.create(mContext, R.raw.rockmusic)
    mMediaPlayer.setVolume(50f, 50f)

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
