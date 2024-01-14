package com.aytachuseynli.facedetectorapp.di

import android.content.Context
import androidx.room.Room
import com.aytachuseynli.facedetectorapp.data.local.AppDatabase
import com.aytachuseynli.facedetectorapp.data.local.TestResultDao
import com.aytachuseynli.facedetectorapp.data.repo.AppRepository
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
    fun provideRoomDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "ResultDataDB"
        ).build()

    @Singleton
    @Provides
    fun provideResultDao(db: AppDatabase): TestResultDao = db.testResultDao()

    @Singleton
    @Provides
    fun provideRepository(db: TestResultDao):AppRepository= AppRepository(db)

}