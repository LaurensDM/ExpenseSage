package com.example.expensesage.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AddCircle
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.expensesage.ui.AppViewModelProvider
import com.example.expensesage.ui.utils.CurrencyVisualTransformation
import com.example.expensesage.ui.utils.DecimalFormatter
import com.example.expensesage.ui.utils.ExpenseDetail
import com.example.expensesage.ui.viewModels.ExpenseDetailsViewModel

/**
 * Create Expense Dialog
 *
 * @param onDialogDismiss onDialogDismiss function
 * @param isOwed isOwed boolean
 * @param dataViewModel ExpenseDetailsViewModel
 */
@Composable
fun Create(
    onDialogDismiss: () -> Unit,
    isOwed: Boolean,
    dataViewModel: ExpenseDetailsViewModel = viewModel(factory = AppViewModelProvider.Factory),
) {
    LaunchedEffect(isOwed) {
        dataViewModel.resetState()
    }


        OutlinedCard(
            elevation = CardDefaults.cardElevation(5.dp),
            shape = RoundedCornerShape(32.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            border = BorderStroke(2.dp, MaterialTheme.colorScheme.onSurface),
        ) {
            Spacer(modifier = Modifier.size(16.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterHorizontally),
                verticalAlignment = Alignment.CenterVertically,
            ) {
//                Icon(Icons.Outlined., contentDescription = null)
                Text(
                    text = "Add Expense",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.displaySmall
                )
            }

            CreateForm(
                expenseState = dataViewModel.expenseDetailState,
                updateState = dataViewModel::updateState,
                onDoneClicked = {
                    dataViewModel.updateState(dataViewModel.expenseDetailState.copy(owed = isOwed))
                    onDialogDismiss()
                    dataViewModel.saveExpense()
                },
                nameError = dataViewModel.nameError,
                amountError = dataViewModel.amountError,
            )
        }
}

/**
 *  Create Expense Form
 *
 * @param expenseState ExpenseDetail state
 * @param updateState update ExpenseDetail state
 * @param onDoneClicked onDoneClicked function
 * @param nameError nameError state boolean
 * @param amountError amountError state boolean
 * @param decimalFormatter DecimalFormatter object to format amount input
 */
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun CreateForm(
    expenseState: ExpenseDetail,
    updateState: (ExpenseDetail) -> Unit,
    onDoneClicked: () -> Unit = {},
    nameError: Boolean,
    amountError: Boolean,
    decimalFormatter: DecimalFormatter = DecimalFormatter()
) {

    val keyboardController = LocalSoftwareKeyboardController.current

    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
        OutlinedTextField(
            value = expenseState.name,
            onValueChange = {
                updateState(expenseState.copy(name = it))
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text, imeAction = ImeAction.Done),
            label = { Text(text = "Name") },
            isError = nameError,
        )
        if (nameError) {
            Text(
                text = "name is not valid",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.error,
            )
        }
        OutlinedTextField(
            value = expenseState.amount,
            onValueChange = {
                updateState(expenseState.copy(amount = decimalFormatter.cleanup(expenseState.amount,it)))
            },
            label = { Text(text = "Amount") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text, imeAction = ImeAction.Done),
            isError = amountError,
            visualTransformation = CurrencyVisualTransformation(decimalFormatter),
        )
        if (amountError) {
            Text(
                text = "amount is not valid",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.error,
            )
        }
        CategoryDropdown(
            onSelect = { updateState(expenseState.copy(category = it)) },
            category = expenseState.category
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Button(
                onClick = {
                    onDoneClicked()
                },
                enabled = !(nameError || amountError),
            ) {
                Text(text = "Add", style = MaterialTheme.typography.labelMedium, modifier = Modifier.padding(end = 8.dp))
                    Icon(Icons.Rounded.AddCircle, contentDescription = "Add", tint = MaterialTheme.colorScheme.secondary,)

            }
        }

    }
}
