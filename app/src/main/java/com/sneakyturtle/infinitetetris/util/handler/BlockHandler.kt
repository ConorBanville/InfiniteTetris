package com.sneakyturtle.infinitetetris.util.handler

import com.sneakyturtle.infinitetetris.game.block.Block

class BlockHandler {

    fun moveDown(block: Block) {
        if(block.isFalling) {
            var canMove = true
            val otherBlocks = block.activeBlocks.toMutableList()
            otherBlocks.remove(block)

            for(b in otherBlocks) {
                for(c in b.cells) {
                    if(block.getCell(b.x + c.x, b.y + c.y - 1) != null) {
                        canMove = false
                        break
                    }
                }
            }

            if(canMove && block.y < 19) {
                block.y ++
            } else {
                block.isFalling = false
                block.spawnMethod.invoke()
            }
        }
    }

    fun moveLeft(block: Block) {
        if(block.isFalling) {
            var canMove = true
            for (cell in block.cells) {
                if(block.x + cell.x <= 0) {
                    canMove = false
                }
            }

            if(canMove) {
                block.x --
            }
        }
    }

    fun moveRight(block: Block) {
        if(block.isFalling) {
            var canMove = true
            for (cell in block.cells) {
                if(block.x + cell.x >= 9) {
                    canMove = false
                }
            }

            if(canMove) {
                block.x ++
            }
        }
    }

    fun rotateLeft(block: Block) {
        if(block.index > 0) block.index --
        else block.index = block.forms.size - 1

        block.refreshForm()
    }

    fun rotateRight(block: Block) {
        if(block.index < block.forms.size - 1) block.index ++
        else block.index = 0

        block.refreshForm()
    }
}