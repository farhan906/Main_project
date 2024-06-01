package com.example.myapplication10;

import android.content.ComponentName;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;


public class UserProfilecreat extends AppCompatActivity {

    private EditText editTextName;
    private EditText editTextAge;
    private EditText editTextGender;
    private EditText editTextNationality;
    private Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profilecreat);

        // Initialize views
        editTextName = findViewById(R.id.editTextName);
        editTextAge = findViewById(R.id.editTextAge);
        editTextGender = findViewById(R.id.editTextGender);
        editTextNationality = findViewById(R.id.editTextNationality);
        btnSubmit = findViewById(R.id.btnSubmit);

        // Set onClickListener for Submit button
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle submit button click here
                // Retrieve user inputs from EditText fields
                String name = editTextName.getText().toString().trim();
                String age = editTextAge.getText().toString().trim();
                String gender = editTextGender.getText().toString().trim();
                String nationality = editTextNationality.getText().toString().trim();

                // Validate user inputs (e.g., ensure fields are not empty)
                if (name.isEmpty() || age.isEmpty() || gender.isEmpty() || nationality.isEmpty()) {
                    Toast.makeText(UserProfilecreat.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                } else {
                    // Save the profile details
                    saveProfileDetails(name, age, gender, nationality);

                    Intent intent = new Intent(UserProfilecreat.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
            }
        });
    }

    // Method to save profile details
    private void saveProfileDetails(String name, String age, String gender, String nationality) {
        // Save the profile details submission status
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("isProfileSubmitted", true);
        editor.putString("name", name);
        editor.putString("age", age);
        editor.putString("gender", gender);
        editor.putString("nationality", nationality);
        editor.apply();
    }

    // Method to start MainActivity


    // Check if the user is logged in
    private boolean isUserLoggedIn() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        return preferences.getBoolean("isLoggedIn", false);
    }

    // Check if profile details are submitted
    private boolean isProfileDetailsSubmitted() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        return preferences.getBoolean("isProfileSubmitted", false);
    }

    // Method to start SignInActivity
    private void startSignInActivity() {
        Intent intent = new Intent(this, SignInActivity.class);
        startActivity(intent);
        finish(); // Finish the current activity to prevent going back to it with the back button
    }


    @Override
    public void onBackPressed() {
        // Add any custom behavior here if needed
        // For example, you can leave it empty to disable the back button
        // Or you can implement your own logic for handling the back button press
    }
}
