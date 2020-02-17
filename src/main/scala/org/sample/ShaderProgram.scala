package org

import org.lwjgl.opengl.GL20._

class ShaderProgram {

    private val programId: Int = glCreateProgram()
    if(programId == 0) {
        throw new RuntimeException("Could not vreate Shader program")
    }
    
    private var vertexShaderId: Int = 0
    private var fragmentShaderId: Int = 0

    def createVertexChader(shaderCode: String): Unit = {
        vertexShaderId = createShader(shaderCode, GL_VERTEX_SHADER)
    }

    def createFragmentShader(shaderCode: String): Unit = {
        fragmentShaderId = createShader(shaderCode, GL_FRAGMENT_SHADER)
    }

    def createShader(shaderCode: String, shaderKind: Int): Int = {
        val shaderId = glCreateShader(shaderKind)
        if(shaderId == 0) {
            throw new RuntimeException(s"Error creating shader type $shaderKind")
        }

        glShaderSource(shaderId, shaderCode)
        glCompileShader(shaderId)
        
        if(glGetShaderi(shaderId, GL_COMPILE_STATUS) == 0) {
            throw new RuntimeException(s"Error compiling shader code ${glGetShaderInfoLog(shaderId, 1024)}")
        }

        glAttachShader(programId, shaderId)

        shaderId
    }

    def link(): Unit = {
        glLinkProgram(programId)
        if(glGetProgrami(programId, GL_LINK_STATUS) == 0) {
            throw new RuntimeException(s"Error likning Shader code: ${glGetProgramInfoLog(programId, 1024)}")
        }

        if(vertexShaderId != 0) {
            glDetachShader(programId, vertexShaderId)
        }
        if(fragmentShaderId != 0) {
            glDetachShader(programId, fragmentShaderId)
        }

        glValidateProgram(programId)
        if(glGetProgrami(programId, GL_VALIDATE_STATUS) == 0) {
            println(s"Warning: failed validating Shader code: ${glGetProgramInfoLog(programId, 1024)}")
        }
    }

    def bind(): Unit = glUseProgram(programId)
    def unbind(): Unit = glUseProgram(0)
    def cleanup(): Unit = {
        unbind()
        if(programId != 0) {
            glDeleteProgram(programId)
        }
    }
}