package org.sample

trait FPSMeter {
    val fpsSampleInterval = 5000L

    private var frames1: Int = 0
    private var frames2: Int = 0
    private var timing1: Long = 0
    private var timing2: Long = 0

    def measureFPS(): Unit = {
        val time = System.currentTimeMillis()
        if(timing1 == 0L) {
            timing1 = time
        } else {
            frames1 += 1
            val timeElapsed = time - timing1
            if(timeElapsed >= fpsSampleInterval + fpsSampleInterval) {
                println(s"FPS: ${frames1 / (fpsSampleInterval / 500)}")
                timing1 = time
                frames1 = 0
            }
        }
        if(timing2 == 0L && time - timing1 >= fpsSampleInterval) {
            timing2 = time
        } else if(timing2 != 0L) {
            frames2 += 1
            val timeElapsed = time - timing2
            if(timeElapsed >= fpsSampleInterval + fpsSampleInterval) {
                println(s"FPS: ${frames2 / (fpsSampleInterval / 500)}")
                timing2 = time
                frames2 = 0
            }
        }
    }
}