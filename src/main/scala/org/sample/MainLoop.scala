package org.sample

import Types.WindowHandle

import org.lwjgl.glfw.GLFW.glfwWindowShouldClose
import org.lwjgl.glfw.GLFW.glfwPollEvents
import org.lwjgl.glfw.GLFW.glfwSwapBuffers
import org.lwjgl.glfw.GLFW.glfwSetKeyCallback
import org.lwjgl.glfw.GLFW.glfwMakeContextCurrent
import org.lwjgl.opengl.GL

object MainLoop {
    
    var window: WindowHandle = _
    var callback: KeyCallback = _
    
    def setup(window: WindowHandle, callback: KeyCallback): Unit = {
        this.window = window
        this.callback = callback
        glfwSetKeyCallback(window, callback)
        glfwMakeContextCurrent(window)
        GL.createCapabilities()
    }

    def enterLoop(): Unit = {
        while(!glfwWindowShouldClose(window)) {
            glfwSwapBuffers(window)
            glfwPollEvents()
            Thread.sleep(100)
        }
    }
}