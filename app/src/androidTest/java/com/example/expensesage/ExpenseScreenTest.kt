package com.example.expensesage

import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithContentDescription
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.core.app.ApplicationProvider
import com.example.expensesage.data.AppDataContainer
import com.example.expensesage.data.expenses.expenses
import com.example.expensesage.ui.screens.ExpenseScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.junit.Rule
import org.junit.Test

//Requires device language to be set to English
class ExpenseScreenTest {
    
    @get:Rule
    val composeTestRule = createComposeRule()
    
    @Test
    fun expenseScreenTest() {
        composeTestRule.setContent {
            val repo = AppDataContainer( ApplicationProvider.getApplicationContext(), CoroutineScope(Dispatchers.Main)).expenseRepository
            expenses.forEach {
                CoroutineScope(Dispatchers.Main).launch {
                    repo.insert(it)
                }
            }
            ExpenseScreen(
                showModal = { expense, owed, modalType ->

                },
                showAlert = { onConfirm, title, subject, onCancel ->

                },
            )
        }



        // This code is depending on the data in the Room database, if there is no data, this test will fail
        composeTestRule.onNodeWithContentDescription("Add expense",
            substring = true,
            ignoreCase = true
        ).assertHasClickAction()
        composeTestRule.onAllNodesWithContentDescription("Expand")[0].performClick()
        composeTestRule.onAllNodesWithContentDescription("Expanded")[0].assertHasClickAction()
        composeTestRule.onNodeWithText("Details", substring = true, ignoreCase = true).assertHasClickAction()
        composeTestRule.onNodeWithText("Paid", substring = true, ignoreCase = true).assertDoesNotExist()
        composeTestRule.onAllNodesWithContentDescription("Expanded")[0].performClick()
        composeTestRule.onNodeWithText("Edit", substring = true, ignoreCase = true).assertDoesNotExist()
        composeTestRule.onAllNodesWithText("2023", true)[0].assertExists("Headers do not exist")
    }
}
