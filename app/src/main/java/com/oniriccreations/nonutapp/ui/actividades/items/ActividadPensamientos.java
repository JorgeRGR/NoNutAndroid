package com.oniriccreations.nonutapp.ui.actividades.items;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.oniriccreations.nonutapp.R;

import java.util.Calendar;
import java.util.Date;

public class ActividadPensamientos extends AppCompatActivity {

    public Date currentTime = Calendar.getInstance().getTime();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_pensamientos);

        final CheckBox CB1 = (CheckBox) findViewById(R.id.CB1);
        final CheckBox CB2 = (CheckBox) findViewById(R.id.CB2);
        final CheckBox CB3 = (CheckBox) findViewById(R.id.CB3);
        final CheckBox CB4 = (CheckBox) findViewById(R.id.CB4);
        final CheckBox CB5 = (CheckBox) findViewById(R.id.CB5);
        final CheckBox CB6 = (CheckBox) findViewById(R.id.CB6);
        final CheckBox CB7 = (CheckBox) findViewById(R.id.CB7);
        final CheckBox CB8 = (CheckBox) findViewById(R.id.CB8);
        final CheckBox CB9 = (CheckBox) findViewById(R.id.CB9);
        final CheckBox CB10 = (CheckBox) findViewById(R.id.CB10);
        final CheckBox CB11 = (CheckBox) findViewById(R.id.CB11);
        final CheckBox CB12 = (CheckBox) findViewById(R.id.CB12);
        final CheckBox CB13 = (CheckBox) findViewById(R.id.CB13);
        final CheckBox CB14 = (CheckBox) findViewById(R.id.CB14);
        final CheckBox CB15 = (CheckBox) findViewById(R.id.CB15);
        final CheckBox CB16 = (CheckBox) findViewById(R.id.CB16);
        final CheckBox CB17 = (CheckBox) findViewById(R.id.CB17);
        final CheckBox CB18 = (CheckBox) findViewById(R.id.CB18);
        final CheckBox CB19 = (CheckBox) findViewById(R.id.CB19);
        final CheckBox CB20 = (CheckBox) findViewById(R.id.CB20);
        final CheckBox CB21 = (CheckBox) findViewById(R.id.CB21);
        final CheckBox CB22 = (CheckBox) findViewById(R.id.CB22);
        final CheckBox CB23 = (CheckBox) findViewById(R.id.CB23);
        final CheckBox CB24 = (CheckBox) findViewById(R.id.CB24);
        final CheckBox CB25 = (CheckBox) findViewById(R.id.CB25);
        final CheckBox CB26 = (CheckBox) findViewById(R.id.CB26);
        final CheckBox CB27 = (CheckBox) findViewById(R.id.CB27);
        final CheckBox CB28 = (CheckBox) findViewById(R.id.CB28);
        final CheckBox CB29 = (CheckBox) findViewById(R.id.CB29);
        final CheckBox CB30 = (CheckBox) findViewById(R.id.CB30);

        //Respuestas Mal
        EditText ETMal1 = (EditText) findViewById(R.id.ETMal1);
        EditText ETMal2 = (EditText) findViewById(R.id.ETMal2);
        EditText ETMal3 = (EditText) findViewById(R.id.ETMal3);
        EditText ETMal4 = (EditText) findViewById(R.id.ETMal4);
        EditText ETMal5 = (EditText) findViewById(R.id.ETMal5);
        EditText ETMal6 = (EditText) findViewById(R.id.ETMal6);
        EditText ETMal7 = (EditText) findViewById(R.id.ETMal7);
        EditText ETMal8 = (EditText) findViewById(R.id.ETMal8);
        EditText ETMal9 = (EditText) findViewById(R.id.ETMal9);
        EditText ETMal10 = (EditText) findViewById(R.id.ETMal10);
        EditText ETMal11 = (EditText) findViewById(R.id.ETMal11);
        EditText ETMal12 = (EditText) findViewById(R.id.ETMal12);
        EditText ETMal13 = (EditText) findViewById(R.id.ETMal13);
        EditText ETMal14 = (EditText) findViewById(R.id.ETMal14);
        EditText ETMal15 = (EditText) findViewById(R.id.ETMal15);
        EditText ETMal16 = (EditText) findViewById(R.id.ETMal16);
        EditText ETMal17 = (EditText) findViewById(R.id.ETMal17);
        EditText ETMal18 = (EditText) findViewById(R.id.ETMal18);
        EditText ETMal19 = (EditText) findViewById(R.id.ETMal19);
        EditText ETMal20 = (EditText) findViewById(R.id.ETMal20);
        EditText ETMal21 = (EditText) findViewById(R.id.ETMal21);
        EditText ETMal22 = (EditText) findViewById(R.id.ETMal22);
        EditText ETMal23 = (EditText) findViewById(R.id.ETMal23);
        EditText ETMal24 = (EditText) findViewById(R.id.ETMal24);
        EditText ETMal25 = (EditText) findViewById(R.id.ETMal25);
        EditText ETMal26 = (EditText) findViewById(R.id.ETMal26);
        EditText ETMal27 = (EditText) findViewById(R.id.ETMal27);
        EditText ETMal28 = (EditText) findViewById(R.id.ETMal28);
        EditText ETMal29 = (EditText) findViewById(R.id.ETMal29);
        EditText ETMal30 = (EditText) findViewById(R.id.ETMal30);

        //Respuestas bien
        EditText ETBien1 = (EditText) findViewById(R.id.ETBien1);
        EditText ETBien2 = (EditText) findViewById(R.id.ETBien2);
        EditText ETBien3 = (EditText) findViewById(R.id.ETBien3);
        EditText ETBien4 = (EditText) findViewById(R.id.ETBien4);
        EditText ETBien5 = (EditText) findViewById(R.id.ETBien5);
        EditText ETBien6 = (EditText) findViewById(R.id.ETBien6);
        EditText ETBien7 = (EditText) findViewById(R.id.ETBien7);
        EditText ETBien8 = (EditText) findViewById(R.id.ETBien8);
        EditText ETBien9 = (EditText) findViewById(R.id.ETBien9);
        EditText ETBien10 = (EditText) findViewById(R.id.ETBien10);
        EditText ETBien11 = (EditText) findViewById(R.id.ETBien11);
        EditText ETBien12 = (EditText) findViewById(R.id.ETBien12);
        EditText ETBien13 = (EditText) findViewById(R.id.ETBien13);
        EditText ETBien14 = (EditText) findViewById(R.id.ETBien14);
        EditText ETBien15 = (EditText) findViewById(R.id.ETBien15);
        EditText ETBien16 = (EditText) findViewById(R.id.ETBien16);
        EditText ETBien17 = (EditText) findViewById(R.id.ETBien17);
        EditText ETBien18 = (EditText) findViewById(R.id.ETBien18);
        EditText ETBien19 = (EditText) findViewById(R.id.ETBien19);
        EditText ETBien20 = (EditText) findViewById(R.id.ETBien20);
        EditText ETBien21 = (EditText) findViewById(R.id.ETBien21);
        EditText ETBien22 = (EditText) findViewById(R.id.ETBien22);
        EditText ETBien23 = (EditText) findViewById(R.id.ETBien23);
        EditText ETBien24 = (EditText) findViewById(R.id.ETBien24);
        EditText ETBien25 = (EditText) findViewById(R.id.ETBien25);
        EditText ETBien26 = (EditText) findViewById(R.id.ETBien26);
        EditText ETBien27 = (EditText) findViewById(R.id.ETBien27);
        EditText ETBien28 = (EditText) findViewById(R.id.ETBien28);
        EditText ETBien29 = (EditText) findViewById(R.id.ETBien29);
        EditText ETBien30 = (EditText) findViewById(R.id.ETBien30);

        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        final String uidF = uid;
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        final DatabaseReference rootRefF = rootRef;
        DatabaseReference uidRef = rootRef.child("users").child(uid).child("pensamientos");

        uidRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                rootRefF.child("users").child(uidF).child("ultimaConexion").setValue(currentTime);
                //Obtener todos los valores

                if(dataSnapshot.child("p1").getValue() !=null){
                    CB1.setChecked(true);
                    String sm=String.valueOf(dataSnapshot.child("p1").child("mal").getValue());
                    String sb=String.valueOf(dataSnapshot.child("p1").child("bien").getValue());
                    ETMal1.setText(sm);
                    ETBien1.setText(sb);
                }

                if(dataSnapshot.child("p2").getValue() !=null){
                    CB2.setChecked(true);
                    String sm2=String.valueOf(dataSnapshot.child("p2").child("mal").getValue());
                    String sb2=String.valueOf(dataSnapshot.child("p2").child("bien").getValue());
                    ETMal2.setText(sm2);
                    ETBien2.setText(sb2);
                }

                if(dataSnapshot.child("p3").getValue() !=null){
                    CB3.setChecked(true);
                    String sm=String.valueOf(dataSnapshot.child("p3").child("mal").getValue());
                    String sb=String.valueOf(dataSnapshot.child("p3").child("bien").getValue());
                    ETMal3.setText(sm);
                    ETBien3.setText(sb);
                }

                if(dataSnapshot.child("p4").getValue() !=null){
                    CB4.setChecked(true);
                    String sm=String.valueOf(dataSnapshot.child("p4").child("mal").getValue());
                    String sb=String.valueOf(dataSnapshot.child("p4").child("bien").getValue());
                    ETMal4.setText(sm);
                    ETBien4.setText(sb);
                }

                if(dataSnapshot.child("p5").getValue() !=null){
                    CB5.setChecked(true);
                    String sm=String.valueOf(dataSnapshot.child("p5").child("mal").getValue());
                    String sb=String.valueOf(dataSnapshot.child("p5").child("bien").getValue());
                    ETMal5.setText(sm);
                    ETBien5.setText(sb);
                }

                if(dataSnapshot.child("p6").getValue() !=null){
                    CB6.setChecked(true);
                    String sm=String.valueOf(dataSnapshot.child("p6").child("mal").getValue());
                    String sb=String.valueOf(dataSnapshot.child("p6").child("bien").getValue());
                    ETMal6.setText(sm);
                    ETBien6.setText(sb);
                }

                if(dataSnapshot.child("p7").getValue() !=null){
                    CB7.setChecked(true);
                    String sm=String.valueOf(dataSnapshot.child("p7").child("mal").getValue());
                    String sb=String.valueOf(dataSnapshot.child("p7").child("bien").getValue());
                    ETMal7.setText(sm);
                    ETBien7.setText(sb);
                }

                if(dataSnapshot.child("p8").getValue() !=null){
                    CB8.setChecked(true);
                    String sm=String.valueOf(dataSnapshot.child("p8").child("mal").getValue());
                    String sb=String.valueOf(dataSnapshot.child("p8").child("bien").getValue());
                    ETMal8.setText(sm);
                    ETBien8.setText(sb);
                }

                if(dataSnapshot.child("p9").getValue() !=null){
                    CB9.setChecked(true);
                    String sm=String.valueOf(dataSnapshot.child("p9").child("mal").getValue());
                    String sb=String.valueOf(dataSnapshot.child("p9").child("bien").getValue());
                    ETMal9.setText(sm);
                    ETBien9.setText(sb);
                }

                if(dataSnapshot.child("p10").getValue() !=null){
                    CB10.setChecked(true);
                    String sm=String.valueOf(dataSnapshot.child("p10").child("mal").getValue());
                    String sb=String.valueOf(dataSnapshot.child("p10").child("bien").getValue());
                    ETMal10.setText(sm);
                    ETBien10.setText(sb);
                }

                if(dataSnapshot.child("p11").getValue() !=null){
                    CB11.setChecked(true);
                    String sm=String.valueOf(dataSnapshot.child("p11").child("mal").getValue());
                    String sb=String.valueOf(dataSnapshot.child("p11").child("bien").getValue());
                    ETMal11.setText(sm);
                    ETBien11.setText(sb);
                }

                if(dataSnapshot.child("p12").getValue() !=null){
                    CB12.setChecked(true);
                    String sm=String.valueOf(dataSnapshot.child("p12").child("mal").getValue());
                    String sb=String.valueOf(dataSnapshot.child("p12").child("bien").getValue());
                    ETMal12.setText(sm);
                    ETBien12.setText(sb);
                }

                if(dataSnapshot.child("p13").getValue() !=null){
                    CB13.setChecked(true);
                    String sm=String.valueOf(dataSnapshot.child("p13").child("mal").getValue());
                    String sb=String.valueOf(dataSnapshot.child("p13").child("bien").getValue());
                    ETMal13.setText(sm);
                    ETBien13.setText(sb);
                }

                if(dataSnapshot.child("p14").getValue() !=null){
                    CB14.setChecked(true);
                    String sm=String.valueOf(dataSnapshot.child("p14").child("mal").getValue());
                    String sb=String.valueOf(dataSnapshot.child("p14").child("bien").getValue());
                    ETMal14.setText(sm);
                    ETBien14.setText(sb);
                }

                if(dataSnapshot.child("p15").getValue() !=null){
                    CB15.setChecked(true);
                    String sm=String.valueOf(dataSnapshot.child("p15").child("mal").getValue());
                    String sb=String.valueOf(dataSnapshot.child("p15").child("bien").getValue());
                    ETMal15.setText(sm);
                    ETBien15.setText(sb);
                }

                if(dataSnapshot.child("p16").getValue() !=null){
                    CB16.setChecked(true);
                    String sm=String.valueOf(dataSnapshot.child("p16").child("mal").getValue());
                    String sb=String.valueOf(dataSnapshot.child("p16").child("bien").getValue());
                    ETMal16.setText(sm);
                    ETBien16.setText(sb);
                }

                if(dataSnapshot.child("p17").getValue() !=null){
                    CB17.setChecked(true);
                    String sm=String.valueOf(dataSnapshot.child("p17").child("mal").getValue());
                    String sb=String.valueOf(dataSnapshot.child("p17").child("bien").getValue());
                    ETMal17.setText(sm);
                    ETBien17.setText(sb);
                }

                if(dataSnapshot.child("p18").getValue() !=null){
                    CB18.setChecked(true);
                    String sm=String.valueOf(dataSnapshot.child("p18").child("mal").getValue());
                    String sb=String.valueOf(dataSnapshot.child("p18").child("bien").getValue());
                    ETMal18.setText(sm);
                    ETBien18.setText(sb);
                }

                if(dataSnapshot.child("p19").getValue() !=null){
                    CB19.setChecked(true);
                    String sm=String.valueOf(dataSnapshot.child("p19").child("mal").getValue());
                    String sb=String.valueOf(dataSnapshot.child("p19").child("bien").getValue());
                    ETMal19.setText(sm);
                    ETBien19.setText(sb);
                }

                if(dataSnapshot.child("p20").getValue() !=null){
                    CB20.setChecked(true);
                    String sm=String.valueOf(dataSnapshot.child("p20").child("mal").getValue());
                    String sb=String.valueOf(dataSnapshot.child("p20").child("bien").getValue());
                    ETMal20.setText(sm);
                    ETBien20.setText(sb);
                }

                if(dataSnapshot.child("p21").getValue() !=null){
                    CB21.setChecked(true);
                    String sm=String.valueOf(dataSnapshot.child("p21").child("mal").getValue());
                    String sb=String.valueOf(dataSnapshot.child("p21").child("bien").getValue());
                    ETMal21.setText(sm);
                    ETBien21.setText(sb);
                }

                if(dataSnapshot.child("p22").getValue() !=null){
                    CB22.setChecked(true);
                    String sm=String.valueOf(dataSnapshot.child("p22").child("mal").getValue());
                    String sb=String.valueOf(dataSnapshot.child("p22").child("bien").getValue());
                    ETMal22.setText(sm);
                    ETBien22.setText(sb);
                }

                if(dataSnapshot.child("p23").getValue() !=null){
                    CB23.setChecked(true);
                    String sm=String.valueOf(dataSnapshot.child("p23").child("mal").getValue());
                    String sb=String.valueOf(dataSnapshot.child("p23").child("bien").getValue());
                    ETMal23.setText(sm);
                    ETBien23.setText(sb);
                }

                if(dataSnapshot.child("p24").getValue() !=null){
                    CB24.setChecked(true);
                    String sm=String.valueOf(dataSnapshot.child("p24").child("mal").getValue());
                    String sb=String.valueOf(dataSnapshot.child("p24").child("bien").getValue());
                    ETMal24.setText(sm);
                    ETBien24.setText(sb);
                }

                if(dataSnapshot.child("p25").getValue() !=null){
                    CB25.setChecked(true);
                    String sm=String.valueOf(dataSnapshot.child("p25").child("mal").getValue());
                    String sb=String.valueOf(dataSnapshot.child("p25").child("bien").getValue());
                    ETMal25.setText(sm);
                    ETBien25.setText(sb);
                }

                if(dataSnapshot.child("p26").getValue() !=null){
                    CB26.setChecked(true);
                    String sm=String.valueOf(dataSnapshot.child("p26").child("mal").getValue());
                    String sb=String.valueOf(dataSnapshot.child("p26").child("bien").getValue());
                    ETMal26.setText(sm);
                    ETBien26.setText(sb);
                }

                if(dataSnapshot.child("p27").getValue() !=null){
                    CB27.setChecked(true);
                    String sm=String.valueOf(dataSnapshot.child("p27").child("mal").getValue());
                    String sb=String.valueOf(dataSnapshot.child("p27").child("bien").getValue());
                    ETMal27.setText(sm);
                    ETBien27.setText(sb);
                }

                if(dataSnapshot.child("p28").getValue() !=null){
                    CB28.setChecked(true);
                    String sm=String.valueOf(dataSnapshot.child("p28").child("mal").getValue());
                    String sb=String.valueOf(dataSnapshot.child("p28").child("bien").getValue());
                    ETMal28.setText(sm);
                    ETBien28.setText(sb);
                }

                if(dataSnapshot.child("p29").getValue() !=null){
                    CB29.setChecked(true);
                    String sm=String.valueOf(dataSnapshot.child("p29").child("mal").getValue());
                    String sb=String.valueOf(dataSnapshot.child("p29").child("bien").getValue());
                    ETMal29.setText(sm);
                    ETBien29.setText(sb);
                }

                if(dataSnapshot.child("p30").getValue() !=null){
                    CB30.setChecked(true);
                    String sm=String.valueOf(dataSnapshot.child("p30").child("mal").getValue());
                    String sb=String.valueOf(dataSnapshot.child("p30").child("bien").getValue());
                    ETMal30.setText(sm);
                    ETBien30.setText(sb);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

    }

    public void uploadPens(View view){
        boolean flag = true;

        boolean CB1 = ((CheckBox) findViewById(R.id.CB1)).isChecked();
        boolean CB2 = ((CheckBox) findViewById(R.id.CB2)).isChecked();
        boolean CB3 = ((CheckBox) findViewById(R.id.CB3)).isChecked();
        boolean CB4 = ((CheckBox) findViewById(R.id.CB4)).isChecked();
        boolean CB5 = ((CheckBox) findViewById(R.id.CB5)).isChecked();
        boolean CB6 = ((CheckBox) findViewById(R.id.CB6)).isChecked();
        boolean CB7 = ((CheckBox) findViewById(R.id.CB7)).isChecked();
        boolean CB8 = ((CheckBox) findViewById(R.id.CB8)).isChecked();
        boolean CB9 = ((CheckBox) findViewById(R.id.CB9)).isChecked();
        boolean CB10 = ((CheckBox) findViewById(R.id.CB10)).isChecked();
        boolean CB11 = ((CheckBox) findViewById(R.id.CB11)).isChecked();
        boolean CB12 = ((CheckBox) findViewById(R.id.CB12)).isChecked();
        boolean CB13 = ((CheckBox) findViewById(R.id.CB13)).isChecked();
        boolean CB14 = ((CheckBox) findViewById(R.id.CB14)).isChecked();
        boolean CB15 = ((CheckBox) findViewById(R.id.CB15)).isChecked();
        boolean CB16 = ((CheckBox) findViewById(R.id.CB16)).isChecked();
        boolean CB17 = ((CheckBox) findViewById(R.id.CB17)).isChecked();
        boolean CB18 = ((CheckBox) findViewById(R.id.CB18)).isChecked();
        boolean CB19 = ((CheckBox) findViewById(R.id.CB19)).isChecked();
        boolean CB20 = ((CheckBox) findViewById(R.id.CB20)).isChecked();
        boolean CB21 = ((CheckBox) findViewById(R.id.CB21)).isChecked();
        boolean CB22 = ((CheckBox) findViewById(R.id.CB22)).isChecked();
        boolean CB23 = ((CheckBox) findViewById(R.id.CB23)).isChecked();
        boolean CB24 = ((CheckBox) findViewById(R.id.CB24)).isChecked();
        boolean CB25 = ((CheckBox) findViewById(R.id.CB25)).isChecked();
        boolean CB26 = ((CheckBox) findViewById(R.id.CB26)).isChecked();
        boolean CB27 = ((CheckBox) findViewById(R.id.CB27)).isChecked();
        boolean CB28 = ((CheckBox) findViewById(R.id.CB28)).isChecked();
        boolean CB29 = ((CheckBox) findViewById(R.id.CB29)).isChecked();
        boolean CB30 = ((CheckBox) findViewById(R.id.CB30)).isChecked();

        //Respuestas Mal
        EditText ETMal1 = (EditText) findViewById(R.id.ETMal1);
        EditText ETMal2 = (EditText) findViewById(R.id.ETMal2);
        EditText ETMal3 = (EditText) findViewById(R.id.ETMal3);
        EditText ETMal4 = (EditText) findViewById(R.id.ETMal4);
        EditText ETMal5 = (EditText) findViewById(R.id.ETMal5);
        EditText ETMal6 = (EditText) findViewById(R.id.ETMal6);
        EditText ETMal7 = (EditText) findViewById(R.id.ETMal7);
        EditText ETMal8 = (EditText) findViewById(R.id.ETMal8);
        EditText ETMal9 = (EditText) findViewById(R.id.ETMal9);
        EditText ETMal10 = (EditText) findViewById(R.id.ETMal10);
        EditText ETMal11 = (EditText) findViewById(R.id.ETMal11);
        EditText ETMal12 = (EditText) findViewById(R.id.ETMal12);
        EditText ETMal13 = (EditText) findViewById(R.id.ETMal13);
        EditText ETMal14 = (EditText) findViewById(R.id.ETMal14);
        EditText ETMal15 = (EditText) findViewById(R.id.ETMal15);
        EditText ETMal16 = (EditText) findViewById(R.id.ETMal16);
        EditText ETMal17 = (EditText) findViewById(R.id.ETMal17);
        EditText ETMal18 = (EditText) findViewById(R.id.ETMal18);
        EditText ETMal19 = (EditText) findViewById(R.id.ETMal19);
        EditText ETMal20 = (EditText) findViewById(R.id.ETMal20);
        EditText ETMal21 = (EditText) findViewById(R.id.ETMal21);
        EditText ETMal22 = (EditText) findViewById(R.id.ETMal22);
        EditText ETMal23 = (EditText) findViewById(R.id.ETMal23);
        EditText ETMal24 = (EditText) findViewById(R.id.ETMal24);
        EditText ETMal25 = (EditText) findViewById(R.id.ETMal25);
        EditText ETMal26 = (EditText) findViewById(R.id.ETMal26);
        EditText ETMal27 = (EditText) findViewById(R.id.ETMal27);
        EditText ETMal28 = (EditText) findViewById(R.id.ETMal28);
        EditText ETMal29 = (EditText) findViewById(R.id.ETMal29);
        EditText ETMal30 = (EditText) findViewById(R.id.ETMal30);

        //Respuestas bien
        EditText ETBien1 = (EditText) findViewById(R.id.ETBien1);
        EditText ETBien2 = (EditText) findViewById(R.id.ETBien2);
        EditText ETBien3 = (EditText) findViewById(R.id.ETBien3);
        EditText ETBien4 = (EditText) findViewById(R.id.ETBien4);
        EditText ETBien5 = (EditText) findViewById(R.id.ETBien5);
        EditText ETBien6 = (EditText) findViewById(R.id.ETBien6);
        EditText ETBien7 = (EditText) findViewById(R.id.ETBien7);
        EditText ETBien8 = (EditText) findViewById(R.id.ETBien8);
        EditText ETBien9 = (EditText) findViewById(R.id.ETBien9);
        EditText ETBien10 = (EditText) findViewById(R.id.ETBien10);
        EditText ETBien11 = (EditText) findViewById(R.id.ETBien11);
        EditText ETBien12 = (EditText) findViewById(R.id.ETBien12);
        EditText ETBien13 = (EditText) findViewById(R.id.ETBien13);
        EditText ETBien14 = (EditText) findViewById(R.id.ETBien14);
        EditText ETBien15 = (EditText) findViewById(R.id.ETBien15);
        EditText ETBien16 = (EditText) findViewById(R.id.ETBien16);
        EditText ETBien17 = (EditText) findViewById(R.id.ETBien17);
        EditText ETBien18 = (EditText) findViewById(R.id.ETBien18);
        EditText ETBien19 = (EditText) findViewById(R.id.ETBien19);
        EditText ETBien20 = (EditText) findViewById(R.id.ETBien20);
        EditText ETBien21 = (EditText) findViewById(R.id.ETBien21);
        EditText ETBien22 = (EditText) findViewById(R.id.ETBien22);
        EditText ETBien23 = (EditText) findViewById(R.id.ETBien23);
        EditText ETBien24 = (EditText) findViewById(R.id.ETBien24);
        EditText ETBien25 = (EditText) findViewById(R.id.ETBien25);
        EditText ETBien26 = (EditText) findViewById(R.id.ETBien26);
        EditText ETBien27 = (EditText) findViewById(R.id.ETBien27);
        EditText ETBien28 = (EditText) findViewById(R.id.ETBien28);
        EditText ETBien29 = (EditText) findViewById(R.id.ETBien29);
        EditText ETBien30 = (EditText) findViewById(R.id.ETBien30);

        int i1,i2;
        if(CB1) { if(ETMal1.getText().toString().equals("")  || ETBien1.getText().toString().equals(""))  { flag=false; } else{ i1=Integer.parseInt(ETMal1.getText().toString());  i2=Integer.parseInt(ETBien1.getText().toString());  if(i1>10 || i2>10){ flag=false; } } }
        if(CB2) { if(ETMal2.getText().toString().equals("")  || ETBien2.getText().toString().equals(""))  { flag=false; } else{ i1=Integer.parseInt(ETMal2.getText().toString());  i2=Integer.parseInt(ETBien2.getText().toString());  if(i1>10 || i2>10){ flag=false; } } }
        if(CB3) { if(ETMal3.getText().toString().equals("")  || ETBien3.getText().toString().equals(""))  { flag=false; } else{ i1=Integer.parseInt(ETMal3.getText().toString());  i2=Integer.parseInt(ETBien3.getText().toString());  if(i1>10 || i2>10){ flag=false; } } }
        if(CB4) { if(ETMal4.getText().toString().equals("")  || ETBien4.getText().toString().equals(""))  { flag=false; } else{ i1=Integer.parseInt(ETMal4.getText().toString());  i2=Integer.parseInt(ETBien4.getText().toString());  if(i1>10 || i2>10){ flag=false; } } }
        if(CB5) { if(ETMal5.getText().toString().equals("")  || ETBien5.getText().toString().equals(""))  { flag=false; } else{ i1=Integer.parseInt(ETMal5.getText().toString());  i2=Integer.parseInt(ETBien5.getText().toString());  if(i1>10 || i2>10){ flag=false; } } }
        if(CB6) { if(ETMal6.getText().toString().equals("")  || ETBien6.getText().toString().equals(""))  { flag=false; } else{ i1=Integer.parseInt(ETMal6.getText().toString());  i2=Integer.parseInt(ETBien6.getText().toString());  if(i1>10 || i2>10){ flag=false; } } }
        if(CB7) { if(ETMal7.getText().toString().equals("")  || ETBien7.getText().toString().equals(""))  { flag=false; } else{ i1=Integer.parseInt(ETMal7.getText().toString());  i2=Integer.parseInt(ETBien7.getText().toString());  if(i1>10 || i2>10){ flag=false; } } }
        if(CB8) { if(ETMal8.getText().toString().equals("")  || ETBien8.getText().toString().equals(""))  { flag=false; } else{ i1=Integer.parseInt(ETMal8.getText().toString());  i2=Integer.parseInt(ETBien8.getText().toString());  if(i1>10 || i2>10){ flag=false; } } }
        if(CB9) { if(ETMal9.getText().toString().equals("")  || ETBien9.getText().toString().equals(""))  { flag=false; } else{ i1=Integer.parseInt(ETMal9.getText().toString());  i2=Integer.parseInt(ETBien9.getText().toString());  if(i1>10 || i2>10){ flag=false; } } }
        if(CB10){ if(ETMal10.getText().toString().equals("") || ETBien10.getText().toString().equals("")) { flag=false; } else{ i1=Integer.parseInt(ETMal10.getText().toString()); i2=Integer.parseInt(ETBien10.getText().toString()); if(i1>10 || i2>10){ flag=false; } } }
        if(CB11){ if(ETMal11.getText().toString().equals("") || ETBien11.getText().toString().equals("")) { flag=false; } else{ i1=Integer.parseInt(ETMal11.getText().toString()); i2=Integer.parseInt(ETBien11.getText().toString()); if(i1>10 || i2>10){ flag=false; } } }
        if(CB12){ if(ETMal12.getText().toString().equals("") || ETBien12.getText().toString().equals("")) { flag=false; } else{ i1=Integer.parseInt(ETMal12.getText().toString()); i2=Integer.parseInt(ETBien12.getText().toString()); if(i1>10 || i2>10){ flag=false; } } }
        if(CB13){ if(ETMal13.getText().toString().equals("") || ETBien13.getText().toString().equals("")) { flag=false; } else{ i1=Integer.parseInt(ETMal13.getText().toString()); i2=Integer.parseInt(ETBien13.getText().toString()); if(i1>10 || i2>10){ flag=false; } } }
        if(CB14){ if(ETMal14.getText().toString().equals("") || ETBien14.getText().toString().equals("")) { flag=false; } else{ i1=Integer.parseInt(ETMal14.getText().toString()); i2=Integer.parseInt(ETBien14.getText().toString()); if(i1>10 || i2>10){ flag=false; } } }
        if(CB15){ if(ETMal15.getText().toString().equals("") || ETBien15.getText().toString().equals("")) { flag=false; } else{ i1=Integer.parseInt(ETMal15.getText().toString()); i2=Integer.parseInt(ETBien15.getText().toString()); if(i1>10 || i2>10){ flag=false; } } }
        if(CB16){ if(ETMal16.getText().toString().equals("") || ETBien16.getText().toString().equals("")) { flag=false; } else{ i1=Integer.parseInt(ETMal16.getText().toString()); i2=Integer.parseInt(ETBien16.getText().toString()); if(i1>10 || i2>10){ flag=false; } } }
        if(CB17){ if(ETMal17.getText().toString().equals("") || ETBien17.getText().toString().equals("")) { flag=false; } else{ i1=Integer.parseInt(ETMal17.getText().toString()); i2=Integer.parseInt(ETBien17.getText().toString()); if(i1>10 || i2>10){ flag=false; } } }
        if(CB18){ if(ETMal18.getText().toString().equals("") || ETBien18.getText().toString().equals("")) { flag=false; } else{ i1=Integer.parseInt(ETMal18.getText().toString()); i2=Integer.parseInt(ETBien18.getText().toString()); if(i1>10 || i2>10){ flag=false; } } }
        if(CB19){ if(ETMal19.getText().toString().equals("") || ETBien19.getText().toString().equals("")) { flag=false; } else{ i1=Integer.parseInt(ETMal19.getText().toString()); i2=Integer.parseInt(ETBien19.getText().toString()); if(i1>10 || i2>10){ flag=false; } } }
        if(CB20){ if(ETMal20.getText().toString().equals("") || ETBien20.getText().toString().equals("")) { flag=false; } else{ i1=Integer.parseInt(ETMal20.getText().toString()); i2=Integer.parseInt(ETBien20.getText().toString()); if(i1>10 || i2>10){ flag=false; } } }
        if(CB21){ if(ETMal21.getText().toString().equals("") || ETBien21.getText().toString().equals("")) { flag=false; } else{ i1=Integer.parseInt(ETMal21.getText().toString()); i2=Integer.parseInt(ETBien21.getText().toString()); if(i1>10 || i2>10){ flag=false; } } }
        if(CB22){ if(ETMal22.getText().toString().equals("") || ETBien22.getText().toString().equals("")) { flag=false; } else{ i1=Integer.parseInt(ETMal22.getText().toString()); i2=Integer.parseInt(ETBien22.getText().toString()); if(i1>10 || i2>10){ flag=false; } } }
        if(CB23){ if(ETMal23.getText().toString().equals("") || ETBien23.getText().toString().equals("")) { flag=false; } else{ i1=Integer.parseInt(ETMal23.getText().toString()); i2=Integer.parseInt(ETBien23.getText().toString()); if(i1>10 || i2>10){ flag=false; } } }
        if(CB24){ if(ETMal24.getText().toString().equals("") || ETBien24.getText().toString().equals("")) { flag=false; } else{ i1=Integer.parseInt(ETMal24.getText().toString()); i2=Integer.parseInt(ETBien24.getText().toString()); if(i1>10 || i2>10){ flag=false; } } }
        if(CB25){ if(ETMal25.getText().toString().equals("") || ETBien25.getText().toString().equals("")) { flag=false; } else{ i1=Integer.parseInt(ETMal25.getText().toString()); i2=Integer.parseInt(ETBien25.getText().toString()); if(i1>10 || i2>10){ flag=false; } } }
        if(CB26){ if(ETMal26.getText().toString().equals("") || ETBien26.getText().toString().equals("")) { flag=false; } else{ i1=Integer.parseInt(ETMal26.getText().toString()); i2=Integer.parseInt(ETBien26.getText().toString()); if(i1>10 || i2>10){ flag=false; } } }
        if(CB27){ if(ETMal27.getText().toString().equals("") || ETBien27.getText().toString().equals("")) { flag=false; } else{ i1=Integer.parseInt(ETMal27.getText().toString()); i2=Integer.parseInt(ETBien27.getText().toString()); if(i1>10 || i2>10){ flag=false; } } }
        if(CB28){ if(ETMal28.getText().toString().equals("") || ETBien28.getText().toString().equals("")) { flag=false; } else{ i1=Integer.parseInt(ETMal28.getText().toString()); i2=Integer.parseInt(ETBien28.getText().toString()); if(i1>10 || i2>10){ flag=false; } } }
        if(CB29){ if(ETMal29.getText().toString().equals("") || ETBien29.getText().toString().equals("")) { flag=false; } else{ i1=Integer.parseInt(ETMal29.getText().toString()); i2=Integer.parseInt(ETBien29.getText().toString()); if(i1>10 || i2>10){ flag=false; } } }
        if(CB30){ if(ETMal30.getText().toString().equals("") || ETBien30.getText().toString().equals("")) { flag=false; } else{ i1=Integer.parseInt(ETMal30.getText().toString()); i2=Integer.parseInt(ETBien30.getText().toString()); if(i1>10 || i2>10){ flag=false; } } }

        if(!CB1 && !CB2 && !CB3 && !CB4 && !CB5 && !CB6 && !CB7 && !CB8 && !CB9 && !CB10 && !CB11 && !CB12 && !CB13 && !CB14 && !CB15 && !CB16 && !CB17
                && !CB18 && !CB19 && !CB20 && !CB21 && !CB22 && !CB23 && !CB24 && !CB25 && !CB26 && !CB27 && !CB28 && !CB29 && !CB30){
            flag=false;
        }

        if(flag){
            final String SFETMal1 = ETBien1.getText().toString();
            //Register in DB
            String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
            final String uidF = uid;
            DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
            final DatabaseReference rootRefF = rootRef;
            DatabaseReference rootPens = FirebaseDatabase.getInstance().getReference().child("users").child(uidF).child("pensamientos");
            final DatabaseReference rootPensF = rootPens;
            DatabaseReference uidRef = rootRef.child("users").child(uid);
            ValueEventListener eventListener = new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        rootRefF.child("users").child(uidF).child("ultimaConexion").setValue(currentTime);

                        if(CB1){
                            rootPensF.child("p1").child("mal").setValue(ETMal1.getText().toString());
                            rootPensF.child("p1").child("bien").setValue(ETBien1.getText().toString());
                        }
                        if(CB2){
                            rootPensF.child("p2").child("mal").setValue(ETMal2.getText().toString());
                            rootPensF.child("p2").child("bien").setValue(ETBien2.getText().toString());
                        }
                        if(CB3){
                            rootPensF.child("p3").child("mal").setValue(ETMal3.getText().toString());
                            rootPensF.child("p3").child("bien").setValue(ETBien3.getText().toString());
                        }
                        if(CB4){
                            rootPensF.child("p4").child("mal").setValue(ETMal4.getText().toString());
                            rootPensF.child("p4").child("bien").setValue(ETBien4.getText().toString());
                        }
                        if(CB5){
                            rootPensF.child("p5").child("mal").setValue(ETMal5.getText().toString());
                            rootPensF.child("p5").child("bien").setValue(ETBien5.getText().toString());
                        }
                        if(CB6){
                            rootPensF.child("p6").child("mal").setValue(ETMal6.getText().toString());
                            rootPensF.child("p6").child("bien").setValue(ETBien6.getText().toString());
                        }
                        if(CB7){
                            rootPensF.child("p7").child("mal").setValue(ETMal7.getText().toString());
                            rootPensF.child("p7").child("bien").setValue(ETBien7.getText().toString());
                        }
                        if(CB8){
                            rootPensF.child("p8").child("mal").setValue(ETMal8.getText().toString());
                            rootPensF.child("p8").child("bien").setValue(ETBien8.getText().toString());
                        }
                        if(CB9){
                            rootPensF.child("p9").child("mal").setValue(ETMal9.getText().toString());
                            rootPensF.child("p9").child("bien").setValue(ETBien9.getText().toString());
                        }
                        if(CB10){
                            rootPensF.child("p10").child("mal").setValue(ETMal10.getText().toString());
                            rootPensF.child("p10").child("bien").setValue(ETBien10.getText().toString());
                        }
                        if(CB11){
                            rootPensF.child("p11").child("mal").setValue(ETMal11.getText().toString());
                            rootPensF.child("p11").child("bien").setValue(ETBien11.getText().toString());
                        }
                        if(CB12){
                            rootPensF.child("p12").child("mal").setValue(ETMal12.getText().toString());
                            rootPensF.child("p12").child("bien").setValue(ETBien12.getText().toString());
                        }
                        if(CB13){
                            rootPensF.child("p13").child("mal").setValue(ETMal13.getText().toString());
                            rootPensF.child("p13").child("bien").setValue(ETBien13.getText().toString());
                        }
                        if(CB14){
                            rootPensF.child("p14").child("mal").setValue(ETMal14.getText().toString());
                            rootPensF.child("p14").child("bien").setValue(ETBien14.getText().toString());
                        }
                        if(CB15){
                            rootPensF.child("p15").child("mal").setValue(ETMal15.getText().toString());
                            rootPensF.child("p15").child("bien").setValue(ETBien15.getText().toString());
                        }
                        if(CB16){
                            rootPensF.child("p16").child("mal").setValue(ETMal16.getText().toString());
                            rootPensF.child("p16").child("bien").setValue(ETBien16.getText().toString());
                        }
                        if(CB17){
                            rootPensF.child("p17").child("mal").setValue(ETMal17.getText().toString());
                            rootPensF.child("p17").child("bien").setValue(ETBien17.getText().toString());
                        }
                        if(CB18){
                            rootPensF.child("p18").child("mal").setValue(ETMal18.getText().toString());
                            rootPensF.child("p18").child("bien").setValue(ETBien18.getText().toString());
                        }
                        if(CB19){
                            rootPensF.child("p19").child("mal").setValue(ETMal19.getText().toString());
                            rootPensF.child("p19").child("bien").setValue(ETBien19.getText().toString());
                        }
                        if(CB20){
                            rootPensF.child("p20").child("mal").setValue(ETMal20.getText().toString());
                            rootPensF.child("p20").child("bien").setValue(ETBien20.getText().toString());
                        }
                        if(CB21){
                            rootPensF.child("p21").child("mal").setValue(ETMal21.getText().toString());
                            rootPensF.child("p21").child("bien").setValue(ETBien21.getText().toString());
                        }
                        if(CB22){
                            rootPensF.child("p22").child("mal").setValue(ETMal22.getText().toString());
                            rootPensF.child("p22").child("bien").setValue(ETBien22.getText().toString());
                        }
                        if(CB23){
                            rootPensF.child("p23").child("mal").setValue(ETMal23.getText().toString());
                            rootPensF.child("p23").child("bien").setValue(ETBien23.getText().toString());
                        }
                        if(CB24){
                            rootPensF.child("p24").child("mal").setValue(ETMal24.getText().toString());
                            rootPensF.child("p24").child("bien").setValue(ETBien24.getText().toString());
                        }
                        if(CB25){
                            rootPensF.child("p25").child("mal").setValue(ETMal25.getText().toString());
                            rootPensF.child("p25").child("bien").setValue(ETBien25.getText().toString());
                        }
                        if(CB26){
                            rootPensF.child("p26").child("mal").setValue(ETMal26.getText().toString());
                            rootPensF.child("p26").child("bien").setValue(ETBien26.getText().toString());
                        }
                        if(CB27){
                            rootPensF.child("p27").child("mal").setValue(ETMal27.getText().toString());
                            rootPensF.child("p27").child("bien").setValue(ETBien27.getText().toString());
                        }
                        if(CB28){
                            rootPensF.child("p28").child("mal").setValue(ETMal28.getText().toString());
                            rootPensF.child("p28").child("bien").setValue(ETBien28.getText().toString());
                        }
                        if(CB29){
                            rootPensF.child("p29").child("mal").setValue(ETMal29.getText().toString());
                            rootPensF.child("p29").child("bien").setValue(ETBien29.getText().toString());
                        }
                        if(CB30){
                            rootPensF.child("p30").child("mal").setValue(ETMal30.getText().toString());
                            rootPensF.child("p30").child("bien").setValue(ETBien30.getText().toString());
                        }


                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                }
            };
            uidRef.addListenerForSingleValueEvent(eventListener);

            Toast.makeText(this, "Actividad realizada correctamente", Toast.LENGTH_LONG).show();
            finish();

        } else {
            Toast.makeText(this, "Favor de llenar todos los valores correctamente", Toast.LENGTH_LONG).show();
        }

    }
}
