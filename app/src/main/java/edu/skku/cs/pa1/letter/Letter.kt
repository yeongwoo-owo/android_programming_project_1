package edu.skku.cs.pa1.letter

class Letter(
    content: Char,
    var color: LetterColor = LetterColor.GRAY
) {
    val text = content.toString()
}