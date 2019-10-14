package dev.pedroteles.netshoestest.feature.detail.view

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import dev.pedroteles.netshoestest.R
import dev.pedroteles.netshoestest.data.DownloadImageTask
import dev.pedroteles.netshoestest.model.Gist

class DetailActivity : AppCompatActivity() {
    private lateinit var imgAvatar: ImageView
    private lateinit var txtName: TextView
    private lateinit var txtDescription: TextView
    private lateinit var gist: Gist

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        findViews()
        getExtra()
        setViews()
    }

    private fun setViews() {
        if(gist.description.isEmpty()) {
            txtDescription.text = "Sem descrição"
        } else {
            txtDescription.text = gist.description
        }

        txtName.text = gist.owner.login
        DownloadImageTask(imgAvatar).execute(gist.owner.avatarUrl)
    }

    private fun getExtra() {
        gist = intent.extras["gist"] as Gist
    }

    private fun findViews() {
        imgAvatar = findViewById(R.id.imgAvatar)
        txtName = findViewById(R.id.txtName)
        txtDescription = findViewById(R.id.txtDescription)
    }
}
