package com.onlinecompiler.code.controller

import com.onlinecompiler.code.service.CompileService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/compile")
class CompileController(
    private val compileService: CompileService
) {
    @PostMapping("/result")
    fun compileCode(@RequestBody req: CompileReq): ResponseEntity<Any> {
        return ResponseEntity.ok(compileService.compileCode(req));
    }
}

data class CompileReq(
    val code: String,
    val language: String,
)