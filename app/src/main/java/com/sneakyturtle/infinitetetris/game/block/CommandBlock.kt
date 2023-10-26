package com.sneakyturtle.infinitetetris.game.block

abstract class CommandBlock {
    // Enable blocks to recursively spawn another block
    lateinit var spawnMethod: (() -> Boolean)
}