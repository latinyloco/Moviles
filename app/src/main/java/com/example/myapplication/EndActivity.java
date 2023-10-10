package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class EndActivity extends AppCompatActivity implements View.OnClickListener {

    //En esta pantalla se puede mostrar la puntuación de una forma bonita y un boton de volver a jugar
    // Esta pantalla se inicara una vez acabe el juego, con un intent al que se le pasaran los resultados

    private TextView textscore;

    private int score;

    private Button vol, judn;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);
        score = this.getIntent().getExtras().getInt("puntos");     //recoger datos que vienen de otra activity
        textscore = findViewById(R.id.textscore);
        textscore.setText(String.valueOf(score));

        System.out.println("ender");

        vol = findViewById(R.id.volver);
        judn = findViewById(R.id.JugarDeNuevo);

        vol.setOnClickListener(this);
        judn.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.volver){
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        }else{
            Intent intent = new Intent(getApplicationContext(), JuegoActivity.class);
            startActivity(intent);
        }
    }
}
