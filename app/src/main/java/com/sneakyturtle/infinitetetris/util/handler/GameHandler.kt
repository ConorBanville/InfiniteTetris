package com.sneakyturtle.infinitetetris.util.handler

class GameHandler {
    companion object {
        private var running: Boolean = false
        private var threshold: Long = 1000L

        fun run(running: Boolean) {
            this.running = running
        }

        fun gameOn(): Boolean {
            return running
        }

        fun speedUp() {
            threshold = 200L
        }

        fun reset() {
            threshold = 1000L
        }

        fun isUpdateReady(lastUpdate: Long): Boolean {
            return System.currentTimeMillis() - lastUpdate > threshold
        }
    }
}