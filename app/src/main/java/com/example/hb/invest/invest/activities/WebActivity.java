package com.example.hb.invest.invest.activities;

import android.annotation.TargetApi;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.example.hb.invest.R;
import com.example.hb.invest.databinding.ActivityWebBinding;
import com.example.hb.invest.invest.utiles.views.LoadingUtils;

public class WebActivity extends AppCompatActivity {

    ActivityWebBinding binding;
    private Toolbar actionBarToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_web);
        initComponent();
    }

    private void initComponent() {

        binding.tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WebActivity.this,MainActivity.class);
                intent.putExtra("isFromWeb", true);
                startActivity(intent);
            }
        });

        Intent intent = getIntent();
        if (intent != null) {
            String url = intent.getStringExtra("url");

            binding.webview.getSettings().setJavaScriptEnabled(true); // enable javascript
            binding.webview .loadUrl(url);
            binding.webview.setWebViewClient(new MyBrowser());
        }
    }

    private class MyBrowser extends WebViewClient {
        @Override
        public void onPageFinished(WebView view, String url) {
            LoadingUtils.getInstance(WebActivity.this).hideLoading();
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            LoadingUtils.getInstance(WebActivity.this).showLoading();
//            Toast.makeText(WebActivity.this, url, Toast.LENGTH_SHORT).show();
            if (url.contains("https://www.onepay.co.zm/transaction/success")) {
                String id = url.replace("https://www.onepay.co.zm/transaction/success/","");
                Intent intent = new Intent(WebActivity.this,SucessActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
                finish();
            } else if (url.contains("https://www.onepay.co.zm/transaction/declined")){
                        /*String id = url.replace("https://www.onepay.co.zm/transaction/declined/","");

                        Intent intent = new Intent(WebActivity.this,SucessActivity.class);
                        intent.putExtra("id", id);
                        startActivity(intent);
                        finish();*/
                Intent intent = new Intent(WebActivity.this, MainActivity.class);
                intent.putExtra("isFromWeb", true);
                startActivity(intent);
                finish();
            }
        }
    }
}
