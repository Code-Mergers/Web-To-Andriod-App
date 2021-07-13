package com.chatterjeeabhirajofficial.smartclass;

import androidx.appcompat.app.AppCompatActivity;
import android.net.ConnectivityManager;
import android.content.Intent;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;


public class OfflineActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.offline_activity);
    }

    public void reload(View view) {
        if (!isNetworkAvailable()) {
           showToast("Please connect to the internet and refresh.");
            return;
        }
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        //noinspection deprecation
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();

    }

    public void showToast(String toastMessage) {
        Toast toast = Toast.makeText(this, toastMessage, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER_VERTICAL , 0, 0);
        toast.show();
    }
}
