package com.asimodabas.appcent_interview.model

import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("id") val id: Int?,
    @SerializedName("name") val name: String?,
    @SerializedName("released") val released: String?,
    @SerializedName("rating") val rating: Double?,
    @SerializedName("background_image") val imageUrl: String?
)