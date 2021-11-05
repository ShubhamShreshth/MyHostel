package com.example.myhostel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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
import java.util.Map;

public class ProfileAdmin extends AppCompatActivity {
    TextView mprofilepageidadmin,mprofilepagenameadmin,
            mprofilepageemailadmin;
    FirebaseAuth fAuth;
    FirebaseDatabase fData;
    String userid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_admin);
        mprofilepageidadmin=findViewById(R.id.ProfilepageIDadmin);
        mprofilepagenameadmin=findViewById(R.id.ProfilepageNameadmin);
        mprofilepageemailadmin=findViewById(R.id.ProfilepageEmailadmin);

        fAuth = FirebaseAuth.getInstance();
        fData = FirebaseDatabase.getInstance();

        userid = fAuth.getCurrentUser().getUid();
        DatabaseReference databaseReference = fData.getReference("Admins").child(userid);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                HashMap<String, String> array = new HashMap<String, String>();
                array = (HashMap<String, String>) snapshot.getValue();
                for (Map.Entry<String, String> entry : array.entrySet()){
                    if("Admin ID".equals(entry.getKey())){
                        mprofilepageidadmin.setText(entry.getValue());
                    }
                    else if("Name".equals(entry.getKey())){
                        mprofilepagenameadmin.setText(entry.getValue());
                    }
                    else if ("Email".equals(entry.getKey())){
                        mprofilepageemailadmin.setText(entry.getValue());
                    }
                }
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