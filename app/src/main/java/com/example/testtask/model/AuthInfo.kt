package com.example.testtask.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AuthInfo(
    @SerialName("grand_type")
    val grantType: String = "client_credentials",
    @SerialName("client_id")
    val clientId: String,
    @SerialName("client_secret")
    val clientSecret: String
)