package com.example.expensesage.ui.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.expensesage.data.ExpenseRepository
import com.example.expensesage.ui.utils.ExpenseSummary
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.time.LocalDate

sealed interface StatisticUiState {
    data class Success(val summary: ExpenseSummary) : StatisticUiState
    data class Error(val error: String) : StatisticUiState
    data object Loading : StatisticUiState
}

class StatisticViewModel(private val expenseRepository: ExpenseRepository) : ViewModel() {

    var statisticUiState: StatisticUiState by mutableStateOf(StatisticUiState.Loading)
        private set

    init {
        getData()
    }

    fun getData() {
        viewModelScope.launch {
            statisticUiState = StatisticUiState.Loading
            statisticUiState = try {
                val categoryData = expenseRepository.getSumOfCategory(LocalDate.now().year.toString()).first()
                val monthlyData = expenseRepository.getMonthlyExpenseSummary(LocalDate.now().year.toString()).first()
                val owedData = expenseRepository.getSumOfOwed(true).first()
                val summary = ExpenseSummary()
                summary.init(
                    donutChartData = categoryData,
                    monthlyData = monthlyData,
                    owedComparison = owedData,
                )
                StatisticUiState.Success(summary)
            } catch (e: Exception) {
                StatisticUiState.Error(e.localizedMessage ?: "An unknown error occured")
            }
        }
    }

//    fun getMoneySpent(): StateFlow<Double> {
//        return userPref.moneySpent.stateIn(
//            scope = viewModelScope,
//            started = SharingStarted.WhileSubscribed(5_000),
//            initialValue = 0.0,
//        )
//    }
//
//    fun getMoneyOwed(): StateFlow<Double> {
//        return userPref.moneyOwed.stateIn(
//            scope = viewModelScope,
//            started = SharingStarted.WhileSubscribed(5_000),
//            initialValue = 0.0,
//        )
//    }
//
//    fun getMoneyAvailable(): StateFlow<Double> {
//        return userPref.moneyAvailable.stateIn(
//            scope = viewModelScope,
//            started = SharingStarted.WhileSubscribed(5_000),
//            initialValue = 0.0,
//        )
//    }
//
//    fun getMonthlyMoney(): StateFlow<Double> {
//        return userPref.monthlyBudget.stateIn(
//            scope = viewModelScope,
//            started = SharingStarted.WhileSubscribed(5_000),
//            initialValue = 0.0,
//        )
//    }
//
//    fun getMoneySpentByCategory(): Map<String, Double> {
//        return mapOf()
//    }
//
//    fun getMoneySpentByMonth(): Map<String, Double> {
//        return mapOf()
//    }
}
