package com.chatterjeeabhirajofficial.smartclass;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private WebView web;
    RelativeLayout relativeLayout;
    private String webUrl = "https://smartclassmobile83325435435.netlify.app/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the toast and duration

//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        getSupportActionBar().hide();

        if (!isNetworkAvailable()) { // loading offline
            Intent i = new Intent(this, OfflineActivity.class);
            startActivity(i);
        } else {
            showToast("Loading...");
            setContentView(R.layout.activity_main);
            web = findViewById(R.id.webView);
            relativeLayout = (RelativeLayout) findViewById(R.id.relativeLayout);
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
            web.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideKeyEvent(WebView view, KeyEvent event) {
                    return false;
                }

            });
            web.setWebChromeClient(new WebChromeClient());
//        webSettings.setDomStorageEnabled(true);
            web.loadUrl(webUrl);

        }
    }


    //    private class CallbackClass extends WebViewClient {
//        @Override
//        public boolean shouldOverrideKeyEvent(WebView view, KeyEvent event) {
//            return false;
//        }
//
//    }
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        //noinspection deprecation
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public void showToast(String toastMessage) {
        Toast toast = Toast.makeText(this, toastMessage, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    public void checkConnection() {
        ConnectivityManager connectivityManager = (ConnectivityManager)
                this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifi = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobileNetwork = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        if (wifi.isConnected()) {
            web.loadUrl(webUrl);
            web.setVisibility(View.VISIBLE);
        } else if (mobileNetwork.isConnected()) {
            web.loadUrl(webUrl);
            web.setVisibility(View.VISIBLE);
        } else {
            web.setVisibility(View.GONE);
        }
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {

            case R.id.nav_previous:
                if (web.canGoBack()) {
                    web.goBack();
                }
                break;

            case R.id.nav_next:
                if (web.canGoForward()) {
                    web.goForward();
                }
                break;

            case R.id.nav_refresh:
                checkConnection();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}


