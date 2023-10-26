package com.sneakyturtle.infinitetetris.activity

import android.os.Build
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.GridLayout
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.sneakyturtle.infinitetetris.R
import com.sneakyturtle.infinitetetris.databinding.ActivityGameBinding
import com.sneakyturtle.infinitetetris.game.Board
import com.sneakyturtle.infinitetetris.util.handler.GameHandler

class Game : AppCompatActivity() {

    private lateinit var binding: ActivityGameBinding
    private lateinit var gridViews: Array<Array<View?>>
    private lateinit var board: Board


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindBoard()
        initGrid()
        startLoop()
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        board.onTouchEvent(event)
        return true
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun initGrid() {
        val gridLayout = findViewById<GridLayout>(R.id.gridLayout)
        gridLayout.setBackgroundColor(ContextCompat.getColor(this, R.color.black))

        fun set(view: View, params: GridLayout.LayoutParams, i: Int, j: Int) {
            view.setBackgroundColor(ContextCompat.getColor(this, R.color.white))

            params.width = 0
            params.height = 0
            params.rowSpec = GridLayout.spec(i, 1f)
            params.columnSpec = GridLayout.spec(j, 1f)
            params.setMargins(3, 3, 3, 3)

            gridLayout.addView(view, params)
            gridViews[i][j] = view
        }

        for(i in 0 until 20) {
            for(j in 0 until 10) {
                set(View(this), GridLayout.LayoutParams(), i, j)
            }
        }
    }

    private fun bindBoard() {
        binding = ActivityGameBinding.inflate(layoutInflater).also { setContentView(it.root) }
        gridViews = Array(20) { arrayOfNulls(10) }
        board = Board( this, gridViews)
    }

    private fun startLoop() {
        Thread {
            GameHandler.run(board.spawnBlock())
            while (GameHandler.gameOn()) {
                board.update()
            }
        }.start()
    }
}