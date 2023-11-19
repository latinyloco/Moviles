package com.example.myapplication.Game;

import androidx.appcompat.app.AlertDialog;

import android.app.Dialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.myapplication.Partidas.NombreActivity;
import com.example.myapplication.Partidas.PartidasActivity;
import com.example.myapplication.R;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private Button bJugar, bEncuesta;
    private ImageView bInfo, bNosound, bSound, bPartidas;

    private MediaPlayer soundLong, soundPress;

    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        instancias();
        acciones();
        musica();


    }

    private void musica (){
        //musica press
        soundPress = MediaPlayer.create(this, R.raw.press);

        //musica de fondo
        soundLong = MediaPlayer.create(this, R.raw.sound);
    }



    private void instancias() {
        bJugar = findViewById(R.id.botonJuego);
        bInfo = findViewById(R.id.botonInfo);

        bPartidas = (ImageView) findViewById(R.id.listaPartidas);
        bEncuesta = findViewById(R.id.btnEncuestas);
        bNosound = (ImageView) findViewById(R.id.nosound);
        bSound = (ImageView) findViewById(R.id.sound);
    }

    private void acciones() {
        bJugar.setOnClickListener(this);
        bInfo.setOnClickListener(this);

        bPartidas.setOnClickListener(this);
        bEncuesta.setOnClickListener(this);
        bNosound.setOnClickListener(this);
        bSound.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.botonJuego) {
            soundPress.start();
            Intent intent = new Intent(getApplicationContext(), NombreActivity.class);
            startActivity(intent);



        } else if (v.getId() == R.id.botonInfo) {
            soundPress.start();
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Esta es una aplicacion de juego tipo trivial." +
                    " Se te van a realizar varias preguntas, si las aciertas se te sumaran puntos," +
                    " si las fallas te restaran. ¡¡Espero que te diviertas!!")
                    .setTitle("Información")
                    .setCancelable(false).setNeutralButton("Aceptar",
                            (dialog, id) -> {
                                soundPress.start();
                                dialog.cancel();
                            });
            AlertDialog alert = builder.create();
            alert.show();


        } else if(v.getId() == R.id.btnEncuestas){
            soundPress.start();
            showCustomDialog();

        }else if(v.getId() == R.id.listaPartidas){
            soundPress.start();
            Intent intent = new Intent(getApplicationContext(), PartidasActivity.class);
            startActivity(intent);
        }else if(v.getId() == R.id.nosound){
            soundPress.start();
            soundLong.pause();
        }else if(v.getId() == R.id.sound){
            soundPress.start();
            soundLong.start();
        }

    }

    private void showCustomDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.setCancelable(true);         // si se clica fuera, se sale
        dialog.setContentView(R.layout.dialog_encuesta);

        final RadioGroup botonGrupo = dialog.findViewById(R.id.ansEncuesta);
        Button btnEnviar = dialog.findViewById(R.id.btnEnviar);

        btnEnviar.setOnClickListener(v -> {
            soundPress.start();
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