package com.example.expensesage

import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithContentDescription
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.expensesage.ui.screens.OwedScreen
import com.example.expensesage.ui.viewModels.MainViewModel
import org.junit.Rule
import org.junit.Test

class OwedScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testOwedScreen() {
        composeTestRule.setContent {
            OwedScreen(viewModel = MainViewModel())
        }

        composeTestRule.onNodeWithContentDescription("Add expense", true, true).assertHasClickAction()
        composeTestRule.onAllNodesWithContentDescription("Expand")[0].performClick()
        composeTestRule.onNodeWithText("Paid", substring = true, ignoreCase = true).assertHasClickAction()
        composeTestRule.onNodeWithText("Paid", substring = true, ignoreCase = true).performClick()
        composeTestRule.onAllNodesWithContentDescription("Expanded")[0].performClick()
//        composeTestRule.onNodeWithText("Are you sure", substring = true, ignoreCase = true).assertExists("Alert does not show")
    }
}
