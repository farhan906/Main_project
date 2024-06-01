package com.example.myapplication10;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class expert_signin extends AppCompatActivity {

    private static final int RC_SIGN_IN = 1001;
    private static final String TAG = "GoogleSignInActivity";

    private EditText editTextEmail;
    private EditText editTextPassword;
    private Button btnSubmit;
    private GoogleSignInClient mGoogleSignInClientExpert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.expert_signin);

        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        btnSubmit = findViewById(R.id.btnsubmit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editTextEmail.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();

                if (username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(expert_signin.this, "Please enter email and password", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(expert_signin.this, "Email: " + username + ", Password: " + password, Toast.LENGTH_SHORT).show();
                    navigateToProfileCreation();
                }
            }
        });

        GoogleSignInOptions expertSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClientExpert = GoogleSignIn.getClient(this, expertSignInOptions);

        findViewById(R.id.btnSignInGoogle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signInWithGoogle();
            }
        });
    }

    private void signInWithGoogle() {
        Intent signInIntent = mGoogleSignInClientExpert.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            try {
                Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
                GoogleSignInAccount account = task.getResult(ApiException.class);
                handleGoogleSignInSuccess(account);
            } catch (ApiException e) {
                Log.w(TAG, "Google sign in failed", e);
                Toast.makeText(this, "Google Sign-In failed", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void handleGoogleSignInSuccess(GoogleSignInAccount account) {
        String userName = account.getDisplayName();
        String userEmail = account.getEmail();
        Toast.makeText(this, "Signed in as: " + userName + " (" + userEmail + ")", Toast.LENGTH_SHORT).show();
        navigateToProfileCreation();
    }

    private void navigateToProfileCreation() {
        Intent intent = new Intent(this, ExpertProfileCreat.class);
        startActivity(intent);
        finish();
    }
}
