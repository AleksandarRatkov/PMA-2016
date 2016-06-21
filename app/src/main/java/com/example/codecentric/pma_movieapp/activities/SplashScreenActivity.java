package com.example.codecentric.pma_movieapp.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.os.Bundle;
import android.preference.PreferenceManager;

import com.example.codecentric.pma_movieapp.MainActivity;
import com.example.codecentric.pma_movieapp.R;

public class SplashScreenActivity extends Activity {

    private static int SPLASH_TIME_OUT = 4000; // splash ce biti vidljiv minimum SPLASH_TIME_OUT milisekundi
    private boolean splashScreenShow;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        splashScreenShow = PreferenceManager.getDefaultSharedPreferences(this).getBoolean(getString(R.string.pref_splash),true);
        // uradi inicijalizaciju u pozadinksom threadu
        new InitTask().execute();

    }

    private class InitTask extends AsyncTask<Void, Void, Void>
    {
        private long startTime;

        @Override
        protected void onPreExecute()
        {
            startTime = System.currentTimeMillis();
        }

        @Override
        protected Void doInBackground(Void... arg0)
        {
            if( splashScreenShow ){
                continueLogin();
            }
            startMainActivity();

            return null;
        }

        private void continueLogin()
        {
            // sacekaj tako da splash bude vidljiv minimum SPLASH_TIME_OUT milisekundi
            long timeLeft = SPLASH_TIME_OUT - (System.currentTimeMillis() - startTime);
            if(timeLeft < 0) timeLeft = 0;
            SystemClock.sleep(timeLeft);
        }
    }

    private void startMainActivity()
    {
        startActivity(new Intent(SplashScreenActivity.this, MainActivity.class));
        finish(); // da nebi mogao da ode back na splash
    }
}
