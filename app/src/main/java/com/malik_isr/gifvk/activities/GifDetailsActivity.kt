package com.malik_isr.gifvk.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.malik_isr.gifvk.Gif
import com.malik_isr.gifvk.databinding.ActivityGifDetailsBinding

class GifDetailsActivity: AppCompatActivity() {
    companion object {
        const val EXTRA_GIF = "extra_gif"
    }

    private lateinit var binding: ActivityGifDetailsBinding

    private lateinit var gif: Gif

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGifDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        gif = intent.getSerializableExtra(EXTRA_GIF) as Gif

        Glide.with(this)
            .load(gif.images.original.url)
            .into(binding.imageView)

        binding.titleView.text = gif.title
        binding.idView.text = gif.id
    }
}