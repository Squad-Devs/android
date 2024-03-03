package com.shdwraze.metro.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Line(
    val id: Int = 0,
    val name: String = "",
    val color: Int = 0
)
