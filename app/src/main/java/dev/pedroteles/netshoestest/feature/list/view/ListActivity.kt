package dev.pedroteles.netshoestest.feature.list.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dev.pedroteles.netshoestest.R
import dev.pedroteles.netshoestest.`interface`.RecyclerViewClickListener
import dev.pedroteles.netshoestest.feature.detail.view.DetailActivity
import dev.pedroteles.netshoestest.feature.list.adapter.GistAdapter
import dev.pedroteles.netshoestest.feature.list.viewmodel.ListViewModel
import dev.pedroteles.netshoestest.model.Gist

class ListActivity : AppCompatActivity(), RecyclerViewClickListener {

    private val viewModel = ListViewModel()
    private val gistList: MutableList<Gist> = mutableListOf()
    private lateinit var rcvGists: RecyclerView
    private lateinit var pgbList: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        findViews()

        viewModel.getList(0).observe(this, Observer<List<Gist>> { list ->
            showList()
            gistList.addAll(list)
            setupRecyclerView(list)
        })
    }

    private fun showList() {
        pgbList.visibility = View.GONE
        rcvGists.visibility = View.VISIBLE
    }

    override fun recyclerViewListClicked(view: View, position: Int) {
        val gist = gistList.get(position)
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("gist", gist)

        startActivity(intent)
    }

    private fun findViews() {
        rcvGists = findViewById(R.id.rcvGists)
        pgbList = findViewById(R.id.pgbList)
    }

    private fun setupRecyclerView(list: List<Gist>) {
        rcvGists.layoutManager = LinearLayoutManager(this)
        rcvGists.adapter = GistAdapter(list, this, this)
    }
}
