package com.selincengiz.ghibli.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.selincengiz.ghibli.common.Constants.BASE_URL
import com.selincengiz.ghibli.data.api.TMDBService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    private const val TIMEOUT = 60L

    @Provides
    @Singleton
    fun provideChuckerInterceptor(@ApplicationContext context: Context) =
        ChuckerInterceptor.Builder(context).build()

    @Provides
    @Singleton
    fun provideOkHttpClient(chucker: ChuckerInterceptor) = OkHttpClient.Builder().apply {
        addInterceptor(chucker)
        addInterceptor { chain ->
            val original = chain.request()
            val requestBuilder = original.newBuilder()
                .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJmNDQ0MWYwNzBiYTRlYTkzOTFmZDc0ZjBkN2U1MGMxNiIsInN1YiI6IjY1NjM2NmM0MzY3OWExMDk3N2UxNTNiNSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.L7TZI07X6YX5fEAg9fj_UypPUd65KCPCbVaWrX7gK10") // İstediğiniz header'ı burada ekleyin
            val request = requestBuilder.build()
            chain.proceed(request)
        }
        readTimeout(TIMEOUT, TimeUnit.SECONDS)
        connectTimeout(TIMEOUT, TimeUnit.SECONDS)
        writeTimeout(TIMEOUT, TimeUnit.SECONDS)
    }.build()

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient) = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideTMDBService(retrofit: Retrofit) = retrofit.create<TMDBService>()

}


