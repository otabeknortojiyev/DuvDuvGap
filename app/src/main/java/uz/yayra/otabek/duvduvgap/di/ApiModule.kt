package uz.yayra.otabek.duvduvgap.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import uz.yayra.otabek.duvduvgap.data.network.HomeApi
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {

    @[Provides Singleton]
    fun providesAuthApi(retrofit: Retrofit): HomeApi = retrofit.create(HomeApi::class.java)
}