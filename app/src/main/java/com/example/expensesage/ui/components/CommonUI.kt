package com.example.expensesage.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.expensesage.R
import com.example.expensesage.data.Expense
import com.example.expensesage.ui.MainViewModel
import java.time.Month

/**
 * Composable that displays the expense items on the home screen
 *
 * @param expense the expense to display
 */
@Composable
fun ExpenseItemHome(
    expense: Expense,
) {
    Card(colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)) {
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

/**
 * Composable that displays the expense items on the expense screen.
 *
 * @param expense the expense to display
 * @param modifier the modifier to apply to this layout node
 * @param viewModel the view model to use
 */
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
            Button(
                onClick = { expanded = !expanded },
                shape = MaterialTheme.shapes.medium,
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer,
                    contentColor = MaterialTheme.colorScheme.onSecondaryContainer
                )
            ) {


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
                    modifier = Modifier.padding(
                        start = dimensionResource(R.dimen.padding_medium),
                        top = dimensionResource(R.dimen.padding_small),
                        bottom = dimensionResource(R.dimen.padding_medium),
                        end = dimensionResource(R.dimen.padding_medium)
                    ),
                    onDetailClick = { viewModel.onDetailClick(expense) },
                    expense = expense
                )
            }
        }
    }
}

/**
 * Composable that displays expand button on the expense item.
 *
 * @param expanded
 */
@Composable
private fun ExpenseItemButton(
    expanded: Boolean,
) {

    Icon(
        imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
        contentDescription = if (expanded) stringResource(id = R.string.expand) else stringResource(
            id = R.string.expanded
        ),
        tint = MaterialTheme.colorScheme.onSecondaryContainer
    )
}

/**
 *  Composable that displays the expense options on the expense item.
 *
 * @param modifier the modifier to apply to this layout node
 * @param onDetailClick the function to call when the details button is clicked
 */
@Composable
fun ExpenseOptions(
    modifier: Modifier = Modifier,
    onDetailClick: () -> Unit,
    expense: Expense
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.options),
            style = MaterialTheme.typography.labelSmall
        )
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = modifier.fillMaxWidth()
        ) {
            Button(
                onClick = onDetailClick,
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                    contentColor = MaterialTheme.colorScheme.onTertiaryContainer
                )
            ) {
//                    Text(text = stringResource(id = R.string.complete_btn))
                Text(text = "Edit")
            }
            Button(
                onClick = onDetailClick,
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.inversePrimary)
            ) {
//                    Text(text = stringResource(id = R.string.complete_btn))
                Text(text = "Details")
            }
            Button(
                onClick = onDetailClick,
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.error,
                    contentColor = MaterialTheme.colorScheme.onError
                )
            ) {
//                    Text(text = stringResource(id = R.string.complete_btn))
                Text(text = stringResource(id = R.string.delete_btn))
            }


        }
        if (expense.owed) {
            Button(
                onClick = {  }, colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    contentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            ) {
                Text(text = stringResource(id = R.string.paid_btn))
            }
        }

    }
}

/**
 * Composable that displays the expense information on the expense item.
 *
 * @param expenseName the name of the expense
 * @param cost  the cost of the expense
 * @param modifier the modifier to apply to this layout node
 */
@Composable
fun ExpenseInformation(
    expenseName: String, cost: Double, modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = expenseName,
            style = MaterialTheme.typography.displayMedium,
            modifier = Modifier.padding(top = dimensionResource(R.dimen.padding_small))
        )
        Text(
            text = "$ $cost",
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

/**
 * Composable that displays the ExpenseSage icon
 *
 * @param expenseIcon The icon of the expense
 * @param modifier Modifier to apply to this layout node.
 */
@Composable
fun ExpenseSageIcon(
    @DrawableRes expenseIcon: Int, modifier: Modifier = Modifier
) {
    Image(
        modifier = modifier
            .size(dimensionResource(R.dimen.image_size))
            .padding(dimensionResource(R.dimen.padding_small)),
//            .clip(MaterialTheme.shapes.medium),
        contentScale = ContentScale.Crop, painter = painterResource(expenseIcon),

        // Content Description is not needed here - image is decorative, and setting a null content
        // description allows accessibility services to skip this element during navigation.

        contentDescription = null
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ExpenseList(
    it: PaddingValues,
    groupedExpenses: Map<Pair<Month, Int>, List<Expense>>,
    viewModel: MainViewModel
) {
    LazyColumn(contentPadding = it) {
        item {
            Spacer(modifier = Modifier.size(16.dp))
        }
        groupedExpenses.forEach {
            stickyHeader {
                Text(
                    text = " ${it.key.first} ${it.key.second}",
                    modifier = Modifier
                        .background(color = MaterialTheme.colorScheme.surface)
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                )
            }
            items(it.value) { normalExpense ->
                ExpenseItem(
                    expense = normalExpense,
                    modifier = Modifier.padding(dimensionResource(R.dimen.padding_small)),
                    viewModel = viewModel
                )
            }
        }
    }
}