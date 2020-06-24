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

public class SignupPage extends AppCompatActivity {

    private String email, password, confirmPassword;
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    private static final String TAG = "SIGNUP PAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_page);

        final EditText emailView = findViewById(R.id.signup_email);
        final EditText passwordView = findViewById(R.id.signup_password);
        final EditText confirmPswdView = findViewById(R.id.confirm_password);
        Button btn = findViewById(R.id.signup_btn);
        TextView haveAcntView = findViewById(R.id.already_have_acnt);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = emailView.getText().toString().trim().toLowerCase();
                password = passwordView.getText().toString().trim();
                confirmPassword = confirmPswdView.getText().toString().trim();

                if (email.equals("")){
                    emailView.setError("Enter your email");
                    return;
                }else if(password.equals("")){
                    passwordView.setError("Please enter your password");
                    return;
                }else if(confirmPassword.equals("")){
                    confirmPswdView.setError("This field cannot be empty");
                    return;
                }

                if (!password.equals(confirmPassword)){
                    Toast.makeText(SignupPage.this, "Confirm Password did not match.", Toast.LENGTH_SHORT).show();
                    return;
                }

                firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Log.d(TAG, "onComplete: User account created for: "+ email);
                            startActivity(new Intent(SignupPage.this, Home.class));
                            finish();

                        }else{
                            Log.d(TAG, "onComplete: Signup process failed");
                            Toast.makeText(SignupPage.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });

            }
        });
        haveAcntView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignupPage.this, LoginPage.class));
                finish();
            }
        });




    }
}
