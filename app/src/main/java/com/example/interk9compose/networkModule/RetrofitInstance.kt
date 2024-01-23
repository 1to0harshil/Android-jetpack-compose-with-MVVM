package com.example.interk9compose.networkModule

import com.example.interk9compose.interfaces.ProjectServices
import okhttp3.ConnectionSpec
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

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


    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    val projectServices: ProjectServices by lazy {
        retrofit.create(ProjectServices::class.java)
    }

}