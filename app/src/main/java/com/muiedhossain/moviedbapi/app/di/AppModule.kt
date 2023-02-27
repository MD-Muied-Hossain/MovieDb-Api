package com.muiedhossain.moviedbapi.app.di

import android.app.Application
import com.localebro.okhttpprofiler.OkHttpProfilerInterceptor
import com.muiedhossain.moviedbapi.BuildConfig
import com.muiedhossain.moviedbapi.app.api.ApiInterface
import com.muiedhossain.moviedbapi.app.dao.MovieDao
import com.muiedhossain.moviedbapi.app.database.MovieDatabase
import com.muiedhossain.moviedbapi.app.diffUtils.RetrofitInstance
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG)
        {
            builder.addInterceptor(OkHttpProfilerInterceptor())
        }
        return builder.build()
    }

    @Provides
    @Singleton
    fun getRetrofitServiceInstance(retrofit: Retrofit): ApiInterface {
        return retrofit.create(ApiInterface::class.java)
    }

    @Provides
    @Singleton
    fun getRetrofitInstance(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(RetrofitInstance.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    fun getDao(db: MovieDatabase) : MovieDao {
        return db.getDao()
    }

    @Provides
    @Singleton
    fun getPopMovieDB(application: Application) : MovieDatabase {
        return MovieDatabase.getDataBaseInstance(application)
    }


}