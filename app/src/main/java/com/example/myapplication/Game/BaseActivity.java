package com.example.myapplication.Game;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.myapplication.Game.MainActivity;
import com.example.myapplication.Partidas.PartidasActivity;
import com.example.myapplication.R;

public class BaseActivity extends AppCompatActivity implements View.OnClickListener {

    Toolbar toolbar;
    ImageButton btnHome;
    TextView textoTitulo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        instancias();
        acciones();
    }

    private void instancias() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btnHome = findViewById(R.id.btnHome);
        textoTitulo = findViewById(R.id.textoTitulo);
    }

    private void acciones(){
        btnHome.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Log.d("BaseActivity", "Click en btnHome"); // Agrega este log
        if (v.getId() == R.id.btnHome){
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        }
    }
}