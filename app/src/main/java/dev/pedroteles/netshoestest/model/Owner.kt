package dev.pedroteles.netshoestest.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Owner(
    @SerializedName("login")
    val login: String = "",
    @SerializedName("avatar_url")
    val avatarUrl: String = ""
) : Serializable