package com.oniriccreations.nonutapp;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity{
    private static final int MY_REQUEST_CODE = 7117; //Any number
    List<AuthUI.IdpConfig> providers;
    public Date currentTime = Calendar.getInstance().getTime();
    BottomNavigationView navView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_actividades, R.id.navigation_calendario, R.id.navigation_comunidad,
                R.id.navigation_home, R.id.navigation_perfil)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        //Init provider
        providers = Arrays.asList(
                new AuthUI.IdpConfig.EmailBuilder().build(), //Email Login
                new AuthUI.IdpConfig.FacebookBuilder().build(), //Facebook Login
                new AuthUI.IdpConfig.GoogleBuilder().build() //Google Login
        );

        if (FirebaseAuth.getInstance().getCurrentUser() == null){
            showSignInOptions();
        } else {
            String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
            final String uidF = uid;
            checkFirstTime(uidF);
        }

    }

    public void checkFirstTime(String uid){
        ValueEventListener check;
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference uidRefCheck = rootRef.child("users").child(uid).child("firstRegister");
        check = uidRefCheck.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(!dataSnapshot.exists()){
                    //Activity Intent
                    Intent intent=new Intent(MainActivity.this,PrimeraVez.class);
                    startActivityForResult(intent, 2);// Activity is started with requestCode 2
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    private void showSignInOptions() {
        startActivityForResult(
                AuthUI.getInstance().createSignInIntentBuilder()
                        .setAvailableProviders(providers)
                        .setTheme(R.style.LoginTheme)
                        .setLogo(R.mipmap.logo)
                        .build(),MY_REQUEST_CODE
        );
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            //Usuario se registró correctamente
            if (requestCode == MY_REQUEST_CODE) {
                IdpResponse response = IdpResponse.fromResultIntent(data);
                if (resultCode == RESULT_OK) {
                    //Refresh View
                    navView.getMenu().performIdentifierAction(R.id.navigation_home,0);

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
                            } else {
                                //create new user
                                rootRefF.child("users").child(uidF).child("diaRegistro").setValue(currentTime);
                                rootRefF.child("users").child(uidF).child("ultimaConexion").setValue(currentTime);

                                //Activity Intent
                                Intent intent=new Intent(MainActivity.this,PrimeraVez.class);
                                startActivityForResult(intent, 2);// Activity is started with requestCode 2

                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                        }
                    };
                    uidRef.addListenerForSingleValueEvent(eventListener);

                    //Get user
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    //Show email on Toast
                    //Toast.makeText(this, "Bienvenido " + user.getEmail(), Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "" + response.getError().getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        } else {
            //Cerrar app si el usuario se sale sin registrarse
            this.finishAffinity();
        }
    }

    //Metodos de Perfil
    public void logoutPerfil(View v){
        final Button btn_sign_out = (Button) findViewById(R.id.bLogout);
        AuthUI.getInstance()
                .signOut(MainActivity.this)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        btn_sign_out.setEnabled(false);
                        navView.getMenu().performIdentifierAction(R.id.navigation_home,0);
                        showSignInOptions();
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(MainActivity.this,""+e.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }

    public void actualizarPerfil(View v){
        EditText ETNombre = (EditText) findViewById(R.id.ETNombre);
        EditText ETAlimen = (EditText) findViewById(R.id.ETAlimen);
        EditText ETSueno = (EditText) findViewById(R.id.ETSueno);
        EditText ETSocial = (EditText) findViewById(R.id.ETSocial);

        final String NomStr = ETNombre.getText().toString();
        final String AliStr = ETAlimen.getText().toString();
        final String SueStr = ETSueno.getText().toString();
        final String SocStr = ETSocial.getText().toString();

        if(NomStr.isEmpty() || AliStr.isEmpty() || SueStr.isEmpty() || SocStr.isEmpty()){
            Toast.makeText(MainActivity.this,"Favor de llenar todos los campos antes de actualizar los datos",Toast.LENGTH_LONG).show();
        } else {

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
                        rootRefF.child("users").child(uidF).child("nombre").setValue(NomStr);
                        rootRefF.child("users").child(uidF).child("alimentacion").setValue(AliStr);
                        rootRefF.child("users").child(uidF).child("sueno").setValue(SueStr);
                        rootRefF.child("users").child(uidF).child("social").setValue(SocStr);
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Toast.makeText(MainActivity.this,"No se pudo actualizar la informaciön",Toast.LENGTH_LONG).show();
                }
            };
            Toast.makeText(MainActivity.this,"Información actualizada correctamente",Toast.LENGTH_LONG).show();
            uidRef.addListenerForSingleValueEvent(eventListener);

        }

    }

}
