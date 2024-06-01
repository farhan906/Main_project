package com.example.myapplication10;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class searchresult extends AppCompatActivity {

    private EditText editTextSearch, editTextCountry, editTextState, editTextDistrict, editTextField;
    private Button buttonFilter;
    private RecyclerView recyclerViewSearchResults;
    private CardView cardViewFilterPopup;
    private SearchResultAdapter searchResultAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searchresult);

        // Initialize views
        editTextSearch = findViewById(R.id.editTextSearch);
        editTextCountry = findViewById(R.id.editTextCountry);
        editTextState = findViewById(R.id.editTextState);
        editTextDistrict = findViewById(R.id.editTextDistrict);
        editTextField = findViewById(R.id.editTextField);
        buttonFilter = findViewById(R.id.buttonFilter);
        recyclerViewSearchResults = findViewById(R.id.recyclerViewSearchResults);
        cardViewFilterPopup = findViewById(R.id.cardViewFilterPopup);

        // Set up RecyclerView
        recyclerViewSearchResults.setLayoutManager(new LinearLayoutManager(this));
        searchResultAdapter = new SearchResultAdapter(new ArrayList<Expert>());
        recyclerViewSearchResults.setAdapter(searchResultAdapter);

        // Set onClickListener for filter button
        buttonFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String country = editTextCountry.getText().toString().trim();
                String state = editTextState.getText().toString().trim();
                String district = editTextDistrict.getText().toString().trim();
                String fieldOfExpertise = editTextField.getText().toString().trim();

                // Perform filtering logic
                filterExperts(country, state, district, fieldOfExpertise);
            }
        });

        // Set onClickListener for search bar (optional if you want live search)
        editTextSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Perform search logic here (optional if you want live search)
                performSearch(editTextSearch.getText().toString().trim());
            }
        });

        // Set onClickListener for floating filter button
        findViewById(R.id.fabFilter).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show filter popup
                if (cardViewFilterPopup.getVisibility() == View.VISIBLE) {
                    cardViewFilterPopup.setVisibility(View.GONE);
                } else {
                    cardViewFilterPopup.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    // Method to filter experts based on criteria
    private void filterExperts(String country, String state, String district, String fieldOfExpertise) {
        // Perform filtering logic and query Firebase database
        // Once the data is retrieved, update the RecyclerView accordingly
        // This is where you would implement your Firebase query and update the RecyclerView
        // For demonstration, let's just show a toast message
        Toast.makeText(searchresult.this, "Filtering experts...", Toast.LENGTH_SHORT).show();
    }

    // Method to perform search based on search query (optional if you want live search)
    private void performSearch(String searchQuery) {
        // Perform search logic and update the RecyclerView accordingly
        // This is where you would implement your Firebase query and update the RecyclerView
        // For demonstration, let's just show a toast message
        Toast.makeText(searchresult.this, "Searching for: " + searchQuery, Toast.LENGTH_SHORT).show();
    }
}
