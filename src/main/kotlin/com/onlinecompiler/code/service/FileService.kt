package com.onlinecompiler.code.service

interface FileService {
    fun saveIntoTmp (language: String, codeContent: String) : String
}