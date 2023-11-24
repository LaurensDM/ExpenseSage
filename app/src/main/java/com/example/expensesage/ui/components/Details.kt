package com.example.expensesage.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.expensesage.R
import com.example.expensesage.data.expenses.Expense
import com.example.expensesage.ui.theme.ExpenseSageTheme

/**
 * Details Dialog
 *
 * @param selectedExpense Expense: Selected expense
 * @param onDialogDismiss () -> Unit: Callback to dismiss dialog
 */
@Composable
fun Details(selectedExpense: Expense, onDialogDismiss: () -> Unit) {
    OutlinedCard(
        elevation = CardDefaults.cardElevation(8.dp),
        shape = RoundedCornerShape(32.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        border = BorderStroke(2.dp, MaterialTheme.colorScheme.onSurface),
    ) {
        Text(
            text = stringResource(id = R.string.details),
            style = MaterialTheme.typography.headlineMedium,
            textAlign = TextAlign.Center,
            lineHeight = 24.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
        )
        Column(
            modifier = Modifier.padding(start = 8.dp, end = 8.dp, bottom = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(
                16.dp,
                alignment = Alignment.CenterVertically
            ),
        ) {

            Column(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp, vertical = 8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(
                    space = 8.dp,
                    alignment = Alignment.Top,
                ),
            ) {
                ListItem(headlineContent = {
                    Text(
                        text = "${stringResource(id = R.string.name)}: ",
                        style = MaterialTheme.typography.labelLarge,
                        textAlign = TextAlign.Center,
                    )
                }, trailingContent = {
                    Text(
                        text = selectedExpense.name,
                        style = MaterialTheme.typography.labelLarge,
                        textAlign = TextAlign.Center,
                    )
                })
                ListItem(headlineContent = {
                    Text(
                        text = "${stringResource(id = R.string.amount)}:",
                                style = MaterialTheme . typography . labelLarge,
                        textAlign = TextAlign.Center,
                    )
                }, trailingContent = {
                    Text(
                        text =
                        CurrencyString(
                            money = selectedExpense.amount,
                            fractionDigits = 2,
                        ),
                        style = MaterialTheme.typography.labelLarge,
                        textAlign = TextAlign.Center,
                    )
                })
                ListItem(headlineContent = {
                    Text(
                        text = "${stringResource(id = R.string.createdDate)}: ",
                        style = MaterialTheme.typography.labelLarge,
                        textAlign = TextAlign.Center,
                    )
                }, trailingContent = {
                    Text(
                        text = "${selectedExpense.date.toLocalDate()}",
                        style = MaterialTheme.typography.labelLarge,
                        textAlign = TextAlign.Center,
                    )
                })
                ListItem(headlineContent = {
                    Text(
                        text = "${stringResource(id = R.string.category)}: ",
                        style = MaterialTheme.typography.labelLarge,
                        textAlign = TextAlign.Center,
                    )
                }, trailingContent = {
                    Text(
                        text = "${selectedExpense.category}",
                        style = MaterialTheme.typography.labelLarge,
                        textAlign = TextAlign.Center,
                    )
                })

            }
            Row(
                modifier = Modifier.padding(top = 16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.Bottom,
            ) {
                Button(
                    onClick = { onDialogDismiss() },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    ),
                ) {
                    Text(text = stringResource(id = R.string.close))
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun DetailsPreview() {
    ExpenseSageTheme(darkTheme = false) {
        Details(
            selectedExpense = Expense(
                name = "Test",
                imageResourceId = R.drawable.cost,
                owed = true
            ), onDialogDismiss = {})
    }

}

@Preview(showSystemUi = true)
@Composable
fun DetailsDarkPreview() {
    ExpenseSageTheme(darkTheme = true) {
        Details(
            selectedExpense = Expense(
                name = "Test",
                imageResourceId = R.drawable.cost,
                owed = true
            ), onDialogDismiss = {})
    }

}
