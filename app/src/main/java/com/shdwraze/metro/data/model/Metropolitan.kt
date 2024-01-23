package com.shdwraze.metro.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Metropolitan(
    val city: String = "",
    val lines: List<MetroLine> = listOf()
)
