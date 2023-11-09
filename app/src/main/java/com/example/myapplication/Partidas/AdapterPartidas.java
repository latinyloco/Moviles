package com.example.myapplication.Partidas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication.DB.Partida;
import com.example.myapplication.R;

import java.util.ArrayList;

public class AdapterPartidas extends BaseAdapter{

    private static LayoutInflater inflater = null;
    private ArrayList<Partida> l;
    private Context context;
    private int resourceLayout;


    //CONSTRUCTOR
    public AdapterPartidas(@NonNull Context context, int resource, ArrayList<Partida> l) {
        super();

        this.l = l;
        this.context = context;
        this.resourceLayout = resource;

        inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }


    //TAMAÑO DEL ADAPTER
    @Override
    public int getCount() {
        return l.size();
    }


    //OBTENER EL OBJETO DE LA LISTA (supongo)
    @Override
    public Object getItem(int position) {
        return l.get(position);
    }


    //RETORNAR POSICIÓN DE OBJETO
    @Override
    public long getItemId(int position) {
        return position;
    }


    //GETVIEW
    @NonNull
    @Override
    public View getView(int position, @Nullable View contentView, @NonNull ViewGroup parent) {

        ArrayList<Partida> mostrar = l;


        //que la vista se infle para mostrarse
        final View view = inflater.inflate(resourceLayout, null);


        //mostrar nombre del jugador
        TextView nombreJugadorR = view.findViewById(R.id.fechaPartida);
        nombreJugadorR.setText(l.get(position).getFecha());

        //mostrar puntos del jugador
        TextView numPuntosR = view.findViewById(R.id.nombrePartida);
        numPuntosR.setText(l.get(position).getNombre());

        //mostrar puntos del jugador
        TextView numCashR = view.findViewById(R.id.ptsPartida);
        numCashR.setText(Integer.toString(l.get(position).getPts()));


        return view;
    }

}
