package com.delalic.todolistapp.data.repositories

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.room.PrimaryKey
import com.delalic.todolistapp.data.enums.Priority
import com.delalic.todolistapp.util.Constants.PREFERENCE_KEY
import com.delalic.todolistapp.util.Constants.PREFERENCE_NAME
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = PREFERENCE_NAME)

@ViewModelScoped
class DataStoreRepository @Inject constructor(
    @ApplicationContext private val context: Context
){
//    No need for this
//    private object PreferencesKeys {
//        val sortKey = stringPreferencesKey(name = PREFERENCE_KEY)
//    }

    private val dataStore = context.dataStore

    suspend fun persistSortKey(priority: Priority) {
        dataStore.edit { preferences -> {
            preferences[stringPreferencesKey(name = PREFERENCE_KEY)] = priority.name
        } }
    }

    val readSortState: Flow<String> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            val sortState = preferences[stringPreferencesKey(name = PREFERENCE_KEY)] ?: Priority.NONE.name
            sortState
        }
}