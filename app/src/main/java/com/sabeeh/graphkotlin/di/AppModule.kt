package com.sabeeh.graphkotlin.di

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideSharedPrefs(@ApplicationContext context:Context)= context.getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)

    @Singleton
    @Provides
    fun provideSharedPrefsEditor( sharedPreference:SharedPreferences)= sharedPreference.edit()

}