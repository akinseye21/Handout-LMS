package com.akinseye.ndif_yemmanuel.handout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class HandoutCovid19 extends AppCompatActivity implements Maps.OnFragmentInteractionListener, Charts.OnFragmentInteractionListener{

    int TotalNoOfCases;
    int TotalNoOfDeath;
    int TotalNoOfRecovery;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    NavigationView naviview;

    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handout_covid19);

        TabLayout tabLayout = findViewById(R.id.tablayoutcovid);
        tabLayout.addTab(tabLayout.newTab().setText("MAP").setIcon(R.drawable.marker4));
        tabLayout.addTab(tabLayout.newTab().setText("CHART").setIcon(R.drawable.up));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        Intent intent = getIntent();
        String email = intent.getStringExtra("email");
        String fullname = intent.getStringExtra("fullname");

        sharedpreferences = getSharedPreferences("MyCovidInfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString("email", email);
        editor.putString("fullname", fullname);
        editor.commit();


        mDrawerLayout = findViewById(R.id.drawerlayout);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);

        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();



        naviview = findViewById(R.id.navigationview);
        naviview.inflateHeaderView(R.layout.navigation_header);




        //get the data's first
        final int[] numArray = new int[1];
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        String url = "http://54.71.22.155/covid/getAllData";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //array is saved in here
                        try{
                            JSONArray jsonArray = new JSONArray(response);
                            numArray[0] = jsonArray.length();

                            for(int i=0; i<numArray[0]; i++){
                                JSONObject counter = jsonArray.getJSONObject(i);

                                String state = counter.getString("dstate");
                                String cases = counter.getString("cases");
                                String death = counter.getString("deaths");
                                String recovery = counter.getString("recovered");

                                TotalNoOfCases = TotalNoOfCases + Integer.parseInt(cases);
                                TotalNoOfDeath = TotalNoOfDeath + Integer.parseInt(death);
                                TotalNoOfRecovery = TotalNoOfRecovery + Integer.parseInt(recovery);
                            }

                            //Toast.makeText(getApplicationContext(), ""+TotalNoOfCases+" "+TotalNoOfDeath+" "+TotalNoOfRecovery, Toast.LENGTH_LONG).show();


                        }
                        catch (JSONException e){
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(), "Error in connection", Toast.LENGTH_LONG).show();
                        }

                        TextView confirmedcased = findViewById(R.id.confirmedcase);
                        TextView deathcased = findViewById(R.id.deathcase);
                        TextView recoverycase = findViewById(R.id.recoverycase);

                        confirmedcased.setText(Integer.toString(TotalNoOfCases));
                        deathcased.setText(Integer.toString(TotalNoOfDeath));
                        recoverycase.setText(Integer.toString(TotalNoOfRecovery));


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getApplicationContext(), "Error in connection", Toast.LENGTH_LONG).show();

            }
        });
        queue.add(stringRequest);






        final ViewPager viewPager = findViewById(R.id.pager);
        final PagerAdapterCovid adapter = new PagerAdapterCovid(getSupportFragmentManager(), tabLayout.getTabCount());
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



        naviview.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.mHome:
                        mDrawerLayout.closeDrawers();
                        break;
                    case R.id.mWorldMap:
                        Intent w = new Intent(HandoutCovid19.this, WorldMap.class);
                        startActivity(w);
                        break;
                    case R.id.mtips:
                        Intent j = new Intent(HandoutCovid19.this, CovidTips.class);
                        startActivity(j);
                        break;
                    case R.id.mtrivia:
                        Intent d = new Intent(HandoutCovid19.this, HandoutTrivia.class);
                        startActivity(d);
                        break;
                    case R.id.mHotline:
                        Intent e = new Intent(HandoutCovid19.this, NcdcHotline.class);
                        startActivity(e);
                        break;
                    case R.id.mSettings:
                        Intent set = new Intent(HandoutCovid19.this, CovidSettings.class);
                        startActivity(set);
                        break;
                    default:
                        mDrawerLayout.closeDrawers();
                        break;
                }

                return true;
            }
        });


        LinearLayout menu = findViewById(R.id.linmenu);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDrawerLayout.openDrawer(Gravity.LEFT);
            }
        });
        LinearLayout home = findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent move1 = new Intent(HandoutCovid19.this, HandoutCovid19.class);
//                startActivity(move1);
            }
        });
        LinearLayout cov19 = findViewById(R.id.lincovid19);
        cov19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent move2 = new Intent(HandoutCovid19.this, WorldMap.class);
                startActivity(move2);
            }
        });

        LinearLayout tips = findViewById(R.id.lintips);
        tips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent move3 = new Intent(HandoutCovid19.this, CovidTips.class);
                startActivity(move3);
            }
        });
        LinearLayout games = findViewById(R.id.lintrivia);
        games.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent move4 = new Intent(HandoutCovid19.this, HandoutTrivia.class);
                startActivity(move4);
            }
        });
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onBackPressed(){
        //do nothing
    }
}
