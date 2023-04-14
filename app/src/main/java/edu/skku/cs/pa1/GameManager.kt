package edu.skku.cs.pa1

import android.util.Log
import edu.skku.cs.pa1.exception.NoSuchWordException
import edu.skku.cs.pa1.letter.Letter
import edu.skku.cs.pa1.letter.LetterColor
import edu.skku.cs.pa1.repository.WordRepository

class GameManager(
    repository: WordRepository
) {
    private val words = repository.findWordList()
    private val answer = words.random()

    val history: MutableList<List<Letter>> = mutableListOf()
    private val usedLetters: MutableMap<Char, Letter> = sortedMapOf()

    init {
        Log.i("answer", answer)
    }

    fun guess(expect: String) {
        val lowercase = expect.lowercase()
        if (!words.contains(lowercase)) throw NoSuchWordException()

        history.add(lowercase.mapIndexed { i, c ->
            val color = when {
                c in answer && answer[i] == c -> LetterColor.GREEN
                c in answer -> LetterColor.YELLOW
                else -> LetterColor.GRAY
            }
            if (color > usedLetters[c]?.color) usedLetters[c] = Letter(c, color)
            Letter(c, color)
        })
    }

    fun getLettersOf(color: LetterColor): List<Letter> {
        return usedLetters.map { it.value }.filter { it.color == color }.toList()
    }
}