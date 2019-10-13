package dev.pedroteles.netshoestest.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dev.pedroteles.netshoestest.data.retrofit.RetrofitInitializer
import dev.pedroteles.netshoestest.model.Gist
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GistRepository {
    private val gistList: MutableLiveData<List<Gist>> = MutableLiveData()

    fun listGists(page: Int) : LiveData<List<Gist>> {
        val call = RetrofitInitializer().gistService().list(page)

        call.enqueue(object: Callback<List<Gist>> {
            override fun onResponse(call: Call<List<Gist>>, response: Response<List<Gist>>) {
                gistList.postValue(response.body())
            }

            override fun onFailure(call: Call<List<Gist>>, t: Throwable) {
                println("Method: listGists - Callback: onFailure")
                println(t.message)
            }

        })
        return gistList
    }
}