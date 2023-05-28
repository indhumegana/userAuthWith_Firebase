package com.example.hahahaha;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Signup extends AppCompatActivity {

    TextView txt;
    TextInputEditText Signup_enterEmail,Signup_enterPassword;
    Button Signup_button;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        txt=findViewById(R.id.page_2);
        Signup_enterEmail=findViewById(R.id.Signup_enterEmail);
        Signup_enterPassword=findViewById(R.id.Signup_enterPassword);
        Signup_button=findViewById(R.id.Signup_button);
        firebaseAuth=FirebaseAuth.getInstance();

        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Signup.this, MainActivity.class));
            }
        });

        Signup_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Signup_email=Signup_enterEmail.getText().toString();
                String Signup_password=Signup_enterPassword.getText().toString();

                if(Signup_email.equals("") || Signup_password.equals("")){
                    Toast.makeText(Signup.this, "Enter both fields to register", Toast.LENGTH_SHORT).show();
                }else{
                    signup(Signup_email,Signup_password);
                }
            }
        });

    }

    private void signup(String Signup_email,String Signup_password){

        firebaseAuth.createUserWithEmailAndPassword(Signup_email,Signup_password).addOnCompleteListener(Signup.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(Signup.this, "Successfully registered", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(Signup.this, "this mail is already exist", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


}