package com.example.classapp.core.storage

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_prefs")

class UserPreferences(private val context: Context) {

    companion object {
        val USER_ID = intPreferencesKey("user_id")
        val USER_NAME = stringPreferencesKey("user_name")
        val USER_EMAIL = stringPreferencesKey("user_email")
        val USER_ROLE = intPreferencesKey("user_role")
        val TOKEN = stringPreferencesKey("token")
    }

    // Guardar los datos del usuario
    suspend fun saveUserData(userId: Int, name: String, email: String, role: Int, token: String) {
        context.dataStore.edit { prefs ->
            prefs[USER_ID] = userId
            prefs[USER_NAME] = name
            prefs[USER_EMAIL] = email
            prefs[USER_ROLE] = role
            prefs[TOKEN] = token
        }
    }

    // Obtener los datos del usuario
    suspend fun getUserData(): Map<String, Any?> {
        val prefs = context.dataStore.data.first()
        return mapOf(
            "id_user" to prefs[USER_ID],
            "name" to prefs[USER_NAME],
            "email" to prefs[USER_EMAIL],
            "id_role" to prefs[USER_ROLE],
            "token" to prefs[TOKEN]
        )
    }

    // Limpiar los datos del usuario (Logout)
    suspend fun clearUserData() {
        context.dataStore.edit { it.clear() }
    }
}
