package com.example.expensesage

import com.example.expensesage.data.expenses.Expense
import com.example.expensesage.ui.utils.ModalType
import com.example.expensesage.ui.viewModels.MainViewModel
import org.junit.Test

class MainViewModelTest {
    private val viewModel = MainViewModel()

    @Test
    fun `showModal sets isDialogShown to true`() {
        assert(!viewModel.isDialogShown)
        viewModel.showModal(modalType = ModalType.CREATE)
        assert(viewModel.currentModalType == ModalType.CREATE)
        assert(viewModel.isDialogShown)
    }

    @Test
    fun `showModal sets selectedExpense to expense`() {
        val expense = Expense(id = 5,imageResourceId = R.drawable.cost, owed = false)
        assert(viewModel.selectedExpense != expense)
        viewModel.showModal(expense = expense, modalType = ModalType.CREATE)
        assert(viewModel.selectedExpense.id == 5)
    }

    @Test
    fun `showModal sets currentModalType to modalType`() {
        assert(viewModel.currentModalType != ModalType.EDIT)
        viewModel.showModal(modalType = ModalType.EDIT)
        assert(viewModel.currentModalType == ModalType.EDIT)
    }

    @Test
    fun `showModal sets isOwed to owed`() {
        assert(!viewModel.isOwed)
        viewModel.showModal(owed = true, modalType = ModalType.CREATE)
        assert(viewModel.isOwed)
    }

    @Test
    fun `showAlert sets isAlertShown to true`() {
        assert(!viewModel.isAlertShown)
        viewModel.showAlert({}, "", {})
        assert(viewModel.isAlertShown)
    }

    @Test
    fun `showAlert sets alertOnConfirm to onConfirm`() {
        var exampleVariable = 5
        val onConfirm = {
            exampleVariable += 1
        }
        assert(viewModel.alertOnConfirm != onConfirm)
        viewModel.showAlert(onConfirm, "", {})
        assert(viewModel.alertOnConfirm == onConfirm)
    }

    @Test
    fun `showAlert sets alertTitle to title`() {
        val title = "title"
        assert(viewModel.alertText != title)
        viewModel.showAlert({}, title, {})
        assert(viewModel.alertText == title)
    }

    @Test
    fun `showAlert sets alertOnCancel to onCancel`() {
        var exampleVariable = 5
        val onCancel = {
            exampleVariable -= 1
        }
        assert(viewModel.alertOnCancel != onCancel)
        viewModel.showAlert({}, "", onCancel)
        assert(viewModel.alertOnCancel == onCancel)
    }

    @Test
    fun `onDismiss sets dialog and alert to false`() {
        viewModel.showModal(modalType = ModalType.CREATE)
        assert(viewModel.isDialogShown)
        viewModel.showAlert({}, "", {})
        assert(viewModel.isAlertShown)
        viewModel.onDialogDismiss()
        assert(!viewModel.isDialogShown)
        assert(!viewModel.isAlertShown)
    }
}