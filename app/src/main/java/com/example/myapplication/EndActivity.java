package com.example.myapplication;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class EndActivity extends AppCompatActivity {

    //En esta pantalla se puede mostrar la puntuación de una forma bonita y un boton de volver a jugar
    // Esta pantalla se inicara una vez acabe el juego, con un intent al que se le pasaran los resultados

    private int puntos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);

        puntos = this.getIntent().getExtras().getInt("puntos");     //recoger datos que vienen de otra activity
    }
}
