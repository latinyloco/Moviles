package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button bJugar, bEncuesta, btnEnviar;

    private RadioButton ans1;
    RadioGroup rg;

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
        btnEnviar = findViewById(R.id.btnEnviar);

        bEncuesta = findViewById(R.id.btnEncuestas);
        rg = findViewById(R.id.ansEncuesta);
    }

    private void acciones() {
        bJugar.setOnClickListener(this);
        bInfo.setOnClickListener(this);
        bEncuesta.setOnClickListener(this);
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


        } else if(v.getId() == R.id.btnEncuestas){
              //crear la vista inflada
            LayoutInflater inflador = getLayoutInflater();
            View view = inflador.inflate(R.layout.dialog_encuesta,null);

              //construir el diálogo
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setView(view);
            builder.create().show();

              //pop up al enviar
            if (v.getId() == R.id.btnEnviar){

                System.out.println("hola");

                int a = rg.getCheckedRadioButtonId();
                ans1 = findViewById(a);

                builder.create().hide();

                AlertDialog.Builder builderpop = new AlertDialog.Builder(this);
                builderpop.setMessage("Has seleccionado: "+ans1.getText())
                        .setTitle("Gracias por haber seleccionado una respuesta")
                        .setCancelable(false).setNeutralButton("Aceptar",
                                (dialog, id) -> {
                                    dialog.cancel();
                                });
                AlertDialog alert = builderpop.create();
                alert.show();

            }


        }

    }
}