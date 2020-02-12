package org.sample

import org.lwjgl.glfw.GLFWKeyCallback
import org.lwjgl.glfw.GLFW.glfwSetWindowShouldClose

import org.lwjgl.glfw.GLFW.GLFW_KEY_ESCAPE
import org.lwjgl.glfw.GLFW.GLFW_RELEASE


class KeyCallback extends GLFWKeyCallback {
    override def invoke(window: Types.WindowHandle, key: Int, scancode: Int, action: Int, mods: Int): Unit = {
        println(s"Key: [$key], action: [$action], mods: [$mods]")
        if(key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE) {
            glfwSetWindowShouldClose(window, true)
        }
    }
}