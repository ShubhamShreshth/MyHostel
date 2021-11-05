package com.example.myhostel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class wash_rooms extends AppCompatActivity {
    public static final String TAG = "TAG";
    EditText inputBox;
    FloatingActionButton btn;
    FirebaseAuth fAuth;
    FirebaseDatabase fData;
    String userid;
    ArrayList<String> selection = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wash_rooms);
        inputBox= findViewById(R.id.inputDetailwash);
        btn = findViewById(R.id.sendwash);

        fAuth = FirebaseAuth.getInstance();
        fData = FirebaseDatabase.getInstance();

        userid = fAuth.getCurrentUser().getUid();
        String category = "Washrooms";

        DatabaseReference ref = fData.getReference("Users").child(userid).child("Complaint").child(category);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    String data = dataSnapshot.getValue(String.class);
                    selection.add(data);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String complaint = inputBox.getText().toString().trim();
                if (TextUtils.isEmpty(complaint)) {
                    inputBox.setError("No complaint entered");
                    return;
                }
                selection.add(complaint);
                DatabaseReference databaseReference = fData.getReference("Users").child(userid).child("Complaint");
                databaseReference.child(category).setValue(selection);
                startActivity(new Intent(getApplicationContext(),Complaint_cat.class));
                finish();
            }
        });
    }
}