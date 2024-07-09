package com.example.notes_ify

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.notes_ify.databinding.ActivityAddnoteBinding
import com.example.notes_ify.databinding.ActivityMainBinding

class addnote : AppCompatActivity() {

    private lateinit var binding: ActivityAddnoteBinding
    private lateinit var db: NoteDBHelp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddnoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = NoteDBHelp(this)

        binding.saveBtn.setOnClickListener{
            val title = binding.titleTxt.text.toString()
            val content = binding.contentTxt.text.toString()
            val note = Note(0, title, content)
            db.insertNote(note)
            finish()
            Toast.makeText(this, "Note Saved", Toast.LENGTH_SHORT).show()
        }
    }
}