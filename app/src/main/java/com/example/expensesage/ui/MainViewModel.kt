package com.example.expensesage.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.expensesage.data.Expense

/**
 * Main ViewModel class that is used to store the state of the app
 *
 */
class MainViewModel : ViewModel() {


    var isDialogShown by mutableStateOf(false)
        private set

    var money by mutableDoubleStateOf(500.00)
        private set

    var currencyModifier by mutableDoubleStateOf(1.0)
        private set

    var selectedExpense: Expense by mutableStateOf(
        value = Expense(
            imageResourceId = 0,
            owed = false
        )
    )
        private set

    /**
     * Function that is called when the user clicks on the detail button. Shows dialog
     *
     */
    fun onDetailClick(expense: Expense) {
        selectedExpense = expense
        isDialogShown = true
    }

    /**
     * Function that is called when the user dismisses dialog. Hides dialog
     *
     */
    fun onDialogDismiss() {
        isDialogShown = false
    }



    fun changeCurrencyModifier(modifier: Double) {
        currencyModifier = modifier
    }



//    private fun getImages() {
//        viewModelScope.launch {
//            val result = ImageApi.retrofitService.getImage()
//        }
//    }
}