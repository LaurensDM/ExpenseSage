package com.example.expensesage.ui.screens

import android.util.Log
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.expensesage.R
import com.example.expensesage.data.currencyList
import com.example.expensesage.ui.AppViewModelProvider
import com.example.expensesage.ui.components.CurrencyIcon
import com.example.expensesage.ui.components.CurrencyString
import com.example.expensesage.ui.utils.CurrencyVisualTransformation
import com.example.expensesage.ui.utils.DecimalFormatter
import com.example.expensesage.ui.utils.formatToDouble
import com.example.expensesage.ui.viewModels.SettingsViewModel
import com.example.expensesage.ui.viewModels.SnackBarType
import com.example.expensesage.ui.viewModels.budgetFrequencyList

/**
 * This function is responsible for the setting screen.
 *
 * @param showSnackBar The show snack bar function
 * @param showAlert The show alert function
 */
@Composable
fun SettingScreen(
    showSnackBar: (message: String, snackBarType: SnackBarType) -> Unit,
    showAlert: (onConfirm: () -> Unit, title: Int, subject: String, onCancel: () -> Unit) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .verticalScroll(enabled = true, state = ScrollState(0)),
        verticalArrangement = Arrangement.spacedBy(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        CurrencySettings(showSnackBar = showSnackBar, showAlert = showAlert)
        Divider()
//        SoundPreferences()
//        Divider()
        FeedbackHelp()
        Divider()
        About()
    }
}

/**
 * Composable that displays the currency settings
 *
 * @param showSnackBar The show snack bar function
 * @param showAlert The show alert function
 * @param settingsViewModel The settings view model
 */
@Composable
fun CurrencySettings(
    showSnackBar: (message: String, snackBarType: SnackBarType) -> Unit,
    showAlert: (onConfirm: () -> Unit, title: Int, subject: String, onCancel: () -> Unit) -> Unit,
    settingsViewModel: SettingsViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = stringResource(id = R.string.currencySettings), style = MaterialTheme.typography.displaySmall)
        BudgetFrequencySelect(
            settingsViewModel.budgetFrequencyState,
            settingsViewModel::updateBudgetFrequency,
            showAlert = showAlert,
        )
        Budget(
            showSnackBar = showSnackBar,
            budget = settingsViewModel.budget,
            budgetFrequency = settingsViewModel.budgetFrequencyState,
            updateBudget = { settingsViewModel.updateBudget(it) },
            changeBudget = { settingsViewModel.changeBudget() },
        )
        CurrencySelect(
            onSelect = {
                settingsViewModel.changeCurrency(it)
            },
            currency = settingsViewModel.currency,
            showSnackBar = showSnackBar,
        )
    }
}

/**
 * Composable that displays the budget frequency select
 *
 * @param budgetFrequencyState The budget frequency state
 * @param updateBudgetFrequency The update budget frequency function
 * @param showAlert The show alert function
 */
