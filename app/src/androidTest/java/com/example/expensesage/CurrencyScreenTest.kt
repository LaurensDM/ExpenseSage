package com.example.expensesage

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.printToLog
import com.example.expensesage.network.CurrencyApiExecutor
import com.example.expensesage.ui.screens.CurrencyScreen
import com.example.expensesage.ui.viewModels.APIViewModel
import com.example.expensesage.ui.viewModels.CurrencyUiState
import kotlinx.serialization.json.JsonObject
import org.junit.Rule
import org.junit.Test

class CurrencyScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun currencyScreenErrorTest() {
        composeTestRule.setContent {
            CurrencyScreen(currencyUiState = CurrencyUiState.Error("Something went wrong"), onRetry = {})
        }
        composeTestRule.onNodeWithText("Something went wrong", true, true).assertExists()
    }

    @Test
    fun currencyScreenNoDataTest() {
        composeTestRule.setContent {
            CurrencyScreen(currencyUiState = CurrencyUiState.Success(JsonObject(emptyMap())), onRetry = {})
        }
        composeTestRule.onNodeWithText("Retrieval date", true, true).assertExists()
        composeTestRule.onNodeWithText("No results found", true, true).assertExists()
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
