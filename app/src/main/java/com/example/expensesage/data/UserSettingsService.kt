package com.example.expensesage.data

import kotlinx.coroutines.flow.Flow

interface UserSettingsService {

    val playSound: Flow<Boolean>
    val soundVolume: Flow<Double>
    val budget: Flow<Double>
    val firstBudgetChange: Flow<Boolean>
    val moneyAvailable: Flow<Double>
    val budgetFrequency: Flow<String>
    val currency: Flow<String>
    val currencyModifier: Flow<Double>
    val firstTime : Flow<Boolean>
    val moneyOwed : Flow<Double>

    suspend fun saveSoundPreference(playSound: Boolean)
    suspend fun saveSoundVolume(soundVolume: Double)
    suspend fun saveBudget(budget: Double)
    suspend fun saveFirstBudgetChange(firstBudgetChange: Boolean)
    suspend fun saveMoneyAvailable(moneyAvailable: Double)
    suspend fun saveBudgetFrequency(budgetFrequency: String)
    suspend fun saveCurrency(currency: String)
    suspend fun saveCurrencyModifier(currencyModifier: Double)
    suspend fun saveFirstTime(firstTime: Boolean)
    suspend fun saveMoneyOwed(moneyOwed: Double)

}