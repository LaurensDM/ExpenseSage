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

class UserSettings(private val dataStore: DataStore<Preferences>) {
    private companion object {
        val PLAY_SOUND = booleanPreferencesKey("play_sound")
        val SOUND_VOLUME = doublePreferencesKey("sound_volume")
        val MONTHLY_BUDGET = doublePreferencesKey("monthly_budget")
        val MONEY_AVAILABLE = doublePreferencesKey("money_available")
        val MONEY_SPENT = doublePreferencesKey("money_spent")
        val MONEY_OWED = doublePreferencesKey("money_owed")
        val MONEY_SPENT_MONTH = doublePreferencesKey("money_spent_month")
        val CURRENCY = stringPreferencesKey("currency") // USD, EUR, GBP, YEN ?
        const val TAG = "UserSettings"
    }

    suspend fun saveSoundPreference(playSound: Boolean) {
        dataStore.edit { preferences ->
            preferences[PLAY_SOUND] = playSound
        }
    }

    suspend fun saveSoundVolume(soundVolume: Double) {
        dataStore.edit { preferences ->
            preferences[SOUND_VOLUME] = soundVolume
        }
    }

    suspend fun saveMonthlyBudget(monthlyBudget: Double) {
        dataStore.edit { preferences ->
            preferences[MONTHLY_BUDGET] = monthlyBudget
        }
    }

    suspend fun saveMoneyAvailable(moneyAvailable: Double) {
        dataStore.edit { preferences ->
            preferences[MONEY_AVAILABLE] = moneyAvailable
        }
    }

    suspend fun saveMoneySpent(moneySpent: Double) {
        dataStore.edit { preferences ->
            preferences[MONEY_SPENT] = moneySpent
        }
    }

    suspend fun saveMoneyOwed(moneyOwed: Double) {
        dataStore.edit { preferences ->
            preferences[MONEY_OWED] = moneyOwed
        }
    }

    suspend fun saveMoneySpentMonth(moneySpentMonth: Double) {
        dataStore.edit { preferences ->
            preferences[MONEY_SPENT_MONTH] = moneySpentMonth
        }
    }

    suspend fun saveCurrency(currency: String) {
        dataStore.edit { preferences ->
            preferences[CURRENCY] = currency
        }
    }

    val playSound: Flow<Boolean> = dataStore.data
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

    val soundVolume: Flow<Double> = dataStore.data
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

    val monthlyBudget: Flow<Double> = dataStore.data
        .catch {
            if (it is IOException) {
                Log.e(TAG, "Error reading preferences.", it)
                emit(emptyPreferences())
            } else {
                throw it
            }
        }
        .map { preferences ->
            preferences[MONTHLY_BUDGET] ?: 0.0
        }

    val moneyAvailable: Flow<Double> = dataStore.data
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

    val moneySpent: Flow<Double> = dataStore.data
        .catch {
            if (it is IOException) {
                Log.e(TAG, "Error reading preferences.", it)
                emit(emptyPreferences())
            } else {
                throw it
            }
        }
        .map { preferences ->
            preferences[MONEY_SPENT] ?: 0.0
        }

    val moneyOwed: Flow<Double> = dataStore.data
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

    val moneySpentMonth: Flow<Double> = dataStore.data
        .catch {
            if (it is IOException) {
                Log.e(TAG, "Error reading preferences.", it)
                emit(emptyPreferences())
            } else {
                throw it
            }
        }
        .map { preferences ->
            preferences[MONEY_SPENT_MONTH] ?: 0.0
        }

    val currency: Flow<String> = dataStore.data
        .catch {
            if (it is IOException) {
                Log.e(TAG, "Error reading preferences.", it)
                emit(emptyPreferences())
            } else {
                throw it
            }
        }
        .map { preferences ->
            preferences[CURRENCY] ?: "USD"
        }

}