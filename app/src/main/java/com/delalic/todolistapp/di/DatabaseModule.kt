package com.delalic.todolistapp.di

import android.content.Context
import androidx.room.Room
import com.delalic.todolistapp.data.dao.ToDoDatabase
import com.delalic.todolistapp.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(context, ToDoDatabase::class.java, Constants.DATABASE_NAME).build()

    @Provides
    fun provideToDoDao(database: ToDoDatabase) = database.todoDao()
}