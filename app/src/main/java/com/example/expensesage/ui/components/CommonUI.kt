package com.example.expensesage.ui.components

import android.content.res.Configuration
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.example.expensesage.R
import com.example.expensesage.data.Expense
import com.example.expensesage.ui.MainViewModel
import com.example.expensesage.ui.screens.ExpenseInformation
import com.example.expensesage.ui.screens.ExpenseSageIcon

@Composable
fun ExpenseItemHome(
    expense: Expense,
) {
    Card(colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer) ) {
        Column(
            modifier = Modifier
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioNoBouncy,
                        stiffness = Spring.StiffnessMedium
                    )
                )
        ) {


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(R.dimen.padding_small)),
//                horizontalArrangement = Arrangement.Center
            ) {
                ExpenseSageIcon(expense.imageResourceId)
                ExpenseInformation(expense.expenseName, expense.expense)

            }
        }
    }
}


@Composable
fun ExpenseItem(
    expense: Expense,
    modifier: Modifier = Modifier,
    viewModel: MainViewModel
) {
    var expanded by remember { mutableStateOf(false) }
    Card(
        modifier = modifier

    ) {
        Column(
            modifier = Modifier
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioNoBouncy,
                        stiffness = Spring.StiffnessMedium
                    )
                )
        ) {
            Button(onClick = { expanded = !expanded }, shape = MaterialTheme.shapes.medium) {


                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(dimensionResource(R.dimen.padding_small)),
                ) {
                    ExpenseSageIcon(expense.imageResourceId)
                    ExpenseInformation(expense.expenseName, expense.expense)
                    Spacer(Modifier.weight(1f))
                    ExpenseItemButton(
                        expanded = expanded,
                    )
                }
            }
            if (expanded) {
                ExpenseOptions(
                    expense.owed, modifier = Modifier.padding(
                        start = dimensionResource(R.dimen.padding_medium),
                        top = dimensionResource(R.dimen.padding_small),
                        bottom = dimensionResource(R.dimen.padding_medium),
                        end = dimensionResource(R.dimen.padding_medium)
                    ),
                    onDetailClick = { viewModel.onDetailClick() }
                )
            }
        }
    }
}

@Composable
private fun ExpenseItemButton(
    expanded: Boolean,
) {

    Icon(
        imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
        contentDescription = if (expanded) stringResource(id = R.string.expand) else stringResource(
            id = R.string.expanded
        ),
        tint = MaterialTheme.colorScheme.onPrimary
    )
}

@Composable
fun ExpenseOptions(
    owed: Boolean,
    modifier: Modifier = Modifier,
    onDetailClick: () -> Unit
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = stringResource(id = R.string.options),
            style = MaterialTheme.typography.labelSmall
        )
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = modifier.fillMaxWidth()
        ) {
            Button(onClick = onDetailClick) {
//                    Text(text = stringResource(id = R.string.complete_btn))
                Text(text = "Details")
            }


        }

    }
}

@Composable
fun isTabletLandscape(): Boolean {
    val configuration = LocalConfiguration.current
    return configuration.screenWidthDp >= 600 && configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
}