package com.akinseye.ndif_yemmanuel.handout;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

public class WorldMap extends AppCompatActivity implements World.OnFragmentInteractionListener, Africa.OnFragmentInteractionListener{

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    NavigationView naviview;
//    String noOfCases;
//    String noOfDeath;
//    String noOfRecovery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_world_map);

        TabLayout tabLayout = findViewById(R.id.tablayoutcovid);
        tabLayout.addTab(tabLayout.newTab().setText("WORLD").setIcon(R.drawable.world));
        tabLayout.addTab(tabLayout.newTab().setText("AFRICA").setIcon(R.drawable.africa4));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

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
                        Intent q = new Intent(WorldMap.this, HandoutCovid19.class);
                        startActivity(q);
                        break;
                    case R.id.mWorldMap:
                        mDrawerLayout.closeDrawers();
                        break;
                    case R.id.mtips:
                        Intent j = new Intent(WorldMap.this, CovidTips.class);
                        startActivity(j);
                        break;
                    case R.id.mtrivia:
                        Intent d = new Intent(WorldMap.this, HandoutTrivia.class);
                        startActivity(d);
                        break;
                    case R.id.mHotline:
                        Intent e = new Intent(WorldMap.this, NcdcHotline.class);
                        startActivity(e);
                        break;
                    case R.id.mSettings:
                        Intent set = new Intent(WorldMap.this, CovidSettings.class);
                        startActivity(set);
                        break;
                    default:
                        mDrawerLayout.closeDrawers();
                        break;
                }

                return true;
            }
        });


        final ViewPager viewPager = findViewById(R.id.pager);
        final PagerAdapterCovidWorldAfrica adapter = new PagerAdapterCovidWorldAfrica(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager
                .setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });




        LinearLayout menu = findViewById(R.id.linmenu);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDrawerLayout.openDrawer(Gravity.LEFT);
            }
        });

        LinearLayout tips = findViewById(R.id.lincovid19);
        tips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent move = new Intent(WorldMap.this, WorldMap.class);
                startActivity(move);
            }
        });

        LinearLayout home = findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent move1 = new Intent(WorldMap.this, HandoutCovid19.class);
                startActivity(move1);
            }
        });

        LinearLayout hotline = findViewById(R.id.lintips);
        hotline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent move2 = new Intent(WorldMap.this, CovidTips.class);
                startActivity(move2);
            }
        });

        LinearLayout setting = findViewById(R.id.lintrivia);
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent move3 = new Intent(WorldMap.this, HandoutTrivia.class);
                startActivity(move3);
            }
        });
    }

    @Override
    public void onBackPressed(){
        //do nothing
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
