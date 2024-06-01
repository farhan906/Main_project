package com.example.myapplication10;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;
import java.util.List;

public class expert_homepage extends AppCompatActivity {

    private RecyclerView recyclerViewChats;
    private ChatAdapter chatAdapter;
    private List<ChatItem> chatItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.expert_homepage);


        recyclerViewChats = findViewById(R.id.recyclerViewChats);
        recyclerViewChats.setLayoutManager(new LinearLayoutManager(this));
        chatItems = new ArrayList<>();
        chatAdapter = new ChatAdapter(chatItems);
        recyclerViewChats.setAdapter(chatAdapter);

        // Example: Add dummy chat items
        for (int i = 0; i < 10; i++) {
            ChatItem chatItem = new ChatItem("User " + i, "Doubt " + i);
            chatItems.add(chatItem);
        }
        chatAdapter.notifyDataSetChanged();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_options) {
            // Handle click on menu icon here
            showOptionsPopupMenu(findViewById(R.id.menu_options)); // Or any other logic you want to execute
            return true;
        }
        return super.onOptionsItemSelected(item);
    }



    public void showOptionsPopupMenu(View view) {
        PopupMenu popupMenu = new PopupMenu(this, view, Gravity.END, 0, R.style.PopupMenuStyle); // Pass the custom style resource here
        popupMenu.inflate(R.menu.popup_menu);

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.menu_profile_update) {
                    // Handle profile update action
                    Toast.makeText(expert_homepage.this, "Profile Update", Toast.LENGTH_SHORT).show();
                    // Handle profile update option menu click
                    Intent intent = new Intent(expert_homepage.this, expertprofileupdate.class);
                    startActivity(intent);
                    return true;
                } else if (id == R.id.menu_sign_out) {
                    // Handle sign-out action
                    Toast.makeText(expert_homepage.this, "Sign Out", Toast.LENGTH_SHORT).show();
                    // Display sign-out confirmation dialog
                    showSignOutConfirmationDialog();
                    return true;
                }
                return false;
            }
        });

        popupMenu.show();
    }

    private void showSignOutConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to sign out?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Clear sign-in data and navigate to sign-in activity
                        clearSignInData();
                        navigateToSignInActivity();
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


    private void navigateToSignInActivity() {
        // Navigate to the sign-in activity
        Intent intent = new Intent(expert_homepage.this, SignInActivity.class);
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
