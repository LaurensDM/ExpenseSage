package com.example.expensesage.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.expensesage.data.Expense
import com.example.expensesage.ui.AppViewModelProvider
import com.example.expensesage.ui.utils.ExpenseDetail
import com.example.expensesage.ui.viewModels.ExpenseDetailsViewModel

@Composable
fun Edit(
    selectedExpense: Expense,
    onDialogDismiss: () -> Unit,
    dataViewModel: ExpenseDetailsViewModel = viewModel(factory = AppViewModelProvider.Factory),
) {

    LaunchedEffect(selectedExpense) {
        dataViewModel.initializeState(selectedExpense)
    }

    Column(
        modifier = Modifier
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Card(
            elevation = CardDefaults.cardElevation(5.dp),
            shape = MaterialTheme.shapes.extraSmall,
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        ) {
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
}

@Composable
fun EditForm(
    expenseState: ExpenseDetail,
    onValueChange: (ExpenseDetail) -> Unit = {},
    onDoneClicked: (Expense) -> Unit,
    originalExpense: Expense,
    nameError: Boolean,
    amountError: Boolean,
) {
    Column(
        modifier = Modifier.padding(horizontal = 32.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Edit",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineSmall
        )
        Spacer(modifier = Modifier.size(8.dp))
        TextField(
            value = expenseState.name,
            onValueChange = { onValueChange(expenseState.copy(name = it)) },
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
            onValueChange = { onValueChange(expenseState.copy(amount = it)) },
            label = { Text(text = "Amount") },
            isError = amountError,
        )
        if (amountError) {
            Text(
                text = "amount is not valid, must be a number",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.error,
            )
        }
        CategoryDropdown(
            category = expenseState.category,
            onSelect = { onValueChange(expenseState.copy(category = it)) })
        Button(onClick = {
            onDoneClicked(originalExpense)
        }, enabled = !(nameError || amountError)) {
            Text(text = "Done")
        }
    }
}
