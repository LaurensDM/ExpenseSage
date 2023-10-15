package com.example.expensesage.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.expensesage.data.Expense
import com.example.expensesage.ui.AppViewModelProvider
import com.example.expensesage.ui.MainViewModel
import com.example.expensesage.ui.viewModels.ExpenseDetail
import com.example.expensesage.ui.viewModels.ExpenseDetailsViewModel
import com.example.expensesage.ui.viewModels.toExpenseDetails

@Composable
fun Edit(
    viewModel: MainViewModel,
    dataViewModel: ExpenseDetailsViewModel = viewModel(factory = AppViewModelProvider.Factory),

    ) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Card(
            elevation = CardDefaults.cardElevation(5.dp),
            shape = MaterialTheme.shapes.large,
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        ) {
            EditForm(
                dataViewModel = dataViewModel,
                onDoneClicked = { viewModel.onDialogDismiss() },
                expense = dataViewModel.expenseUIState.expenseDetails,
                onValueChange = { dataViewModel :: updateUIState }
            )
        }
    }
}

@Composable
fun EditForm(
    dataViewModel: ExpenseDetailsViewModel,
    onDoneClicked: () -> Unit = {},
    onValueChange: (ExpenseDetail) -> Unit = {},
    expense: ExpenseDetail
) {


    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Text(text = "Edit")
        TextField(
            value = expense.expenseName,
            onValueChange = { onValueChange(expense.copy(expenseName = it))  },
            label = { Text(text = "Name") })
        TextField(
            value = expense.expense,
            onValueChange = {onValueChange(expense.copy(expense = it)) },
            label = { Text(text = "Amount") })
        Button(onClick = {
            println(expense)
            dataViewModel.updateExpense(expense)
            onDoneClicked()
        }) {
            Text(text = "Done")
        }
    }
}

