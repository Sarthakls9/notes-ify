package com.example.notes_ify

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notes_ify.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var db: NoteDBHelp
    private lateinit var notesAdapter: NotesRVAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = NoteDBHelp(this)
        notesAdapter = NotesRVAdapter(db.getAllNotes(), this)

        binding.NotesRV.layoutManager = LinearLayoutManager(this)
        binding.NotesRV.adapter = notesAdapter

        binding.addbtn.setOnClickListener {
            val intent = Intent(this, addnote::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        notesAdapter.refresh_data(db.getAllNotes())
    }
}