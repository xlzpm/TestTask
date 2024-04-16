package com.example.testtask.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Token (
    @SerialName("express_in")
    val expressIn: Int?,
    @SerialName("access_token")
    val accessToken: String?
)
