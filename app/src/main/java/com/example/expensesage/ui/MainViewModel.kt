package com.example.expensesage.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.expensesage.R
import com.example.expensesage.data.Expense
import com.example.expensesage.ui.utils.ModalType

/**
 * Main ViewModel class that is used to store the state of the app
 *
 */
class MainViewModel : ViewModel() {

    var isAlertShown by mutableStateOf(false)
        private set

    var alertOnConfirm by mutableStateOf({})
        private set

    var alertTitle by mutableStateOf("")
        private set

    var isDialogShown by mutableStateOf(false)
        private set

    var money by mutableDoubleStateOf(500.00)
        private set

    var currencyModifier by mutableDoubleStateOf(1.0)
        private set

    var selectedExpense: Expense by mutableStateOf(
        value = Expense(
            imageResourceId =  R.drawable.cost,
            owed = false
        )
    )
        private set

    var isOwed: Boolean by mutableStateOf(false)
        private set

    var currentModalType: ModalType by mutableStateOf(ModalType.DETAIL)
        private set


    /**
     * Function that is called when the user clicks on the detail button. Shows dialog
     *
     */
    fun showModal(expense: Expense = Expense(imageResourceId = R.drawable.cost, owed = false), owed: Boolean = false, modalType: ModalType) {
        selectedExpense = expense
        currentModalType = modalType
        isOwed = owed
        isDialogShown = true
    }

    fun showAlert(onConfirm : () -> Unit, title: String) {
        alertOnConfirm = onConfirm
        isAlertShown = true
        alertTitle = title
    }

    /**
     * Function that is called when the user dismisses dialog. Hides dialog
     *
     */
    fun onDialogDismiss() {
        isAlertShown = false
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