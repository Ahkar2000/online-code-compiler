package com.onlinecompiler.code.database.repo

import com.onlinecompiler.code.database.entity.CompileHistory
import org.springframework.data.jpa.repository.JpaRepository

interface CompileHistoryRepo : JpaRepository<CompileHistory, Long> {
}