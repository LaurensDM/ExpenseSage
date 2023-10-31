package com.example.expensesage.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.example.expensesage.R
import com.example.expensesage.data.Expense
import com.example.expensesage.data.categories
import com.example.expensesage.ui.AppViewModelProvider
import com.example.expensesage.ui.utils.ModalType
import com.example.expensesage.ui.viewModels.ExpenseDetailsViewModel
import com.example.expensesage.ui.viewModels.MainViewModel
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
                        stiffness = Spring.StiffnessMedium,
                    ),
                ),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(R.dimen.padding_small)),
//                horizontalArrangement = Arrangement.Center
            ) {
                ExpenseSageIcon(expense.imageResourceId)
                ExpenseInformation(expense.name, expense.amount)
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
    viewModel: MainViewModel,
    dataViewModel: ExpenseDetailsViewModel,
) {
    var expanded by remember { mutableStateOf(false) }
    Card(
        modifier = modifier,

    ) {
        Column(
            modifier = Modifier
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioNoBouncy,
                        stiffness = Spring.StiffnessMedium,
                    ),
                ),
        ) {
            Button(
                onClick = { expanded = !expanded },
                shape = MaterialTheme.shapes.medium,
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer,
                    contentColor = MaterialTheme.colorScheme.onSecondaryContainer,
                ),
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(dimensionResource(R.dimen.padding_small)),
                ) {
                    ExpenseSageIcon(expense.imageResourceId)
                    ExpenseInformation(expense.name, expense.amount)
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
                        end = dimensionResource(R.dimen.padding_medium),
                    ),
                    onEditClicked = { viewModel.showModal(expense, modalType = ModalType.EDIT) },
                    onDetailClick = { viewModel.showModal(expense, modalType = ModalType.DETAIL) },
                    onPayedClick = { dataViewModel.payOwed(expense) },
                    expense = expense,
                    dataViewModel = dataViewModel,
                    viewModel = viewModel,
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
        contentDescription = if (expanded) {
            stringResource(
                id = R.string.expanded,
            )
        } else {
            stringResource(id = R.string.expand)
        },
        tint = MaterialTheme.colorScheme.onSecondaryContainer,
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
    onEditClicked: () -> Unit = {},
    onDetailClick: () -> Unit,
    onPayedClick: () -> Unit = {},
    expense: Expense,
    dataViewModel: ExpenseDetailsViewModel,
    viewModel: MainViewModel,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = stringResource(id = R.string.options),
            style = MaterialTheme.typography.labelSmall,
        )
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = modifier.fillMaxWidth(),
        ) {
            Button(
                onClick = onEditClicked,
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                    contentColor = MaterialTheme.colorScheme.onTertiaryContainer,
                ),
            ) {
//                    Text(text = stringResource(id = R.string.complete_btn))
                Text(text = "Edit")
            }
            Button(
                onClick = onDetailClick,
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.inversePrimary),
            ) {
//                    Text(text = stringResource(id = R.string.complete_btn))
                Text(text = "Details")
            }
            Button(
                onClick = {
                    viewModel.showAlert(
                        { dataViewModel.deleteExpense(expense) },
                        "Are you sure?",
                    )
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.error,
                    contentColor = MaterialTheme.colorScheme.onError,
                ),
            ) {
//                    Text(text = stringResource(id = R.string.complete_btn))
                Text(text = stringResource(id = R.string.delete_btn))
            }
        }
        if (expense.owed) {
            Button(
                onClick = {
                    viewModel.showAlert(
                        { onPayedClick() },
                        "Are you sure?",
                    )
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                ),
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
    expenseName: String,
    cost: Double,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Text(
            text = expenseName,
            style = MaterialTheme.typography.displaySmall,
            modifier = Modifier.padding(top = dimensionResource(R.dimen.padding_small)),
        )
        Text(
            text = CurrencyString(money = cost, 2),
            style = MaterialTheme.typography.labelLarge,
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
    @DrawableRes expenseIcon: Int,
    modifier: Modifier = Modifier,
) {
    Image(
        modifier = modifier
            .size(dimensionResource(R.dimen.image_size))
            .padding(dimensionResource(R.dimen.padding_small)),
//            .clip(MaterialTheme.shapes.medium),
        contentScale = ContentScale.Crop,
        painter = painterResource(expenseIcon),

        // Content Description is not needed here - image is decorative, and setting a null content
        // description allows accessibility services to skip this element during navigation.

        contentDescription = null,
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ExpenseList(
    it: PaddingValues,
    groupedExpenses: Map<Pair<Month, Int>, List<Expense>>,
    viewModel: MainViewModel,
    dataViewModel: ExpenseDetailsViewModel = viewModel(factory = AppViewModelProvider.Factory),
) {
    LazyColumn(contentPadding = it) {
        item {
            Spacer(modifier = Modifier.size(16.dp))
        }
        if (groupedExpenses.isEmpty()) {
            item {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        text = "Nothing to see here",
                        style = MaterialTheme.typography.headlineSmall,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(dimensionResource(R.dimen.padding_medium)),
                        textAlign = TextAlign.Center,
                    )
                    Spacer(modifier = Modifier.height(64.dp))
                    Image(
                        painter = rememberAsyncImagePainter(R.drawable.logo),
                        contentDescription = null,
                        modifier = Modifier.size(200.dp),
                    )
                }
            }
        } else {
            groupedExpenses.forEach {
                stickyHeader {
                    Text(
                        text = " ${it.key.first} ${it.key.second}",
                        modifier = Modifier
                            .background(color = MaterialTheme.colorScheme.surface)
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        style = MaterialTheme.typography.labelSmall,
                    )
                }
                items(it.value) { normalExpense ->
                    ExpenseItem(
                        expense = normalExpense,
                        modifier = Modifier.padding(dimensionResource(R.dimen.padding_small)),
                        viewModel = viewModel,
                        dataViewModel = dataViewModel,
                    )
                }
            }
        }
    }
}

@Composable
fun CategoryDropdown(onSelect: (String) -> Unit = {}, category: String) {
    var expanded by rememberSaveable { mutableStateOf(false) }
    Box {
        Button(onClick = { expanded = !expanded }) {
            Text(text = category)
            if (expanded) {
                Icon(Icons.Filled.ExpandLess, contentDescription = "Expand")
            } else {
                Icon(Icons.Filled.ExpandMore, contentDescription = "Expand")
            }
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            offset = DpOffset(
                8.dp,
                0.dp,
            ),
        ) {
            categories.forEach {
                DropdownMenuItem(
                    text = { Text(text = it) },
                    onClick = {
                        onSelect(it)
                        expanded = false
                    },
                )
            }
        }
    }
}
