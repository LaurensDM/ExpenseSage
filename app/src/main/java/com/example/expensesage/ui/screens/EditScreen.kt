package com.example.expensesage.ui.screens

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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.expensesage.ui.AppViewModelProvider
import com.example.expensesage.ui.viewModels.ExpenseDetail
import com.example.expensesage.ui.viewModels.ExpenseDetailsViewModel

@Composable
fun EditScreen(
    id: String,
    dataViewModel: ExpenseDetailsViewModel = viewModel(factory = AppViewModelProvider.Factory),
    onDoneClicked: () -> Unit
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
            if (id.toInt() <= 0) {
                CreateForm(
                    dataViewModel = dataViewModel,
                    onDoneClicked = onDoneClicked,
                    owed = id.toInt() != 0
                    )
            } else {

                EditForm(dataViewModel = dataViewModel, onDoneClicked = onDoneClicked, expenseId = id.toInt())
            }

        }
    }
}

@Composable
fun EditForm(dataViewModel: ExpenseDetailsViewModel, onDoneClicked: () -> Unit = {}, expenseId: Int) {
    val expenseState = dataViewModel.getExpense(expenseId).collectAsState()
    val expense = expenseState.value.expenseDetails

    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Text(text = "Edit")
        TextField(value = "${expense.expenseName}", onValueChange = {expense.copy(expenseName = it)}, label = { Text(text = "Name") })
        TextField(value = "${expense.expense}", onValueChange = {}, label = { Text(text = "Amount") })
        Button(onClick = {
            dataViewModel.updateExpense(expense)
            onDoneClicked() }) {
            Text(text = "Done")
        }
    }
}

@Composable
fun CreateForm(dataViewModel: ExpenseDetailsViewModel, onDoneClicked: () -> Unit = {}, owed: Boolean) {
    var name by rememberSaveable { mutableStateOf("") }
    var amount by rememberSaveable { mutableStateOf("") }
    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Text(text = "Create")
        TextField(value = "$name", onValueChange = { name = it}, label = { Text(text = "Name") })
        TextField(value = "$amount", onValueChange = { amount = it}, label = { Text(text = "Amount") }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword))
        Button(onClick = {
            dataViewModel.saveExpense(ExpenseDetail(expenseName = name, expense = amount, owed = owed))
            onDoneClicked() }) {
            Text(text = "Done")
        }
    }
}