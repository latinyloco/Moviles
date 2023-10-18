package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class EndActivity extends AppCompatActivity implements View.OnClickListener {

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

        if (score < 7.5){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Has perdido, intentalo de nuevo")
                    .setTitle("Derrota")
                    .setCancelable(false).setNeutralButton("Aceptar",
                            (dialog, id) -> {
                                dialog.cancel();
                            });
            AlertDialog alert = builder.create();
            alert.show();
        } else if (score >= 7.5 && score < 15) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Felicidades, has ganado")
                    .setTitle("Victoria")
                    .setCancelable(false).setNeutralButton("Aceptar",
                            (dialog, id) -> {
                                dialog.cancel();
                            });
            AlertDialog alert = builder.create();
            alert.show();
        } else if (score == 15) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Enhorabuena!!, has conseguido una puntuacion perfecta")
                    .setTitle("Flawless Victory")
                    .setCancelable(false).setNeutralButton("Aceptar",
                            (dialog, id) -> {
                                dialog.cancel();
                            });
            AlertDialog alert = builder.create();
            alert.show();
        }

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
