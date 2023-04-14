package edu.skku.cs.pa1.letter

import edu.skku.cs.pa1.R

enum class LetterColor(
    val value: Int,
    val text: Int,
    val background: Int
) {
    GRAY(0, R.color.gray_text, R.color.gray_background),
    YELLOW(1, R.color.yellow_text, R.color.yellow_background),
    GREEN(2, R.color.green_text, R.color.green_background);

    operator fun compareTo(color: LetterColor?): Int {
        return when {
            color == null -> 1
            value > color.value -> 1
            value == color.value -> 0
            else -> -1
        }
    }
}
