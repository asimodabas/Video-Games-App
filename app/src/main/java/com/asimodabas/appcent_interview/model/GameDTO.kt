package com.asimodabas.appcent_interview.model

import com.google.gson.annotations.SerializedName

data class GameDTO(
    @SerializedName("results") val results: List<Result>?
)