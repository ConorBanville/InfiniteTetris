package com.sneakyturtle.infinitetetris.util.handler

import android.view.MotionEvent
import com.sneakyturtle.infinitetetris.game.block.Block
import kotlin.math.abs

class MotionHandler(private var blockHandler: BlockHandler) {

    private var x: Float = 0f
    private var y: Float = 0f
    private var evalEvent = true
    lateinit var activeBlock: Block

    fun onTouchEvent(event: MotionEvent) {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                x = event.x
                y = event.y
            }
            MotionEvent.ACTION_MOVE -> {
                if(!evalEvent) return
                evaluateEvent(event)
            }
            MotionEvent.ACTION_UP -> {
                evalEvent = true
                GameHandler.reset()
            }
        }
    }

    private fun evaluateEvent(event: MotionEvent) {
        val dX = event.x - x
        val dY = (event.y - y) * 0.5

        if (dY > 0 && abs(dX) <= abs(dY)) {
            moveActiveBlockDown()
            evalEvent = false
            return
        }

        evalEvent = when (dX < 0) {
            true -> {
                moveActiveBlockLeft()
                false
            }

            else -> {
                moveActiveBlockRight()
                false
            }
        }
    }

    private fun moveActiveBlockLeft() {
        blockHandler.moveLeft(activeBlock)
    }

    private fun moveActiveBlockRight() {
        blockHandler.moveRight(activeBlock)
    }

    private fun moveActiveBlockDown() {
        GameHandler.speedUp()
    }
}