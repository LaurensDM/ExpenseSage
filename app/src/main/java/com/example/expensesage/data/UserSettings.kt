package com.example.expensesage.data

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.doublePreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

class UserSettings(private val dataStore: DataStore<Preferences>) : UserSettingsService {
    private companion object {
        val PLAY_SOUND = booleanPreferencesKey("play_sound")
        val SOUND_VOLUME = doublePreferencesKey("sound_volume")
        val BUDGET = doublePreferencesKey("budget") // weekly budget
        val FIRST_BUDGET_CHANGE = booleanPreferencesKey("first_budget_change") // if it's the first time the user changes the settings
        val MONEY_AVAILABLE = doublePreferencesKey("money_available") // money available to spend, gets reset every week
        val BUDGET_FREQUENCY = stringPreferencesKey("budget_frequency") // money saved from last week, gets changed after every week
        val MONEY_OWED = doublePreferencesKey("money_owed") // How much money you spend on debts
        val CURRENCY = stringPreferencesKey("currency") // USD, EUR, GBP, YEN ?
        val CURRENCY_MODIFIER = doublePreferencesKey("currency_modifier") //TODO save in Room database
       val FIRST_TIME = booleanPreferencesKey("first_time") // if it's the first time the user opens the app
        const val TAG = "UserSettings"
    }

    /**
     * Save sound preference
     *
     * @param playSound Boolean whether to play sound or not
     */
    override suspend fun saveSoundPreference(playSound: Boolean) {
        dataStore.edit { preferences ->
            preferences[PLAY_SOUND] = playSound
        }
    }

    /**
     * Save sound volume
     *
     * @param soundVolume Double Sound volume
     */
    override suspend fun saveSoundVolume(soundVolume: Double) {
        dataStore.edit { preferences ->
            preferences[SOUND_VOLUME] = soundVolume
        }
    }

    /**
     * Save budget
     *
     * @param budget Double Budget
     */
    override suspend fun saveBudget(budget: Double) {
        dataStore.edit { preferences ->
            preferences[BUDGET] = budget
        }
    }

    /**
     * Save money available
     *
     * @param moneyAvailable Double Money available
     */
    override suspend fun saveMoneyAvailable(moneyAvailable: Double) {
        dataStore.edit { preferences ->
            preferences[MONEY_AVAILABLE] = moneyAvailable
        }
    }

    /**
     * Save budget frequency
     *
     * @param budgetFrequency String Budget frequency
     */
    override suspend fun saveBudgetFrequency(budgetFrequency: String) {
        dataStore.edit { preferences ->
            preferences[BUDGET_FREQUENCY] = budgetFrequency
        }
    }

    /**
     * Save money owed
     *
     * @param moneyOwed Double Money owed
     */
    override suspend fun saveMoneyOwed(moneyOwed: Double) {
        dataStore.edit { preferences ->
            preferences[MONEY_OWED] = moneyOwed
        }
    }

    /**
     * Save currency
     *
     * @param currency String Currency
     */
    override suspend fun saveCurrency(currency: String) {
        dataStore.edit { preferences ->
            preferences[CURRENCY] = currency
        }
    }

    /**
     * Save currency modifier
     *
     * @param currencyModifier Double Currency modifier
     */
    override suspend fun saveCurrencyModifier(currencyModifier: Double) {
        dataStore.edit { preferences ->
            preferences[CURRENCY_MODIFIER] = currencyModifier
        }
    }

    /**
     * Save first budget change
     *
     * @param firstSettingChange Boolean whether it's the first time the user changes the settings
     */
    override suspend fun saveFirstBudgetChange(firstSettingChange: Boolean) {
        dataStore.edit { preferences ->
            preferences[FIRST_BUDGET_CHANGE] = firstSettingChange
        }
    }

    /**
     * Save first time
     *
     * @param firstTime Boolean whether it's the first time the user opens the app
     */
    override suspend fun saveFirstTime(firstTime: Boolean) {
        dataStore.edit { preferences ->
            preferences[FIRST_TIME] = firstTime
        }
    }


    override val playSound: Flow<Boolean> = dataStore.data
        .catch {
            if (it is IOException) {
                Log.e(TAG, "Error reading preferences.", it)
                emit(emptyPreferences())
            } else {
                throw it
            }
        }
        .map { preferences ->
            preferences[PLAY_SOUND] ?: true
        }

    override val soundVolume: Flow<Double> = dataStore.data
        .catch {
            if (it is IOException) {
                Log.e(TAG, "Error reading preferences.", it)
                emit(emptyPreferences())
            } else {
                throw it
            }
        }
        .map { preferences ->
            preferences[SOUND_VOLUME] ?: 0.5
        }

    override val budget: Flow<Double> = dataStore.data
        .catch {
            if (it is IOException) {
                Log.e(TAG, "Error reading preferences.", it)
                emit(emptyPreferences())
            } else {
                throw it
            }
        }
        .map { preferences ->
            preferences[BUDGET] ?: 0.0
        }

    override val moneyAvailable: Flow<Double> = dataStore.data
        .catch {
            if (it is IOException) {
                Log.e(TAG, "Error reading preferences.", it)
                emit(emptyPreferences())
            } else {
                throw it
            }
        }
        .map { preferences ->
            preferences[MONEY_AVAILABLE] ?: 0.0
        }

    override val budgetFrequency: Flow<String> = dataStore.data
        .catch {
            if (it is IOException) {
                Log.e(TAG, "Error reading preferences.", it)
                emit(emptyPreferences())
            } else {
                throw it
            }
        }
        .map { preferences ->
            preferences[BUDGET_FREQUENCY] ?: "Weekly"
        }

    override val moneyOwed: Flow<Double> = dataStore.data
        .catch {
            if (it is IOException) {
                Log.e(TAG, "Error reading preferences.", it)
                emit(emptyPreferences())
            } else {
                throw it
            }
        }
        .map { preferences ->
            preferences[MONEY_OWED] ?: 0.0
        }

    override val currency: Flow<String> = dataStore.data
        .catch {
            if (it is IOException) {
                Log.e(TAG, "Error reading preferences.", it)
                emit(emptyPreferences())
            } else {
                throw it
            }
        }
        .map { preferences ->
            preferences[CURRENCY] ?: "EUR"
        }

    override val currencyModifier: Flow<Double> = dataStore.data
        .catch {
            if (it is IOException) {
                Log.e(TAG, "Error reading preferences.", it)
                emit(emptyPreferences())
            } else {
                throw it
            }
        }.map { preferences ->
            preferences[CURRENCY_MODIFIER] ?: 1.0
        }

    override val firstBudgetChange: Flow<Boolean> = dataStore.data
        .catch {
            if (it is IOException) {
                Log.e(TAG, "Error reading preferences.", it)
                emit(emptyPreferences())
            } else {
                throw it
            }
        }.map { preferences ->
            preferences[FIRST_BUDGET_CHANGE] ?: true
        }

    override val firstTime: Flow<Boolean> = dataStore.data
        .catch {
            if (it is IOException) {
                Log.e(TAG, "Error reading preferences.", it)
                emit(emptyPreferences())
            } else {
                throw it
            }
        }.map { preferences ->
            preferences[FIRST_TIME] ?: true
        }
}

val currencyList = listOf(
    "EUR",
    "USD",
    "JPY",
)


