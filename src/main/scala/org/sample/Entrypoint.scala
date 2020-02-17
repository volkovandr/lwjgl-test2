package org.sample

object Entrypoint extends App {
    
    val (errorCallback, window) = GLInit.init()
    val callback = new KeyCallback

    MainLoop.setup(window, callback)
    GLInit.printCaps()

    println("Strarted...")
    
    try {
        MainLoop.enterLoop()
    } catch {
        case ex: Throwable => 
            println(s"Exception in the main loop: ${ex.getMessage()}")
            ex.printStackTrace()
    } finally {
        MainLoop.cleanup()
        GLInit.tearDown(window, callback, errorCallback)
    }
    
    println("Terminated...")
}
