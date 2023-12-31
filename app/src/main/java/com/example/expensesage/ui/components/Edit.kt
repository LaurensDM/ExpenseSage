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
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.expensesage.R
import com.example.expensesage.data.expenses.Expense
import com.example.expensesage.ui.AppViewModelProvider
import com.example.expensesage.ui.utils.CurrencyVisualTransformation
import com.example.expensesage.ui.utils.DecimalFormatter
import com.example.expensesage.ui.utils.ExpenseDetail
import com.example.expensesage.ui.viewModels.ExpenseDetailsViewModel

/**
 * Edit Dialog
 *
 * @param selectedExpense Expense: Selected expense
 * @param onDialogDismiss () -> Unit: Callback to dismiss dialog
 * @param dataViewModel ExpenseDetailsViewModel: View model for expense details
 */
@Composable
fun Edit(
    selectedExpense: Expense,
    onDialogDismiss: () -> Unit,
    dataViewModel: ExpenseDetailsViewModel = viewModel(factory = AppViewModelProvider.Factory),
) {

    LaunchedEffect(selectedExpense) {
        dataViewModel.initializeState(selectedExpense)
    }


    Card(
        elevation = CardDefaults.cardElevation(5.dp),
        shape = RoundedCornerShape(32.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        border = BorderStroke(2.dp, MaterialTheme.colorScheme.onSurface),
    ) {
        Spacer(modifier = Modifier.size(16.dp))
        Text(
            text = stringResource(id = R.string.edit),
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.displaySmall
        )
        EditForm(
            expenseState = dataViewModel.expenseDetailState,
            onDoneClicked = {
                dataViewModel.updateExpense(selectedExpense)
                onDialogDismiss()
            },
            originalExpense = selectedExpense,
            onValueChange = { dataViewModel.updateState(it) },
            nameError = dataViewModel.nameError,
            amountError = dataViewModel.amountError,
        )
    }
}

/**
 * Edit Form
 *
 * @param expenseState ExpenseDetail State
 * @param onValueChange (ExpenseDetail) -> Unit:  Callback to update state
 * @param onDoneClicked (Expense) -> Unit: Callback to update expense
 * @param originalExpense Expense: Original expense
 * @param nameError Boolean: Name error
 * @param amountError Boolean: Amount error
 * @param decimalFormatter DecimalFormatter: Decimal formatter for currency
 */
@Composable
fun EditForm(
    expenseState: ExpenseDetail,
    onValueChange: (ExpenseDetail) -> Unit = {},
    onDoneClicked: (Expense) -> Unit,
    originalExpense: Expense,
    nameError: Boolean,
    amountError: Boolean,
    decimalFormatter: DecimalFormatter = DecimalFormatter()
) {
    Column(
        modifier = Modifier.padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Spacer(modifier = Modifier.size(8.dp))
        OutlinedTextField(
            value = expenseState.name,
            onValueChange = { onValueChange(expenseState.copy(name = it)) },
            label = { Text(text = stringResource(id = R.string.name)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text, imeAction = ImeAction.Done),
            isError = nameError,
        )
        if (nameError) {
            Text(
                text = stringResource(id = R.string.nameNotValid),
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.error,
            )
        }
        OutlinedTextField(
            value = expenseState.amount,
            onValueChange = { onValueChange(expenseState.copy(amount = decimalFormatter.cleanup( expenseState.amount,it))) },
            label = { Text(text = stringResource(id = R.string.amount)) },
            isError = amountError,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text, imeAction = ImeAction.Done),
            visualTransformation = CurrencyVisualTransformation(decimalFormatter)
        )
        if (amountError) {
            Text(
                text = stringResource(id = R.string.amountNotValid) ,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.error,
            )
        }
        CategoryDropdown(
            category = expenseState.category,
            onSelect = { onValueChange(expenseState.copy(category = it)) })
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.End
        ) {
            Button(onClick = {
                onDoneClicked(originalExpense)
            }, enabled = !(nameError || amountError)) {
                Text(text = stringResource(id = R.string.save), modifier = Modifier.padding(end = 8.dp))
                Icon(Icons.Default.Edit, contentDescription = "Edit expense")
            }
        }


    }
}
