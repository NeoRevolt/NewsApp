package com.dzalfikri.newsapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.dzalfikri.newsapp.databinding.ActivityNewsDetailBinding
import com.dzalfikri.newsapp.utils.DateFormatter

class NewsDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewsDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val title = intent.getStringExtra(EXTRA_TITLE)
        val source = intent.getStringExtra(EXTRA_SOURCE)
        val author = intent.getStringExtra(EXTRA_AUTHOR)
        val date = intent.getStringExtra(EXTRA_DATE)
        val image = intent.getStringExtra(EXTRA_IMAGE)
        val desc = intent.getStringExtra(EXTRA_DESC)
        val content = intent.getStringExtra(EXTRA_CONTENT)

        Glide.with(this)
            .load(image)
            .transition(DrawableTransitionOptions.withCrossFade())
            .fitCenter()
            .into(binding.ivImage)
        binding.apply {
            tvTitle.text = title
            tvItemPublishedDate.text = date?.let { DateFormatter.formatDate(it) }
            tvAuthor.text = author
            tvSource.text = source
            tvDescription.text = desc
            tvContent.text = content
        }
    }

    companion object {
        val EXTRA_TITLE = "extra_title"
        val EXTRA_SOURCE = "extra_source"
        val EXTRA_AUTHOR = "extra_author"
        val EXTRA_DATE = "extra_date"
        val EXTRA_IMAGE = "extra_image"
        val EXTRA_DESC = "extra_desc"
        val EXTRA_CONTENT = "extra_content"
    }
}