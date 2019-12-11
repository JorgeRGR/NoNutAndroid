package com.oniriccreations.nonutapp.ui.calendario;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.oniriccreations.nonutapp.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static android.app.Activity.RESULT_OK;

public class CalendarioFragment extends Fragment {

    private CalendarioViewModel calendarioViewModel;
    public Date currentTime = Calendar.getInstance().getTime();

    public static final String RESULT = "result";
    public static final String EVENT = "event";
    private static final int ADD_NOTE = 44;

    private CalendarView mCalendarView;
    private List<EventDay> mEventDays = new ArrayList<>();
    private List<Calendar> calendarList = new ArrayList<>();

    public List<Integer> yearList = new ArrayList<>();
    public List<Integer> monthMinList = new ArrayList<>();
    public List<Integer> monthMaxList = new ArrayList<>();
    public List<Integer> dayMinList = new ArrayList<>();
    public List<Integer> dayMaxList = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        calendarioViewModel =
                ViewModelProviders.of(this).get(CalendarioViewModel.class);
        View root = inflater.inflate(R.layout.fragment_calendario, container, false);
        calendarioViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

            }
        });
        return root;
    }

    //Equivale al onCreate
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mCalendarView = (CalendarView) view.findViewById(R.id.calendarView);
        Calendar min = Calendar.getInstance();
        Calendar max = Calendar.getInstance();

        mCalendarView.setOnDayClickListener(new OnDayClickListener() {
            @Override
            public void onDayClick(EventDay eventDay) {
                Calendar clickedDayCalendar = eventDay.getCalendar();
                boolean flagEntered = false;
                for(int i=0;i<calendarList.size();i++){
                    int res = clickedDayCalendar.compareTo(calendarList.get(i));
                    if(res == 0){
                        flagEntered = true;
                        openDay(clickedDayCalendar);
                        break;
                    }
                }
                if(!flagEntered){
                    Toast.makeText(getActivity(), "No existe registro en ese día", Toast.LENGTH_SHORT).show();
                }

            }
        });

        if (FirebaseAuth.getInstance().getCurrentUser() != null){

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

                    if(dataSnapshot.child("Registros").getValue()!=null){

                        //Dividir en años
                        for (DataSnapshot yearSnapshot: dataSnapshot.child("Registros").getChildren()) {
                            String sYear = String.valueOf(yearSnapshot.getKey());
                            int iYear = Integer.parseInt(sYear);
                            yearList.add(iYear);
                        }

                        Collections.sort(yearList);
                        int maxYear = yearList.get(yearList.size()-1);
                        int minYear = yearList.get(0);

                        final String minYearF=String.valueOf(minYear);
                        final String maxYearF=String.valueOf(maxYear);

                        //Mes minimo
                        for (DataSnapshot monthSnapshot: dataSnapshot.child("Registros").child(minYearF).getChildren()) {
                            String sMonth = String.valueOf(monthSnapshot.getKey());
                            int iMonth = Integer.parseInt(sMonth);
                            monthMinList.add(iMonth);
                        }
                        Collections.sort(monthMinList);
                        int minMonth = monthMinList.get(0);
                        final String minMonthF=String.valueOf(minMonth);

                        //Mes maximo
                        for (DataSnapshot monthSnapshot: dataSnapshot.child("Registros").child(maxYearF).getChildren()) {
                            String sMonth = String.valueOf(monthSnapshot.getKey());
                            int iMonth = Integer.parseInt(sMonth);
                            monthMaxList.add(iMonth);
                        }
                        Collections.sort(monthMaxList);
                        int maxMonth = monthMaxList.get(monthMaxList.size()-1);
                        final String maxMonthF=String.valueOf(maxMonth);

                        //Dia minimo
                        for (DataSnapshot daySnapshot: dataSnapshot.child("Registros").child(minYearF).child(minMonthF).getChildren()) {
                            String sDay = String.valueOf(daySnapshot.getKey());
                            int iDay = Integer.parseInt(sDay);
                            dayMinList.add(iDay);
                        }
                        Collections.sort(dayMinList);
                        int minDay = dayMinList.get(0);
                        final String minDayF=String.valueOf(minDay);

                        //Dia maximo
                        for (DataSnapshot daySnapshot: dataSnapshot.child("Registros").child(maxYearF).child(maxMonthF).getChildren()) {
                            String sDay = String.valueOf(daySnapshot.getKey());
                            int iDay = Integer.parseInt(sDay);
                            dayMaxList.add(iDay);
                        }
                        Collections.sort(dayMaxList);
                        int maxDay = dayMaxList.get(dayMaxList.size()-1);
                        final String maxDayF=String.valueOf(maxDay);

                        //Meses los toma de 0 a 11
                        //Minimo quita el dia puesto
                        min.set(minYear,minMonth-1,minDay-1);
                        max.set(maxYear,maxMonth-1,maxDay);
                        mCalendarView.setMinimumDate(min);
                        mCalendarView.setMaximumDate(max);

                        //Cargar todos los elementos
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

                                    Calendar current = Calendar.getInstance();
                                    current.set(iYear,iMonth-1,iDay);

                                    if(dataSnapshot.child("Registros").child(sYear).child(sMonth).child(sDay).child("state").getValue()!=null){
                                        int dss=dataSnapshot.child("Registros").child(sYear).child(sMonth).child(sDay).child("state").getValue(int.class);
                                        if(dss==1){
                                            mEventDays.add(new EventDay(current,R.drawable.ic_sentiment_very_satisfied_black_24dp));
                                        } else if(dss==2){
                                            mEventDays.add(new EventDay(current,R.drawable.ic_sentiment_neutral_black_24dp));
                                        } else if(dss==3){
                                            mEventDays.add(new EventDay(current,R.drawable.ic_sentiment_dissatisfied_black_24dp));
                                        }
                                        calendarList.add(current);
                                    }



                                }

                            }

                        }

                        mCalendarView.setEvents(mEventDays);

                    } else {
                        Toast.makeText(getActivity(), "Debes de registrar por lo menos un día en el apartado de actividades para poder usar el calendario", Toast.LENGTH_LONG).show();
                    }

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                }
            });
        }

    }

    private void openDay(Calendar selectedDate){
        //Log.d("Day", ""+String.valueOf(selectedDate));
        //Log.d("YEAR", ""+String.valueOf  (selectedDate.get(Calendar.YEAR)));
        //Log.d("MONTH", ""+String.valueOf (selectedDate.get(Calendar.MONTH)));
        //Log.d("DAY", ""+String.valueOf   (selectedDate.get(Calendar.DAY_OF_MONTH)));
        Intent intent = new Intent(getActivity(), CheckDay.class);
        intent.putExtra("Year", selectedDate.get(Calendar.YEAR));
        intent.putExtra("Month", selectedDate.get(Calendar.MONTH)+1);
        intent.putExtra("Day", selectedDate.get(Calendar.DAY_OF_MONTH));
        startActivity(intent);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == ADD_NOTE && resultCode == RESULT_OK) {
            mCalendarView.setEvents(mEventDays);
        }
    }

}