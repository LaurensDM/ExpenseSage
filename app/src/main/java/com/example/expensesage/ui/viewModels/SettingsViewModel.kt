package com.example.expensesage.ui.viewModels

import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.expensesage.data.ExpenseRepository
import com.example.expensesage.data.UserSettings
import com.example.expensesage.network.CurrencyApiExecutor
import com.example.expensesage.ui.utils.formatToCurrency
import com.example.expensesage.workers.changeInterval
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val context: Context,
    private val userSettings: UserSettings,
    private val currencyApiExecutor: CurrencyApiExecutor,
    private val expenseRepository: ExpenseRepository
) : ViewModel() {
    var budget by mutableStateOf("0.0")
        private set

    var budgetFrequencyState by mutableStateOf("Weekly")
        private set

    private var modifier by mutableDoubleStateOf(1.0)

    var currency by mutableStateOf("EUR")
        private set

    init {
        viewModelScope.launch {
            currency = userSettings.currency.first()
            modifier = userSettings.currencyModifier.first()
            budget =  (userSettings.budget.first() * modifier).formatToCurrency()
            budgetFrequencyState = userSettings.budgetFrequency.first()
        }
    }

    fun updateBudget(newBudget: String) {
        try {
            // If the new budget is not a valid double, this will throw
            newBudget.toDouble()
            budget = newBudget
        } catch (e: NumberFormatException) {
            Log.d("SettingsViewModel", "updateMonthlyBudget: $e")
        }

    }

    fun updateBudgetFrequency(budgetFrequency: String) {
        viewModelScope.launch {
            if (budgetFrequencyList.contains(budgetFrequency) && budgetFrequency != budgetFrequencyState) {
                budgetFrequencyState = budgetFrequency
                userSettings.saveBudgetFrequency(budgetFrequencyState)
                    userSettings.saveFirstBudgetChange(true)
                    budget = "0"
                userSettings.saveBudget(0.0)
                calculateMoneyAvailable()
                changeInterval(context)
            }
        }
    }

    fun getMoneyAvailable(): StateFlow<Double> {
        return userSettings.moneyAvailable.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = 0.0,
        )
    }

    fun changeBudget() {
        viewModelScope.launch {
            Log.d("SettingsViewModel", "budget: $budget")
            userSettings.saveBudget(budget.toDouble() / modifier)
            if (userSettings.firstBudgetChange.first()) {
                calculateMoneyAvailable()
                userSettings.saveFirstBudgetChange(false)
            }

        }
    }

    private suspend fun calculateMoneyAvailable() {
        val sumExpenses = when (userSettings.budgetFrequency.first()) {
            "Weekly" -> expenseRepository.getSumOfWeek().first()
            "Monthly" -> expenseRepository.getSumOfMonth().first()
            "Yearly" -> expenseRepository.getSumOfYear().first()
            else -> 0.0
        }
            userSettings.saveMoneyAvailable(budget.toDouble() / modifier - sumExpenses)
    }


    fun turnOffSound() {
        viewModelScope.launch {
            userSettings.saveSoundPreference(false)
        }
    }

    fun changeSoundVolume(newVolume: Double) {
        viewModelScope.launch {
            userSettings.saveSoundVolume(newVolume)
        }
    }

    fun getSoundVolume(): StateFlow<Double> {
        return userSettings.soundVolume.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = 1.0,
        )
    }

    fun getSoundPreference(): StateFlow<Boolean> {
        return userSettings.playSound.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = true,
        )
    }

    fun changeCurrency(newCurrency: String) {
        viewModelScope.launch {
            try {
                userSettings.saveCurrencyModifier(currencyApiExecutor.getRate(newCurrency))
                userSettings.saveCurrency(newCurrency)
                val newModifier = userSettings.currencyModifier.first()
                budget = (budget.toDouble() / modifier * newModifier).toString()
                modifier = newModifier
                currency = newCurrency
            } catch (e: Exception) {
                userSettings.saveCurrencyModifier(
                    //TODO get from local database
//                    when (newCurrency) {
//                        "EUR" -> 1.0
//                        "USD" -> 1.068
//                        "JPY" -> 159.798
//                        else -> 1.0
//                    },
                    1.0
                )
                userSettings.saveCurrency(
//                    if (currencyList.contains(newCurrency)) newCurrency else "EUR",
                    "EUR"
                )
            }


        }
    }

    fun getCurrency(): StateFlow<String> {
        return userSettings.currency.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = "EUR",
        )
    }

    fun getCurrencyModifier(): StateFlow<Double> {
        return userSettings.currencyModifier.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = 1.0,
        )
    }
}

val budgetFrequencyList = listOf("Weekly", "Monthly", "Yearly")
