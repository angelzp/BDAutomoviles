package com.example.angel.bdautomoviles.adaptadores;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.angel.bdautomoviles.R;
import com.example.angel.bdautomoviles.modelos.autos;

import java.util.List;

/**
 * Created by Angel on 12/05/2015.
 */
public class autosAdaptador extends RecyclerView.Adapter<autosAdaptador.autosViewHolder> {
    private List<autos> items;

    public static class autosViewHolder extends RecyclerView.ViewHolder{

        public TextView serie;
        public TextView marca;
        public TextView modelo;
        public TextView color;

        public autosViewHolder(View v){
            super(v);

            serie = (TextView) v.findViewById(R.id.n_serie);
            marca = (TextView) v.findViewById(R.id.n_marca);
            modelo = (TextView) v.findViewById(R.id.n_modelo);
            color = (TextView) v.findViewById(R.id.n_color);
        }
    }

    public autosAdaptador(List<autos> items){
        this.items = items;
    }

    @Override
    public autosViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_auto, viewGroup, false);
        return new autosViewHolder(v);
    }

    @Override
    public void onBindViewHolder(autosViewHolder viewHolder, int i) {
        viewHolder.serie.setText(String.valueOf(items.get(i).getSerie()));
        viewHolder.marca.setText(items.get(i).getMarca());
        viewHolder.modelo.setText(items.get(i).getModelo());
        viewHolder.color.setText(items.get(i).getColor());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
