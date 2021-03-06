package com.example.intesavincente;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Launch_Screen extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*
         * Handle the splash screen transition with SplashScreen API
         * (see https://developer.android.com/guide/topics/ui/splash-screen)
         * SplashScreen splashScreen = SplashScreen.installSplashScreen(this);
         */

        setContentView(R.layout.launch_screen);

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
            navController.navigate(R.id.action_from_launchScreenActivity_to_MainActivity2);
            /* The equivalent way with explicit Intent
            if (arePreferencesSet()) {
                startActivity(new Intent(this, NewsActivity.class));
            } else {
                startActivity(new Intent(this, PreferencesActivity.class));
            }*/
        } else {
            navController.navigate(R.id.action_from_launchScreenActivity_to_authenticationActivity);
        }
        finish();
    }


/*    private boolean arePreferencesSet() {
        SharedPreferences sharedPref = getSharedPreferences(Constants.SHARED_PREFERENCES_FILE_NAME, Context.MODE_PRIVATE);

        String countryOfInterest = sharedPref.getString(Constants.SHARED_PREFERENCES_COUNTRY_OF_INTEREST, null);
        Set<String> topicsOfInterest = sharedPref.getStringSet(Constants.SHARED_PREFERENCES_TOPICS_OF_INTEREST, null);

        if (countryOfInterest != null && topicsOfInterest != null) {
            return true;
        }

        return false;
    }*/
}
