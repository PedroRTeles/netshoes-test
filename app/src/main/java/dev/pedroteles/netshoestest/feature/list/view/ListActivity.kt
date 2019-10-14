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
    private var currentPage = 0
    private var isLoading = false
    private lateinit var observer: Observer<List<Gist>>
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var adapter: GistAdapter
    private lateinit var rcvGists: RecyclerView
    private lateinit var pgbList: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        findViews()
        setupRecyclerView()
        setupObserver()
        requestList()
    }

    private fun requestList() {
        if(!isLoading) {
            isLoading = true
            viewModel.getList(currentPage).observe(this, observer)
        }
    }

    private fun setupObserver() {
        observer = Observer {
            showList()
            isLoading = false
            currentPage++
            handleResult(it)
        }
    }

    private fun handleResult(list: List<Gist>) {
        gistList.addAll(list)
        adapter.notifyDataSetChanged()
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

    private fun setupRecyclerView() {
        layoutManager = LinearLayoutManager(this)
        adapter = GistAdapter(gistList, this, this)

        rcvGists.layoutManager = layoutManager
        rcvGists.adapter = adapter

        rcvGists.addOnScrollListener(object: RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val visibleItemCount = layoutManager.childCount
                val pastVisibleItem = layoutManager.findFirstCompletelyVisibleItemPosition()
                val total = adapter.itemCount

                if(visibleItemCount + pastVisibleItem > total) {
                    requestList()
                }
            }
        })
    }
}
