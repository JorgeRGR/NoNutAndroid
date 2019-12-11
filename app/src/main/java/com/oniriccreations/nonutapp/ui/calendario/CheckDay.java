package com.oniriccreations.nonutapp.ui.calendario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.applandeo.materialcalendarview.EventDay;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.oniriccreations.nonutapp.R;

import java.util.Calendar;
import java.util.Date;

public class CheckDay extends AppCompatActivity {

    TextView TVRegistro;
    public Date currentTime = Calendar.getInstance().getTime();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_day);
        TVRegistro = (TextView) findViewById(R.id.TVRegistro);
        Intent intent = getIntent();
        int year = intent.getIntExtra("Year",0);
        int month = intent.getIntExtra("Month",0);
        int day = intent.getIntExtra("Day",0);

        String sYear = String.valueOf(year);
        String sMonth = String.valueOf(month);
        String sDayTemp = String.valueOf(day);
        if(day<10){
            sDayTemp="0"+sDayTemp;
        }
        String sDay = sDayTemp;

        TVRegistro.setText("Registro de la fecha "+sDay+"/"+sMonth+"/"+sYear);
        final RadioButton psi = (RadioButton) findViewById(R.id.RBPornSi);
        final RadioButton pno = (RadioButton) findViewById(R.id.RBPornNo);
        final RadioButton msi = (RadioButton) findViewById(R.id.RBMast1);
        final RadioButton mno = (RadioButton) findViewById(R.id.RBMast2);
        final RadioButton fel = (RadioButton) findViewById(R.id.RBFeliz);
        final RadioButton reg = (RadioButton) findViewById(R.id.RBRegular);
        final RadioButton tri = (RadioButton) findViewById(R.id.RBTriste);
        EditText ETNotas = (EditText) findViewById(R.id.ETNotas);
        ETNotas.setKeyListener(null);

        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        final String uidF = uid;
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        final DatabaseReference rootRefF = rootRef;
        DatabaseReference uidRef = rootRef.child("users").child(uid);

        uidRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                rootRefF.child("users").child(uidF).child("ultimaConexion").setValue(currentTime);
                //Obtener todos los valores
                if(dataSnapshot.child("Registros").child(sYear).child(sMonth).child(sDay).child("porn").getValue()!=null){
                    boolean dsp=dataSnapshot.child("Registros").child(sYear).child(sMonth).child(sDay).child("porn").getValue(boolean.class);
                    if(dsp){
                        psi.setChecked(true);
                    } else {
                        pno.setChecked(true);
                    }
                }

                if(dataSnapshot.child("Registros").child(sYear).child(sMonth).child(sDay).child("mast").getValue()!=null){
                    boolean dsm=dataSnapshot.child("Registros").child(sYear).child(sMonth).child(sDay).child("mast").getValue(boolean.class);
                    if(dsm){
                        msi.setChecked(true);
                    } else {
                        mno.setChecked(true);
                    }
                }

                if(dataSnapshot.child("Registros").child(sYear).child(sMonth).child(sDay).child("state").getValue()!=null){
                    int dss=dataSnapshot.child("Registros").child(sYear).child(sMonth).child(sDay).child("state").getValue(int.class);
                    if(dss==1){
                        fel.setChecked(true);
                    } else if(dss==2){
                        reg.setChecked(true);
                    } else if(dss==3){
                        tri.setChecked(true);
                    }
                }

                if(dataSnapshot.child("Registros").child(sYear).child(sMonth).child(sDay).child("notes").getValue()!=null){
                    String dsn=String.valueOf(dataSnapshot.child("Registros").child(sYear).child(sMonth).child(sDay).child("notes").getValue());
                    ETNotas.setText(dsn);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });


    }
}
