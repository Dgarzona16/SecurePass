package com.dgarzona16.securepass.di

import android.content.Context
import androidx.room.Room
import com.dgarzona16.securepass.data.SecurePassDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): SecurePassDatabase {
        return Room.databaseBuilder(
            appContext,
            SecurePassDatabase::class.java,
            "secure_pass.db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideAccountsDao(db: SecurePassDatabase) = db.accountsDao()

    @Provides
    @Singleton
    fun provideApplicationsDao(db: SecurePassDatabase) = db.applicationsDao()

    @Provides
    @Singleton
    fun provideConfigsDao(db: SecurePassDatabase) = db.configsDao()
}