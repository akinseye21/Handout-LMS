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
import android.widget.ImageView;
import android.widget.TextView;

public class CovidSettings extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    NavigationView naviview;
    TextView abtHandOut, tellafriend;
    ImageView menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_covid_settings);

        abtHandOut = findViewById(R.id.abtHandout);
        abtHandOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(CovidSettings.this, AboutHandout.class);
                startActivity(i);
            }
        });

        tellafriend = findViewById(R.id.tellafriend);
        tellafriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent j = new Intent(CovidSettings.this, CovidTellAFriend.class);
                startActivity(j);
            }
        });

        mDrawerLayout = findViewById(R.id.drawerlayout);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);

        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();


        naviview = findViewById(R.id.navigationview);
        naviview.inflateHeaderView(R.layout.navigation_header);

        naviview.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.mHome:
                        Intent z = new Intent(CovidSettings.this, HandoutCovid19.class);
                        startActivity(z);
                        break;
                    case R.id.mWorldMap:
                        Intent w = new Intent(CovidSettings.this, WorldMap.class);
                        startActivity(w);
                        break;
                    case R.id.mtips:
                        Intent j = new Intent(CovidSettings.this, CovidTips.class);
                        startActivity(j);
                        break;
                    case R.id.mtrivia:
                        Intent d = new Intent(CovidSettings.this, HandoutTrivia.class);
                        startActivity(d);
                        break;
                    case R.id.mHotline:
                        Intent e = new Intent(CovidSettings.this, NcdcHotline.class);
                        startActivity(e);
                        break;
                    case R.id.mSettings:
                        mDrawerLayout.closeDrawers();
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
    }

    @Override
    public void onBackPressed(){
        //do nothing
    }
}
