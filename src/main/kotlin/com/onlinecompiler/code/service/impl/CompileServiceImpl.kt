package com.onlinecompiler.code.service.impl

import com.onlinecompiler.code.controller.CompileReq
import com.onlinecompiler.code.service.CompileService
import org.springframework.stereotype.Service

@Service
class CompileServiceImpl : CompileService{
    override fun compileCode(req: CompileReq) {
        println("Compile")
    }
}