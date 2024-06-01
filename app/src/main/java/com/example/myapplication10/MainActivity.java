package com.example.myapplication10;
//user_home_page

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class MainActivity extends AppCompatActivity {

    private Button buttonSearch;
    private ListView listViewChats;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonSearch = findViewById(R.id.buttonSearch);
        listViewChats = findViewById(R.id.listViewChats);

        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, searchresult.class);
                startActivity(intent);
            }
        });

        // Check if the user is logged in
        if (isUserLoggedIn()) {
            // Check if the profile details are submitted
            if (isProfileDetailsSubmitted()) {
                // If profile details are submitted, start the main activity
                startMainActivity();
                return;
            }
        }else {
            // If none of the conditions are met, start the sign-in activity
            startSignInActivity();

        }



    }

    private void startExpertSignin() {
        Intent intent = new Intent(this, expert_signin.class);
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

    private boolean isUserLoggedIn() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        return preferences.getBoolean("isLoggedIn", false);
    }

    private boolean isProfileDetailsSubmitted() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        return preferences.getBoolean("isProfileSubmitted", false);
    }


    private void startSignInActivity() {
        Intent intent = new Intent(this, SignInActivity.class);
        startActivity(intent);
        finish();
    }


    private void startMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void startUserProfileCreation() {
        Intent intent = new Intent(this, UserProfilecreat.class);
        startActivity(intent);
        finish();
    }


    // Show options popup menu
    public void showOptionsPopupMenu(View view) {
        PopupMenu popupMenu = new PopupMenu(this, view, Gravity.END, 0, R.style.PopupMenuStyle); // Pass the custom style resource here
        popupMenu.inflate(R.menu.popup_menu);

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.menu_profile_update) {
                    // Handle profile update action
                    Toast.makeText(MainActivity.this, "Profile Update", Toast.LENGTH_SHORT).show();
                    // Handle profile update option menu click
                    Intent intent = new Intent(MainActivity.this, userprofileupdate.class);
                    startActivity(intent);
                    return true;
                } else if (id == R.id.menu_sign_out) {
                    // Handle sign-out action
                    Toast.makeText(MainActivity.this, "Sign Out", Toast.LENGTH_SHORT).show();
                    // Display sign-out confirmation dialog
                    showSignOutConfirmationDialog();
                    return true;
                }
                return false;
            }
        });

        popupMenu.show();
    }

    // Show sign-out confirmation dialog
    private void showSignOutConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to sign out?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Clear sign-in data and navigate to sign-in activity
                        clearSignInData();
                        navigateToSignInActivity();

                        // Exit the app
                        finishAffinity();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                        dialog.dismiss();
                    }
                });
        // Create and show the AlertDialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    // Clear sign-in data and revoke access token
    private void clearSignInData() {
        // Implement logic to clear any stored sign-in data, such as user credentials or tokens
        // For example, you can clear SharedPreferences or reset user session data
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.apply();

        // Revoke access token
        GoogleSignIn.getClient(this, new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).build())
                .revokeAccess().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            // Access token revoked successfully
                        } else {
                            // Failed to revoke access token
                        }
                    }
                });
    }

    // Navigate to sign-in activity
    private void navigateToSignInActivity() {
        Intent intent = new Intent(MainActivity.this, SignInActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        // Add any custom behavior here if needed
        // For example, you can leave it empty to disable the back button
        // Or you can implement your own logic for handling the back button press
    }
}
