package com.example.expensesage.ui.viewModels

import android.util.Log
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.expensesage.R
import com.example.expensesage.data.Expense
import com.example.expensesage.ui.utils.ModalType
import kotlinx.coroutines.launch

/**
 * Main ViewModel class that is used to store the state of the app
 *
 */
class MainViewModel : ViewModel() {

    val snackbarHostState = SnackbarHostState()

    var snackbarType by mutableStateOf(SnackBarType.SUCCESS)
        private set

    var isAlertShown by mutableStateOf(false)
        private set

    var alertOnConfirm: () -> Unit  by mutableStateOf({})
        private set

    var alertOnCancel: () -> Unit by mutableStateOf({})
        private set
    var alertTitle by mutableStateOf("")
        private set

    var isDialogShown by mutableStateOf(false)
        private set

    var selectedExpense: Expense by mutableStateOf(
        value = Expense(
            imageResourceId = R.drawable.cost,
            owed = false,
        ),
    )
        private set

    var isOwed: Boolean by mutableStateOf(false)
        private set

    var currentModalType: ModalType by mutableStateOf(ModalType.DETAIL)
        private set


    init {
//        Log.d("MainViewModel", "init")
    }

    /**
     * Function that is called when the user clicks on the detail button. Shows dialog
     *
     */
    fun showModal(expense: Expense? = Expense(imageResourceId = R.drawable.cost, owed = false), owed: Boolean = false, modalType: ModalType) {
        if (expense != null) {
            selectedExpense = expense
        }
        currentModalType = modalType
        isOwed = owed
        isDialogShown = true
    }

    fun showAlert(onConfirm: () -> Unit, title: String, onCancel: () -> Unit) {
        alertOnConfirm = onConfirm
        isAlertShown = true
        alertTitle = title
        alertOnCancel = onCancel
    }

    fun showSnackBar(message: String, type: SnackBarType) {
        viewModelScope.launch {
            snackbarType = type
            snackbarHostState.showSnackbar(message)
        }

    }

    /**
     * Function that is called when the user dismisses dialog. Hides dialog
     *
     */
    fun onDialogDismiss() {
        isAlertShown = false
        isDialogShown = false
    }
}

enum class SnackBarType {
    SUCCESS, ERROR
}
