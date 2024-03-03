package com.shdwraze.metro.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Connection(
    val fromStation: ConnectedStation,
    val toStation: ConnectedStation,
    val type: String
)
