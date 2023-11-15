package com.example.expensesage.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile


/**
 * Singleton class that handles the creation of the DataStore
 */
object DataStoreSingleton {
    @Volatile
    private var instance: DataStore<Preferences>? = null

    fun getInstance(context: Context): DataStore<Preferences> {
        return instance ?: synchronized(this) {
            instance ?: createDataStore(context).also { instance = it }
        }
    }

    /**
     * Create DataStore
     *
     * @param context Context Application context
     * @return DataStore<Preferences> DataStore
     */
    private fun createDataStore(context: Context): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create { context.preferencesDataStoreFile("settings_preferences") }
    }
}