package com.oniriccreations.nonutapp.ui.actividades.items;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.oniriccreations.nonutapp.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.time.DateUtils;

public class ActividadRegistro extends AppCompatActivity {

    public Date currentTime = Calendar.getInstance().getTime();
    SimpleDateFormat dy = new SimpleDateFormat("yyyy");
    SimpleDateFormat dm = new SimpleDateFormat("M");
    SimpleDateFormat dd = new SimpleDateFormat("dd");
    final String sYear = dy.format(currentTime);
    //final String sYear = "2019";
    final String sMonth = dm.format(currentTime);
    //final String sMonth = "11";
    final String sDay = dd.format(currentTime);
    //final String sDay = "23";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_registro);

        final RadioButton psi = (RadioButton) findViewById(R.id.RBPornSi);
        final RadioButton pno = (RadioButton) findViewById(R.id.RBPornNo);
        final RadioButton msi = (RadioButton) findViewById(R.id.RBMast1);
        final RadioButton mno = (RadioButton) findViewById(R.id.RBMast2);
        final RadioButton fel = (RadioButton) findViewById(R.id.RBFeliz);
        final RadioButton reg = (RadioButton) findViewById(R.id.RBRegular);
        final RadioButton tri = (RadioButton) findViewById(R.id.RBTriste);
        EditText ETNotas = (EditText) findViewById(R.id.ETNotas);

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

    public void uploadReg(View view){
        boolean flag=true;

        boolean psi = ((RadioButton) findViewById(R.id.RBPornSi)).isChecked();
        boolean pno = ((RadioButton) findViewById(R.id.RBPornNo)).isChecked();
        boolean msi = ((RadioButton) findViewById(R.id.RBMast1)).isChecked();
        boolean mno = ((RadioButton) findViewById(R.id.RBMast2)).isChecked();
        boolean fel = ((RadioButton) findViewById(R.id.RBFeliz)).isChecked();
        boolean reg = ((RadioButton) findViewById(R.id.RBRegular)).isChecked();
        boolean tri = ((RadioButton) findViewById(R.id.RBTriste)).isChecked();
        EditText ETNotas = (EditText) findViewById(R.id.ETNotas);
        final String nota = ETNotas.getText().toString();

        boolean bPorn = false;
        boolean bMast = false;
        int bState = 0;

        if(psi){
            bPorn=true;
        } else if(pno){
            bPorn=false;
        } else{
            flag=false;
        }

        if(msi){
            bMast=true;
        } else if(mno){
            bMast=false;
        } else{
            flag=false;
        }

        if(fel){
            bState=1;
        } else if(reg){
            bState=2;
        } else if (tri){
            bState=3;
        } else {
            flag=false;
        }

        if(flag){

            final boolean bPornF = bPorn;
            final boolean bMastF = bMast;
            final int bStateF = bState;

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
                        rootRefF.child("users").child(uidF).child("Registros")
                                .child(sYear).child(sMonth).child(sDay).child("porn").setValue(bPornF);
                        rootRefF.child("users").child(uidF).child("Registros")
                                .child(sYear).child(sMonth).child(sDay).child("mast").setValue(bMastF);
                        rootRefF.child("users").child(uidF).child("Registros")
                                .child(sYear).child(sMonth).child(sDay).child("state").setValue(bStateF);
                        rootRefF.child("users").child(uidF).child("Registros")
                                .child(sYear).child(sMonth).child(sDay).child("notes").setValue(nota);

                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                }
            };
            uidRef.addListenerForSingleValueEvent(eventListener);

            Toast.makeText(this, "Registro guardado correctamente", Toast.LENGTH_LONG).show();
            finish();

        } else{
            Toast.makeText(this, "Favor de llenar todos los datos", Toast.LENGTH_LONG).show();
        }

    }

}
