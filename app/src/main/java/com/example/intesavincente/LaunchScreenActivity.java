package com.example.intesavincente;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.gms.common.internal.Constants;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Set;
import androidx.appcompat.app.AppCompatActivity;
// import androidx.core.splashscreen.SplashScreen;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Set;

import com.example.intesavincente.R;
import com.example.intesavincente.ui.LoginFragment;
import com.example.intesavincente.Constants;
public class LaunchScreenActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*
         * Handle the splash screen transition with SplashScreen API
         * (see https://developer.android.com/guide/topics/ui/splash-screen)
         * SplashScreen splashScreen = SplashScreen.installSplashScreen(this);
         */

        setContentView(R.layout.fragment_profilo);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onStart() {
        super.onStart();
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.launch_screen_fragment_container_view);
        NavController navController = navHostFragment.getNavController();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (currentUser != null) {
            // Start another Activity with Navigation Controller
            if (arePreferencesSet()) {
                navController.navigate(R.id.action_from_launchScreenActivity_to_);
            } else {
                navController.navigate(R.id.action_from_launchScreenActivity_to_preferencesActivity);
            }
            /* The equivalent way with explicit Intent
            if (arePreferencesSet()) {
                startActivity(new Intent(this, NewsActivity.class));
            } else {
                startActivity(new Intent(this, PreferencesActivity.class));
            }*/
        } else {
            navController.navigate(R.id.action_from_MainActivity_to_authenticationActivity);
        }
        finish();
    }

    /**
     * It checks if the preferences have already been set by the user.
     *
     * @return true if the preferences have been set, false otherwise.
     */
    private boolean arePreferencesSet() {
        SharedPreferences sharedPref = getSharedPreferences(Constants.SHARED_PREFERENCES_FILE_NAME, Context.MODE_PRIVATE);

        String countryOfInterest = sharedPref.getString(Constants.SHARED_PREFERENCES_COUNTRY_OF_INTEREST, null);
        Set<String> topicsOfInterest = sharedPref.getStringSet(Constants.SHARED_PREFERENCES_TOPICS_OF_INTEREST, null);

        if (countryOfInterest != null && topicsOfInterest != null) {
            return true;
        }

        return false;
    }
}
