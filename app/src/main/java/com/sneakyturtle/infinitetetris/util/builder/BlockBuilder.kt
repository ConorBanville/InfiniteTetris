package com.sneakyturtle.infinitetetris.util.builder

import com.sneakyturtle.infinitetetris.game.Board
import com.sneakyturtle.infinitetetris.game.block.Block
import com.sneakyturtle.infinitetetris.game.block.IBlock
import com.sneakyturtle.infinitetetris.game.block.JBlock
import com.sneakyturtle.infinitetetris.game.block.LBlock
import com.sneakyturtle.infinitetetris.game.block.OBlock
import com.sneakyturtle.infinitetetris.game.block.SBlock
import com.sneakyturtle.infinitetetris.game.block.TBlock
import com.sneakyturtle.infinitetetris.game.block.ZBlock
import com.sneakyturtle.infinitetetris.util.handler.MotionHandler
import java.util.Objects

class BlockBuilder {

    private var type: Block.Type? = null
    private var block: Block? = null
    private var board: Board? = null
    private var blocks: ArrayList<Block>? = null
    private var method: (() -> Boolean)? = null
    private var handler: MotionHandler? = null

    companion object {
        fun getBlockHelper(): BlockBuilder {
            return BlockBuilder()
        }
    }

    fun withType(type: Block.Type): BlockBuilder {
        this.type = type

        return this
    }

    fun withRandom(): BlockBuilder {
        this.type = Block.Type.values().random()

        return this
    }

    fun withBlock(block: Block): BlockBuilder {
        this.block = block

        return this
    }

    fun withBoard(board: Board): BlockBuilder {
        this.board = board

        return this
    }

    fun withHandler(handler: MotionHandler): BlockBuilder {
        this.handler = handler

        return this
    }

    fun withBlocks(blocks: ArrayList<Block>): BlockBuilder {
        this.blocks = blocks

        return this
    }

    fun withMethod(method: () -> Boolean): BlockBuilder {
        this.method = method

        return this
    }

    fun build(): Boolean {
        if(type == null) {
            this.type = Block.Type.values().random()
        }

        if(block == null) {
            this.block = getBlock(this.type!!)
        }

        if(handler != null) {
            handler!!.activeBlock = block as Block
        }

        if(blocks != null) {
            blocks!!.add(block!!)
            block!!.activeBlocks = blocks as ArrayList<Block>
        }

        if(method != null) {
            block!!.spawnMethod = method as () -> Boolean
        }

        return true
    }

    private fun getBlock(type: Block.Type): Block {
        return when(type) {
            Block.Type.I_BLOCK -> IBlock()
            Block.Type.J_BLOCK -> JBlock()
            Block.Type.L_BLOCK -> LBlock()
            Block.Type.O_BLOCK -> OBlock()
            Block.Type.S_BLOCK -> SBlock()
            Block.Type.T_BLOCK -> TBlock()
            Block.Type.Z_BLOCK -> ZBlock()
        }
    }
}