package com.example.myapplication10;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication10.R;
import com.example.myapplication10.UserProfilecreat;
import com.example.myapplication10.expert_signin;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.firebase.auth.AuthCredential;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class SignInActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN_USER = 1001;
    private static final String TAG = "GoogleSignInActivity";

    private EditText editTextName, editTextPassword;
    private Button btnSubmit, btnExpertSignIn;
    private GoogleSignInClient mGoogleSignInClientUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_sign_in);

        // Initialize views
        editTextName = findViewById(R.id.editTextName);
        editTextPassword = findViewById(R.id.editTextPassword);
        btnSubmit = findViewById(R.id.btnsubmit);
        btnExpertSignIn = findViewById(R.id.btnExpertSignIn);

        // Set onClickListener for Submit button
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle submit button click
                String username = editTextName.getText().toString();
                String password = editTextPassword.getText().toString();
                // Validate email and password, and proceed accordingly
                if (username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(SignInActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                } else {
                    signInWithEmailPassword(username, password);
                }
            }
        });

        // Set onClickListener for Expert Sign-In button
        btnExpertSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle expert sign-in button click
                Toast.makeText(SignInActivity.this, "Expert Sign-In Clicked", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SignInActivity.this, expert_signin.class);
                startActivity(intent);
            }
        });

        // Configure Google Sign-In for user sign-in page
        GoogleSignInOptions userSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClientUser = GoogleSignIn.getClient(this, userSignInOptions);

        // Set onClickListener for Sign-In with Google button
        findViewById(R.id.btnSignInGoogle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signInWithGoogle();
            }
        });
    }

    private void signInWithGoogle() {
        Intent signInIntent = mGoogleSignInClientUser.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN_USER);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN_USER) {
            try {
                // Google Sign In was successful, authenticate with Firebase
                Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
                GoogleSignInAccount account = task.getResult(ApiException.class);
                handleGoogleSignInSuccess(account);
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e);
                Toast.makeText(this, "Google Sign-In failed", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void handleGoogleSignInSuccess(GoogleSignInAccount account) {
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        FirebaseAuth.getInstance().signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign-in success
                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                            if (user != null) {
                                String userName = user.getDisplayName();
                                String userEmail = user.getEmail();
                                Toast.makeText(SignInActivity.this, "Signed in as: " + userName + " (" + userEmail + ")", Toast.LENGTH_SHORT).show();
                                navigateToProfileCreation();
                            }
                        } else {
                            // Sign-in failed
                            Toast.makeText(SignInActivity.this, "Google Sign-In failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void signInWithEmailPassword(String username, String password) {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(username, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign-in success
                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                            if (user != null) {
                                String userName = user.getDisplayName();
                                String password = user.getDisplayName();
                                Toast.makeText(SignInActivity.this, "Signed in as: " + userName , Toast.LENGTH_SHORT).show();
                                navigateToProfileCreation();
                            }
                        } else {
                            // Sign-in failed
                            Toast.makeText(SignInActivity.this, "Authentication failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }




    private void navigateToProfileCreation() {
        // Save the logged-in state in SharedPreferences
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("isLoggedIn", true);
        editor.apply();

        Intent intent = new Intent(SignInActivity.this, UserProfilecreat.class);
        startActivity(intent);
        finish(); // Finish the sign-in activity to prevent going back to it with back button
    }
}

