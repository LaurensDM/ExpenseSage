package com.example.expensesage

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.expensesage.data.AppContainer
import com.example.expensesage.data.AppDataContainer
import com.example.expensesage.data.UserSettings
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

private const val SETTINGS_PREFERENCE_NAME = "settings_preferences"
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
    name = SETTINGS_PREFERENCE_NAME,
)

class ExpenseSageApplication : Application() {
    lateinit var userSettings: UserSettings
    lateinit var container: AppContainer
    private lateinit var appScope: CoroutineScope

    override fun onCreate() {
        super.onCreate()
        appScope = CoroutineScope(SupervisorJob() + Dispatchers.Main)
        userSettings = UserSettings(dataStore)
        container = AppDataContainer(this, appScope)
    }
}
