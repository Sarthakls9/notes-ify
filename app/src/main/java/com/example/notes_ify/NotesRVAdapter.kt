package com.example.notes_ify

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class NotesRVAdapter(private var notes: List<Note>, context: Context) :
    RecyclerView.Adapter<NotesRVAdapter.NoteViewHolder>() {

    private val db: NoteDBHelp = NoteDBHelp(context)

    class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val titleTextView: TextView = itemView.findViewById(R.id.titleTV)
        val contextTextView: TextView = itemView.findViewById(R.id.contentTV)
        val updateBtn: ImageView = itemView.findViewById(R.id.UpdateBtn)
        val deletBtn: ImageView = itemView.findViewById(R.id.DeleteBtn)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.note_display, parent, false)
        return NoteViewHolder(view)
    }

    override fun getItemCount(): Int = notes.size

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = notes[position]
        holder.titleTextView.text = note.title
        holder.contextTextView.text = note.content

        holder.updateBtn.setOnClickListener{
            val intent = Intent(holder.itemView.context, UpdateActivity::class.java).apply {
                putExtra("note_id", note.id)
            }
            holder.itemView.context.startActivity(intent)
        }

        holder.deletBtn.setOnClickListener{
            db.DeleteNote(note.id)
            refresh_data(db.getAllNotes())
            Toast.makeText(holder.itemView.context, "Note Deleted", Toast.LENGTH_SHORT).show()
        }

    }

    fun refresh_data(newNotes: List<Note>){
        notes = newNotes
        notifyDataSetChanged()
    }

}