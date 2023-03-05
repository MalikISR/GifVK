package com.malik_isr.gifvk.`object`

import com.malik_isr.gifvk.data.GiphyApi
import okhttp3.OkHttpClient
import org.json.JSONArray
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object GiphyApiClient {
    private const val BASE_URL = "https://api.giphy.com/v1/"

    private val client = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val request = chain.request().newBuilder()
                .build()
            chain.proceed(request)
        }
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val api = retrofit.create(GiphyApi::class.java)

    suspend fun searchGifs(apiKey: String, query: String, limit: Int, offset: Int): JSONArray {
        return api.searchGifs(apiKey, query, limit, offset)
    }
}