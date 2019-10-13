package dev.pedroteles.netshoestest.feature.list.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dev.pedroteles.netshoestest.R
import dev.pedroteles.netshoestest.feature.list.adapter.GistAdapter
import dev.pedroteles.netshoestest.feature.list.viewmodel.ListViewModel
import dev.pedroteles.netshoestest.model.Gist

class ListActivity : AppCompatActivity() {
    private val viewModel = ListViewModel()
    private lateinit var rcvGists: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        findViews()

        viewModel.getList(0).observe(this, Observer<List<Gist>> { list ->
            setupRecyclerView(list)
        })
    }

    private fun findViews() {
        rcvGists = findViewById(R.id.rcvGists)
    }

    private fun setupRecyclerView(list: List<Gist>) {
        rcvGists.layoutManager = LinearLayoutManager(this)
        rcvGists.adapter = GistAdapter(list, this)
    }
}
