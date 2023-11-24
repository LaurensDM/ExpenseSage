package com.example.expensesage.ui.viewModels

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.expensesage.R
import com.example.expensesage.data.expenses.Expense
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
    var alertText by mutableIntStateOf(0)
        private set

    var alertTextSubject by mutableStateOf("")
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
     * Shows modal
     *
     * @param expense The expense
     * @param owed Whether the expense is owed
     * @param modalType The modal type
     */
    fun showModal(expense: Expense? = Expense(imageResourceId = R.drawable.cost, owed = false), owed: Boolean = false, modalType: ModalType) {
        if (expense != null) {
            selectedExpense = expense
        }
        currentModalType = modalType
        isOwed = owed
        isDialogShown = true
    }

    /**
     * Shows alert
     *
     * @param onConfirm The on confirm function
     * @param text The text
     * @param onCancel The on cancel function
     */
    fun showAlert(onConfirm: () -> Unit, text: Int, subject: String, onCancel: () -> Unit) {
        alertOnConfirm = onConfirm
        isAlertShown = true
        alertText = text
        alertOnCancel = onCancel
    }

    /**
     * Shows snackbar
     *
     * @param message The message
     * @param type The type of snackbar
     */
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
