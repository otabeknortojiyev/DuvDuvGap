package uz.yayra.otabek.duvduvgap.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import uz.yayra.otabek.duvduvgap.data.local.room.NewsDataBase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @[Provides Singleton]
    fun provideCardDatabase(@ApplicationContext context: Context): NewsDataBase =
        Room.databaseBuilder(context = context, NewsDataBase::class.java, "Database.db")
            .build()
}