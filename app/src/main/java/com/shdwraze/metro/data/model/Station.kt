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
    val transferTo: ShortStationInfo? = null
)

@Serializable
data class ShortStationInfo(
    val id: String,
    val name: String
)