package com.alex.concertapp.data.di

import com.alex.concertapp.data.repository.ConcertRepositoryImpl
import com.alex.concertapp.domain.repository.ConcertRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds @Singleton
    abstract fun bindConcertRepository(impl: ConcertRepositoryImpl): ConcertRepository
}
