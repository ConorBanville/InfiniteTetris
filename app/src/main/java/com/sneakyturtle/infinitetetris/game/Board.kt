package com.sneakyturtle.infinitetetris.game

import android.content.Context
import android.view.MotionEvent
import android.view.View
import androidx.core.content.ContextCompat
import com.sneakyturtle.infinitetetris.R
import com.sneakyturtle.infinitetetris.game.block.Block
import com.sneakyturtle.infinitetetris.util.handler.GameHandler
import com.sneakyturtle.infinitetetris.util.builder.BlockBuilder
import com.sneakyturtle.infinitetetris.util.handler.BlockHandler
import com.sneakyturtle.infinitetetris.util.handler.MotionHandler

class Board(private val context: Context, private var gridViews: Array<Array<View?>>) {
    private var blockHandler = BlockHandler()
    private var motionHandler = MotionHandler(blockHandler)

    private var blocks: ArrayList<Block> = arrayListOf()
    private var lastUpdate = 0L


    fun onTouchEvent(event: MotionEvent) {
        motionHandler.onTouchEvent(event)
    }

    fun update() {
        if (GameHandler.isUpdateReady(lastUpdate)) {
            for (block in blocks) {
                blockHandler.moveDown(block)
            }
            lastUpdate = System.currentTimeMillis()
        }
        drawBlocks()
    }

    fun spawnBlock(): Boolean {
        return BlockBuilder.getBlockHelper()
            .withType(Block.Type.J_BLOCK)
            .withBoard(this)
            .withBlocks(blocks)
            .withHandler(motionHandler)
            .withMethod { spawnBlock() }
            .build()
    }

    private fun drawBlocks() {
        for(j in gridViews.indices) {
            for(i in 0 until gridViews[j].size) {
                gridViews[j][i]!!.setBackgroundColor(getCellColor(i, j))
            }
        }
    }

    private fun getCellColor(i: Int, j: Int): Int {
        for(block in blocks) {
            val cell = block.getCell(i, j)
            if (cell != null && cell.display) {
                return block.color
            }
        }
        return ContextCompat.getColor(context, R.color.white)
    }
}