@Composable
fun BudgetFrequencySelect(
    budgetFrequencyState: String,
    updateBudgetFrequency: (String) -> Unit,
    showAlert: (onConfirm: () -> Unit, title: Int, subject: String, onCancel: () -> Unit) -> Unit,
) {


    Column {
        Text(
            text = stringResource(id = R.string.budgetFrequency),
            style = MaterialTheme.typography.labelLarge,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        budgetFrequencyList.forEach {
            val frequency = when (it) {
                "Weekly" -> stringResource(id = R.string.weekly)
                "Monthly" -> stringResource(id = R.string.monthly)
                "Yearly" -> stringResource(id = R.string.yearly)
                else -> ""
            }
            Row(
                Modifier.selectable(
                    selected = (it == budgetFrequencyState),
                    onClick = {
                        showAlert(
                            { updateBudgetFrequency(it) },
                            R.string.budgetFrequencyAlert,
                            "",
                            {}
                        )
                    },
                    role = Role.RadioButton
                )
            ) {
                ListItem(
                    headlineContent = {
                        Text(
                            text = frequency,
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
 * Composable that displays the budget text field
 *
 * @param moneyAvailable The money available
 * @param updateMoneyAvailable The update money available function
 * @param onDone The on done function
 * @param decimalFormatter The decimal formatter
 */
@Composable
fun BudgetTextField(
    moneyAvailable: String,
    updateMoneyAvailable: (String) -> Unit,
    onDone: () -> Unit,
    decimalFormatter: DecimalFormatter = DecimalFormatter()
) {
    OutlinedTextField(
        modifier = Modifier
            .width(128.dp),
        label = { Text(text = stringResource(id = R.string.budget)) },
        placeholder = { Text(text = stringResource(id = R.string.enterBudget)) },
        value = moneyAvailable,
        onValueChange = {
            updateMoneyAvailable(decimalFormatter.cleanup(moneyAvailable, it))
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text, imeAction = ImeAction.Done),
        visualTransformation = CurrencyVisualTransformation(decimalFormatter),
        keyboardActions = KeyboardActions(
            onDone = {
                this.defaultKeyboardAction(ImeAction.Done)
                onDone()
            },
        ),
    )
}

/**
 *  Composable that displays the budget
 *
 * @param showSnackBar The show snack bar function
 * @param budget    The budget
 * @param budgetFrequency The budget frequency
 * @param updateBudget The update budget function
 * @param changeBudget The change budget function
 * @param currency  The currency
 */
@Composable
fun Budget(
    showSnackBar: (message: String, snackBarType: SnackBarType) -> Unit,
    budget: String,
    budgetFrequency: String,
    updateBudget: (String) -> Unit,
    changeBudget: () -> Unit,
) {

    var edit by rememberSaveable { mutableStateOf(false) }

    val message = "${stringResource(id = R.string.budgetChange)} $budget"
    val frequency = when (budgetFrequency) {
        "Weekly" -> stringResource(id = R.string.weekly)
        "Monthly" -> stringResource(id = R.string.monthly)
        "Yearly" -> stringResource(id = R.string.yearly)
        else -> ""
    }
    ListItem(
        modifier = Modifier.clickable {
            edit = !edit
        },
        leadingContent = {
            Icon(Icons.Default.Edit, contentDescription = "Edit")
        },
        headlineContent = {
            Text(
                text = "$frequency ${stringResource(id = R.string.budget)}",
                style = MaterialTheme.typography.labelLarge
            )
        },
        supportingContent = {
            if (edit) {
                BudgetTextField(
                    moneyAvailable = budget,
                    updateMoneyAvailable = updateBudget,
                    onDone = {
                        edit = false
                        changeBudget()
                        showSnackBar(
                            message,
                            SnackBarType.SUCCESS
                        )
                    },
                )
            } else {
                Text(text = CurrencyString(money = budget.formatToDouble(), fractionDigits = 2))
            }

        },
    )
}

/**
 * Composable that displays the currency select
 *
 * @param onSelect The on select function
 * @param currency The currency
 * @param showSnackBar  The show snack bar function
 */
@Composable
fun CurrencySelect(
    onSelect: (String) -> Unit,
    currency: String,
    showSnackBar: (message: String, snackBarType: SnackBarType) -> Unit
) {
    var expanded by rememberSaveable { mutableStateOf(false) }
    var message = stringResource(id = R.string.changedCurrency)
    
    Box(modifier = Modifier.fillMaxWidth()) {
        ListItem(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = { expanded = !expanded }),
            leadingContent = {
                CurrencyIcon()
            },
            headlineContent = {
                Text(text = stringResource(id = R.string.currencyChange), style = MaterialTheme.typography.labelLarge)
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
                    showSnackBar("$message $it", SnackBarType.SUCCESS)
                })
            }
        }
    }
}



/**
 * Composable that displays the sound preferences
 *
 *
 */
//@Composable
//fun SoundPreferences() {
////    val mContext = LocalContext.current
////    val mMediaPlayer = MediaPlayer.create(mContext, R.raw.rockmusic)
////    mMediaPlayer.setVolume(50f, 50f)
//    var soundOn by remember { mutableStateOf(true) }
//    var volume by remember { mutableStateOf(0.5f) }
//    Column(
//        modifier = Modifier.fillMaxWidth(),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.spacedBy(32.dp),
//    )
//    {
//        Text(text = "Sound Settings", style = MaterialTheme.typography.displaySmall)
//        ListItem(
//            modifier = Modifier.fillMaxWidth(),
//            headlineContent = {
//                Icon(
//                    Icons.Outlined.MusicNote,
//                    contentDescription = null,
//                    tint = MaterialTheme.colorScheme.onSurfaceVariant
//                )
//            },
//            trailingContent = {
//                Slider(
//                    value = volume,
//                    onValueChange = { volume = it },
//                    modifier = Modifier.fillMaxWidth(0.8f)
//                )
//            }
//        )
//
//
//
//        ListItem(
//            modifier = Modifier.fillMaxWidth(),
//            headlineContent = {
//                Text(text = "Sound On", style = MaterialTheme.typography.labelLarge)
//            }, trailingContent = {
//                Switch(checked = soundOn, onCheckedChange = { soundOn = it })
//            })
//
//
//    }
//
//}

/**
 * Composable that displays the feedback and help
 *
 */
@Composable
fun FeedbackHelp() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(32.dp),
    ) {
        Text(text = stringResource(id = R.string.feedbackHelp), style = MaterialTheme.typography.displaySmall)
        ListItem(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { },
            leadingContent = {
                Icon(Icons.Default.Feedback, contentDescription = stringResource(id = R.string.feedback))
            },
            headlineContent = {
                Text(text = stringResource(id = R.string.feedback), style = MaterialTheme.typography.labelLarge)
            }, supportingContent = {
                Text(text = stringResource(id = R.string.sendFeedback))
            })
        ListItem(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { },
            leadingContent = {
                Icon(Icons.Default.Help, contentDescription = stringResource(id = R.string.help))
            },
            headlineContent = {
                Text(text = stringResource(id = R.string.help), style = MaterialTheme.typography.labelLarge)
            }, supportingContent = {
                Text(text = stringResource(id = R.string.helpDescription))
            })
    }
}

/**
 * Composable that displays the about section
 *
 */
@Composable
fun About() {
    var aboutClicked by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioNoBouncy,
                    stiffness = Spring.StiffnessVeryLow
                )
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        ListItem(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { aboutClicked = !aboutClicked },
            leadingContent = {
                Icon(Icons.Default.Info, contentDescription = "Info")
            },
            headlineContent = {
                Text(text = stringResource(id = R.string.about), style = MaterialTheme.typography.labelLarge)
            }, supportingContent = {
                Text(text = stringResource(id = R.string.aboutDescription))
            },
            trailingContent = {
                Icon(
                    if (aboutClicked)
                        Icons.Default.ExpandLess
                    else
                        Icons.Default.ExpandMore,

                    contentDescription = null,
                )
            }
        )
        if (aboutClicked) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                text = stringResource(id = R.string.aboutText),
                style = MaterialTheme.typography.bodyLarge,
            )
        }
    }
}
