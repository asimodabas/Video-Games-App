package com.asimodabas.appcent_interview.model

import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class Detail(
    @PrimaryKey
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("released")
    val released: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("metacritic")
    val metacritic: String?,
)