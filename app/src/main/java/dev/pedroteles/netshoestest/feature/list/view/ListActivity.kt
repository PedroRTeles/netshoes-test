package dev.pedroteles.netshoestest.feature.list.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import dev.pedroteles.netshoestest.R
import dev.pedroteles.netshoestest.feature.list.viewmodel.ListViewModel
import dev.pedroteles.netshoestest.model.Gist

class ListActivity : AppCompatActivity() {
    private val viewModel = ListViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.getList(0).observe(this, Observer<List<Gist>> { list ->
            println("onChanged")
            println(list[0].url)
        })
    }
}
