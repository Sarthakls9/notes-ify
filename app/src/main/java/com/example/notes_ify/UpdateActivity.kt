package com.example.notes_ify

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.notes_ify.databinding.ActivityUpdateNoteBinding

class UpdateActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateNoteBinding
    private lateinit var db: NoteDBHelp
    private var noteId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = NoteDBHelp(this)

        noteId = intent.getIntExtra("note_id", -1)
        if (noteId == -1)
        {
            finish()
            return
        }

        val note = db.getNoteByID(noteId)
        binding.edittitleTxt.setText(note.title)
        binding.editcontentTxt.setText(note.content)
        binding.updatesaveBtn.setOnClickListener {
            val newTitle = binding.edittitleTxt.text.toString()
            val newContent = binding.editcontentTxt.text.toString()
            val updatednote = Note(noteId, newTitle, newContent)
            db.updateNote(updatednote)
            finish()
            Toast.makeText(this, "Changes Saved", Toast.LENGTH_SHORT).show()
        }
    }
}