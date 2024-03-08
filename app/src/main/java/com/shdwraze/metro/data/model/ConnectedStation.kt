package com.shdwraze.metro.data.model

import kotlinx.serialization.Serializable

@Serializable
data class ConnectedStation(
    val id: Int,
    val name: String,
    val line: Line
)
