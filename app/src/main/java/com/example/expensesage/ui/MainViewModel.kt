package com.example.expensesage.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

/**
 * Main ViewModel class that is used to store the state of the app
 *
 */
class MainViewModel: ViewModel() {


    var isDialogShown by mutableStateOf(false)
    private set

    /**
     * Function that is called when the user clicks on the detail button. Shows dialog
     *
     */
    fun onDetailClick() {
        isDialogShown = true
    }

    /**
     * Function that is called when the user dismisses dialog. Hides dialog
     *
     */
    fun onDialogDismiss() {
        isDialogShown = false
    }
}