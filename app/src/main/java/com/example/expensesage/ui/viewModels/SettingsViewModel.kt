package com.example.expensesage.ui.viewModels

import android.util.Log
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

     var moneyAvailable by mutableStateOf("0.0")
        private set


     var monthlyBudget by mutableStateOf("0.0")
        private set

    private var modifier by mutableDoubleStateOf(1.0)

    init {
        viewModelScope.launch {
            modifier = userSettings.currencyModifier.first()
            moneyAvailable = (userSettings.moneyAvailable.first() * modifier).toString()
            monthlyBudget = (userSettings.monthlyBudget.first() * modifier).toString()
        }
    }

    fun updateMoneyAvailable(money: String) {
        viewModelScope.launch {
            moneyAvailable = money
        }
    }

    fun updateMonthlyBudget(budget: String) {
        viewModelScope.launch {
            monthlyBudget = budget
        }
    }

    fun getMoneyAvailable(): StateFlow<Double> {
        return userSettings.moneyAvailable.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = 0.0,
        )
    }

//    fun getMonthlyBudget(): Double {
//        return userSettings.moneyAvailable.stateIn(
//            scope = viewModelScope,
//            started = SharingStarted.WhileSubscribed(5_000),
//            initialValue = 0.0,
//        )
//    }

    fun changeMoneyAvailable() {
        viewModelScope.launch {
            userSettings.saveMoneyAvailable(moneyAvailable.toDouble()/modifier)
        }
    }

    fun changeMonthlyBudget() {
        viewModelScope.launch {
            userSettings.saveMonthlyBudget(monthlyBudget.toDouble()/modifier)
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

             moneyAvailable = (moneyAvailable.toDouble() / modifier * newModifier).toString()
            monthlyBudget = (monthlyBudget.toDouble() / modifier * newModifier).toString()

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
    
    fun getMoneyOwed(): StateFlow<Double> {
        return userSettings.moneyOwed.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = 0.0,
        )
    }
}
