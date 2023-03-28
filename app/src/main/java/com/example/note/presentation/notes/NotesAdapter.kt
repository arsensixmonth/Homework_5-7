package com.example.note.presentation.notes

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.note.databinding.ItemFillingNotesBinding
import com.example.note.domain.model.Note

@Suppress("DEPRECATION")
class NotesAdapter(

    private val onItemClick: (Note) -> Unit,
    private val onLongClick: (Note, position: Int) -> Unit,
) : RecyclerView.Adapter<NotesAdapter.ViewHolder>() {
    private var list = mutableListOf<Note>()

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(notes: MutableList<Note>) {
        this.list = (notes)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemFillingNotesBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun deleteItem(position: Int): Note {
        notifyItemRemoved(position)
        return list.removeAt(position)
    }

    fun getPosition(position: Int): Note {
        return list[position]
    }


    inner class ViewHolder(private val binding: ItemFillingNotesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(note: Note) {
            binding.description.text = note.description
            binding.title.text = note.title
            binding.time.text = note.createdAt.toString()

            binding.root.setOnClickListener {
                onItemClick.invoke(note)

            }

            binding.root.setOnLongClickListener {
                onLongClick.invoke(note, position)
                true
            }
        }

    }
}