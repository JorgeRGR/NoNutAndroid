package com.oniriccreations.nonutapp.ui.perfil;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.oniriccreations.nonutapp.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class PerfilFragment extends Fragment {

    Activity activity = getActivity();

    private PerfilViewModel perfilViewModel;
    public Date currentTime = Calendar.getInstance().getTime();

    private EditText ETNombre;
    private EditText ETAlimen;
    private EditText ETSueno;
    private EditText ETSocial;
    private ImageView IVFoto;
    private TextView TVCorreo;
    private TextView TVNombre;
    private TextView TVSeg; //Dias seguidos totales
    private TextView TVTot; //Dias limpios totales
    private Button bCambiar;
    private Button bActualizar;
    private Button bLogout;

    public List<Boolean> pornList = new ArrayList<>();
    public List<Boolean> mastList = new ArrayList<>();
    public int segPorn=0,totPorn=0,segMast=0,totMast=0;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        perfilViewModel =
                ViewModelProviders.of(this).get(PerfilViewModel.class);
        View view = inflater.inflate(R.layout.fragment_perfil, container, false);

        return view;
    }

    //Equivale al onCreate
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        ETNombre = (EditText) view.findViewById(R.id.ETNombre);
        ETAlimen = (EditText) view.findViewById(R.id.ETAlimen);
        ETSueno = (EditText) view.findViewById(R.id.ETSueno);
        ETSocial = (EditText) view.findViewById(R.id.ETSocial);
        TVCorreo = (TextView) view.findViewById(R.id.TVCorreo);
        TVNombre = (TextView) view.findViewById(R.id.TVNombre);
        TVSeg = (TextView) view.findViewById(R.id.TVSeg);
        TVTot = (TextView) view.findViewById(R.id.TVTot);
        bActualizar = (Button) view.findViewById(R.id.bActualizar);

        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        final String uidF = uid;
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        final DatabaseReference rootRefF = rootRef;
        DatabaseReference uidRef = rootRef.child("users").child(uid);
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        uidRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                rootRefF.child("users").child(uidF).child("ultimaConexion").setValue(currentTime);

                TVCorreo.setText("Correo: " + user.getEmail());
                //Obtener todos los valores
                if(dataSnapshot.child("nombre").getValue()!=null){
                    ETNombre.setText(dataSnapshot.child("nombre").getValue().toString());
                }

                if(dataSnapshot.child("alimentacion").getValue()!=null){
                    ETAlimen.setText(dataSnapshot.child("alimentacion").getValue().toString());
                }

                if(dataSnapshot.child("sueno").getValue()!=null){
                    ETSueno.setText(dataSnapshot.child("sueno").getValue().toString());
                }

                if(dataSnapshot.child("social").getValue()!=null){
                    ETSocial.setText(dataSnapshot.child("social").getValue().toString());
                }

                //Lista
                for (DataSnapshot yearSnapshot: dataSnapshot.child("Registros").getChildren()) {
                    String sYear = String.valueOf(yearSnapshot.getKey());
                    int iYear = Integer.parseInt(sYear);
                    //Log.d("Year", ""+iYear);

                    for (DataSnapshot monthSnapshot: dataSnapshot.child("Registros").child(sYear).getChildren()) {
                        String sMonth = String.valueOf(monthSnapshot.getKey());
                        int iMonth = Integer.parseInt(sMonth);
                        //Log.d("Month", ""+iMonth);

                        for (DataSnapshot daySnapshot: dataSnapshot.child("Registros").child(sYear).child(sMonth).getChildren()) {
                            String sDay = String.valueOf(daySnapshot.getKey());
                            int iDay = Integer.parseInt(sDay);
                            //Log.d("Day", ""+iDay);

                            if(dataSnapshot.child("Registros").child(sYear).child(sMonth).child(sDay).child("porn").getValue()!=null){
                                boolean bpo=dataSnapshot.child("Registros").child(sYear).child(sMonth).child(sDay).child("porn").getValue(boolean.class);
                                pornList.add(!bpo);

                            }

                            if(dataSnapshot.child("Registros").child(sYear).child(sMonth).child(sDay).child("mast").getValue()!=null){
                                boolean bma=dataSnapshot.child("Registros").child(sYear).child(sMonth).child(sDay).child("mast").getValue(boolean.class);
                                mastList.add(!bma);

                            }

                        }

                    }

                }
                //Actualizar listas
                updateLists();
                //Actualizar TextView
                TVSeg.setText("Días limpios seguidos: "+segPorn+" de pornografía, "+segMast+" de masturbación");
                TVTot.setText("Días limpios totales: "+totPorn+" de pornografía, "+totMast+" de masturbación");
                //Actualizar trofeos
                updateAwards(view);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

    }

    public void updateLists(){
        for(int i=0;i<pornList.size();i++) {
            if (pornList.get(i)) {
                totPorn++;
                segPorn++;
            } else {
                segPorn=0;
            }
        }
        for(int i=0;i<mastList.size();i++) {
            if (mastList.get(i)) {
                totMast++;
                segMast++;
            } else {
                segMast=0;
            }
        }

    }

    public void updateAwards(View view){
        ImageView IV1 = view.findViewById(R.id.IV1);
        ImageView IV2 = view.findViewById(R.id.IV2);
        ImageView IV3 = view.findViewById(R.id.IV3);
        ImageView IV4 = view.findViewById(R.id.IV4);
        ImageView IV5 = view.findViewById(R.id.IV5);
        ImageView IV6 = view.findViewById(R.id.IV6);
        ImageView IV7 = view.findViewById(R.id.IV7);
        ImageView IV8 = view.findViewById(R.id.IV8);

        if(segPorn>=1 && segMast>=1){
            IV1.setImageResource(R.drawable.day1);
        }
        if(segPorn>=3 && segMast>=3){
            IV2.setImageResource(R.drawable.day3);
        }
        if(segPorn>=7 && segMast>=7){
            IV3.setImageResource(R.drawable.day7);
        }
        if(segPorn>=14 && segMast>=14){
            IV4.setImageResource(R.drawable.day14);
        }
        if(segPorn>=30 && segMast>=30){
            IV5.setImageResource(R.drawable.month1);
        }
        if(segPorn>=90 && segMast>=90){
            IV6.setImageResource(R.drawable.month3);
        }
        if(segPorn>=182 && segMast>=182){
            IV7.setImageResource(R.drawable.month6);
        }
        if(segPorn>=365 && segMast>=365){
            IV8.setImageResource(R.drawable.year);
        }

    }

}