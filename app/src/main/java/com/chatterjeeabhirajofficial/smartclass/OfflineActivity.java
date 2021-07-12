package com.chatterjeeabhirajofficial.smartclass;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class OfflineActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.offline_activity);
    }

    public void reload(View view){
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
        finish();
    }
    // setContentView(R.layout.offline_activity);
}
