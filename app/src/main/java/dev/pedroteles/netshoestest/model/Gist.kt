package dev.pedroteles.netshoestest.model

import com.google.gson.annotations.SerializedName

data class Gist(
    @SerializedName("url")
    val url: String,
    @SerializedName("owner")
    val owner: Owner
)