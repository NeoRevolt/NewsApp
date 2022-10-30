package com.dzalfikri.newsapp.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.dzalfikri.newsapp.BuildConfig
import com.dzalfikri.newsapp.data.offline.entity.NewsEntity
import com.dzalfikri.newsapp.data.offline.room.NewsDao
import com.dzalfikri.newsapp.data.online.responses.ArticlesItem
import com.dzalfikri.newsapp.data.online.responses.NewsResponse
import com.dzalfikri.newsapp.data.online.retrofit.ApiService
import com.dzalfikri.newsapp.utils.AppExecutor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsRepository private constructor(
    private val apiService: ApiService,
    private val newsDao: NewsDao,
    private val appExecutor: AppExecutor
) {
    private val result = MediatorLiveData<Result<List<NewsEntity>>>()

    fun getHeadline(): LiveData<Result<List<NewsEntity>>> {
        result.value = Result.Loading
        val client = apiService.getNews(BuildConfig.API_KEY)
        client.enqueue(object : Callback<NewsResponse> {
            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                if (response.isSuccessful) {
                    val articles = response.body()?.articles
                    val newslist = ArrayList<NewsEntity>()
                    appExecutor.diskIO.execute {
                        articles?.forEach { article ->
                            val isBookmarked = newsDao.isNewsBookmarked(article.title)
                            val news = NewsEntity(
                                article.title,
                                article.publishedAt,
                                article.urlToImage,
                                article.url,
                                article.author,
                                article.source.name,
                                article.description,
                                article.content,
                                isBookmarked
                            )
                            newslist.add(news)
                        }
                        newsDao.deleteAll()
                        newsDao.insertNews(newslist)
                    }
                }
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                result.value = Result.Error(t.message.toString())
            }
        })
        val offlineData = newsDao.getNews()
        result.addSource(offlineData) { newData: List<NewsEntity> ->
            result.value = Result.Success(newData)
        }
        return result
    }

    fun getBookmarkedNews(): LiveData<List<NewsEntity>> {
        return newsDao.getBookmarkedNews()
    }

    fun setBookmarkedNews(news: NewsEntity, bookmarkState: Boolean) {
        appExecutor.diskIO.execute {
            news.isBookmarked = bookmarkState
            newsDao.updateNews(news)
        }
    }

    companion object {
        @Volatile
        private var instance: NewsRepository? = null

        fun getInstance(
            apiService: ApiService,
            newsDao: NewsDao,
            appExecutor: AppExecutor
        ): NewsRepository =
            instance ?: synchronized(this) {
                instance ?: NewsRepository(apiService, newsDao, appExecutor)
            }.also { instance = it }
    }
}