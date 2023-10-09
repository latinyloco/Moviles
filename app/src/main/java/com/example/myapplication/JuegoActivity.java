package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class JuegoActivity extends AppCompatActivity {

    private int puntos;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);
    }


    private void endGame(){

        Intent intent = new Intent(getApplicationContext(), JuegoActivity.class);
        intent.putExtra("puntos",puntos);   //para pasar los puntos de un activity a otro

        startActivity(intent);
    }

}
