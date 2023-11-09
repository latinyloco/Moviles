package com.example.myapplication.Partidas;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.DB.DbPartidas;
import com.example.myapplication.DB.Partida;
import com.example.myapplication.R;

import java.util.ArrayList;


public class PartidasActivity extends AppCompatActivity {

    private ArrayList<Partida> l;
    private ListView jugadoresPerfil;
    private AdapterPartidas mApapter;
    private ImageView atras;

    protected void onCreate(Bundle savedIntenceState) {

        super.onCreate(savedIntenceState);
        setContentView(R.layout.activity_partidas);


        DbPartidas db = new DbPartidas(this);
        l = db.mostrarPartidas();


        /******* LISTA JUGADORES *******/
        mApapter = new AdapterPartidas(this, R.layout.adapter_partidas,l);
        jugadoresPerfil = findViewById(R.id.jugadoresJugadores);
        jugadoresPerfil.setAdapter(mApapter);




        /******* BOTONES *******/
        atras = findViewById(R.id.atras);
        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();    //volver atrás
            }
        });

    }

}
