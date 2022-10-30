package com.dzalfikri.newsapp.injection

import android.content.Context
import com.dzalfikri.newsapp.data.NewsRepository
import com.dzalfikri.newsapp.data.offline.room.NewsDatabase
import com.dzalfikri.newsapp.data.online.retrofit.ApiConfig
import com.dzalfikri.newsapp.utils.AppExecutor

object Injection {

    fun provideRepository(context: Context): NewsRepository {
        val apiService = ApiConfig.getApiService()
        val database = NewsDatabase.getInstance(context)
        val dao = database.newsDao()
        val appExecutor = AppExecutor()
        return NewsRepository.getInstance(apiService, dao, appExecutor)
    }
}