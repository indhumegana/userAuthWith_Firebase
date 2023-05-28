package com.example.hahahaha;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    TextInputEditText Login_enter_email,Login_enter_password;
    Button Login_button;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView txt=findViewById(R.id.page_1);
        Login_enter_email=findViewById(R.id.Login_enter_email);
        Login_enter_password=findViewById(R.id.Login_enter_password);
        Login_button=findViewById(R.id.Login_button);
        firebaseAuth=FirebaseAuth.getInstance();

        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, Signup.class);
                startActivity(intent);
            }
        });



      Login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String login_email=Login_enter_email.getText().toString();
                String login_password=Login_enter_password.getText().toString();

                if(login_email.equals("") || login_password.equals("")){
                    Toast.makeText(MainActivity.this, "Enter both fields to register", Toast.LENGTH_SHORT).show();
                }else
                {
                    login(login_email,login_password);

                }
            }
      });



    }

    private void login(String login_email,String login_password){

        firebaseAuth.signInWithEmailAndPassword(login_email,login_password).addOnSuccessListener(MainActivity.this, new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {

               // Toast.makeText(MainActivity.this, "Successfully loged in", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this, loggedinPage.class));

            }
        }).addOnFailureListener(MainActivity.this, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(MainActivity.this, "email or password is incorrect", Toast.LENGTH_SHORT).show();
            }
        });


    }


}