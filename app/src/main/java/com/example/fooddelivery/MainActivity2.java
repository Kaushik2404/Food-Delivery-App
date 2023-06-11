package com.example.fooddelivery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity2 extends AppCompatActivity {
    Button Mlogin,Msignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Mlogin=findViewById(R.id.mainlogin);
        Msignup=findViewById(R.id.maisnsignup);
        getSupportActionBar().hide();

        Mlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity2.this,Login.class);
                startActivity(i);
                finish();
            }
        });
        Msignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity2.this,Signup.class);
                startActivity(i);
                finish();
            }
        });
    }
}