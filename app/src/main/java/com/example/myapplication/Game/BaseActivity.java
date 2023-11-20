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

public class BaseActivity extends AppCompatActivity{

    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        instancias();

    }


    private void instancias() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }
}