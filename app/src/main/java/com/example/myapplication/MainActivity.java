package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button bJugar;
    private ImageButton bInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        instancias();
        acciones();
    }

    @SuppressLint("WrongViewCast")
    private void instancias() {
        bJugar = findViewById(R.id.botonJuego);
        bInfo = findViewById(R.id.botonInfo);
    }

    private void acciones() {
        bJugar.setOnClickListener(this);
        bInfo.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.botonJuego) {
            Intent intent = new Intent(getApplicationContext(), JuegoActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.botonInfo) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Esta es una aplicacion de juego tipo trivial." +
                    " Se te van a realizar varias preguntas, si las aciertas se te sumaran puntos," +
                    " si las fallas te restaran. ¡¡Espero que te diviertas!!")
                    .setTitle("Información")
                    .setCancelable(false).setNeutralButton("Aceptar",
                            (dialog, id) -> {
                                dialog.cancel();
                            });
            AlertDialog alert = builder.create();
            alert.show();
        }

    }
}