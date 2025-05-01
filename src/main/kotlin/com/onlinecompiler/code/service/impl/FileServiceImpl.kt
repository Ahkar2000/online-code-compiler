package com.onlinecompiler.code.service.impl

import com.onlinecompiler.code.service.FileService
import org.springframework.stereotype.Service
import java.io.File
import java.io.IOException
import java.nio.file.Files
import java.nio.file.StandardOpenOption

@Service
class FileServiceImpl : FileService {
    override fun saveIntoTmp(language: String, codeContent: String): String {
        val tempDir = File("tmp")

        if (!tempDir.exists()) {
            tempDir.mkdirs()
        }
        val extension = getFileExtensionForLanguage(language)
        val tempFileName = "tmp_${System.currentTimeMillis()}$extension"
        val tempFile = tempDir.resolve(tempFileName)

        try {
            Files.createFile(tempFile.toPath())
            Files.write(tempFile.toPath(), codeContent.toByteArray(), StandardOpenOption.WRITE)

            return tempFile.absolutePath
        } catch (e: IOException) {
            throw RuntimeException("Failed to create temp file", e)
        }
    }

    private fun getFileExtensionForLanguage(language: String): String {
        return when (language) {
            "java" -> ".java"
            "python" -> ".py"
            "go" -> ".go"
            "c" -> ".c"
            else -> throw IllegalArgumentException("Unsupported language: $language")
        }
    }
}