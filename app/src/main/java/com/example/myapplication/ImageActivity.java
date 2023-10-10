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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);


        imgA = findViewById(R.id.img_A);
        imgB = findViewById(R.id.img_B);
        imgC = findViewById(R.id.img_C);
        imgD = findViewById(R.id.img_D);
        submitBtn = findViewById(R.id.submit_btn);

        imgA.setOnClickListener(this);
        imgB.setOnClickListener(this);
        imgC.setOnClickListener(this);
        imgD.setOnClickListener(this);
        submitBtn.setOnClickListener(this);
        score = this.getIntent().getExtras().getInt("puntos");

    }

    @Override
    public void onClick(View view) {

        imgA.setBackgroundColor(Color.WHITE);
        imgB.setBackgroundColor(Color.WHITE);
        imgC.setBackgroundColor(Color.WHITE);
        imgD.setBackgroundColor(Color.WHITE);

        Button clickedButton = (Button) view;
        if(clickedButton.getId() ==R.id.submit_btn){

            if(selectedAnswer == R.id.ans_A){
                score+=3;
            }
            else{
                score-=2;
            }
            endGame();

        }else{
            //choices button clicked

            selectedAnswer  = view.getId();
            clickedButton.setBackgroundColor(Color.MAGENTA);

        }

    }

    private void endGame(){

        Intent intent = new Intent(getApplicationContext(), EndActivity.class);
        intent.putExtra("puntos",score);   //para pasar los puntos de un activity a otro

        startActivity(intent);
    }
}