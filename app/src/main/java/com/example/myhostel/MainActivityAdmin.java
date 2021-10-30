package com.example.myhostel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.HashMap;

public class MainActivityAdmin extends AppCompatActivity {
    TextView mNameadmin;
    ImageButton mprofilebuttonadmin;
    FirebaseAuth fAuth;
    FirebaseDatabase fData;
    String userid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_admin);
        mNameadmin = findViewById(R.id.NameAdmin);
        mprofilebuttonadmin=findViewById(R.id.profileButtonAdmin);

        fAuth = FirebaseAuth.getInstance();
        fData = FirebaseDatabase.getInstance();

        userid = fAuth.getCurrentUser().getUid();
        DatabaseReference databaseReference = fData.getReference("Admins").child(fAuth.getCurrentUser().getUid()).child("Name");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String array = (String) snapshot.getValue();
                mNameadmin.setText(array);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void Logout(View view){
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(),AdminLogin.class));
        finish();
    }
}