package org.sample

import Types.WindowHandle

import org.lwjgl.glfw.GLFW.glfwWindowShouldClose
import org.lwjgl.glfw.GLFW.glfwPollEvents
import org.lwjgl.glfw.GLFW.glfwSwapBuffers
import org.lwjgl.glfw.GLFW.glfwSetKeyCallback
import org.lwjgl.glfw.GLFW.glfwMakeContextCurrent
import org.lwjgl.glfw.GLFW.glfwSwapInterval
import org.lwjgl.opengl.GL
import org.ShaderProgram
import scala.io.Source

object MainLoop extends FPSMeter {

    var window: WindowHandle = _
    var callback: KeyCallback = _
    private var shaderProgram: Option[ShaderProgram] = None

    def setup(window: WindowHandle, callback: KeyCallback): Unit = {
        this.window = window
        this.callback = callback
        glfwSetKeyCallback(window, callback)
        glfwMakeContextCurrent(window)
        GL.createCapabilities()
        glfwSwapInterval(1)
        shaderProgram = Some(new ShaderProgram)
        shaderProgram.foreach(sp => {
            sp.createVertexChader(Source.fromResource("vertex.vs").getLines().mkString("\n"))
            sp.createFragmentShader(Source.fromResource("fragment.fs").getLines().mkString("\n"))
            sp.link()
        })
    }

    def enterLoop(): Unit = {
        while(!glfwWindowShouldClose(window)) {
            glfwSwapBuffers(window)
            glfwPollEvents()
            measureFPS()
        }
    }

    def cleanup(): Unit = {
        shaderProgram.foreach(_.cleanup())
    }
}