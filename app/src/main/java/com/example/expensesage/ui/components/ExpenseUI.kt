package com.example.expensesage.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.FloatingActionButton
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
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.example.expensesage.R
import com.example.expensesage.data.expenses.Expense
import com.example.expensesage.data.expenses.categories
import com.example.expensesage.ui.AppViewModelProvider
import com.example.expensesage.ui.utils.ModalType
import com.example.expensesage.ui.viewModels.ExpenseDetailsViewModel
import java.time.Month

/**
 * Composable that displays an expense item
 *
 * @param expense  the expense to display
 * @param modifier the modifier to apply to this layout node
 * @param showModal show the modal
 * @param showAlert show the alert
 * @param dataViewModel ExpenseDetailsViewModel: View model for expense details
 */
@Composable
fun ExpenseItem(
    expense: Expense,
    modifier: Modifier = Modifier,
    showModal: (expense: Expense?, isOwed: Boolean, modalType: ModalType) -> Unit,
    showAlert: (onConfirm: () -> Unit, title: Int, subject: String, onCancel: () -> Unit) -> Unit,
    dataViewModel: ExpenseDetailsViewModel = viewModel(factory = AppViewModelProvider.Factory),
) {
    var expanded by remember { mutableStateOf(false) }
    Card(
        modifier = modifier,

        ) {
        Column(
            modifier = modifier
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioNoBouncy,
                        stiffness = Spring.StiffnessVeryLow,
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
                    onEditClicked = { showModal(expense, false, ModalType.EDIT) },
                    onDetailClick = { showModal(expense, false, ModalType.DETAIL) },
                    onPayedClick = {
                        expanded = false
                        showAlert(
                            {
                                dataViewModel.payOwed(expense)
                            },
                            R.string.payAlert,
                            expense.name
                        ) {
                            expanded = true
                        }
                    },
                    onDeleteClick = {
                        expanded = false
                        showAlert(
                            {
                                dataViewModel.deleteExpense(expense)
                            },
                            R.string.deleteAlert,
                            expense.name
                        ) {
                            expanded = true
                        }
                    },
                    expense = expense,
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
 * Composable that displays the expense options on the expense item.
 *
 * @param onEditClicked callback for edit button
 * @param onDetailClick callback for detail button
 * @param onPayedClick callback for payed button
 * @param onDeleteClick callback for delete button
 * @param expense the expense to display
 */
@Composable
fun ExpenseOptions(
    onEditClicked: () -> Unit = {},
    onDetailClick: () -> Unit,
    onPayedClick: () -> Unit = {},
    onDeleteClick: () -> Unit,
    expense: Expense,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = stringResource(id = R.string.options),
            style = MaterialTheme.typography.labelSmall,
        )
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Button(
                onClick = onEditClicked,
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                    contentColor = MaterialTheme.colorScheme.onTertiaryContainer,
                ),
            ) {
//                    Text(text = stringResource(id = R.string.complete_btn))
                Text(text = stringResource(id = R.string.edit))
            }
            Button(
                onClick = onDetailClick,
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.inversePrimary),
            ) {
//                    Text(text = stringResource(id = R.string.complete_btn))
                Text(text = stringResource(id = R.string.details))
            }
            Button(
                onClick = onDeleteClick,
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
                onClick = onPayedClick,
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

/**
 * Composable that displays the expense list
 *
 * @param groupedExpenses the expenses to display
 * @param showModal callback to show the modal
 * @param showAlert callback to show the alert
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ExpenseList(
    groupedExpenses: Map<Pair<Month, Int>, List<Expense>>,
    showModal: (expense: Expense?, isOwed: Boolean, modalType: ModalType) -> Unit,
    showAlert: (onConfirm: () -> Unit, title: Int, subject: String, onCancel: () -> Unit) -> Unit,
) {
    LazyColumn(
        modifier = Modifier.padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        if (groupedExpenses.isEmpty()) {
            item {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        text = stringResource(id = R.string.noData),
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
            groupedExpenses.forEach { entry ->
                stickyHeader {
                    Text(
                        text = " ${entry.key.first} ${entry.key.second}",
                        modifier = Modifier
                            .background(color = MaterialTheme.colorScheme.surface)
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        style = MaterialTheme.typography.labelSmall,
                    )
                }
                items(entry.value, key = { it.id }) { normalExpense ->
                    ExpenseItem(
                        expense = normalExpense,
                        modifier = Modifier.animateItemPlacement(
                            animationSpec = spring(
                                dampingRatio = Spring.DampingRatioLowBouncy,
                                stiffness = Spring.StiffnessMedium,
                            ),
                        ),
                        showModal = showModal,
                        showAlert = showAlert,
                    )
                }
            }
        }
    }
}

/**
 * Composable that displays the expense list
 *
 * @param onSelect callback to select a category
 * @param category the category to display
 */
@Composable
fun CategoryDropdown(onSelect: (String) -> Unit = {}, category: String) {
    var expanded by rememberSaveable { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        contentAlignment = Alignment.Center,
    ) {
        Button(onClick = { expanded = !expanded }) {
            Text(text = category)
            if (expanded) {
                Icon(
                    Icons.Filled.ExpandLess,
                    contentDescription = stringResource(id = R.string.expand)
                )
            } else {
                Icon(
                    Icons.Filled.ExpandMore,
                    contentDescription = stringResource(id = R.string.expanded)
                )
            }
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            offset = DpOffset(
                Dp.Infinity,
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

/**
 * Composable that displays the expense list
 *
 * @param onClick callback to add an expense
 */
@Composable
fun ExpenseFloatingActionButton(
    onClick: () -> Unit,
) {
    var multiFloatingState by remember { mutableStateOf(MultiFloatingState.Collapsed) }
    val transition = updateTransition(targetState = multiFloatingState, label = "transition")
    val rotate by transition.animateFloat(label = "rotate") {
        if (it == MultiFloatingState.Expanded) 360f else 0f
    }
    FloatingActionButton(onClick = {
        multiFloatingState = if (transition.currentState == MultiFloatingState.Expanded) {
            MultiFloatingState.Collapsed
        } else {
            MultiFloatingState.Expanded
        }
        onClick()
    }) {
        Icon(
            Icons.Default.Add,
            contentDescription = stringResource(id = R.string.addExpense),
            modifier = Modifier.rotate(rotate)
        )
    }
}

enum class MultiFloatingState {
    Expanded, Collapsed
}
