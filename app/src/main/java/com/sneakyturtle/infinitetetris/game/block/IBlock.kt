package com.sneakyturtle.infinitetetris.game.block

import com.sneakyturtle.infinitetetris.R

class IBlock : Block() {
    var type: Type = Type.I_BLOCK
    override var color = R.color.i_block
    override var index = 0
    override var forms: Array<Array<Array<Int>>> = arrayOf(
        arrayOf(arrayOf(0, 0), arrayOf(1, 0), arrayOf(2, 0), arrayOf(3, 0)),
        arrayOf(arrayOf(0, 0), arrayOf(0, 1), arrayOf(0, 2), arrayOf(0, 3)),
        arrayOf(arrayOf(0, 0), arrayOf(-1, 0), arrayOf(-2, 0), arrayOf(-3, 0)),
        arrayOf(arrayOf(0, 0), arrayOf(0, 1), arrayOf(0, 2), arrayOf(0, 3))
    )
}