package com.example.android.searchdebounce.networking

import com.example.android.searchdebounce.data.PhotosSearchResponse
import retrofit2.http.GET

interface ApiService {
    @GET("?method=flickr.photos.search&api_key=3e7cc266ae2b0e0d78e279ce8e361736&format=json&nojsoncallback=1&safe_search=1&text=kittens")
    suspend fun fetchImages(): PhotosSearchResponse
}
