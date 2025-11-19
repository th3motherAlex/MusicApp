package com.alex.concertapp.data.di

import android.content.Context
import androidx.room.Room
import com.alex.concertapp.data.local.AppDb
import com.alex.concertapp.data.local.ConcertDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides @Singleton
    fun provideDb(@ApplicationContext ctx: Context): AppDb =
        Room.databaseBuilder(ctx, AppDb::class.java, "concerts.db").build()

    @Provides
    fun provideDao(db: AppDb): ConcertDao = db.concertDao()
}
