package edu.skku.cs.pa1.repository

import android.content.Context

class FileWordRepository(
    private val filename: String,
    private val context: Context
): WordRepository {
    override fun findWordList(): List<String> {
        val inputStream = context.assets.open(filename)
        val input = inputStream.readBytes().toString(Charsets.UTF_8)
        return input.lines()
    }
}