package com.example.myapplication.DB;

import static com.example.myapplication.DB.DbHelper.TABLE_PARTIDAS;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DbPartidas {

    Context context;

    public DbPartidas(@Nullable Context context) {
        super();
        this.context = context;
    }

    public long InsertarPartida(int score, String name) {

        long id = 0;

        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();     //creamos variable de la base de datos

            String fecha = this.getDatePhone();

            //Crear contenedor para los valores
            ContentValues jugador = new ContentValues();

            jugador.put("fecha",fecha);
            jugador.put("nombre",name);
            jugador.put("pts",score);

            id = db.insert(TABLE_PARTIDAS, null, jugador);


        }catch (Exception ex){
            ex.toString();
        }
        return id;

    }

    public ArrayList<Partida> mostrarPartidas(){

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();     //creamos variable de la base de datos

        ArrayList<Partida> lp = new ArrayList<>();
        Partida p;
        Cursor cursorJugador;

        cursorJugador = db.rawQuery("SELECT * FROM "+TABLE_PARTIDAS,null);

        if(cursorJugador.moveToFirst()){
            do{

                p = new Partida(null,null,0);

                p.setFecha(cursorJugador.getString(0));
                p.setNombre(cursorJugador.getString(1));
                p.setPts(cursorJugador.getInt(2));

                lp.add(p);

            }while(cursorJugador.moveToNext());
        }

        return lp;
    }


    //DEVOLVER FECHA ACTUAL
    private String getDatePhone() {
        Calendar cal = new GregorianCalendar();
        Date date = cal.getTime();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        String formatteDate = df.format(date);
        return formatteDate;
    }
}
