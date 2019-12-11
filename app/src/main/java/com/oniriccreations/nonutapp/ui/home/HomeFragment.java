package com.oniriccreations.nonutapp.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import com.oniriccreations.nonutapp.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    public Date currentTime = Calendar.getInstance().getTime();
    Date c = Calendar.getInstance().getTime();
    SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
    String fDate = df.format(c);
    private TextView TVNombre;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

            }
        });

        return root;
    }

    //Equivale al onCreate
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        TVNombre = (TextView) view.findViewById(R.id.TVNombre);

        if (FirebaseAuth.getInstance().getCurrentUser() != null){

            String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
            final String uidF = uid;
            DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
            final DatabaseReference rootRefF = rootRef;
            DatabaseReference uidRef = rootRef.child("users").child(uid);
            final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            //Toast.makeText(getActivity(), ""+fDate, Toast.LENGTH_LONG).show();
            TVNombre.setText("No tienes nuevas notificaciones.");

            uidRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    rootRefF.child("users").child(uidF).child("ultimaConexion").setValue(currentTime);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                }
            });
        }

    }
}