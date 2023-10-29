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
import com.example.expensesage.ui.AppViewModelProvider
import com.example.expensesage.ui.viewModels.ExpenseDetailsViewModel
import com.example.expensesage.ui.viewModels.MainViewModel
import java.text.NumberFormat
import java.util.Locale

@Composable
fun Create(
    viewModel: MainViewModel,
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
                dataViewModel = dataViewModel,
                onDoneClicked = { viewModel.onDialogDismiss() },
                owed = viewModel.isOwed,
            )
        }
    }
}

@Composable
fun CreateForm(
    dataViewModel: ExpenseDetailsViewModel,
    onDoneClicked: () -> Unit = {},
    owed: Boolean,
) {
    var name by rememberSaveable { mutableStateOf("") }
    var nameError by rememberSaveable { mutableStateOf(true) }
    var amount by rememberSaveable { mutableStateOf("") }
    var amountError by rememberSaveable { mutableStateOf(true) }
    val format: NumberFormat = NumberFormat.getInstance(Locale.getDefault())
    var category by rememberSaveable { mutableStateOf("Select a category") }

    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Text(text = "Create")
        TextField(
            value = name,
            onValueChange = {
                name = it
                nameError = name.isEmpty()
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
            value = amount,
            onValueChange = {
                amount = it
                try {
                    amountError = amount.isEmpty() || format.parse(amount)?.toDouble()?.isNaN() ?: true
                } catch (e: NumberFormatException) {
                    amountError = true
                    amount = 0.00.toString()
                }
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
        CategoryDropdown(onSelect = { category = it }, category = category)
        Button(
            onClick = {
                dataViewModel.saveExpense(
                    ExpenseDetailsViewModel.ExpenseDetail(
                        name = name,
                        amount = format.parse(amount)?.toDouble().toString(),
                        owed = owed,
                        category = if (category == "Select a category") "Other" else category,
                    ),
                )
                onDoneClicked()
            },
            enabled = !(nameError || amountError),
        ) {
            Text(text = "Done")
        }
    }
}
