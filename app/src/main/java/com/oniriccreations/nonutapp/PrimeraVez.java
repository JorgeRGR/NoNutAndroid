package com.oniriccreations.nonutapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;

public class PrimeraVez extends AppCompatActivity {

    Button bIngresar;
    EditText ETNombre;
    public Date currentTime = Calendar.getInstance().getTime();
    private static int RESULT_LOAD_IMAGE = 1;

    int PICK_IMAGE_REQUEST = 111;
    ProgressDialog pd;

    //Firebase storage reference
    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference storageRef = storage.getReferenceFromUrl("gs://no-nut-app-oc.appspot.com/");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_primera_vez);

        bIngresar = (Button) findViewById(R.id.bIngresar);
        ETNombre = (EditText) findViewById(R.id.ETNombre);

        pd = new ProgressDialog(this);
        pd.setMessage("Subiendo....");

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //preventing default implementation previous to android.os.Build.VERSION_CODES.ECLAIR
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void getPicture(View v){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_PICK);
        startActivityForResult(Intent.createChooser(intent, "Elige una imagen"), PICK_IMAGE_REQUEST);

    }

    //Cerrar al picarle a un botón
    public void closeAct(View v){
        //Puso foto y nombre
        if(!ETNombre.getText().toString().equals("")){

            String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
            final String uidF = uid;
            DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
            final DatabaseReference rootRefF = rootRef;
            DatabaseReference uidRef = rootRef.child("users").child(uid);

            //Subir nombre y referencia a Firebase
            ValueEventListener eventListener = new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        rootRefF.child("users").child(uidF).child("ultimaConexion").setValue(currentTime);
                        rootRefF.child("users").child(uidF).child("nombre").setValue(ETNombre.getText().toString());
                        rootRefF.child("users").child(uidF).child("firstRegister").setValue(1);
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                }
            };
            uidRef.addListenerForSingleValueEvent(eventListener);

            Intent returnIntent = new Intent();
            setResult(Activity.RESULT_CANCELED, returnIntent);
            finish();
        } else {
            Toast.makeText(PrimeraVez.this, "Favor de llenar todos tus datos.", Toast.LENGTH_LONG).show();
        }
    }

}