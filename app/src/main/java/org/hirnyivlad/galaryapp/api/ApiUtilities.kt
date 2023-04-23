package org.hirnyivlad.galaryapp.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiUtilities {
    companion object{
         private const val BASE_URL : String = "https://api.unsplash.com"
        const val API_KEY : String = "GRYpbyvYvUQrpAYzwrvyk5mh7AWfpiNpqvCpJGifE8I"
        private var retrofit: Retrofit? = null

        fun getApiInterface(): ApiInterface{
            if(retrofit ==null){
                retrofit = Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit!!.create(ApiInterface::class.java)
        }
    }
}