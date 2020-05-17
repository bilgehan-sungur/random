package com.example.chatlingg;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class KullaniciProfili extends AppCompatActivity {

  //  FirebaseAuth auth;
   // FirebaseUser user;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;
    EditText kullaniciIsmi;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kullanici_profili);
        tanimla();
        getir();
    }
    public void tanimla(){

       // auth=FirebaseAuth.getInstance();
       // user=auth.getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance();
        reference=firebaseDatabase.getReference().child("Kullanıcı Özellikleri").child(user.getUid());
        String isim = kullaniciIsmi.toString() ;
              isim  = getIntent().getExtras().getString("kullaniciIsmi");


    }
    public String gecici(String abc){
        kullaniciIsmi=(EditText)findViewById(R.id.kullaniciIsmi);
        abc = kullaniciIsmi.toString();
        return abc;
    }
    public void getir()
    {
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Users users = dataSnapshot.getValue(Users.class);
                Log.i("bilgiler",users.toString());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }



}
