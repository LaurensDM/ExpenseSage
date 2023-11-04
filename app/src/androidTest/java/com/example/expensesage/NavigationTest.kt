package com.example.expensesage

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithContentDescription
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.expensesage.ui.utils.Navigations
import org.junit.Assert.* // ktlint-disable no-wildcard-imports
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class NavigationTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    private lateinit var navController: TestNavHostController

    @Before
    fun setupNavHost() {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current).apply {
                navigatorProvider.addNavigator(ComposeNavigator())
            }
            ExpenseSageApp(navController = navController, windowSize = WindowWidthSizeClass.Compact)
        }
    }


    @Test
    fun navigation_SettingScreen_navigates() {
        assertEquals(navController.currentDestination?.route, Navigations.Start.route)
        composeTestRule.onNodeWithContentDescription("Settings").performClick()
        composeTestRule.onNodeWithText("Pocket Money", substring = true, ignoreCase = true)
            .assertExists()
        assertEquals(navController.currentDestination?.route, Navigations.Settings.route)

    }

    @Test
    fun navigation_ExpenseScreen_navigates() {
        assertEquals(navController.currentDestination?.route, Navigations.Start.route)
        composeTestRule.onAllNodesWithContentDescription("Expense")[0].performClick()
        composeTestRule.onNodeWithContentDescription("Add expense",
            substring = true,
            ignoreCase = true
        ).assertHasClickAction()
            .assertExists()
        assertEquals(navController.currentDestination?.route, Navigations.Expenses.route)

    }

    @Test
    fun navigation_OwedScreen_navigates() {
        assertEquals(navController.currentDestination?.route, Navigations.Start.route)
        composeTestRule.onAllNodesWithContentDescription("Owed")[0].performClick()
        composeTestRule.onNodeWithContentDescription("Add expense",
            substring = true,
            ignoreCase = true
        ).assertHasClickAction()
            .assertExists()
        assertEquals(navController.currentDestination?.route, Navigations.Owed.route)

    }

    @Test
    fun navigation_CurrencyScreen_navigates() {
        assertEquals(navController.currentDestination?.route, Navigations.Start.route)
        composeTestRule.onAllNodesWithContentDescription("Currencies")[0].performClick()
        // Wait for loader to finish loading
        Thread.sleep(2000)
        composeTestRule.onNodeWithText("date", substring = true, ignoreCase = true)
            .assertExists()
        assertEquals(navController.currentDestination?.route, Navigations.Currencies.route)
    }

    @Test
    fun navigation_SummaryScreen_navigates() {
        assertEquals(navController.currentDestination?.route, Navigations.Start.route)
        composeTestRule.onAllNodesWithContentDescription("Summary")[0].performClick()
//        composeTestRule.onNodeWithText("Please wait", substring = true, ignoreCase = true).assertExists()
        // Wait for loader to finish loading
Thread.sleep(1000)
        composeTestRule.onNodeWithText("Total money spent", substring = true, ignoreCase = true)
            .assertExists()
        assertEquals(navController.currentDestination?.route, Navigations.Summary.route)
    }
}
