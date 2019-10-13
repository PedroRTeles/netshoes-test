package dev.pedroteles.netshoestest.data

import dev.pedroteles.netshoestest.model.Gist
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GistService {
    @GET("gists/public")
    fun list(@Query("page") page: Int) : Call<List<Gist>>
}