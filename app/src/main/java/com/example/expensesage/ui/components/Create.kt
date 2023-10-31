package com.example.expensesage.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.expensesage.data.Expense
import com.example.expensesage.ui.AppViewModelProvider
import com.example.expensesage.ui.utils.ExpenseDetail
import com.example.expensesage.ui.viewModels.ExpenseDetailsViewModel
import com.example.expensesage.ui.viewModels.MainViewModel
import java.text.NumberFormat
import java.util.Locale

@Composable
fun Create(
    onDialogDismiss: () -> Unit,
    isOwed: Boolean,
    dataViewModel: ExpenseDetailsViewModel = viewModel(factory = AppViewModelProvider.Factory),
) {
    Column(
        modifier = Modifier.offset(y = ((-32).dp)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
    ) {
        Card(
            elevation = CardDefaults.cardElevation(5.dp),
            shape = MaterialTheme.shapes.large,
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        ) {
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
}

@Composable
fun CreateForm(
    expenseState: ExpenseDetail,
    updateState: (ExpenseDetail) -> Unit,
    onDoneClicked: () -> Unit = {},
    nameError: Boolean,
    amountError: Boolean,
) {

    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Text(text = "Create")
        TextField(
            value = expenseState.name,
            onValueChange = {
                updateState(expenseState.copy(name = it))
            },
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
        TextField(
            value = expenseState.amount,
            onValueChange = {
                updateState(expenseState.copy(amount = it))
            },
            label = { Text(text = "Amount") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            isError = amountError,
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
        Button(
            onClick = {
                onDoneClicked()
            },
            enabled = !(nameError || amountError),
        ) {
            Text(text = "Done")
        }
    }
}
