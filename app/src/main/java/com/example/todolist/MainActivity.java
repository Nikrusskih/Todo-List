package com.example.todolist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private LinearLayout linearLayoutNote;
    private FloatingActionButton buttonAddNote;

    private ArrayList<Note> notes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();

        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            Note note = new Note(i, "Note " + i, random.nextInt(3));
            notes.add(note);
        }

        showNotes();
        buttonAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = AddNoteActivity.newIntent(MainActivity.this);
                startActivity(intent);
            }
        });

    }

    private void initViews() {
        linearLayoutNote = findViewById(R.id.linearLayoutNote);
        buttonAddNote = findViewById(R.id.buttonAddNote);
    }

    private void showNotes() {
        for (Note note : notes) {
            View view = getLayoutInflater().inflate(
                    R.layout.note_item,
                    linearLayoutNote,
                    false
            );
            TextView textViewNote = view.findViewById(R.id.textViewNote);
            textViewNote.setText(note.getText());

            int colorResId = 0;
            switch (note.getPriority()) {
                case 0:
                    colorResId = android.R.color.holo_green_dark;
                    break;
                case 1:
                    colorResId = android.R.color.holo_orange_dark;
                    break;
                case 2:
                    colorResId = android.R.color.holo_red_dark;
                    break;
            }
            int color = ContextCompat.getColor(this, colorResId);
            textViewNote.setBackgroundColor(color);
            linearLayoutNote.addView(view);
        }
    }
}