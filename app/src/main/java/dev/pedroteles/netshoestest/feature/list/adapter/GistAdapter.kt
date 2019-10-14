package dev.pedroteles.netshoestest.feature.list.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import dev.pedroteles.netshoestest.R
import dev.pedroteles.netshoestest.`interface`.RecyclerViewClickListener
import dev.pedroteles.netshoestest.data.DownloadImageTask
import dev.pedroteles.netshoestest.model.Gist
import kotlinx.android.synthetic.main.list_layout.view.*

class GistAdapter(private val gists: List<Gist>,
                  private val context: Context,
                  private val listener: RecyclerViewClickListener) : RecyclerView.Adapter<GistAdapter.GistViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GistViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_layout, parent, false)
        return GistViewHolder(view)
    }

    override fun getItemCount(): Int {
        return gists.size
    }

    override fun onBindViewHolder(holder: GistViewHolder, position: Int) {
        val gist = gists[position]
        holder.txtOwnerName.text = gist.owner.login
        DownloadImageTask(holder.imgGist).execute(gist.owner.avatarUrl)

        holder.cstItem.setOnClickListener {
            listener.recyclerViewListClicked(it, position)
        }
    }

    class GistViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cstItem: ConstraintLayout = itemView.cstItem
        val imgGist: ImageView = itemView.imgGist
        val txtOwnerName: TextView = itemView.txtOwnerName
    }
}