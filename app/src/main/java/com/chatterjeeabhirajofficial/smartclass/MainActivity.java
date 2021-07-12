package com.chatterjeeabhirajofficial.smartclass;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private WebView web;
    private Toast mToastToShow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        // Set the toast and duration

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        if ( !isNetworkAvailable() ) { // loading offline
            Intent i = new Intent(this,OfflineActivity.class);
            startActivity(i);
        }
        else {
            Toast.makeText(MainActivity.this, "App is loading....", Toast.LENGTH_LONG).show();
            setContentView(R.layout.activity_main);
            web = findViewById(R.id.webView);
            WebSettings webSettings = web.getSettings();
//        webSettings.setAppCacheMaxSize( 5 * 1024 * 1024 ); // 5MB
//        webSettings.setAppCachePath( getApplicationContext().getCacheDir().getAbsolutePath() );
//        webSettings.setAllowFileAccess( true );
//        webSettings.setAppCacheEnabled( true );
//        webSettings.setJavaScriptEnabled( true );
//        webSettings.setCacheMode( WebSettings.LOAD_DEFAULT ); // load online by default

//        if ( !isNetworkAvailable() ) { // loading offline
//            webSettings.setCacheMode( WebSettings.LOAD_CACHE_ELSE_NETWORK );
//        }

            webSettings.setJavaScriptEnabled(true);
            web.setWebViewClient(new CallbackClass());
            web.setWebChromeClient(new WebChromeClient());
//        webSettings.setDomStorageEnabled(true);
            web.loadUrl("https://smartclass-mobile.netlify.app/");
        }

    }


    private class CallbackClass extends WebViewClient {
        @Override
        public boolean shouldOverrideKeyEvent(WebView view, KeyEvent event) {
            return false;
        }

    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService( CONNECTIVITY_SERVICE );
        //noinspection deprecation
         NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}