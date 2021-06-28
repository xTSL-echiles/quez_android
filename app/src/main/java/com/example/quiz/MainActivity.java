package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
    private TextView showAnswer;
    private String[] answers = new String[10];
    private String r_ans;
    int total = 0;
    private Question[] questions = {
            new Question(R.string.question0, true),
            new Question(R.string.question1, false),
            new Question(R.string.question2, false),
            new Question(R.string.question3, true),
            new Question(R.string.question4, false),
            new Question(R.string.question5, false),
            new Question(R.string.question6, false),
            new Question(R.string.question7, false),
            new Question(R.string.question8, true),
            new Question(R.string.question9, true),
    };
    private int questionIndex = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("SYSTEM INFO", "Метод onCreate запущен");
        if(savedInstanceState != null) {
            questionIndex = savedInstanceState.getInt("CurrentIndex", 0);
            total = savedInstanceState.getInt("CurrentTotal", 0);
            answers = savedInstanceState.getStringArray("CurrentAnswers");
        }
        questionTextView = findViewById(R.id.questiontextView);
        questionTextView.setText(questions[questionIndex].getQuestion());
        yesBtn = findViewById(R.id.yesBtn);
        noBtn = findViewById(R.id.noBtn);
        showAnswer = findViewById(R.id.showAnswer);
        yesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(questionIndex == 0)
                    total = 0;
                String r = getResources().getString(questions[questionIndex].getQuestion());
                answers[questionIndex] = "\n \""+r+"\" вы ответили: ";
                if(questions[questionIndex].isAnswer()){
                    Toast.makeText(MainActivity.this, R.string.correct, Toast.LENGTH_SHORT).show();
                    total += 10;
                }
                else{
                    Toast.makeText(MainActivity.this, R.string.incorrect, Toast.LENGTH_SHORT).show();
                   // answers[questionIndex] += "Нет.\n";
                }
                answers[questionIndex] += ("Да.\n");
                answers[questionIndex] += "Правильный ответ: "+ (questions[questionIndex].isAnswer() ? "Да": "Нет");
                questionIndex++;// = (questionIndex + 1)%questions.length
                if(questionIndex == 10)
                {
                    Intent intent = new Intent(MainActivity.this, ActivityEndScreen.class);
                    for (int i = 0; i < 10; i++) {
                        if(i == 0)
                            r_ans = Integer.toString(total) + "/100\n";
                        r_ans += answers[i];
                        r_ans += '\n';
                    }
                    intent.putExtra("answer", r_ans);
                    startActivity(intent);
                    questionIndex = 0;
                }
                questionTextView.setText(questions[questionIndex].getQuestion());
                //Toast.makeText(MainActivity.this, "Правильно", Toast.LENGTH_SHORT);
            }
        });

        noBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(questionIndex == 0)
                    total = 0;
                String r = getResources().getString(questions[questionIndex].getQuestion());
                answers[questionIndex] = "\n \""+r+"\" вы ответили: ";
                if (!questions[questionIndex].isAnswer()) {
                    Toast.makeText(MainActivity.this, R.string.correct, Toast.LENGTH_SHORT).show();
                    total += 10;
                  //  answers[questionIndex] += ("Нет.\n");
                } else {
                    Toast.makeText(MainActivity.this, R.string.incorrect, Toast.LENGTH_SHORT).show();
                 //   answers[questionIndex] += ("Да.\n");
                }
                answers[questionIndex] += ("Нет.\n");
                answers[questionIndex] += "Правильный ответ: "+ (questions[questionIndex].isAnswer() ? "Да": "Нет");
                questionIndex++;// = (questionIndex + 1) % questions.length;
                if(questionIndex == 10)
                {
                    Intent intent = new Intent(MainActivity.this, ActivityEndScreen.class);
                    for (int i = 0; i < 10; i++) {
                        if(i == 0)
                            r_ans = Integer.toString(total) + "/100\n";
                        r_ans += answers[i];
                        r_ans += '\n';
                    }
                    intent.putExtra("answer", r_ans);
                    startActivity(intent);
                    questionIndex = 0;
                }
                questionTextView.setText(questions[questionIndex].getQuestion());
                // Toast.makeText(MainActivity.this, R.string.incorrect, Toast.LENGTH_SHORT).show();
            }
        });
        showAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ActivityAnswer.class);
                intent.putExtra("answer", questions[questionIndex].isAnswer());
                startActivity(intent);
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
        savedInstanceState.putInt("CurrentTotal", total);
        savedInstanceState.putStringArray("CurrentAnswers", answers);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("SYSTEM INFO", "Метод destroy запущен");
    }
}