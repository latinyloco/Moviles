package com.example.myapplication.Game;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.graphics.Color;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

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

    private Button selectedAnswerButton = null;
    private Button lastSelectedAnswerButton = null;
    private String name;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);
        name = this.getIntent().getExtras().getString("nombre");     //recoger datos que vienen de otra activity
        Toast.makeText(JuegoActivity.this, name,Toast.LENGTH_SHORT).show();

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

    public void onClick(View view) {

        ansA.setBackgroundColor(Color.WHITE);
        ansB.setBackgroundColor(Color.WHITE);
        ansC.setBackgroundColor(Color.WHITE);
        ansD.setBackgroundColor(Color.WHITE);

        Button clickedButton = (Button) view;
        if(clickedButton.getId()==R.id.submit_btn){
            if (selectedAnswerButton!=null) {
                if (selectedAnswer.equals(correct[currentQuestionIndex])) {
                    if(currentQuestionIndex+1 < totalQuestion) {
                        score += 3;
                        selectedAnswerButton.setBackgroundColor(Color.GREEN);
                        correctMessage();
                    }

                } else {
                    if(currentQuestionIndex+1 < totalQuestion){
                        score -= 2;
                        showCorrect(selectedAnswerButton);
                        errorMessage();
                    }else{
                        lastSelectedAnswerButton = selectedAnswerButton;
                    }

                }
            }
            currentQuestionIndex++;
            selectedAnswerButton = null;

            // Agregar una pausa antes de cargar una nueva pregunta
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    ansA.setBackgroundColor(Color.WHITE);
                    ansB.setBackgroundColor(Color.WHITE);
                    ansC.setBackgroundColor(Color.WHITE);
                    ansD.setBackgroundColor(Color.WHITE);
                    loadNewQuestion();
                }
            }, 500);
        }else{
            //choices button clicked
            selectedAnswer  = clickedButton.getText().toString();
            selectedAnswerButton = clickedButton;
            clickedButton.setBackgroundColor(Color.MAGENTA);
        }
    }

    void errorMessage(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Has fallado")
                .setTitle("Incorrecto")
                .setCancelable(false)
                .setPositiveButton("Continuar", (dialog, which) -> {
                    if(currentQuestionIndex == totalQuestion ){
                        imgGame();
                    }
                })
                .setNegativeButton("Repetir desde el inicio", (dialog, which) -> {
                    endQuestions();
                });

        AlertDialog alert = builder.create();
        alert.show();
    }


    void correctMessage(){
        Toast.makeText(this, "Correcto", Toast.LENGTH_SHORT).show();
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


    void loadNewQuestion(){

        if(currentQuestionIndex == totalQuestion){
            lastQuestion();
        }

        selectedAnswer = "";
        if(currentQuestionIndex < totalQuestion){
            questionTextView.setText(question[currentQuestionIndex]);
            ansA.setText(choices[currentQuestionIndex][0]);
            ansB.setText(choices[currentQuestionIndex][1]);
            ansC.setText(choices[currentQuestionIndex][2]);
            ansD.setText(choices[currentQuestionIndex][3]);
        }

    }


    private void lastQuestion(){
        currentQuestionIndex--;
        if (selectedAnswer.equals(correct[currentQuestionIndex])) {
            score += 3;
            correctMessage();
            imgGame();

        } else {
            score -= 2;
            showCorrect(lastSelectedAnswerButton);

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Has fallado")
                    .setTitle("Incorrecto")
                    .setCancelable(false)
                    .setPositiveButton("Continuar", (dialog, which) -> {
                        imgGame();
                    })
                    .setNegativeButton("Repetir desde el inicio", (dialog, which) -> {
                        endQuestions();
                    });

            AlertDialog alert = builder.create();
            alert.show();

        }}

    private void imgGame(){

        Intent intent = new Intent(getApplicationContext(), ImageActivity.class);
        intent.putExtra("puntos",score);   //para pasar los puntos de un activity a otro
        intent.putExtra("nombre",name);

        Toast.makeText(JuegoActivity.this, name,Toast.LENGTH_SHORT).show();
        startActivity(intent);
    }

    private void endQuestions(){
        Intent i = new Intent(this, MainActivity.class);
        i.putExtra("nombre",name);
        startActivity(i);
    }

}