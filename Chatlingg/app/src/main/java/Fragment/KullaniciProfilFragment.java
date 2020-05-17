package Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chatlingg.R;
import com.example.chatlingg.Users;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class KullaniciProfilFragment extends Fragment {

    FirebaseAuth auth;
    FirebaseUser user;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       view = inflater.inflate(R.layout.fragment_kullanici_profil, container, false);
       tanimla();
       getir();
       return view;
    }

    public void tanimla(){

        auth=FirebaseAuth.getInstance();
        user=auth.getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance();
        reference=firebaseDatabase.getReference().child("Kullanıcı Özellikleri").child(user.getUid());

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
