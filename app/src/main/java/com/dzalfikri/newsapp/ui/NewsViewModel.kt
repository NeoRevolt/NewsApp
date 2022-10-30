package com.dzalfikri.newsapp.ui

import androidx.lifecycle.ViewModel
import com.dzalfikri.newsapp.data.NewsRepository
import com.dzalfikri.newsapp.data.offline.entity.NewsEntity

class NewsViewModel(private val newsRepository: NewsRepository) : ViewModel() {
    fun getHeadLineNews() = newsRepository.getHeadline()

    fun getBookmarkedNews() = newsRepository.getBookmarkedNews()

    fun saveNews(news: NewsEntity) {
        newsRepository.setBookmarkedNews(news, true)
    }

    fun deleteNews(news: NewsEntity) {
        newsRepository.setBookmarkedNews(news, false)
    }
}