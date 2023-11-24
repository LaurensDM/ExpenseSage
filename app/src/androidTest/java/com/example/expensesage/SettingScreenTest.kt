package com.example.expensesage

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.expensesage.ui.screens.SettingScreen
import org.junit.Rule
import org.junit.Test


//Requires device language to be set to English
class SettingScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun settingScreenTest() {
        composeTestRule.setContent {
            SettingScreen(showSnackBar = {message, snackBarType ->  }, showAlert = {onConfirm, title, subject, message ->  })
        }

        composeTestRule.onAllNodesWithText("budget", substring = true, ignoreCase = true)[0]
            .assertExists()
        composeTestRule.onNodeWithContentDescription("Edit").performClick()
        composeTestRule.onNodeWithContentDescription("Edit").performClick()
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
