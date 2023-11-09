package com.example.myapplication.Partidas;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Game.JuegoActivity;
import com.example.myapplication.R;

public class NombreActivity extends AppCompatActivity implements View.OnClickListener{

    private Button empezar;
    private EditText nombre;
    private String name;


    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nombre);

        empezar = findViewById(R.id.empezar);
        empezar.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.empezar) {

            nombre = findViewById(R.id.nombre);
            name = String.valueOf(nombre.getText());

            if(name.equals("Nombre")){
                Toast.makeText(NombreActivity.this, "Introduce tu nombre",Toast.LENGTH_SHORT).show();
            }else{
                Intent intent = new Intent(getApplicationContext(), JuegoActivity.class);
                intent.putExtra("nombre",name);   //para pasar los puntos de un activity a otro
                startActivity(intent);
            }
        }
    }
}
