package com.myapp.memotutorial;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginPage extends AppCompatActivity {

    private String email, password;
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    private static final String TAG = "LOGIN PAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        final EditText emailView = findViewById(R.id.login_email);
        final EditText pswdView = findViewById(R.id.login_password);
        Button btn = findViewById(R.id.login_btn);
        TextView createAcntView = findViewById(R.id.create_account);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = emailView.getText().toString().trim().toLowerCase();
                password = pswdView.getText().toString().trim();

                if (email.equals("")){
                    emailView.setError("Please enter your email");
                    return;
                }
                if (password.equals("")){
                    pswdView.setError("Please enter your password");
                    return;
                }

                firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Log.d(TAG, "onComplete: User successfully logged in for: "+ email);
                            startActivity(new Intent(LoginPage.this, Home.class));
                            finish();

                        }else {
                            Log.d(TAG, "onComplete: User log in failed.");
                            Toast.makeText(LoginPage.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        createAcntView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginPage.this, SignupPage.class));

            }
        });


    }
}
