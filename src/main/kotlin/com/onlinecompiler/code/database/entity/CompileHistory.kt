package com.onlinecompiler.code.database.entity

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime

@Entity
@Table(name = "compile_history")
data class CompileHistory (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Long,

    @Lob
    @Column(nullable = false)
    val code: String,

    val language: String,

    val status: String,

    @Lob
    val output: String?,

    val executionTime: Double,

    @CreationTimestamp
    val createdAt: LocalDateTime,

    @UpdateTimestamp
    val updatedAt: LocalDateTime
)