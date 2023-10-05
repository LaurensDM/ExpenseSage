package com.example.expensesage.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.expensesage.R
import com.example.expensesage.data.Expense
import com.example.expensesage.ui.MainViewModel

/**
 * Composable that displays the expense items on the home screen
 *
 * @param expense the expense to display
 */
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
                        modifier = Modifier.padding(
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
        tint = MaterialTheme.colorScheme.onPrimary
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
            text = stringResource(id = R.string.expense) + " $cost",
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