package com.khalti.checkOut.api

import com.google.gson.GsonBuilder
import com.khalti.BuildConfig
import com.khalti.utils.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception
import java.util.concurrent.TimeUnit

public class ApiHelper_ {

    companion object {
        private val TIME_OUT = 30L

        fun apiBuilder(): KhaltiApi_ {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            val okHttpClient = OkHttpClient.Builder()
                    .readTimeout(TIME_OUT, TimeUnit.SECONDS)
                    .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                    .addInterceptor { chain ->
                        val request = chain.request().newBuilder()
                                .addHeader("checkout-version", BuildConfig.VERSION_NAME)
                                .addHeader("checkout-source", "android")
                                .addHeader("checkout-android-version", AppUtil.getOsVersion())
                                .addHeader("checkout-android-api-level", AppUtil.getApiLevel()!!.toString() + "")
                                .build()
                        chain.proceed(request)
                    }
                    .addInterceptor(interceptor)
                    .build()

            return Retrofit.Builder()
                    .baseUrl(Constant.url)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                    .build()
                    .create(KhaltiApi_::class.java)
        }

        fun <T : Any> callApi(response: Response<T>): Result<T> {
            return try {
                if (response.isSuccessful) {
                    Result.Success(response.body()!!)
                } else {
                    Result.Error(Throwable(ErrorUtil.parseError(if (EmptyUtil.isNotNull(response.errorBody())) String(response.errorBody()!!.bytes()) else "")))
                }
            } catch (e: Exception) {
                Result.Error(if (EmptyUtil.isNotNull(e)) e else Throwable(ErrorUtil.parseError("")))
            }
        }
    }
}