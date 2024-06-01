package com.example.myapplication10;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class expertprofileupdate extends AppCompatActivity {

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
        setContentView(R.layout.expertprofileupdate);

        // Initialize views
        editTextName = findViewById(R.id.editTextName);
        editTextAge = findViewById(R.id.editTextAge);
        editTextGender = findViewById(R.id.editTextGender);
        editTextFieldOfExpertise = findViewById(R.id.editTextFieldOfExpertise);
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
                    Toast.makeText(expertprofileupdate.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                } else {
                    // Proceed to save the expert profile (you can implement your logic here)
                    // For demo, just displaying a Toast message
                    Toast.makeText(expertprofileupdate.this, "Profile updated successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(expertprofileupdate.this, expert_homepage.class);
                    startActivity(intent);
                }
            }
        });
    }
}
