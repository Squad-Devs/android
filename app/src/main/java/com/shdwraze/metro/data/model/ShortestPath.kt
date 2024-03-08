package com.shdwraze.metro.data.model

import kotlinx.serialization.Serializable

@Serializable
data class ShortestPath(
    val transfersNumber: Int = 0,
    val travelTimeInMinutes: Int = 0,
    val path: List<Station> = emptyList()
)
