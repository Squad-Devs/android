package com.shdwraze.metro.data.model

import kotlinx.serialization.Serializable

@Serializable
data class MetroLine(
    val name: String,
    val color: Int,
    val stations: List<Station>
)
