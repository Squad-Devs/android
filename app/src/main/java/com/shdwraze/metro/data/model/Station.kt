package com.shdwraze.metro.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Station(
    val id: Int = 0,
    val name: String = "",
    val line: Line = Line(),
    val city: String = "",
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
    val exits: List<Exit> = listOf(),
    val connections: List<Connection> = listOf()
)