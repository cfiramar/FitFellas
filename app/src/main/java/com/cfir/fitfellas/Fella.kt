package com.cfir.fitfellas

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import kotlinx.coroutines.delay
import kotlin.random.Random

abstract class Fella(x_pos: Float, y_pos: Float) {
    open var level: Int = 1
    open var experience: Int = 0

    abstract val name: String
    abstract val drawableResourceId: Int
    abstract val evolutionLevel: Int
    abstract val evolution: Class<out Fella>?

    var size = Size(0f, 0f)
    open val sizeFactor = 1f

    var position by mutableStateOf((Offset(x_pos, y_pos)))
    var isFacingLeft by mutableStateOf(true)

    fun setSize(context: Context) {
        val w = context.resources.getDrawable(drawableResourceId, null).intrinsicWidth.toFloat()
        val h = context.resources.getDrawable(drawableResourceId, null).intrinsicHeight.toFloat()
        val density = context.resources.displayMetrics.density
        size = Size(w * sizeFactor / density, h * sizeFactor / density)
    }

    private fun getNewPosition(screenWidth: Float, screenHeight: Float): Pair<Float, Float> {
        val stepSize = 25
        val topBoundary = screenHeight * 0.1f

        val isXChange = Random.nextBoolean()
        val d = Random.nextInt(-1, 2) * stepSize
        val dx = if (isXChange) d else 0
        val dy = if (!isXChange) d else 0

        val newX = (position.x + dx).coerceIn(0f, screenWidth - size.width)
        val newY = (position.y + dy).coerceIn(topBoundary, screenHeight - size.height)

        isFacingLeft = dx < 0 || (dx == 0 && isFacingLeft)
        return Pair(newX, newY)
    }

    suspend fun move(screenWidth: Float, screenHeight: Float) {

        while (true) {
            delay(750)
            val (x, y) = getNewPosition(screenWidth, screenHeight)
            position = Offset(x, y)
        }
    }

    fun evolved(): Fella {
        val evolvedFella = this.evolution?.newInstance() ?: return this
        evolvedFella.level = this.level
        evolvedFella.experience = this.experience
        return evolvedFella
    }
}

class Fitling(x_pos: Float, y_pos: Float) : Fella(x_pos, y_pos) {
    override val name: String = "Fitling"
    override val drawableResourceId = R.drawable.fitling
    override val evolutionLevel = 8
    override val evolution = Fitmore::class.java
    override val sizeFactor = 0.8f
}

class Fitmore(x_pos: Float, y_pos: Float) : Fella(x_pos, y_pos) {
    override val name: String = "Fitmore"
    override val drawableResourceId = R.drawable.fitmore
    override val evolutionLevel = -1
    override val evolution = null
}