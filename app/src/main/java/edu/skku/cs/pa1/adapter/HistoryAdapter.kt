package edu.skku.cs.pa1.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import edu.skku.cs.pa1.GameManager
import edu.skku.cs.pa1.R

class HistoryAdapter(
    private val manager: GameManager
): RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {

    private var history = manager.history.flatten()

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val textView = view.findViewById<TextView>(R.id.word_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.word_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return history.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val letter = history[position]
        val textView = holder.textView
        textView.text = letter.text.uppercase()
        textView.setTextColor(ContextCompat.getColor(textView.context, letter.color.text))
        textView.setBackgroundColor(ContextCompat.getColor(textView.context, letter.color.background))
    }

    fun update() {
        history = manager.history.flatten()
        notifyDataSetChanged()
    }
}