package org.hirnyivlad.galaryapp.api

import org.hirnyivlad.galaryapp.api.ApiUtilities.Companion.API_KEY
import org.hirnyivlad.galaryapp.model.Image
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiInterface {
    @Headers("Authorization: Client-ID $API_KEY")
    @GET("/photos")
    fun getImages(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): Call<List<Image>>
}