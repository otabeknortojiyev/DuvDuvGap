package uz.yayra.otabek.duvduvgap.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @[Provides Singleton]
    fun providesOkHttpClient(
        @ApplicationContext context: Context,
    ): OkHttpClient = OkHttpClient.Builder().addInterceptor(ChuckerInterceptor.Builder(context).build()).addInterceptor { chain ->
        val request = chain.request()
        val newRequest = request.newBuilder().header("x-rapidapi-key", "94f0cbbaafmshaa2cb482ce083b4p1f4d62jsn5de805cdb5b7")
            .header("x-rapidapi-host", "bbc-api2.p.rapidapi.com").build()
        chain.proceed(newRequest)
    }.build()

    @[Provides Singleton]
    fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder().baseUrl("https://bbc-api2.p.rapidapi.com/").client(okHttpClient).addConverterFactory(GsonConverterFactory.create()).build()
}