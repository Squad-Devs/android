package com.shdwraze.metro.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Station(
    val id: String = "",
    val name: String = "",
    val line: String = "",
    val city: String = "",
    val nextStation: ShortStationInfo? = null,
    val prevStation: ShortStationInfo? = null,
    val transferTo: ShortStationInfo? = null,
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
    val exits: List<Exit> = listOf()
)

@Serializable
data class ShortStationInfo(
    val id: String,
    val name: String
)

@Serializable
data class Exit(
    val exitNumber: Int,
    val latitude: Float,
    val longitude: Float
)