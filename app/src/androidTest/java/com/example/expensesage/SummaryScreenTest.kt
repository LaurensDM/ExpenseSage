package com.example.expensesage

import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.assertAll
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithText
import com.example.expensesage.ui.screens.CategoryChart
import com.example.expensesage.ui.screens.MonthChart
import com.example.expensesage.ui.screens.PrimaryChart
import com.example.expensesage.ui.screens.SummaryScreen
import com.example.expensesage.ui.utils.ExpenseSummaryItem
import org.junit.Rule
import org.junit.Test

class SummaryScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testSummaryScreen() {
        composeTestRule.setContent {
            SummaryScreen()
        }

        // Loading screen
        composeTestRule.onNodeWithText("Please wait", substring = true, ignoreCase = true)
            .assertExists()
        // Give application time to finish loading
        Thread.sleep(2000)
        // Actual summary screen
        composeTestRule.onNodeWithText("Total money spent", substring = true, ignoreCase = true)
            .assertExists()
        composeTestRule.onNodeWithText(
            "Money spent per category",
            substring = true,
            ignoreCase = true
        ).assertExists()
        // category list seems to be empty
        composeTestRule.onAllNodesWithText("No data", true, true).assertCountEquals(3)
//        composeTestRule.onNodeWithText("Total", ignoreCase = true).assertExists()
        // Lazy column does not load final item until it is scrolled into view
    }

    @Test
    fun monthGraphFilledListTest() {
        composeTestRule.setContent {
            MonthChart(monthData = listOf(ExpenseSummaryItem("2023-10",100.0)))
        }

        composeTestRule.onNodeWithText("month", substring = true, ignoreCase = true).assertExists()
        composeTestRule.onNodeWithText("No data", substring = true, ignoreCase = true).assertDoesNotExist()
    }

    @Test
    fun monthGraphNotFilledTest(){
        composeTestRule.setContent {
            MonthChart(monthData = emptyList())
        }

        composeTestRule.onNodeWithText("month", substring = true, ignoreCase = true).assertExists()
        composeTestRule.onNodeWithText("No data", substring = true, ignoreCase = true).assertExists()
    }

    @Test
    fun primaryChartEmptyTest(){
        composeTestRule.setContent {
            PrimaryChart(totalSpent = 0.0, owedSpent = 10.0)
        }

        composeTestRule.onNodeWithText("No data", substring = true, ignoreCase = true).assertExists()
        composeTestRule.onAllNodesWithText("expenses", substring = true, ignoreCase = true)[0].assertDoesNotExist()

    }

    @Test
    fun categoryChartNotEmptyTest(){
        composeTestRule.setContent {
            CategoryChart(categoryData = listOf(ExpenseSummaryItem("Food", 100.0)))
        }

        composeTestRule.onNodeWithText("No data", substring = true, ignoreCase = true).assertDoesNotExist()
        composeTestRule.onNodeWithText("Total", substring = true, ignoreCase = true).assertExists()
    }



}