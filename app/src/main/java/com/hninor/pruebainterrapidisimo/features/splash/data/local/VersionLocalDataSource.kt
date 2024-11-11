package com.hninor.pruebainterrapidisimo.features.splash.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first


// At the top level of your kotlin file:
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class VersionLocalDataSource(private val context: Context) {

    private val VERSION = stringPreferencesKey("version")
    suspend fun getVersion() : String  = getPreferences()[VERSION] ?: "0"

    suspend fun saveVersion(version: String) {
        context.dataStore.edit { preferences ->
            preferences[VERSION] = version
        }
    }


    private suspend fun getPreferences(): Preferences =
        context.dataStore.data.first()

}