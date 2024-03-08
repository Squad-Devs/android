package com.shdwraze.metro.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Exit(
    val exitNumber: Int,
    val latitude: Float,
    val longitude: Float
)
