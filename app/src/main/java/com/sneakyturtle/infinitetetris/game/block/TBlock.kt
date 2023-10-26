package com.sneakyturtle.infinitetetris.game.block

import com.sneakyturtle.infinitetetris.R

class TBlock: Block() {
    var type: Type = Type.J_BLOCK
    override var color = R.color.j_block
    override var index = 0
    override var forms: Array<Array<Array<Int>>> = arrayOf(
        arrayOf(arrayOf(0, 0), arrayOf(0, -1), arrayOf(1, 0), arrayOf(2, 0)),
        arrayOf(arrayOf(0, 0), arrayOf(-1, 0), arrayOf(-1, 1), arrayOf(-1, 2)),
        arrayOf(arrayOf(0, 0), arrayOf(0, -1), arrayOf(0, -1), arrayOf(0, -1)),
        arrayOf(arrayOf(0, 0), arrayOf(-1, 0), arrayOf(0, -1), arrayOf(0, -2))
    )
}