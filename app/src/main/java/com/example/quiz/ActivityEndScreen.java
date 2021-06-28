package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ActivityEndScreen extends AppCompatActivity {
    TextView endTextView;
    private String answers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_screen);
        endTextView = findViewById(R.id.endTextView);
        answers = getIntent().getStringExtra("answer");
        endTextView.setText(answers);
    }
}