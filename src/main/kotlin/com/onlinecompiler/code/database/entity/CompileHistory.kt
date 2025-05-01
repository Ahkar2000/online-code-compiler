package com.onlinecompiler.code.database.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "compile_history")
data class CompileHistory (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Long = 0,

    @Lob
    @Column(nullable = false)
    val code: String,

    val language: String,

    val status: String,

    @Lob
    val output: String?,

    val executionTime: Double,

    val createdAt: LocalDateTime,

    val updatedAt: LocalDateTime
)