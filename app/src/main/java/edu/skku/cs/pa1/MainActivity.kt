package edu.skku.cs.pa1

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import edu.skku.cs.pa1.adapter.HistoryAdapter
import edu.skku.cs.pa1.adapter.LettersAdapter
import edu.skku.cs.pa1.exception.NoSuchWordException
import edu.skku.cs.pa1.letter.LetterColor
import edu.skku.cs.pa1.repository.FileWordRepository

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val gameManager = GameManager(FileWordRepository("wordle_words.txt", this))

        val historyView = findViewById<RecyclerView>(R.id.recycler_view)
        val historyAdapter = HistoryAdapter(gameManager)
        historyView.adapter = historyAdapter
        historyView.layoutManager = GridLayoutManager(this, 5)

        val grayLettersView = findViewById<RecyclerView>(R.id.gray_letters)
        val grayLettersAdapter = LettersAdapter(gameManager, LetterColor.GRAY)
        grayLettersView.adapter = grayLettersAdapter
        grayLettersView.layoutManager = LinearLayoutManager(this)

        val yellowLettersView = findViewById<RecyclerView>(R.id.yellow_letters)
        val yellowLettersAdapter = LettersAdapter(gameManager, LetterColor.YELLOW)
        yellowLettersView.adapter = yellowLettersAdapter
        yellowLettersView.layoutManager = LinearLayoutManager(this)

        val greenLettersView = findViewById<RecyclerView>(R.id.green_letters)
        val greenLettersAdapter = LettersAdapter(gameManager, LetterColor.GREEN)
        greenLettersView.adapter = greenLettersAdapter
        greenLettersView.layoutManager = LinearLayoutManager(this)

        val inputField = findViewById<EditText>(R.id.input_field)
        val submitButton = findViewById<Button>(R.id.submit_button)

        submitButton.setOnClickListener {
            val text = inputField.text.toString()
            try {
                gameManager.guess(text)
            } catch (e: NoSuchWordException) {
                Toast.makeText(
                    this,
                    "Word '$text' not in dictionary!",
                    Toast.LENGTH_SHORT
                ).show()
            }
            inputField.text.clear()

            historyAdapter.update()
            grayLettersAdapter.update()
            yellowLettersAdapter.update()
            greenLettersAdapter.update()
        }
    }
}