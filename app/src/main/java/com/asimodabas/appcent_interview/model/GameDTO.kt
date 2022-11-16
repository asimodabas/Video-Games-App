package com.asimodabas.appcent_interview.model


import com.google.gson.annotations.SerializedName

data class GameDTO(
    @SerializedName("count")
    val count: Int,
    @SerializedName("next")
    val next: String,
    @SerializedName("previous")
    val previous: Any,
    @SerializedName("results")
    val results: List<Result>,
    @SerializedName("user_platforms")
    val userPlatforms: Boolean
)