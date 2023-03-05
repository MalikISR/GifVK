package com.malik_isr.gifvk.data


import org.json.JSONArray
import retrofit2.http.GET
import retrofit2.http.Query

interface GiphyApi {

    @GET("gifs/search")
    suspend fun searchGifs(
        @Query("api_key") apiKey: String,
        @Query("q") query: String,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): JSONArray
}
