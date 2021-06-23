package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private Button yesBtn;
    private Button noBtn;
    private TextView questionTextView;

    private Question[] questions = {
            new Question(R.string.question0, true),
            new Question(R.string.question0, true),
            new Question(R.string.question0, true),
            new Question(R.string.question0, true),
            new Question(R.string.question0, true),
            new Question(R.string.question1, false),
            new Question(R.string.question2, false),
            new Question(R.string.question3, true),
            new Question(R.string.question4, false),
            new Question(R.string.question5, false),
            new Question(R.string.question6,false)
    };
    private int questionIndex = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("SYSTEM INFO", "Метод onCreate запущен");
        if(savedInstanceState != null)
            questionIndex = savedInstanceState.getInt("CurrentIndex", 0);
        questionTextView = findViewById(R.id.questiontextView);
        questionTextView.setText(questions[questionIndex].getQuestion());
        yesBtn = findViewById(R.id.yesBtn);
        noBtn = findViewById(R.id.noBtn);
        yesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(questions[questionIndex].isAnswer()){
                    Toast.makeText(MainActivity.this, R.string.correct, Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity.this, R.string.incorrect, Toast.LENGTH_SHORT).show();

                }
                questionIndex = (questionIndex + 1)%questions.length;
                questionTextView.setText(questions[questionIndex].getQuestion());
                //Toast.makeText(MainActivity.this, "Правильно", Toast.LENGTH_SHORT);
            }
        });

        noBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!questions[questionIndex].isAnswer()){
                    Toast.makeText(MainActivity.this, R.string.correct, Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity.this, R.string.incorrect, Toast.LENGTH_SHORT).show();

                }

                questionIndex = (questionIndex + 1)%questions.length;
                questionTextView.setText(questions[questionIndex].getQuestion());
               // Toast.makeText(MainActivity.this, R.string.incorrect, Toast.LENGTH_SHORT).show();
            }
        });


    }
    @Override
    protected void onStart(){
        super.onStart();
        Log.d("SYSTEM INFO", "Метод старт запущен");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("SYSTEM INFO", "Метод resume запущен");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("SYSTEM INFO", "Метод stop запущен");
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.d("SYSTEM INFO", "Метод saved запущен");
        savedInstanceState.putInt("CurrentIndex", questionIndex);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("SYSTEM INFO", "Метод destroy запущен");
    }
}