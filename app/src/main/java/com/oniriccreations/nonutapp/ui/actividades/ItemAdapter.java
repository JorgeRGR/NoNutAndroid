package com.oniriccreations.nonutapp.ui.actividades;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.oniriccreations.nonutapp.R;
import com.oniriccreations.nonutapp.ui.actividades.items.ActividadCadenaNeg;
import com.oniriccreations.nonutapp.ui.actividades.items.ActividadCadenaPos;
import com.oniriccreations.nonutapp.ui.actividades.items.ActividadCarta;
import com.oniriccreations.nonutapp.ui.actividades.items.ActividadDiario;
import com.oniriccreations.nonutapp.ui.actividades.items.ActividadPensamientos;
import com.oniriccreations.nonutapp.ui.actividades.items.ActividadRegistro;

import java.util.Calendar;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder>{

    private List<Item> itemList;
    private Intent intent;

    public ItemAdapter(List<Item> itemList) {
        this.itemList = itemList;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_row, parent, false);

        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, final int position) {
        holder.title.setText(itemList.get(position).getTitle());
        holder.description.setText(itemList.get(position).getDescription());

        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                int currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY); //Current hour

                Context context = v.getContext();
                switch (position){
                    case 0:
                        //Debe de ser después de las 8 pm para poder registrar el día
                        if(currentHour >=0){
                            intent =  new Intent(context, ActividadRegistro.class);
                            context.startActivity(intent);
                        } else {
                            Toast.makeText(context,"Sólo puedes registrar tu día después de las 8 P.M.",Toast.LENGTH_LONG).show();
                        }
                        break;
                    case 1:
                        intent =  new Intent(context, ActividadDiario.class);
                        context.startActivity(intent);
                        break;
                    case 2:
                        intent =  new Intent(context, ActividadCarta.class);
                        context.startActivity(intent);
                        break;
                    case 3:
                        intent =  new Intent(context, ActividadPensamientos.class);
                        context.startActivity(intent);
                        break;
                    case 4:
                        intent =  new Intent(context, ActividadCadenaPos.class);
                        context.startActivity(intent);
                        break;
                    case 5:
                        intent =  new Intent(context, ActividadCadenaNeg.class);
                        context.startActivity(intent);
                        break;
                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder{
        public TextView title;
        public TextView description;

        public ItemViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            description = (TextView) view.findViewById(R.id.description);
        }

    }

}
