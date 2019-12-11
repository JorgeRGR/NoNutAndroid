package com.oniriccreations.nonutapp.ui.actividades.items;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.oniriccreations.nonutapp.R;
import com.oniriccreations.nonutapp.ui.actividades.Cadena;
import com.oniriccreations.nonutapp.ui.actividades.CadenaAdapter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ActividadCadenaPos extends AppCompatActivity {

    private Date currentTime = Calendar.getInstance().getTime();
    private List<Cadena> listCadena = new ArrayList<>();
    private RecyclerView recyclerView;
    private CadenaAdapter cadenaAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_cadena_pos);

        EditText ETExt = (EditText) findViewById(R.id.ETExt);
        EditText ETInt = (EditText) findViewById(R.id.ETInt);
        EditText ETCre = (EditText) findViewById(R.id.ETCre);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        cadenaAdapter = new CadenaAdapter(listCadena);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(cadenaAdapter);

        //Cargar cadenas previas:

        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        final String uidF = uid;
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        final DatabaseReference rootRefF = rootRef;
        DatabaseReference uidRef = rootRef.child("users").child(uid);

        //Obtener lista
        uidRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                rootRefF.child("users").child(uidF).child("ultimaConexion").setValue(currentTime);
                Cadena cad;
                if(dataSnapshot.child("CadenaPositiva").child("num").getValue()!=null){
                    int num = dataSnapshot.child("CadenaPositiva").child("num").getValue(Integer.class);
                    for(int i=0;i<=num;i++){
                        String is = String.valueOf(i);
                        if(dataSnapshot.child("CadenaPositiva").child(is).getValue()!=null){
                            String extS = dataSnapshot.child("CadenaPositiva").child(is).child("externos").getValue(String.class);
                            String intS = dataSnapshot.child("CadenaPositiva").child(is).child("internos").getValue(String.class);
                            String creS = dataSnapshot.child("CadenaPositiva").child(is).child("creencias").getValue(String.class);

                            //Log.d(is, extS);
                            //Log.d(is, intS);
                            //Log.d(is, creS);

                            cad = new Cadena(extS,intS,creS);
                            listCadena.add(cad);
                            cadenaAdapter.notifyDataSetChanged();
                        }
                    }
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

    }

    public void uploadCadPos(View view){
        boolean flag=true;

        EditText ETExt = (EditText) findViewById(R.id.ETExt);
        EditText ETInt = (EditText) findViewById(R.id.ETInt);
        EditText ETCre = (EditText) findViewById(R.id.ETCre);

        if (ETExt.getText().toString().equals("") || ETInt.getText().toString().equals("") || ETCre.getText().toString().equals("")){
            flag=false;
        }

        if(flag){
            String ETES = ETExt.getText().toString();
            String ETIS = ETInt.getText().toString();
            String ETCS = ETCre.getText().toString();

            //Register in DB
            String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
            final String uidF = uid;
            DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
            final DatabaseReference rootRefF = rootRef;
            DatabaseReference uidRef = rootRef.child("users").child(uid);
            ValueEventListener eventListener = new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        rootRefF.child("users").child(uidF).child("ultimaConexion").setValue(currentTime);

                        //Set
                        if(dataSnapshot.child("CadenaPositiva").child("num").getValue()==null){
                            rootRefF.child("users").child(uidF).child("CadenaPositiva").child("num").setValue(0);

                            rootRefF.child("users").child(uidF).child("CadenaPositiva").child("0").child("externos").setValue(ETES);
                            rootRefF.child("users").child(uidF).child("CadenaPositiva").child("0").child("internos").setValue(ETIS);
                            rootRefF.child("users").child(uidF).child("CadenaPositiva").child("0").child("creencias").setValue(ETCS);
                        } else {
                            int num = dataSnapshot.child("CadenaPositiva").child("num").getValue(Integer.class);
                            num++;
                            rootRefF.child("users").child(uidF).child("CadenaPositiva").child("num").setValue(num);
                            String numS = String.valueOf(num);
                            rootRefF.child("users").child(uidF).child("CadenaPositiva").child(numS).child("externos").setValue(ETES);
                            rootRefF.child("users").child(uidF).child("CadenaPositiva").child(numS).child("internos").setValue(ETIS);
                            rootRefF.child("users").child(uidF).child("CadenaPositiva").child(numS).child("creencias").setValue(ETCS);
                        }

                    }

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                }
            };
            uidRef.addListenerForSingleValueEvent(eventListener);

            Toast.makeText(this, "Cadena guardada correctamente", Toast.LENGTH_LONG).show();
            finish();

        } else {
            Toast.makeText(this, "Favor de llenar todos los datos antes de continuar.", Toast.LENGTH_LONG).show();
        }

    }
}
