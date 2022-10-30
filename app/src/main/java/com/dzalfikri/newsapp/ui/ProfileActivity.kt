package com.dzalfikri.newsapp.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.dzalfikri.newsapp.R
import com.dzalfikri.newsapp.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupActivityLink()

        Glide.with(this)
            .load(R.drawable.me_bw)
            .transition(DrawableTransitionOptions.withCrossFade())
            .fitCenter()
            .circleCrop()
            .into(binding.ivProfile)
    }

    private fun setupActivityLink() {
        val linkTextView = findViewById<TextView>(R.id.tv_github)
        linkTextView.setOnClickListener {
            val browserIntent =
                Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/NeoRevolt"))
            startActivity(browserIntent)
        }
    }
}