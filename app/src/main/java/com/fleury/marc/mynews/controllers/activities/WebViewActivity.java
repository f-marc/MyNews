package com.fleury.marc.mynews.controllers.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.fleury.marc.mynews.R;

public class WebViewActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        mWebView = findViewById(R.id.webview);

        Bundle mExtra = getIntent().getExtras();
        String mKey = mExtra.getString("KEY_URL");
        Log.i("Test", mKey);

        configureToolbar();

        mWebView.setWebViewClient(new WebViewClient());
        mWebView.getSettings().setUseWideViewPort(true);
        mWebView.loadUrl(mKey);
    }


    private void configureToolbar(){
        this.toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Article");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
