package com.example.expensesage.ui.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.expensesage.data.UserSettings
import com.example.expensesage.data.currencyList
import com.example.expensesage.network.CurrencyApiExecutor
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val userSettings: UserSettings,
    private val currencyApiExecutor: CurrencyApiExecutor,
) : ViewModel() {
    var budget by mutableStateOf("0.0")
        private set

    var budgetFrequencyState by mutableStateOf("Weekly")
        private set

    private var modifier by mutableDoubleStateOf(1.0)

    init {
        viewModelScope.launch {
            modifier = userSettings.currencyModifier.first()
            budget = (userSettings.budget.first() * modifier).toString()
        }
    }

    fun updateMonthlyBudget(budget: String) {
        viewModelScope.launch {
            this@SettingsViewModel.budget = budget
        }
    }

    fun updateBudgetFrequency(budgetFrequency: String) {
        viewModelScope.launch {
            budgetFrequencyState = budgetFrequency
        }
    }

    fun getMoneyAvailable(): StateFlow<Double> {
        return userSettings.moneyAvailable.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = 0.0,
        )
    }

    fun changeMonthlyBudget() {
        viewModelScope.launch {
            val moneyAvailable = userSettings.moneyAvailable.first()
            userSettings.saveBudget(budget.toDouble() / modifier)
            if (moneyAvailable == 0.0) {
                userSettings.saveMoneyAvailable(budget.toDouble() / modifier)
            }

        }
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

    fun changeCurrency(currency: String) {
        viewModelScope.launch {
            try {
                userSettings.saveCurrencyModifier(currencyApiExecutor.getRate(currency))
                userSettings.saveCurrency(currency)
            } catch (e: Exception) {
                userSettings.saveCurrencyModifier(
                    when (currency) {
                        "EUR" -> 1.0
                        "USD" -> 1.068
                        "JPY" -> 159.798
                        else -> 1.0
                    },
                )
                userSettings.saveCurrency(
                    if (currencyList.contains(currency)) currency else "EUR",
                )
            }

            val newModifier = userSettings.currencyModifier.first()
            budget = (budget.toDouble() / modifier * newModifier).toString()

            modifier = newModifier
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
