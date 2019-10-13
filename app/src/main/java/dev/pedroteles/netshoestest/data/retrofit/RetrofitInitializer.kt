package dev.pedroteles.netshoestest.data.retrofit

import dev.pedroteles.netshoestest.data.GistService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RetrofitInitializer {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.github.com/")
        .client(OkHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun gistService() = retrofit.create(GistService::class.java)
}

