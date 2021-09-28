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

public class ProfileAdmin extends AppCompatActivity {
    TextView mprofilepageidadmin,mprofilepagenameadmin,
            mprofilepageemailadmin,mprofilepagebuildadmin;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_admin);
        mprofilepageidadmin=findViewById(R.id.ProfilepageIDadmin);
        mprofilepagenameadmin=findViewById(R.id.ProfilepageNameadmin);
        mprofilepageemailadmin=findViewById(R.id.ProfilepageEmailadmin);
        mprofilepagebuildadmin=findViewById(R.id.ProfilepageBuildadmin);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        userid = fAuth.getCurrentUser().getUid();
        DocumentReference documentReference = fStore.collection("Admins").document(userid);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                mprofilepageidadmin.setText(value.getString("Admin ID"));
                mprofilepagenameadmin.setText(value.getString("Name"));
                mprofilepageemailadmin.setText(value.getString("Email"));
                mprofilepagebuildadmin.setText(value.getString("Building Number"));
            }
        });
    }
    public void Logout(View view){
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(),AdminLogin.class));
        finish();
    }

}