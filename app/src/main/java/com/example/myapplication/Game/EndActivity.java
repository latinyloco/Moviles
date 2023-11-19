package com.example.myapplication.Game;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.BaseActivity;
import com.example.myapplication.DB.DbPartidas;
import com.example.myapplication.R;

public class EndActivity extends BaseActivity implements View.OnClickListener {

    private TextView textscore;

    private int score;
    private String name;

    private Button vol, judn;

    private MediaPlayer soundPress, soundSuperwin, soundWin, soundLose;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        musica();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);
        score = this.getIntent().getExtras().getInt("puntos");     //recoger datos que vienen de otra activity
        name = this.getIntent().getExtras().getString("nombre");
        textscore = findViewById(R.id.textscore);
        textscore.setText(String.valueOf(score));


        if (score < 7.5){
            soundLose.start();
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Has perdido, intentalo de nuevo")
                    .setTitle("Derrota")
                    .setCancelable(false).setNeutralButton("Aceptar",
                            (dialog, id) -> {
                                soundPress.start();
                                dialog.cancel();
                            });
            AlertDialog alert = builder.create();
            alert.show();
        } else if (score >= 7.5 && score < 15) {
            soundWin.start();
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Felicidades, has ganado")
                    .setTitle("Victoria")
                    .setCancelable(false).setNeutralButton("Aceptar",
                            (dialog, id) -> {
                                soundPress.start();
                                dialog.cancel();
                            });
            AlertDialog alert = builder.create();
            alert.show();
        } else if (score == 15) {
            soundSuperwin.start();
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Enhorabuena!!, has conseguido una puntuacion perfecta")
                    .setTitle("Flawless Victory")
                    .setCancelable(false).setNeutralButton("Aceptar",
                            (dialog, id) -> {
                                soundPress.start();
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

    private void musica(){
        soundPress = MediaPlayer.create(this, R.raw.press);
        soundSuperwin = MediaPlayer.create(this, R.raw.superwin);
        soundWin = MediaPlayer.create(this, R.raw.win);
        soundLose = MediaPlayer.create(this, R.raw.lose);
    }


    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.volver){
            soundPress.start();
            saveDB();
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        }else{
            soundPress.start();
            saveDB();
            Intent intent = new Intent(getApplicationContext(), JuegoActivity.class);
            startActivity(intent);
        }
    }

    private void saveDB(){
        DbPartidas db = new DbPartidas(EndActivity.this);
        long id = db.InsertarPartida(score,name);

        if(id>0)
            Toast.makeText(EndActivity.this, "Se ha guardado la partida",Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(EndActivity.this, "Error al guardar la partida",Toast.LENGTH_SHORT).show();
    }
}
