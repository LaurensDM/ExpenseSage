package com.example.expensesage.ui.viewModels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.expensesage.data.UserSettingsService
import com.example.expensesage.data.currencies.CurrencyRepository
import com.example.expensesage.data.expenses.ExpenseRepository
import com.example.expensesage.ui.utils.formatToCurrency
import com.example.expensesage.ui.utils.formatToDouble
import com.example.expensesage.workers.WorkersExecutor
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.io.IOException

/**
 * This class is responsible for the settings view model.
 *
 * @property executor The executor for the workers
 * @property userSettings The user settings
 * @property expenseRepository The expense repository
 * @property currencyRepository The currency repository
 */
class SettingsViewModel(
    private val changeInterval: () -> Unit,
    private val userSettings: UserSettingsService,
    private val expenseRepository: ExpenseRepository,
    private val currencyRepository: CurrencyRepository,
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
            budget = (userSettings.budget.first() * modifier).formatToCurrency()
            budgetFrequencyState = userSettings.budgetFrequency.first()
        }
    }

    /**
     * This function updates the budget.
     *
     * @param newBudget The new budget
     */
    fun updateBudget(newBudget: String) {
        try {
            // If the new budget is not a valid double, this will throw
            newBudget.formatToDouble()
            budget = newBudget
        } catch (e: NumberFormatException) {
            budget = ""
            Log.d("SettingsViewModel", "updateMonthlyBudget: $e")
        }

    }

    /**
     * This function updates the budget frequency.
     *
     * @param budgetFrequency The new budget frequency
     */
    fun updateBudgetFrequency(budgetFrequency: String) {
        viewModelScope.launch {
            if (budgetFrequencyList.contains(budgetFrequency) && budgetFrequency != budgetFrequencyState) {
                budgetFrequencyState = budgetFrequency
                userSettings.saveBudgetFrequency(budgetFrequencyState)
                userSettings.saveFirstBudgetChange(true)
                budget = "0"
                userSettings.saveBudget(0.0)
                calculateMoneyAvailable()
                changeInterval()
            }
        }
    }

    /**
     * This function gets the budget frequency.
     *
     * @return The budget frequency
     */
    fun getMoneyAvailable(): StateFlow<Double> {
        return userSettings.moneyAvailable.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = 0.0,
        )
    }

    /**
     * This function changes the budget.
     *
     */
    fun changeBudget() {
        viewModelScope.launch {
//            Log.d("SettingsViewModel", "budget: $budget")
            userSettings.saveBudget(budget.formatToDouble()/ modifier)
            if (userSettings.firstBudgetChange.first()) {
                calculateMoneyAvailable()
                userSettings.saveFirstBudgetChange(false)
            }

        }
    }

    /**
     * This function calculates the money available.
     *
     */
    private suspend fun calculateMoneyAvailable() {
        val sumExpenses = when (userSettings.budgetFrequency.first()) {
            "Weekly" -> expenseRepository.getSumOfWeek().first()
            "Monthly" -> expenseRepository.getSumOfMonth().first()
            "Yearly" -> expenseRepository.getSumOfYear().first()
            else -> 0.0
        }
        userSettings.saveMoneyAvailable(budget.formatToDouble() / modifier - sumExpenses)
    }


    /**
     * This function turns the sound off.
     *
     */
    fun turnOffSound() {
        viewModelScope.launch {
            userSettings.saveSoundPreference(false)
        }
    }

    /**
     * This function changes the sound volume.
     *
     * @param newVolume The new volume
     */
    fun changeSoundVolume(newVolume: Double) {
        viewModelScope.launch {
            userSettings.saveSoundVolume(newVolume)
        }
    }

    /**
     * This function returns the sound volume
     *
     * @return The sound volume
     */
    fun getSoundVolume(): StateFlow<Double> {
        return userSettings.soundVolume.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = 1.0,
        )
    }

    /**
     * This function returns the sound preference.
     *
     * @return The sound preference
     */
    fun getSoundPreference(): StateFlow<Boolean> {
        return userSettings.playSound.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = true,
        )
    }

    /**
     * This function changes the sound preference.
     *
     * @param newCurrency The new currency
     */
    fun changeCurrency(newCurrency: String) {
        viewModelScope.launch {
            try {
                    userSettings.saveCurrencyModifier(
                        currencyRepository.getCurrency(newCurrency.lowercase()).first().rate
                    )
            } catch (e: IOException) {
                // This only occurs if a sync hasn't occurred yet,
                // and the user has no internet when calling this function
                // Very unlikely, but still possible
                userSettings.saveCurrencyModifier(
                    when (newCurrency) {
                        "EUR" -> 1.0
                        "USD" -> 1.068
                        "JPY" -> 159.798
                        else -> 1.0
                    },
                )
            }
            userSettings.saveCurrency(newCurrency)
            val newModifier = userSettings.currencyModifier.first()
            budget = (budget.formatToDouble() / modifier * newModifier).toString()
            modifier = newModifier
            currency = newCurrency
        }
    }

    /**
     * This function returns the currency.
     *
     * @return The currency
     */
    fun getCurrency(): StateFlow<String> {
        return userSettings.currency.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = "EUR",
        )
    }

    /**
     * This function returns the currency modifier.
     *
     * @return The currency modifier
     */
    fun getCurrencyModifier(): StateFlow<Double> {
        return userSettings.currencyModifier.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = 1.0,
        )
    }
}

val budgetFrequencyList = listOf("Weekly", "Monthly", "Yearly")
