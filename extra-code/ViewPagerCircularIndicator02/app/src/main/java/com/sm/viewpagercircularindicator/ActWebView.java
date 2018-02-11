package com.sm.viewpagercircularindicator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class ActWebView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_web_view);
        WebView sysSimpleWebView=(WebView) findViewById(R.id.sysSimpleWebView);
        sysSimpleWebView.loadUrl("file:///android_asset/sample.html");
    }
}
