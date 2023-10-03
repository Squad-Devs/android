package com.shdwraze.metro.network

import kotlinx.serialization.Serializable

@Serializable
data class Station(
    val id: String,
    val name: String,
    val line: String,
    val city: String,
    val nextStationId: String?,
    val prevStationId: String?,
    val transferTo: String?
)
