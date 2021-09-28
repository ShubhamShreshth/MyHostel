package com.example.myhostel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AdminRegister extends AppCompatActivity {
    public static final String TAG = "TAG";
    EditText mUID, mName,mEmail,mPassword,mBuild;
    Button mAdminSignUp,madminlogin;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_register);
        mUID=findViewById(R.id.UID);
        mEmail=findViewById(R.id.Email);
        mName=findViewById(R.id.adminName);
        mPassword=findViewById(R.id.Password);
        mBuild=findViewById(R.id.Build);
        mAdminSignUp=findViewById(R.id.adminSignUp);
        madminlogin = findViewById(R.id.adminSignIn);

        fAuth=FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        if(fAuth.getCurrentUser()!=null){
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }

        mAdminSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String UID = mUID.getText().toString().trim();
                String Password = mPassword.getText().toString().trim();
                String Name = mName.getText().toString().trim();
                String Email =mEmail.getText().toString().trim();
                String Build = mBuild.getText().toString().trim();
                if(TextUtils.isEmpty(UID)){
                    mUID.setError("USN is Required!");
                    return;
                }
                if(TextUtils.isEmpty(Password)){
                    mPassword.setError("Password is required!");
                    return;
                }
                if(Password.length()<6){
                    mPassword.setError("Password must be 6 digits long!");
                    return;
                }
                if(TextUtils.isEmpty(Name)){
                    mName.setError("Name is Required!");
                    return;
                }
                if(TextUtils.isEmpty(Email)){
                    mEmail.setError("Email is required!");
                    return;
                }
                if(TextUtils.isEmpty(Build)){
                    mBuild.setError("Building Number is required!");
                    return;
                }
                fAuth.createUserWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(AdminRegister.this,"User Created!",Toast.LENGTH_SHORT).show();
                            userid = fAuth.getCurrentUser().getUid();
                            DocumentReference documentReference = fStore.collection("Admins").document(userid);
                            Map<String,Object> user = new HashMap<>();
                            user.put("Admin ID",UID);
                            user.put("Name",Name);
                            user.put("Email",Email);
                            user.put("Building Number",Build);
                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Log.d(TAG,"on success: user profile is created for "+UID);
                                }
                            });
                            startActivity(new Intent(getApplicationContext(),MainActivityAdmin.class));
                            finish();
                        }
                        else{
                            Toast.makeText(AdminRegister.this, "Error! "+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        madminlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),AdminLogin.class));
                finish();
            }
        });
    }
}