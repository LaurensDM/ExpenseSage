package com.example.expensesage.ui.screens

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.expensesage.R
import com.example.expensesage.data.expenses
import com.example.expensesage.ui.MainViewModel
import com.example.expensesage.ui.components.ExpenseItemHome
import com.example.expensesage.ui.theme.ExpenseSageTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StartScreen(
    modifier: Modifier = Modifier, viewModel: MainViewModel
) {
    Scaffold(topBar = {
        ExpenseSageTopAppBar()
    }) { innerPadding ->
        Column() {

            Spacer(modifier = Modifier.size(32.dp))
            LazyColumn(
                contentPadding = innerPadding,
                modifier = modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item {
                    TopTile()
                }
                item {
                    Spacer(modifier = Modifier.size(32.dp))
                }
                item {
                    Text(
                        text = "Latest expenses",
                        style = MaterialTheme.typography.headlineMedium,
                        modifier = Modifier.padding(dimensionResource(R.dimen.padding_small)),
                        textAlign = TextAlign.Center,
                    )
                }
                items(expenses) {
                    ExpenseItemHome(
                        expense = it,
                    )
                }

            }
        }

    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpenseSageTopAppBar(modifier: Modifier = Modifier) {

    CenterAlignedTopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier
                        .size(dimensionResource(R.dimen.image_size))
                        .padding(dimensionResource(R.dimen.padding_small)),
                    painter = painterResource(R.drawable.logo),

                    // Content Description is not needed here - image is decorative, and setting a
                    // null content description allows accessibility services to skip this element
                    // during navigation.

                    contentDescription = null
                )
                Text(
                    text = stringResource(R.string.app_name),
                    style = MaterialTheme.typography.displayLarge
                )
            }
        },
        modifier = modifier,
    )
}

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

@Composable
fun TopTile() {
    Card(
        elevation = CardDefaults.cardElevation(5.dp),
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, end = 8.dp, top = 24.dp, bottom = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(
                16.dp, alignment = Alignment.CenterVertically
            )
        ) {
            Image(
                modifier = Modifier
                    .size(dimensionResource(R.dimen.image_size))
                    .padding(dimensionResource(R.dimen.padding_small)),
                painter = painterResource(R.drawable.money),

                // Content Description is not needed here - image is decorative, and setting a
                // null content description allows accessibility services to skip this element
                // during navigation.

                contentDescription = null
            )
            Text(text = "You have $ 1  left", style = MaterialTheme.typography.headlineMedium)
        }
    }


}


//@Composable
//fun BottomTile() {
//
//        LazyColumn( contentPadding = PaddingValues(dimensionResource(R.dimen.padding_small))) {
//            item {
//                Text(
//                    text = "Oi Mte",
//                    style = MaterialTheme.typography.headlineMedium,
//                    modifier = Modifier.padding(dimensionResource(R.dimen.padding_small))
//                )
//            }
//            items(expenses) {
//
//            }
//        }
//    }


@Preview(showSystemUi = true)
@Composable
fun ExpensePreview() {
    ExpenseSageTheme(darkTheme = false) {
        StartScreen(viewModel = MainViewModel())
    }
}

/**
 * Composable that displays what the UI of the app looks like in dark theme in the design tab.
 */
@Preview
@Composable
fun ExpenseDarkThemePreview() {
    ExpenseSageTheme(darkTheme = true) {
        StartScreen(viewModel = MainViewModel())
    }
}