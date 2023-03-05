package com.malik_isr.gifvk

import org.json.JSONObject

data class Gif(
    val id: String,
    val title: String,
    val images: Images
):java.io.Serializable {
    companion object {
        fun fromJson(jsonObject: JSONObject): Gif {
            val id = jsonObject.getString("id")
            val title = jsonObject.getString("title")
            val images = Images.fromJson(jsonObject.getJSONObject("images"))
            println("$id $title")
            return Gif(id, title, images)
        }
    }
}

data class Images(
    val original: Image
) {
    companion object {
        fun fromJson(jsonObject: JSONObject): Images {
            val original = Image.fromJson(jsonObject.getJSONObject("original"))
            return Images(original)
        }
    }
}

data class Image(
    val url: String
) {
    companion object {
        fun fromJson(jsonObject: JSONObject): Image {
            val url = jsonObject.getString("url")
            return Image(url)
        }
    }
}