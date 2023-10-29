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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.expensesage.data.Expense
import com.example.expensesage.ui.AppViewModelProvider
import com.example.expensesage.ui.viewModels.ExpenseDetailsViewModel
import com.example.expensesage.ui.viewModels.MainViewModel

@Composable
fun Edit(
    viewModel: MainViewModel,
    dataViewModel: ExpenseDetailsViewModel = viewModel(factory = AppViewModelProvider.Factory),
) {
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
    expense: Expense,
) {
    var name by rememberSaveable {
        mutableStateOf(expense.name)
    }
    var amount by rememberSaveable {
        mutableStateOf(expense.amount.toString())
    }
    var category by rememberSaveable {
        mutableStateOf(expense.category)
    }

    Column(modifier = Modifier.padding(horizontal = 32.dp, vertical = 16.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Text(text = "Edit", modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center, style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.size(8.dp))
        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text(text = "Name") },
        )
        TextField(
            value = amount,
            onValueChange = { amount = it },
            label = { Text(text = "Amount") },
        )
        CategoryDropdown(category = category, onSelect = { category = it })
        Button(onClick = {
            println(expense)
            val editedExpense = ExpenseDetailsViewModel.ExpenseDetail(
                id = expense.id,
                name = name,
                amount = amount,
                owed = expense.owed,
                date = expense.date.toString(),
                category = category,
            )
            dataViewModel.updateExpense(editedExpense, expense)
            onDoneClicked()
        }) {
            Text(text = "Done")
        }
    }
}
