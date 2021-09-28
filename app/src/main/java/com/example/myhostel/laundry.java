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
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class laundry extends AppCompatActivity {
    public static final String TAG = "TAG";
    EditText inputBox;
    FloatingActionButton btn;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laundry);
        inputBox= findViewById(R.id.inputDetailbugs);
        btn = findViewById(R.id.sendbugs);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String complaint = inputBox.getText().toString().trim();
                String category = "Laundry";
                if (TextUtils.isEmpty(complaint)) {
                    inputBox.setError("No complaint entered");
                    return;
                }
                userid = fAuth.getCurrentUser().getUid();
                DocumentReference documentReference = fStore.collection("Users").document(userid);
                documentReference.update("Complaint",complaint);
                documentReference.update("Complaint Category",category);
                startActivity(new Intent(getApplicationContext(),laundry.class));
                finish();
            }
        });
    }
}