package com.bw.kf.club_fengzy.net

import com.bw.kf.club_fengzy.appContext
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetModule {
    private val DEFAULT_TIME_OUT = 60L
    var cache: Cache = Cache(File("${appContext.externalCacheDir?.path}/okhttp_cache/") ,100 * 1024 * 1024)

    @Singleton
    @Provides
    fun bindApiService(@CommonRetrofit retrofit: Retrofit): ApiService{
        return retrofit.create(ApiService::class.java)
    }

    @Singleton
    @Provides
    @CommonRetrofit
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(API.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            //2.6.0以上默认支持协程
            // .addCallAdapterFactory(CoroutinesCallAdapterFactory.invoke())
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideOkhttpClient(): OkHttpClient {
        val httpLoggingInterceptor =
            okhttp3.logging.HttpLoggingInterceptor()
        //HttpLoggingInterceptor()

        return OkHttpClient.Builder()
            .writeTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS)
            .readTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS)
            .connectTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .cache(cache)
            .addInterceptor(httpLoggingInterceptor)
           // .addInterceptor(HttpHeaderInterceptor())
//            .sslSocketFactory(
//                SSLSocketFactoryUtil.createSSLSocketFactory(),
//                SSLSocketFactoryUtil.createTrustAllManager()
//            )
           // .hostnameVerifier(SSLSocketFactoryUtil.TrustAllHostnameVerifier())
            // .protocols(listOf(Protocol.HTTP_1_1))

            .build()
    }



    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class CommonRetrofit

}