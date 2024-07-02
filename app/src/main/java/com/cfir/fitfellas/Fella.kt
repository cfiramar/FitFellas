package com.cfir.fitfellas

data class Fella(
    val name: String,
    val level: Int,
    val experience: Int,
    val evolutionStage: Int,
    val imageResourceId: Int
)

sealed class FellaEvolution {
    data class Fitling(val level: Int, val experience: Int) : FellaEvolution()
    data class Fitmore(val level: Int, val experience: Int) : FellaEvolution()
    data class Fitmaster(val level: Int, val experience: Int) : FellaEvolution()

    data class Liftlet(val level: Int, val experience: Int) : FellaEvolution()
    data class Liftmore(val level: Int, val experience: Int) : FellaEvolution()
    data class Liftking(val level: Int, val experience: Int) : FellaEvolution()
}