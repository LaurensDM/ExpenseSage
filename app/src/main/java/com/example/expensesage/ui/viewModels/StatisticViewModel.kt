package com.example.expensesage.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.expensesage.data.UserSettings
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class StatisticViewModel(private val userPref: UserSettings) : ViewModel() {

    fun getMoneySpent(): StateFlow<Double> {
        return userPref.moneySpent.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = 0.0,
        )
    }

    fun getMoneyOwed(): StateFlow<Double> {
        return userPref.moneyOwed.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = 0.0,
        )
    }

    fun getMoneyAvailable(): StateFlow<Double> {
        return userPref.moneyAvailable.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = 0.0,
        )
    }

    fun getMonthlyMoney(): StateFlow<Double> {
        return userPref.monthlyBudget.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = 0.0,
        )
    }

    fun getMoneySpentByCategory(): Map<String, Double> {
        return mapOf()
    }

    fun getMoneySpentByMonth(): Map<String, Double> {
        return mapOf()
    }
}
