package com.akinseye.ndif_yemmanuel.handout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.squareup.picasso.Picasso;


public class CovidTipsPDFview extends AppCompatActivity {

    WebView webView;
    String content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_covid_tips_pdfview);

        Intent intent = getIntent();
        String title = intent.getStringExtra("Title");
        content = intent.getStringExtra("Content");
        String source = intent.getStringExtra("Source");
        String icon = intent.getStringExtra("Icon");

        ImageView backBtn = findViewById(R.id.backbtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mc = new Intent(CovidTipsPDFview.this, CovidTips.class);
                startActivity(mc);
            }
        });
        ImageView ico = findViewById(R.id.icon);
        TextView titl = findViewById(R.id.title);
        TextView sour = findViewById(R.id.source);

        titl.setText(title);
        sour.setText(source);
        Picasso.get().load(icon).into(ico);

        final ProgressBar progressBar = findViewById(R.id.progressBar);


        webView = findViewById(R.id.webview);
        webView.setWebViewClient(new WebViewClient()
        {
            public void onPageFinished(WebView view, String url) {
                // do your stuff here
                progressBar.setVisibility(View.INVISIBLE);
            }
        });
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("http://docs.google.com/gview?embedded=true&url="+content);

    }


    @Override
    public void onBackPressed(){
        //do nothing
    }



}
