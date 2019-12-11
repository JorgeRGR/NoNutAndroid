package com.oniriccreations.nonutapp.ui.actividades;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.oniriccreations.nonutapp.R;

import java.util.Calendar;
import java.util.List;
import androidx.recyclerview.widget.RecyclerView;

public class CadenaAdapter extends RecyclerView.Adapter<CadenaAdapter.CadenaViewHolder>{

    private List<Cadena> cadenaList;
    private Intent intent;

    public CadenaAdapter(List<Cadena> cadenaList) {
        this.cadenaList = cadenaList;
    }

    @Override
    public CadenaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View cadenaView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cadena_row, parent, false);

        return new CadenaViewHolder(cadenaView);
    }

    @Override
    public void onBindViewHolder(CadenaViewHolder holder, final int position) {
        holder.externo.setText(cadenaList.get(position).getExterno());
        holder.interno.setText(cadenaList.get(position).getInterno());
        holder.creencia.setText(cadenaList.get(position).getCreencia());
    }

    @Override
    public int getItemCount() {
        return cadenaList.size();
    }

    public class CadenaViewHolder extends RecyclerView.ViewHolder{
        public TextView externo;
        public TextView interno;
        public TextView creencia;

        public CadenaViewHolder(View view) {
            super(view);
            externo = (TextView) view.findViewById(R.id.externo);
            interno = (TextView) view.findViewById(R.id.interno);
            creencia = (TextView) view.findViewById(R.id.creencia);
        }

    }

}
