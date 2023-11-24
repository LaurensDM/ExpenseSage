package com.example.expensesage

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithText
import com.example.expensesage.ui.screens.StartScreen
import org.junit.Rule
import org.junit.Test

//Requires device language to be set to English

class StartScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    
    @Test
    fun startScreenTest() {
        composeTestRule.setContent {
            StartScreen(showModal = {_,_,_ -> }, showAlert = {_,_,_,_ -> })
        }

        composeTestRule.onNodeWithText("ExpenseSage", substring = true, ignoreCase = true).assertExists()
        composeTestRule.onNodeWithText("left", substring = true, ignoreCase = true).assertExists()
        composeTestRule.onNodeWithText("Latest Expenses", substring = true, ignoreCase = true).assertExists()
        // May not always work, depends on user preferences, € could be $ or JPY instead, € should be default
        composeTestRule.onAllNodesWithText("€", substring = true, ignoreCase = true)[0].assertExists()
//        composeTestRule.onAllNodesWithText("$", substring = true, ignoreCase = true)[0].assertExists()

    }
}
