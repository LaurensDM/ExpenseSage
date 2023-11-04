package com.example.expensesage

import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.expensesage.ui.screens.SettingScreen
import org.junit.Rule
import org.junit.Test

class SettingScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun settingScreenTest() {
        composeTestRule.setContent {
            SettingScreen(showSnackBar = {message, snackBarType ->  })
        }

        composeTestRule.onNodeWithText("Pocket Money", substring = true, ignoreCase = true)
            .assertExists().assertIsNotEnabled()
        composeTestRule.onNodeWithContentDescription("Edit").assertHasClickAction()
        composeTestRule.onNodeWithContentDescription("Edit").performClick()
        composeTestRule.onNodeWithText("Pocket Money", substring = true, ignoreCase = true)
            .assertIsEnabled()
        composeTestRule.onNodeWithContentDescription("Edit").performClick()
        composeTestRule.onNodeWithText("Pocket Money", substring = true, ignoreCase = true)
            .assertExists().assertIsNotEnabled()
        // May not always work, depends on user preferences, EUR could be USD or JPY instead, EUR should be default
        Thread.sleep(500)
        composeTestRule.onNodeWithText("EUR", substring = true, ignoreCase = true).performClick()
        composeTestRule.onNodeWithText("USD", substring = true, ignoreCase = true).assertExists().performClick()
        Thread.sleep(500)
        composeTestRule.onNodeWithText("EUR", substring = true, ignoreCase = true).assertDoesNotExist()

//        composeTestRule.onNodeWithText("USD", substring = true, ignoreCase = true).performClick()
//        composeTestRule.onNodeWithText("EUR", substring = true, ignoreCase = true).performClick()
//        composeTestRule.onNodeWithText("USD", substring = true, ignoreCase = true)
//            .assertDoesNotExist()

//        composeTestRule.onNodeWithText("JPY", substring = true, ignoreCase = true).performClick()
//        composeTestRule.onNodeWithText("EUR", substring = true, ignoreCase = true).performClick()
//        composeTestRule.onNodeWithText("JPY", substring = true, ignoreCase = true).assertDoesNotExist()


    }
}
