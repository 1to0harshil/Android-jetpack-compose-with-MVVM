package com.example.ComposeWithMVVM.networkModule

import com.example.ComposeWithMVVM.interfaces.ProjectServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.ConnectionSpec
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitInstance {

    private const val BASE_URL = "https://YOUT-BASE-API/api/"

    private val interceptor =
        HttpLoggingInterceptor().apply { this.level = HttpLoggingInterceptor.Level.BODY }

    val client = OkHttpClient.Builder().apply {
        connectionSpecs(listOf(ConnectionSpec.COMPATIBLE_TLS))
        addInterceptor { chain ->
            val builder = chain.request().newBuilder()
            with(builder) {
                header("Content-Type", "application/json")
                header("Cache-Control", "no-cache")
                method(chain.request().method, chain.request().body)
            }
            chain.proceed(builder.build())

        }
        this.addInterceptor(interceptor)
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(25, TimeUnit.SECONDS)
    }.build()

    @Provides
    @Singleton
    fun retrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    fun projectServices(retrofit: Retrofit): ProjectServices {
        return retrofit.create(ProjectServices::class.java)
    }

}