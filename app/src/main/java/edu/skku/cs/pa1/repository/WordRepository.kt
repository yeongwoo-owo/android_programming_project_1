package edu.skku.cs.pa1.repository

interface WordRepository {
    fun findWordList(): List<String>
}