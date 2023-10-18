package com.example.expensesage.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.expensesage.data.Expense
import com.example.expensesage.ui.AppViewModelProvider
import com.example.expensesage.ui.MainViewModel
import com.example.expensesage.ui.viewModels.ExpenseDetail
import com.example.expensesage.ui.viewModels.ExpenseDetailsViewModel

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
                expense = viewModel.selectedExpense,
//                onValueChange = { dataViewModel :: updateUIState }
            )
        }
    }
}

@Composable
fun EditForm(
    dataViewModel: ExpenseDetailsViewModel,
    onDoneClicked: () -> Unit = {},
//    onValueChange: (ExpenseDetail) -> Unit = {},
    expense: Expense
) {
    var name by rememberSaveable {
        mutableStateOf(expense.expenseName)
    }
    var amount by rememberSaveable {
        mutableStateOf(expense.expense.toString())
    }

    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Text(text = "Edit")
        TextField(
            value = name,
            onValueChange = { name = it  },
            label = { Text(text = "Name") }
        )
        TextField(
            value = amount,
            onValueChange = {amount = it},
            label = { Text(text = "Amount") })
        Button(onClick = {
            println(expense)
            val editedExpense = ExpenseDetail(
                id = expense.id,
                expenseName = name,
                expense = amount,
                owed = expense.owed,
                date = expense.date.toString()
            )
            dataViewModel.updateExpense(editedExpense, expense)
            onDoneClicked()
        }) {
            Text(text = "Done")
        }
    }
}

