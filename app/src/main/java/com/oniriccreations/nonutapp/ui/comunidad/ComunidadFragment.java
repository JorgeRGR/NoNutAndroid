package com.oniriccreations.nonutapp.ui.comunidad;

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

import com.oniriccreations.nonutapp.R;

public class ComunidadFragment extends Fragment {

    private ComunidadViewModel comunidadViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        comunidadViewModel =
                ViewModelProviders.of(this).get(ComunidadViewModel.class);
        View root = inflater.inflate(R.layout.fragment_comunidad, container, false);
        comunidadViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

            }
        });
        return root;
    }
}