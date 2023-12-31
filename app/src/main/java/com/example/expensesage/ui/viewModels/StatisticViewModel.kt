package com.example.expensesage.ui.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.expensesage.data.UserSettings
import com.example.expensesage.data.UserSettingsService
import com.example.expensesage.data.expenses.ExpenseRepository
import com.example.expensesage.ui.utils.ExpenseSummary
import com.example.expensesage.ui.utils.ExpenseSummaryItem
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.time.LocalDateTime

/**
 * This interface is responsible for the statisticUiState.
 *
 */
sealed interface StatisticUiState {
    data class Success(val summary: ExpenseSummary) : StatisticUiState
    data class Error(val error: String) : StatisticUiState
    data object Loading : StatisticUiState
}

/**
 * This class is responsible for the statistic view model.
 *
 * @property expenseRepository The expense repository
 * @property userPref The user preferences
 */
class StatisticViewModel(
    private val expenseRepository: ExpenseRepository,
    private val userPref: UserSettingsService,
) : ViewModel() {

    var statisticUiState: StatisticUiState by mutableStateOf(StatisticUiState.Loading)
        private set

    init {
        getData()
    }

    /**
     * This function gets the data.
     *
     */
    fun getData() {
        viewModelScope.launch {
            statisticUiState = StatisticUiState.Loading
            statisticUiState = try {
                val currentDate = LocalDateTime.now()
                val categoryData =
                    expenseRepository.getSumOfCategory(currentDate.year.toString()).first()
                val monthlyData =
                    expenseRepository.getMonthlyExpenseSummary(currentDate.year.toString())
                        .first()
                val totalSpent = expenseRepository.getSumOfAll().first()
                val moneyOwed = userPref.moneyOwed.first()
                val moneySaved =
                    expenseRepository.getWeeklyExpenseSummary("${currentDate.year}-${currentDate.monthValue}")
                        .first()
                val moneySavedData = if (moneySaved.size > 2) moneySaved.subList(
                    moneySaved.size - 2,
                    moneySaved.size
                ) else moneySaved
                val currencyModifier = userPref.currencyModifier.first()
                val summary = ExpenseSummary(
                    categoryData = categoryData,
                    monthlyData = monthlyData.map {
                        ExpenseSummaryItem(
                            it.subject,
                            it.totalExpense * currencyModifier
                        )
                    },
                    totalSpent = totalSpent,
                    owedTotal = moneyOwed,
                    moneySaved = moneySavedData.map {
                        ExpenseSummaryItem(
                            it.subject,
                            it.totalExpense * currencyModifier
                        )
                    }
                )
                StatisticUiState.Success(summary)
            } catch (e: Exception) {
                StatisticUiState.Error(e.localizedMessage ?: "An unknown error occured")
            }
        }
    }
}
