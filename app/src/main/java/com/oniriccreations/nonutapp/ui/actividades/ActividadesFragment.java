package com.oniriccreations.nonutapp.ui.actividades;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.oniriccreations.nonutapp.R;

import java.util.ArrayList;
import java.util.List;

import static com.firebase.ui.auth.AuthUI.getApplicationContext;

public class ActividadesFragment extends Fragment {

    private ActividadesViewModel actividadesViewModel;
    private List<Item> listItem = new ArrayList<>();
    private RecyclerView recyclerView;
    private ItemAdapter itemAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        actividadesViewModel =
                ViewModelProviders.of(this).get(ActividadesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_actividades, container, false);
        actividadesViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

            }
        });
        return root;
    }

    //Equivale al onCreate
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        itemAdapter = new ItemAdapter(listItem);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(itemAdapter);
        initItemData();

    }

    private void initItemData(){
        Item item = new Item("Registro","Completa este registro cada día para monitorear el avance de tu recuperación.");
        listItem.add(item);
        item = new Item("Diario","Obtén un diario pequeño con el que puedas escribir cada vez que quieras recaer.");
        listItem.add(item);
        item = new Item("Carta","Escribe una carta dedicada a la adicción que procederás a destruir como simbolismo de tu nuevo ser.");
        listItem.add(item);
        item = new Item("Pensamientos limitantes","Llena un pequeño ejercicio para evaluar tus pensamientos negativos.");
        listItem.add(item);
        item = new Item("Cadena virtuosa","Cada que tengas un día limpio, registra aquí como te sentiste y los factores que influyeron.");
        listItem.add(item);
        item = new Item("Cadena viciosa","Cada que tengas un mal día y hayas recaído, registra aquí como te sentiste y los factores que influyeron.");
        listItem.add(item);
        itemAdapter.notifyDataSetChanged();
    }

}