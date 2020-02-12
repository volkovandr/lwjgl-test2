package org.sample

object Entrypoint extends App {
    
    val (errorCallback, window) = GLInit.init()
    val callback = new KeyCallback

    MainLoop.setup(window, callback)
    GLInit.printCaps()

    println("Strarted...")
    
    MainLoop.enterLoop()
    GLInit.tearDown(window, callback, errorCallback)
    
    println("Terminated...")
}
