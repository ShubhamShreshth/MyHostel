package com.example.myhostel;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class Profile extends AppCompatActivity {
    TextView mprofilepageusn,mprofilepagename,mprofilepageemail,mprofilepagedept,
            mprofilepageroom,mprofilepagebuild,mprofilepagesem;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        mprofilepageusn=findViewById(R.id.ProfilepageUSN);
        mprofilepagename=findViewById(R.id.ProfilepageName);
        mprofilepageemail=findViewById(R.id.ProfilepageEmail);
        mprofilepagedept=findViewById(R.id.ProfilepageDept);
        mprofilepageroom=findViewById(R.id.ProfilepageRoom);
        mprofilepagebuild=findViewById(R.id.ProfilepageBuild);
        mprofilepagesem=findViewById(R.id.ProfilepageSem);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        userid = fAuth.getCurrentUser().getUid();
        DocumentReference documentReference = fStore.collection("Users").document(userid);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                mprofilepageusn.setText(value.getString("User ID"));
                mprofilepagename.setText(value.getString("Name"));
                mprofilepageemail.setText(value.getString("Email"));
                mprofilepagedept.setText(value.getString("Department Name"));
                mprofilepageroom.setText(value.getString("Room Number"));
                mprofilepagebuild.setText(value.getString("Building Number"));
                mprofilepagesem.setText(value.getString("Semester"));
            }
        });
    }
    public void Logout(View view){
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(),UserLogin.class));
        finish();
    }

}