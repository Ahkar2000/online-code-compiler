package com.onlinecompiler.code.service

import com.onlinecompiler.code.controller.CompileReq

interface CompileService {
    fun compileCode (req: CompileReq)
}