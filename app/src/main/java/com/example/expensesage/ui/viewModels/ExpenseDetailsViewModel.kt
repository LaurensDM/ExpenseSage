package com.example.expensesage.ui.viewModels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.expensesage.data.ExpenseRepository
import kotlinx.coroutines.flow.StateFlow

class ExpenseDetailsViewModel(
    savedStateHandle: SavedStateHandle,
    private val expenseRepository: ExpenseRepository,
) : ViewModel(){

//    private val itemId: Int = checkNotNull(savedStateHandle[Expens])

//    val uiState: StateFlow<ItemDetailsUiState>


}

data class ItemDetailsUiState(
    val owed: Boolean = false,
//    val expenseDetails: ExpenseDetails = ExpenseDetails()
)