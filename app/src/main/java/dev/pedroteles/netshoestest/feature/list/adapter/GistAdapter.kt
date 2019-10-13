package dev.pedroteles.netshoestest.feature.list.adapter

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import dev.pedroteles.netshoestest.R
import dev.pedroteles.netshoestest.model.Gist
import kotlinx.android.synthetic.main.list_layout.view.*

class GistAdapter(private val gists: List<Gist>,
                  private val context: Context) : RecyclerView.Adapter<GistAdapter.GistViewHolder>() {

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
    }

    class GistViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgGist: ImageView = itemView.imgGist
        val txtOwnerName: TextView = itemView.txtOwnerName
    }

    class DownloadImageTask(internal var bmImage: ImageView) : AsyncTask<String, Void, Bitmap>() {

        override fun doInBackground(vararg urls: String): Bitmap? {
            val urldisplay = urls[0]
            var mIcon11: Bitmap? = null
            try {
                val `in` = java.net.URL(urldisplay).openStream()
                mIcon11 = BitmapFactory.decodeStream(`in`)
            } catch (e: Exception) {
                Log.e("Error", e.message)
                e.printStackTrace()
            }

            return mIcon11
        }

        override fun onPostExecute(result: Bitmap) {
            bmImage.setImageBitmap(result)
        }
    }
}