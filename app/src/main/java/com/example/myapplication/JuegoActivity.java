package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.graphics.Color;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class JuegoActivity extends AppCompatActivity implements View.OnClickListener{

    public static String question[] ={
            "¿De qué color fue el primer Hulk?",
            "¿Cada cuántos minutos tenían que pulsar la tecla en LOST?",
            "¿Cómo se llama la digievolución de Gabumon?",
            "'Nada es verdad...' ",

    };


    public static String choices[] []={
            {"Gris","Verde","Rojo","Blanco"},
            {"42","120","108","55"},
            {"Greymon","Súper Gabumon","Angemon","Garurumon"},
            {"salvo algunas cosas'","hasta que se hace realidad'","aunque en realidad si'","todo esta permitido'"},
    };

    public static String correct[] ={
            "Gris",
            "108",
            "Garurumon",
            "todo esta permitido'",
    };

    private int score=0;
    TextView questionTextView;
    Button ansA, ansB, ansC, ansD;
    Button submitBtn;

    int totalQuestion = question.length;
    int currentQuestionIndex = 0;
    String selectedAnswer = "";

    Button selectedAnswerButton = null;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);

        questionTextView = findViewById(R.id.question);
        ansA = findViewById(R.id.ans_A);
        ansB = findViewById(R.id.ans_B);
        ansC = findViewById(R.id.ans_C);
        ansD = findViewById(R.id.ans_D);
        submitBtn = findViewById(R.id.submit_btn);

        ansA.setOnClickListener(this);
        ansB.setOnClickListener(this);
        ansC.setOnClickListener(this);
        ansD.setOnClickListener(this);
        submitBtn.setOnClickListener(this);

        loadNewQuestion();
    }

    @Override
    public void onClick(View view) {

        ansA.setBackgroundColor(Color.WHITE);
        ansB.setBackgroundColor(Color.WHITE);
        ansC.setBackgroundColor(Color.WHITE);
        ansD.setBackgroundColor(Color.WHITE);

        Button clickedButton = (Button) view;
        if(clickedButton.getId()==R.id.submit_btn){
            if (selectedAnswerButton!=null) {
                if (selectedAnswer.equals(correct[currentQuestionIndex])) {
                    score += 3;
                    selectedAnswerButton.setBackgroundColor(Color.GREEN);
                    correctMessage();

                } else {
                    score -= 2;
                    showCorrect(selectedAnswerButton);
                    errorMessage();
                }
            }
            currentQuestionIndex++;
            System.out.println(currentQuestionIndex);
            selectedAnswerButton = null;
            loadNewQuestion();
        }else{
            //choices button clicked
            selectedAnswer  = clickedButton.getText().toString();
            selectedAnswerButton = clickedButton;
            clickedButton.setBackgroundColor(Color.MAGENTA);
        }
    }
    void showCorrect(Button button){
        button.setBackgroundColor(Color.RED);
        if (ansA.getText().toString().equals(correct[currentQuestionIndex])){
            ansA.setBackgroundColor(Color.GREEN);
        }
        else if (ansB.getText().toString().equals(correct[currentQuestionIndex])){
            ansB.setBackgroundColor(Color.GREEN);
        }else if (ansC.getText().toString().equals(correct[currentQuestionIndex])){
            ansC.setBackgroundColor(Color.GREEN);
        }else {
            ansD.setBackgroundColor(Color.GREEN);
        }
    }

    void errorMessage(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Has fallado")
                .setTitle("Incorrecto")
                .setCancelable(false)
                .setPositiveButton("Continuar", (dialog, which) -> {
                })
                .setNegativeButton("Repetir desde el inicio", (dialog, which) -> {
                    Intent i = new Intent(this, MainActivity.class);
                    startActivity(i);
                });

        AlertDialog alert = builder.create();
        alert.show();
    }

    void correctMessage(){
        Toast.makeText(this, "Correcto", Toast.LENGTH_SHORT).show();
    }

    void loadNewQuestion(){
        selectedAnswer = "";
        System.out.println(currentQuestionIndex+"   "+totalQuestion);

        if(currentQuestionIndex == totalQuestion ){
            imgGame();
        }
        else{
            questionTextView.setText(question[currentQuestionIndex]);
            ansA.setText(choices[currentQuestionIndex][0]);
            ansB.setText(choices[currentQuestionIndex][1]);
            ansC.setText(choices[currentQuestionIndex][2]);
            ansD.setText(choices[currentQuestionIndex][3]);
        }

    }

    void finishQuiz(){
        String passStatus = "";
        if(score > totalQuestion*0.60){
            passStatus = "Passed";
        }else{
            passStatus = "Failed";
        }
    }


    private void imgGame(){

        Intent intent = new Intent(getApplicationContext(), ImageActivity.class);
        intent.putExtra("puntos",score);   //para pasar los puntos de un activity a otro

        startActivity(intent);
    }

}
