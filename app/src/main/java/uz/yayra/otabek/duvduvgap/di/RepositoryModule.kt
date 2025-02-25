package uz.yayra.otabek.duvduvgap.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.yayra.otabek.duvduvgap.repository.AppRepository
import uz.yayra.otabek.duvduvgap.repository.AppRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @[Binds Singleton]
    fun provideHomeRepository(impl: AppRepositoryImpl): AppRepository
}