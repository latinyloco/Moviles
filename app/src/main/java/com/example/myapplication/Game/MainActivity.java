package com.example.myapplication.Game;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.myapplication.Partidas.NombreActivity;
import com.example.myapplication.Partidas.PartidasActivity;
import com.example.myapplication.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button bJugar, bEncuesta;
    private ImageView bPartidas;
    private ImageView bInfo;

    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        instancias();
        acciones();
    }



    private void instancias() {
        bJugar = findViewById(R.id.botonJuego);
        bInfo = findViewById(R.id.botonInfo);

        bPartidas = (ImageView) findViewById(R.id.listaPartidas);
        bEncuesta = findViewById(R.id.btnEncuestas);
    }

    private void acciones() {
        bJugar.setOnClickListener(this);
        bInfo.setOnClickListener(this);

        bPartidas.setOnClickListener(this);
        bEncuesta.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.botonJuego) {
            Intent intent = new Intent(getApplicationContext(), NombreActivity.class);
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

            showCustomDialog();

        }else if(v.getId() == R.id.listaPartidas){
            Intent intent = new Intent(getApplicationContext(), PartidasActivity.class);
            startActivity(intent);
        }

    }

    private void showCustomDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.setCancelable(true);         // si se clica fuera, se sale
        dialog.setContentView(R.layout.dialog_encuesta);

        final RadioGroup botonGrupo = dialog.findViewById(R.id.ansEncuesta);
        Button btnEnviar = dialog.findViewById(R.id.btnEnviar);

        btnEnviar.setOnClickListener(v -> {
            int selectedId = botonGrupo.getCheckedRadioButtonId();
            if (selectedId != -1) {
                RadioButton bontonSelecionado = dialog.findViewById(selectedId);
                String texto = bontonSelecionado.getText().toString();
                dialog.dismiss();
                Toast.makeText(this, "Has selecionado: "+texto, Toast.LENGTH_SHORT).show();
            }
        });

        dialog.show();
    }
}