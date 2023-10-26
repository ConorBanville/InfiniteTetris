package com.sneakyturtle.infinitetetris.game.block

import com.sneakyturtle.infinitetetris.game.Cell

abstract class Block : CommandBlock() {
    var x: Int = 0
    var y: Int = 0
    var isFalling = true
    val cells: ArrayList<Cell> = arrayListOf()
    lateinit var activeBlocks: ArrayList<Block>

    abstract var color: Int
    abstract var index: Int
    abstract var forms: Array<Array<Array<Int>>>

    fun init() {
        refreshForm()
        setOrigin()
    }

    fun getCell(x: Int, y: Int): Cell? {
        for (cell in cells) {
            if (this.x + cell.x == x && this.y + cell.y == y) {
                return cell
            }
        }
        return null
    }

    private fun addCell(cell: Cell) {
        cells.add(cell)
    }

    fun refreshForm() {
        val form = forms[index]

        cells.clear()
        for(i in forms.indices) {
            addCell(Cell(form[i][0], form[i][1]))
        }
    }

    private fun setOrigin() {
        x = cells[0].x
        y = cells[0].y
    }

    enum class Type {
        I_BLOCK,
        J_BLOCK,
        L_BLOCK,
        O_BLOCK,
        S_BLOCK,
        T_BLOCK,
        Z_BLOCK
    }
}