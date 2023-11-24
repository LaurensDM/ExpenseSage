package com.example.expensesage

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithText
import com.example.expensesage.ui.screens.CurrenciesList
import com.example.expensesage.ui.screens.CurrencyScreen
import com.example.expensesage.ui.screens.Error
import org.junit.Rule
import org.junit.Test

//Requires device language to be set to English
class CurrencyScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun currencyScreenErrorTest() {
        composeTestRule.setContent {
           Error(
                retryAction = {},
                error = "Something went wrong"
            )
        }
        composeTestRule.onNodeWithText("Something went wrong", substring = true, ignoreCase = true).assertExists()
    }

    @Test
    fun currencyScreenNoDataTest() {
        composeTestRule.setContent {
            CurrenciesList(date = "2023-10-11")
        }
        composeTestRule.onNodeWithText("date", substring = true, ignoreCase = true).assertExists()
        composeTestRule.onNodeWithText("No results found", substring = true, ignoreCase = true).assertExists()
    }

    @Test
    fun currencyScreenTest() {
        composeTestRule.setContent {
            CurrencyScreen()
        }
        // Loader appears first
        composeTestRule.onNodeWithText("Retrieval date", substring = true, ignoreCase = true).assertDoesNotExist()
        Thread.sleep(1000)
        // Loader finishes loading
        composeTestRule.onNodeWithText("Retrieval date", substring = true, ignoreCase = true).assertExists()
        composeTestRule.onNodeWithText("No results found", substring = true, ignoreCase = true).assertDoesNotExist()
        // If data is displayed, EUR should be found somewhere
        composeTestRule.onAllNodesWithText("EUR", substring = true, ignoreCase = true)[0].assertExists()
    }

//    @Test
//    fun currencyScreenTest() {
//        composeTestRule.setContent {
//            CurrencyScreen(currencyUiState = APIViewModel(CurrencyApiExecutor()).currencyUiState, onRetry = {})
//        }
//        composeTestRule.onNodeWithText("Retrieval date", true, true).assertExists()
//        composeTestRule.onNodeWithText("No results found", true, true).assertExists()
//    }


}
