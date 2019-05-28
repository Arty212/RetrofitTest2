package com.example.retrofittest

data class Meteo(var date: String, var tod: String, var temp: String) {

    override fun toString(): String {
        return "$date | $tod | $temp"
    }
}