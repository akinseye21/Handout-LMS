package com.akinseye.ndif_yemmanuel.handout;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class AboutHandout extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    NavigationView naviview;
    WebView webView;
    ImageView menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_handout);

        mDrawerLayout = findViewById(R.id.drawerlayout);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);

        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();


        final ProgressBar progressBar = findViewById(R.id.progressBar);

        naviview = findViewById(R.id.navigationview);
        naviview.inflateHeaderView(R.layout.navigation_header);

        naviview.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.mHome:
                        Intent z = new Intent(AboutHandout.this, HandoutCovid19.class);
                        startActivity(z);
                        break;
                    case R.id.mWorldMap:
                        Intent w = new Intent(AboutHandout.this, WorldMap.class);
                        startActivity(w);
                        break;
                    case R.id.mtips:
                        Intent j = new Intent(AboutHandout.this, CovidTips.class);
                        startActivity(j);
                        break;
                    case R.id.mtrivia:
                        Intent d = new Intent(AboutHandout.this, HandoutTrivia.class);
                        startActivity(d);
                        break;
                    case R.id.mHotline:
                        Intent e = new Intent(AboutHandout.this, NcdcHotline.class);
                        startActivity(e);
                        break;
                    case R.id.mSettings:
                        Intent set = new Intent(AboutHandout.this, CovidSettings.class);
                        startActivity(set);
                        break;
                    default:
                        mDrawerLayout.closeDrawers();
                        break;
                }

                return true;
            }
        });

        menu = findViewById(R.id.menu);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDrawerLayout.openDrawer(Gravity.LEFT);
            }
        });

        webView = findViewById(R.id.webview);
        webView.setWebViewClient(new WebViewClient()
        {
            public void onPageFinished(WebView view, String url) {
                // do your stuff here
                progressBar.setVisibility(View.INVISIBLE);
            }
        });
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("http://handout.com.ng/");
    }

    @Override
    public void onBackPressed(){
        //do nothing
    }
}
