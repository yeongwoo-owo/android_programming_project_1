package edu.skku.cs.pa1.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import edu.skku.cs.pa1.GameManager
import edu.skku.cs.pa1.R
import edu.skku.cs.pa1.letter.LetterColor

class LettersAdapter(
    private val gameManager: GameManager,
    private val color: LetterColor
): RecyclerView.Adapter<LettersAdapter.ViewHolder>() {

    private var letters = gameManager.getLettersOf(color)

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val textView = view.findViewById<TextView>(R.id.word_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.word_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return letters.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val letter = letters[position]
        val textView = holder.textView
        textView.text = letter.text.uppercase()
        textView.setTextColor(ContextCompat.getColor(textView.context, color.text))
        textView.setBackgroundColor(ContextCompat.getColor(textView.context, color.background))
    }

    fun update() {
        this.letters = gameManager.getLettersOf(color)
        notifyDataSetChanged()
    }
}