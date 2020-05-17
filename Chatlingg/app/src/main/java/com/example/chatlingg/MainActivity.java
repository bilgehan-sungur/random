package com.example.chatlingg;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    String username;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;
    List<String> list;
    RecyclerView userRecyView;
    UserAdapter userAdapter;
    ImageView backImage2;
    TextView search;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tanimla();
        listele();




    }
    private void searchUser(String s){

        final FirebaseUser fuser = FirebaseAuth.getInstance().getCurrentUser();
        Query query = FirebaseDatabase.getInstance().getReference("Kullanıcılar").orderByChild("username")
                .startAt(s)
                .endAt(s+"\uf0ff");

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Users users =snapshot.getValue(Users.class);

                    assert users != null;
                    assert fuser !=null;
                    if(!dataSnapshot.getKey().equals(username)){
                        list.add(dataSnapshot.getKey());
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void tanimla(){
        username = getIntent().getExtras().getString("kadi");
        firebaseDatabase = FirebaseDatabase.getInstance();
        reference=firebaseDatabase.getReference();
        list = new ArrayList<>();
        userRecyView = (RecyclerView)findViewById(R.id.userRecyView);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(MainActivity.this,2);
        userRecyView.setLayoutManager(layoutManager);
        userAdapter = new UserAdapter(MainActivity.this,list,MainActivity.this,username);
        userRecyView.setAdapter(userAdapter);
        backImage2 = (ImageView)findViewById(R.id.backImage2);
        search = (TextView)findViewById(R.id.search);

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        backImage2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,GirisActivity.class);
                intent.putExtra("kadi",username);
                startActivity(intent);
            }
        });


    }

    public void listele(){
        reference.child("Kullanıcılar").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                if((!dataSnapshot.getKey().equals(username))){
                    list.add(dataSnapshot.getKey());
                    Log.i("Kullanıcı",dataSnapshot.getKey());
                    userAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
