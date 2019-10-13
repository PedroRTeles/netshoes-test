package dev.pedroteles.netshoestest.feature.list.viewmodel

import androidx.lifecycle.LiveData
import dev.pedroteles.netshoestest.data.repository.GistRepository
import dev.pedroteles.netshoestest.model.Gist

class ListViewModel {
    private val repository = GistRepository()

    fun getList(page: Int) : LiveData<List<Gist>> {
        return repository.listGists(page)
    }
}