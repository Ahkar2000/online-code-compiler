package com.onlinecompiler.code.service.impl

import com.onlinecompiler.code.controller.CompileReq
import com.onlinecompiler.code.database.entity.CompileHistory
import com.onlinecompiler.code.database.repo.CompileHistoryRepo
import com.onlinecompiler.code.service.CompileService
import com.onlinecompiler.code.service.FileService
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.concurrent.TimeUnit

@Service
class CompileServiceImpl (
    private val fileService: FileService,
    private val compileHistoryRepo: CompileHistoryRepo
) : CompileService{

    override fun compileCode(req: CompileReq): CompileHistory {
        val tmpFile = fileService.saveIntoTmp(req.language, req.code)

        val dockerImage = when (req.language) {
            "c" -> "gcc:latest"
            "python" -> "python:latest"
            "java" -> "openjdk:11"
            else -> throw IllegalArgumentException("Unsupported language")
        }

        val compileAndRunCommand = when (req.language) {
            "c" -> "gcc /app/main.c -o /app/main && /app/main"
            "python" -> "python /app/main.py"
            "java" -> "javac /app/Main.java && java -cp /app Main"
            else -> ""
        }

        val process = ProcessBuilder(
            "docker", "run", "--rm",
            "--net", "none",
            "--memory=256m",
            "--cpus=0.5",
            "-v", "${tmpFile}:/app",
            dockerImage,
            "bash", "-c", compileAndRunCommand
        ).redirectErrorStream(true).start()

        val finished = process.waitFor(6000, TimeUnit.SECONDS)

        if (!finished) {
            process.destroyForcibly()
            throw RuntimeException("Execution timed out.")
        }

        val output = process.inputStream.bufferedReader().readText()
        val exitCode = process.exitValue()

        if (exitCode != 0) {
            throw RuntimeException("Docker execution failed: $output")
        }

        val compileHistory = CompileHistory(
            code = req.code,
            language = req.language,
            output = output,
            status = "FINISHED",
            executionTime = 1.1,
            createdAt = LocalDateTime.now(),
            updatedAt = LocalDateTime.now(),
        )

        val result = compileHistoryRepo.save(compileHistory)

        return result
    }


}