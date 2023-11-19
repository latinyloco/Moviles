package com.example.myapplication.Game;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.myapplication.R;

public class ImageActivity extends BaseActivity implements View.OnClickListener {

    LinearLayout imgA, imgB, imgC, imgD;
    Button submitBtn;
    int score;
    private String name;
    boolean correct;
    private MediaPlayer soundFail, soundCorrect, soundPress;


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
        name = this.getIntent().getExtras().getString("nombre");

        imgA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                correct = true;
                imgB.setBackgroundColor(Color.WHITE);
                imgC.setBackgroundColor(Color.WHITE);
                imgD.setBackgroundColor(Color.WHITE);
                view.setBackgroundColor(Color.MAGENTA);
            }
        });

        imgB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                correct = false;
                imgA.setBackgroundColor(Color.WHITE);
                imgC.setBackgroundColor(Color.WHITE);
                imgD.setBackgroundColor(Color.WHITE);
                view.setBackgroundColor(Color.MAGENTA);
            }
        });

        imgC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                correct = false;
                imgB.setBackgroundColor(Color.WHITE);
                imgC.setBackgroundColor(Color.WHITE);
                imgA.setBackgroundColor(Color.WHITE);
                view.setBackgroundColor(Color.MAGENTA);
            }
        });

        imgD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                correct = false;
                imgB.setBackgroundColor(Color.WHITE);
                imgC.setBackgroundColor(Color.WHITE);
                imgA.setBackgroundColor(Color.WHITE);
                view.setBackgroundColor(Color.MAGENTA);
            }
        });

        submitBtn.setOnClickListener(this);

        musica();
    }

    private void musica(){
        soundPress = MediaPlayer.create(this, R.raw.press);
        soundCorrect = MediaPlayer.create(this, R.raw.correct);
        soundFail = MediaPlayer.create(this, R.raw.fail);
    }

    @Override
    public void onClick(View view) {
        if(correct){
            soundCorrect.start();
            score+=3;
        }
        else{
            soundFail.start();
            score-=2;
        }
        endGame();
    }

    private void endGame(){

        Intent intent = new Intent(getApplicationContext(), EndActivity.class);
        intent.putExtra("puntos",score);   //para pasar los puntos de un activity a otro
        intent.putExtra("nombre",name);

        startActivity(intent);
    }
}