package dev.pedroteles.netshoestest.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Gist(
    @SerializedName("url")
    val url: String,
    @SerializedName("owner")
    val owner: Owner,
    @SerializedName("description")
    val description: String
) : Serializable