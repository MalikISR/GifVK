package com.malik_isr.gifvk.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.malik_isr.gifvk.*
import com.malik_isr.gifvk.`object`.GiphyRepository
import com.malik_isr.gifvk.adapter.GifListAdapter
import com.malik_isr.gifvk.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var adapter: GifListAdapter

    private var gifs: MutableList<Gif> = mutableListOf()

    private var isLoading = false
    private var offset = 0
    private var query = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = GifListAdapter(gifs) { gif ->
            val intent = Intent(this, GifDetailsActivity::class.java)
            intent.putExtra(GifDetailsActivity.EXTRA_GIF, gif)
            startActivity(intent)
        }
        binding.recyclerView.adapter = adapter

        binding.searchButton.setOnClickListener {
            val newQuery = binding.searchEditText.text.toString().trim()
            if (newQuery != query) {
                query = newQuery
                gifs.clear()
                adapter.notifyDataSetChanged()
                offset = 0
                searchGifs()
            }
        }

        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 0) {
                    val layoutManager = binding.recyclerView.layoutManager as LinearLayoutManager
                    val lastVisibleItem = layoutManager.findLastVisibleItemPosition()
                    if (!isLoading && lastVisibleItem == gifs.size - 1) {
                        offset += 25
                        searchGifs()
                    }
                }
            }
        })
    }

    private fun searchGifs() {
        isLoading = true
        GlobalScope.launch(Dispatchers.Main) {
            try {
                val newGifs = withContext(Dispatchers.IO) {
                    GiphyRepository.searchGifs(query, 25, offset)
                }
                gifs.addAll(newGifs)
                adapter.notifyDataSetChanged()
            } catch (e: ApiException) {
                Toast.makeText(this@MainActivity, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
            }
            isLoading = false
        }
    }
}