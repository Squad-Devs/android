package com.shdwraze.metro.network

import kotlinx.serialization.Serializable

@Serializable
data class Station(
    val id: String,
    val name: String,
    val line: String,
    val city: String,
    val nextStation: ShortStationInfo?,
    val prevStation: ShortStationInfo?,
    val transferTo: ShortStationInfo?
)

@Serializable
data class ShortStationInfo(
    val id: String,
    val name: String
)