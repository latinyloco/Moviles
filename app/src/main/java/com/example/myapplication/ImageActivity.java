package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class ImageActivity extends AppCompatActivity implements View.OnClickListener {

    LinearLayout imgA, imgB, imgC, imgD;
    Button submitBtn;

    int selectedAnswer;

    int score;

    boolean correct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);


        imgA = findViewById(R.id.img_A);
        imgB = findViewById(R.id.img_B);
        imgC = findViewById(R.id.img_C);
        imgD = findViewById(R.id.img_D);
        submitBtn = findViewById(R.id.submit_btn);

        correct = false;

        score = this.getIntent().getExtras().getInt("puntos");

        imgA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                correct = true;
            }
        });

        imgB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                correct = false;
            }
        });

        imgC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                correct = false;
            }
        });

        imgD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                correct = false;
            }
        });

        submitBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(correct){
            score+=3;
        }
        else{
            score-=2;
        }
        endGame();
    }

    private void endGame(){

        Intent intent = new Intent(getApplicationContext(), EndActivity.class);
        intent.putExtra("puntos",score);   //para pasar los puntos de un activity a otro

        startActivity(intent);
    }
}