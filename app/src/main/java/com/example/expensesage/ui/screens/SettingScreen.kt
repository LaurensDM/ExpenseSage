package com.example.expensesage.ui.screens

import android.util.Log
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.Feedback
import androidx.compose.material.icons.filled.Help
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.outlined.MusicNote
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Slider
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.expensesage.data.currencyList
import com.example.expensesage.ui.AppViewModelProvider
import com.example.expensesage.ui.components.CurrencyIcon
import com.example.expensesage.ui.components.CurrencyString
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
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(enabled = true, state = ScrollState(0)),
        verticalArrangement = Arrangement.spacedBy(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        CurrencySettings(showSnackBar = showSnackBar)
        Divider()
        SoundPreferences()
        Divider()
        FeedbackHelp()
        Divider()
        About()
    }
}

@Composable
fun CurrencySettings(
    showSnackBar: (message: String, snackBarType: SnackBarType) -> Unit,
    settingsViewModel: SettingsViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = "Currency Settings", style = MaterialTheme.typography.displaySmall)
        BudgetFrequencySelect(
            settingsViewModel.budgetFrequencyState,
            settingsViewModel::updateBudgetFrequency
        )
        MonthlyBudget(
            showSnackBar = showSnackBar,
            budget = settingsViewModel.budget,
            budgetFrequency = settingsViewModel.budgetFrequencyState,
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
    }
}

