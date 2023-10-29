package com.example.expensesage.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.expensesage.data.UserSettings
import com.example.expensesage.data.currencyList
import com.example.expensesage.network.CurrencyApiExecutor
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val userSettings: UserSettings,
    private val currencyApiExecutor: CurrencyApiExecutor,
) : ViewModel() {

    fun onMoneyChange(newMoney: Double) {
        viewModelScope.launch {
            userSettings.saveMoneyAvailable(newMoney)
        }
    }

    fun changeMonthlyBudget(newBudget: Double) {
        viewModelScope.launch {
            userSettings.saveMonthlyBudget(newBudget)
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

    fun getMoneyAvailable(): StateFlow<Double> {
        return userSettings.moneyAvailable.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = 0.0,
        )
    }

    fun getMonthlyBudget(): StateFlow<Double> {
        return userSettings.monthlyBudget.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = 0.0,
        )
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
    
    fun getMoneyOwed(): StateFlow<Double> {
        return userSettings.moneyOwed.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = 0.0,
        )
    }
}
