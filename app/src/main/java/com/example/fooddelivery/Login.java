package com.example.fooddelivery;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class Login extends AppCompatActivity {
    EditText eemail,password;
    Button login,fb,google;
    TextView forgetpass,signup;
    FirebaseAuth auth=FirebaseAuth.getInstance();
    ProgressDialog progressBar;

    GoogleSignInClient googleSignInClient;
    private GoogleApiClient googleApiClient;
    String name, email;
    String idToken;
    private static final int RC_SIGN_IN = 1;
    private FirebaseAuth.AuthStateListener authStateListener;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        eemail=findViewById(R.id.Lemail);
        password=findViewById(R.id.Lpassword);
        login=findViewById(R.id.login);
        fb=findViewById(R.id.fblogin);
        google=findViewById(R.id.googlelogin);
        forgetpass=findViewById(R.id.forgetpass);
        signup=findViewById(R.id.signup);
        progressBar = new ProgressDialog(this);


        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("345190570734-f4j4a8h3jd2iu4n0d23ms3h6scq0dmqm.apps.googleusercontent.com")
                .requestEmail()
                .build();

        // Initialize sign in client
        googleSignInClient = GoogleSignIn.getClient(Login.this, googleSignInOptions);




        google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = googleSignInClient.getSignInIntent();
                // Start activity for result
                startActivityForResult(intent, 100);
            }
        });


        FirebaseUser firebaseUser = auth.getCurrentUser();

        if (firebaseUser != null) {
            // When user already sign in redirect to profile activity
            Intent intent=new Intent(Login.this,Home.class);
            startActivity(intent);
            finish();
        }

        login.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 if(eemail.getText().toString().isEmpty() && password.getText().toString().isEmpty()){
                     Toast.makeText(Login.this, " filed can't  be blank", Toast.LENGTH_SHORT).show();
                 }else{
                     progressBar.setMessage("Processing...");
                     progressBar.show();
                                     FirebaseAuth.getInstance().signInWithEmailAndPassword(eemail.getText().toString(),password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                         @Override
                                         public void onComplete(@NonNull Task<AuthResult> task) {
                                             if(task.isSuccessful()){
                                                 Intent i=new Intent(Login.this,Home.class);
                                                 startActivity(i);
                                                 Toast.makeText(Login.this,"Sucsessfully Login",Toast.LENGTH_SHORT).show();
                                                 progressBar.dismiss();
                                                 finish();

                                             }
                                             else {
                                                 Toast.makeText(Login.this,"Failed",Toast.LENGTH_SHORT).show();
                                                 progressBar.dismiss();
                                             }
                                         }
                                     });

                                 }

                 }




         });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(Login.this,Signup.class);
                startActivity(i);
                finish();
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Check condition
        if (requestCode == 100) {
            // When request code is equal to 100 initialize task
            Task<GoogleSignInAccount> signInAccountTask = GoogleSignIn.getSignedInAccountFromIntent(data);
            // check condition
            if (signInAccountTask.isSuccessful()) {
                // When google sign in successful initialize string
                String s = "Google sign in successful";
                // Display Toast
                displayToast(s);
                // Initialize sign in account
                try {
                    // Initialize sign in account
                    GoogleSignInAccount googleSignInAccount = signInAccountTask.getResult(ApiException.class);
                    // Check condition
                    if (googleSignInAccount != null) {
                        // When sign in account is not equal to null initialize auth credential
                        AuthCredential authCredential = GoogleAuthProvider.getCredential(googleSignInAccount.getIdToken(), null);
                        // Check credential
                        auth.signInWithCredential(authCredential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                // Check condition
                                if (task.isSuccessful()) {
                                    // When task is successful redirect to profile activity display Toast
                                    startActivity(new Intent(Login.this, Home.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                                    displayToast("Firebase authentication successful");
                                } else {
                                    // When task is unsuccessful display Toast
                                    displayToast("Authentication Failed :" + task.getException().getMessage());
                                }
                            }
                        });
                    }
                } catch (ApiException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void displayToast(String s) {
        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
    }

}