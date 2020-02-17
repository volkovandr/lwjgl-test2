package org.sample

import org.lwjgl.system.MemoryUtil.NULL

import org.lwjgl.glfw.GLFW.GLFW_CONTEXT_VERSION_MAJOR
import org.lwjgl.glfw.GLFW.GLFW_CONTEXT_VERSION_MINOR
import org.lwjgl.glfw.GLFW.GLFW_OPENGL_PROFILE
import org.lwjgl.glfw.GLFW.GLFW_OPENGL_CORE_PROFILE
import org.lwjgl.glfw.GLFW.GLFW_OPENGL_FORWARD_COMPAT
import org.lwjgl.glfw.GLFW.GLFW_TRUE

import org.lwjgl.glfw.GLFW.glfwDestroyWindow
import org.lwjgl.glfw.GLFW.glfwTerminate
import org.lwjgl.glfw.GLFW.glfwSetErrorCallback
import org.lwjgl.glfw.GLFW.glfwInit
import org.lwjgl.glfw.GLFW.glfwCreateWindow
import org.lwjgl.glfw.GLFW.glfwDefaultWindowHints
import org.lwjgl.glfw.GLFW.glfwWindowHint

import org.lwjgl.glfw.GLFWErrorCallback

import Types.WindowHandle
import org.lwjgl.Version
import org.lwjgl.opengl.GL

object GLInit {
    
    def init(): (GLFWErrorCallback, WindowHandle) = {
        val errorCallback = GLFWErrorCallback.createPrint(System.err)
        glfwSetErrorCallback(errorCallback)
    
        if(!glfwInit()) {
            throw new IllegalStateException("Failed initializing glfw")
        }

        println(s"LWJGL version: ${Version.getVersion()}")

        glfwDefaultWindowHints()
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3)
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 2)
        // glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE)
        // glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GLFW_TRUE)

        val window: WindowHandle = glfwCreateWindow(640, 480, "Hello", NULL, NULL)
        if(window == NULL) {
            glfwTerminate()
            throw new IllegalStateException("Failed creating a GLFW window")
        }

        (errorCallback, window)
    }

    def tearDown(window: WindowHandle, callback: KeyCallback, errorCallback: GLFWErrorCallback): Unit = {
        glfwDestroyWindow(window)
        callback.free()
        glfwTerminate()
        errorCallback.free()
    }    

    def printCaps(): Unit = {
        val capabilities = GL.getCapabilities()
        println(s"OpenGl version 1.1 supported: ${capabilities.OpenGL11}")
        println(s"OpenGl version 2.0 supported: ${capabilities.OpenGL20}")
        println(s"OpenGl version 2.1 supported: ${capabilities.OpenGL21}")
        println(s"OpenGl version 3.0 supported: ${capabilities.OpenGL30}")
        println(s"OpenGl version 3.3 supported: ${capabilities.OpenGL33}")
        println(s"OpenGl version 4.0 supported: ${capabilities.OpenGL40}")
        println(s"OpenGl version 4.6 supported: ${capabilities.OpenGL46}")
    }
}