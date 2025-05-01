package com.onlinecompiler.code.service

import com.onlinecompiler.code.controller.CompileReq
import com.onlinecompiler.code.database.entity.CompileHistory

interface CompileService {
    fun compileCode (req: CompileReq): CompileHistory
}