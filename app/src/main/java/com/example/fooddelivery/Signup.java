package com.example.fooddelivery;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Signup extends AppCompatActivity {
    EditText name,email,mobile,address,password,cp;
    Button signup_ok;
    TextView login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        getSupportActionBar().hide();

        name=findViewById(R.id.name);
        email=findViewById(R.id.email);
        mobile=findViewById(R.id.mobile);
        address=findViewById(R.id.address);
        password=findViewById(R.id.pass);
        cp=findViewById(R.id.cp);
        signup_ok=findViewById(R.id.signup_ok);
        login=findViewById(R.id.Login_ok);




            signup_ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(erro()) {
                        String Email = email.getText().toString();
                        String Pass = password.getText().toString();

                        FirebaseAuth.getInstance().createUserWithEmailAndPassword(Email,Pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    Intent i = new Intent(Signup.this, Login.class);
                                     i.putExtra("EMAIL", Email);
                                     i.putExtra("PASS", Pass);
                                     startActivity(i);
                                     finish();
                                     Toast.makeText(Signup.this, "sucsessfully Signup", Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    Toast.makeText(Signup.this, "Failed Signup", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });


                    }
                }
            });


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Signup.this,Login.class);
                startActivity(i);
            }
        });


    }

    private boolean erro() {
        if(name.getText().toString().isEmpty()){
            Toast.makeText(this, "name enter please", Toast.LENGTH_SHORT).show();
            return  false;
        }
        if(email.getText().toString().isEmpty()){
            Toast.makeText(this, "email enter please", Toast.LENGTH_SHORT).show();
            return  false;
        }
        if(mobile.getText().toString().isEmpty()){
            Toast.makeText(this, "mobile enter please", Toast.LENGTH_SHORT).show();
            return  false;
        }
        if(mobile.getText().toString().length()!=10){
            Toast.makeText(this, "please enter 10 digit " , Toast.LENGTH_SHORT).show();
            return  false;
        }
        if(password.getText().toString().isEmpty()){
            Toast.makeText(this, "password enter please", Toast.LENGTH_SHORT).show();
            return  false;
        }
        if(cp.getText().toString().isEmpty()){
            Toast.makeText(this, "confirm password enter please", Toast.LENGTH_SHORT).show();
            return  false;
        }
        if(!password.getText().toString().equals(cp.getText().toString())){
            Toast.makeText(this, "check the password", Toast.LENGTH_SHORT).show();
            return  false;
        }
        return true;
    }

}