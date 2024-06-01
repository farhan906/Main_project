package com.example.myapplication10;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;


public class userprofileupdate extends AppCompatActivity {

    private EditText editTextName, editTextAge, editTextGender, editTextNationality;
    private Button btnUpdate;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userprofileupdate);

        // Initialize views
        editTextName = findViewById(R.id.editTextName);
        editTextAge = findViewById(R.id.editTextAge);
        editTextGender = findViewById(R.id.editTextGender);
        editTextNationality = findViewById(R.id.editTextNationality);
        btnUpdate = findViewById(R.id.btnUpdate);

        // Set onClickListener for Update button
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle update button click
                String name = editTextName.getText().toString().trim();
                String age = editTextAge.getText().toString().trim();
                String gender = editTextGender.getText().toString().trim();
                String nationality = editTextNationality.getText().toString().trim();

                // Validate inputs (optional)
                if (name.isEmpty() || age.isEmpty() || gender.isEmpty() || nationality.isEmpty()) {
                    Toast.makeText(userprofileupdate.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                } else {
                    // Proceed with updating the profile (implement your logic here)
                    // For demo, just displaying a Toast message
                    Toast.makeText(userprofileupdate.this, "Profile updated successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(userprofileupdate.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}