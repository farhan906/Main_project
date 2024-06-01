package com.example.myapplication10;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

public class ExpertProfileCreat extends AppCompatActivity {

    private EditText editTextName;
    private EditText editTextAge;
    private EditText editTextGender;
    private EditText editTextFieldOfExpertise;
    private EditText editTextQualifications;
    private EditText editTextRemarks;
    private EditText editTextLocation;
    private Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.expert_profile_creat);

        // Initialize views
        editTextName = findViewById(R.id.editTextName);
        editTextAge = findViewById(R.id.editTextAge);
        editTextGender = findViewById(R.id.editTextGender);
        editTextFieldOfExpertise = findViewById(R.id.editTextExpertise);
        editTextQualifications = findViewById(R.id.editTextQualifications);
        editTextRemarks = findViewById(R.id.editTextRemarks);
        editTextLocation = findViewById(R.id.editTextLocation);
        btnSubmit = findViewById(R.id.btnSubmit);

        // Set onClickListener for Submit button
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle submit button click here
                // Retrieve expert profile details from EditText fields
                String name = editTextName.getText().toString().trim();
                String age = editTextAge.getText().toString().trim();
                String gender = editTextGender.getText().toString().trim();
                String fieldOfExpertise = editTextFieldOfExpertise.getText().toString().trim();
                String qualifications = editTextQualifications.getText().toString().trim();
                String remarks = editTextRemarks.getText().toString().trim();
                String location = editTextLocation.getText().toString().trim();

                // Validate expert profile details (e.g., ensure fields are not empty)
                if (name.isEmpty() || age.isEmpty() || gender.isEmpty() || fieldOfExpertise.isEmpty()
                        || qualifications.isEmpty() || remarks.isEmpty() || location.isEmpty()) {
                    Toast.makeText(ExpertProfileCreat.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                } else {

                    // Proceed to save the expert profile (you can implement your logic here)
                    // For demo, just displaying a Toast message
                    Toast.makeText(ExpertProfileCreat.this, "Profile saved successfully", Toast.LENGTH_SHORT).show();

                    saveProfileDetailsSubmitted();
                    navigateToExpertHomePage();
                }
            }
        });


    }

    private boolean isExpertLoggedIn() {
        SharedPreferences preferences = android.preference.PreferenceManager.getDefaultSharedPreferences(this);
        return preferences.getBoolean("isExpertLoggedIn", false);
    }

    private boolean isExpertProfileSubmitted() {
        SharedPreferences preferences = android.preference.PreferenceManager.getDefaultSharedPreferences(this);
        return preferences.getBoolean("isExpertProfileSubmitted", false);
    }

    private void startSignInActivity() {
        Intent intent = new Intent(this, SignInActivity.class);
        startActivity(intent);
        finish();
    }

    private void startExpertProfileCreation() {
        Intent intent = new Intent(this, ExpertProfileCreat.class);
        startActivity(intent);
        finish();
    }

    private void startExpertHomePage() {
        Intent intent = new Intent(this, expert_homepage.class);
        startActivity(intent);
        finish();
    }

    private void saveProfileDetailsSubmitted() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("isExpertProfileSubmitted", true);
        editor.apply();
    }

    private void navigateToExpertHomePage() {
        Intent intent = new Intent(ExpertProfileCreat.this, expert_homepage.class);
        intent.putExtra("intent_to_expert_home_page", true);
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