@Composable
fun BudgetFrequencySelect(budgetFrequencyState: String, updateBudgetFrequency: (String) -> Unit) {
    val budgetFrequency = listOf("Weekly", "Monthly", "Yearly")

    Column {
        Text(
            text = "Budget Frequency",
            style = MaterialTheme.typography.labelLarge,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        budgetFrequency.forEach {
            Row(
                Modifier.selectable(
                    selected = (it == budgetFrequencyState),
                    onClick = { updateBudgetFrequency(it) },
                    role = Role.RadioButton
                )
            ) {
                ListItem(
                    headlineContent = {
                        Text(
                            text = it,
                            style = MaterialTheme.typography.labelMedium
                        )
                    },
                    trailingContent = {
                        RadioButton(
                            selected = (it == budgetFrequencyState),
                            onClick = null,
                        )
                    })
            }

        }
    }

}

/**
 * Composable that displays the pocket money field
 *
 */
@Composable
fun PocketMoney(
    moneyAvailable: String,
    updateMoneyAvailable: (String) -> Unit,
    onDone: () -> Unit,
) {
    OutlinedTextField(
        modifier = Modifier
            .width(128.dp),
        label = { Text(text = "Budget") },
        placeholder = { Text(text = "Enter your budget") },
        value = moneyAvailable,
        onValueChange = {
            updateMoneyAvailable(it)
        },

        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
        visualTransformation = CurrencyVisualTransformation(),
        keyboardActions = KeyboardActions(
            onDone = {
                this.defaultKeyboardAction(ImeAction.Done)
                onDone()
            },
        ),
    )
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun MonthlyBudget(
    showSnackBar: (message: String, snackBarType: SnackBarType) -> Unit,
    budget: String,
    budgetFrequency: String,
    updateMonthlyBudget: (String) -> Unit,
    changeMonthlyBudget: () -> Unit,
) {

    var edit by rememberSaveable { mutableStateOf(false) }



    ListItem(
        modifier = Modifier.clickable {
            edit = !edit
        },
        leadingContent = {
            Icon(Icons.Default.Edit, contentDescription = "Edit")
        },
        headlineContent = {
            Text(
                text = "$budgetFrequency budget",
                style = MaterialTheme.typography.labelLarge
            )
        },
        supportingContent = {
            if (edit) {
                PocketMoney(
                    moneyAvailable = budget,
                    updateMoneyAvailable = updateMonthlyBudget,
                    onDone = {
                        edit = false
                        changeMonthlyBudget()
                        showSnackBar(
                            "Changed budget to $budget",
                            SnackBarType.SUCCESS
                        )
                    },
                )
            } else {
                Text(text = CurrencyString(money = budget.toDouble(), fractionDigits = 2))
            }

        },
    )

//    val keyboardController = LocalSoftwareKeyboardController.current
//
//    Row(
//        modifier = Modifier.padding(16.dp),
//    ) {
//        TextField(
//            modifier = Modifier
//                .width(164.dp),
//            label = { Text(text = "Monthly Budget") },
//            placeholder = { Text(text = "Enter your pocket money") },
//            value = monthlyBudget,
//            onValueChange = {
//                updateMonthlyBudget(it)
//            },
//            singleLine = true,
//            leadingIcon = {
//                CurrencyIcon()
//            },
//            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
//            visualTransformation = CurrencyVisualTransformation(),
//            keyboardActions = KeyboardActions(
//                onDone = {
//                    changeMonthlyBudget()
//                    showSnackBar("Changed monthly budget to $monthlyBudget", SnackBarType.SUCCESS)
//                    keyboardController?.hide()
//                },
//            ),
//        )
//    }
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

    Box(modifier = Modifier.fillMaxWidth()) {
        ListItem(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = { expanded = !expanded }),
            leadingContent = {
                CurrencyIcon()
            },
            headlineContent = {
                Text(text = "Change currency", style = MaterialTheme.typography.labelLarge)
            },
            supportingContent = {
                Text(text = currency)
            },
            trailingContent = {
                Icon(
                    if (expanded)
                        Icons.Default.ExpandLess
                    else
                        Icons.Default.ExpandMore,

                    contentDescription = null,
                )
            }
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            offset = DpOffset(Dp.Infinity, 0.dp),
        ) {
            currencyList.forEach {
                DropdownMenuItem(text = { Text(text = it) }, onClick = {
                    onSelect(it)
                    Log.i("Dropdown", "$it selected")
                    expanded = false
                    showSnackBar("Changed currency to $it", SnackBarType.SUCCESS)
                })
            }
        }
    }
}

@Composable
fun SoundPreferences() {
//    val mContext = LocalContext.current
//    val mMediaPlayer = MediaPlayer.create(mContext, R.raw.rockmusic)
//    mMediaPlayer.setVolume(50f, 50f)
    var soundOn by remember { mutableStateOf(true) }
    var volume by remember { mutableStateOf(0.5f) }
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(32.dp),
    )
    {
        Text(text = "Sound Settings", style = MaterialTheme.typography.displaySmall)
        ListItem(
            modifier = Modifier.fillMaxWidth(),
            headlineContent = {
                Icon(
                    Icons.Outlined.MusicNote,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
            },
            trailingContent = {
                Slider(
                    value = volume,
                    onValueChange = { volume = it },
                    modifier = Modifier.fillMaxWidth(0.8f)
                )
            }
        )



        ListItem(
            modifier = Modifier.fillMaxWidth(),
            headlineContent = {
                Text(text = "Sound On", style = MaterialTheme.typography.labelLarge)
            }, trailingContent = {
                Switch(checked = soundOn, onCheckedChange = { soundOn = it })
            })


    }

}

@Composable
fun FeedbackHelp() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(32.dp),
    ) {
        Text(text = "Feedback and Help", style = MaterialTheme.typography.displaySmall)
        ListItem(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { },
            leadingContent = {
                Icon(Icons.Default.Feedback, contentDescription = "Feedback")
            },
            headlineContent = {
                Text(text = "Feedback", style = MaterialTheme.typography.labelLarge)
            }, supportingContent = {
                Text(text = "Send us your feedback")
            })
        ListItem(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { },
            leadingContent = {
                Icon(Icons.Default.Help, contentDescription = "Help")
            },
            headlineContent = {
                Text(text = "Help", style = MaterialTheme.typography.labelLarge)
            }, supportingContent = {
                Text(text = "Get help with the app")
            })
    }
}

@Composable
fun About() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(32.dp),
    ) {
        ListItem(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { },
            leadingContent = {
                Icon(Icons.Default.Info, contentDescription = "Info")
            },
            headlineContent = {
                Text(text = "About", style = MaterialTheme.typography.labelLarge)
            }, supportingContent = {
                Text(text = "About the app")
            })
    }
}
