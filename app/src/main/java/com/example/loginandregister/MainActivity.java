package com.example.loginandregister;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private TextView tvWelcome;
    private Button btnLogout;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // --- Initialize Firebase Auth ---
        mAuth = FirebaseAuth.getInstance();

        // --- Initialize UI Components ---
        tvWelcome = findViewById(R.id.tvWelcome);
        btnLogout = findViewById(R.id.btnLogout);

        // Check if a user is currently logged in
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser == null) {
            // If no user is logged in, redirect to the Login page and close this activity
            Log.d(TAG, "No user logged in, redirecting to LoginActivity.");
            startActivity(new Intent(this, LoginActivity.class));
            finish();
            return; // Stop further execution of onCreate
        }

        // If a user is logged in, display their name or email
        String name = currentUser.getDisplayName();
        if (name == null || name.trim().isEmpty()) {
            name = currentUser.getEmail();
        }
        tvWelcome.setText("Welcome, " + (name != null ? name : "User") + "!");

        // --- Set up Logout Button (Simplified) ---
        btnLogout.setOnClickListener(v -> {
            // Sign the user out
            mAuth.signOut();
            Log.d(TAG, "User signed out.");

            // Redirect to the Login page
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);

            // Close MainActivity so the user cannot go back to it
            finish();
        });
    }
}
