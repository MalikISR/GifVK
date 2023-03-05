package com.malik_isr.gifvk.`object`

import com.malik_isr.gifvk.ApiException
import com.malik_isr.gifvk.Gif
import retrofit2.Response

object GiphyRepository {
    private const val API_KEY = "MnfRkxlRAki7w6e0bn6mFVWxeZAFfJ1s"

    private val apiClient = GiphyApiClient

    suspend fun searchGifs(query: String, limit: Int, offset: Int): List<Gif> {
        val objects = apiClient.searchGifs(API_KEY, query, limit, offset)


        val gifList = mutableListOf<Gif>()

        for (i in 0 until objects.length()) {
            val gifJson = objects.getJSONObject(i)
            val gif = Gif.fromJson(gifJson)
            gifList.add(gif)
            println(gif.title)
        }

        val response:Response<List<Gif>> = Response.success(gifList)
        return if (response.isSuccessful) {
            response.body() ?: emptyList()
        } else {
            throw ApiException(response.code(), response.message())
        }
    }
}