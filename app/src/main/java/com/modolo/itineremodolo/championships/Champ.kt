package com.modolo.itineremodolo.championships

data class Champ(
    var ID: Int,
    var nome: String,
    var logo: String,
    var races: Int,
    var racers: Int,
    var registered: Boolean
)