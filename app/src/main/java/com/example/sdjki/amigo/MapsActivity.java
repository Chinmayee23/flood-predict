package com.example.sdjki.amigo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class MapsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        WebView browser = (WebView) findViewById(R.id.webview);
        browser.loadUrl("https://www.ieeevesit.org/teproject/map.html");
    }

}
