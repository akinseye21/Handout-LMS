package com.akinseye.ndif_yemmanuel.handout;

import android.app.ProgressDialog;
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
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
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

import java.util.ArrayList;

public class CovidTips extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    NavigationView naviview;
    String title, content, source, icon;
    int l;
    int counter = 1;

    ArrayList<String> titled = new ArrayList<>();
    ArrayList<String> contentd = new ArrayList<>();
    ArrayList<String> sourced = new ArrayList<>();
    ArrayList<String> icond = new ArrayList<>();


    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_covid_tips);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading content... Please wait");
        progressDialog.show();


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
                        Intent q = new Intent(CovidTips.this, HandoutCovid19.class);
                        startActivity(q);
                        break;
                    case R.id.mWorldMap:
                        Intent j = new Intent(CovidTips.this, WorldMap.class);
                        startActivity(j);
                        break;
                    case R.id.mtips:
                        mDrawerLayout.closeDrawers();
                        break;
                    case R.id.mtrivia:
                        Intent d = new Intent(CovidTips.this, HandoutTrivia.class);
                        startActivity(d);
                        break;
                    case R.id.mHotline:
                        Intent e = new Intent(CovidTips.this, NcdcHotline.class);
                        startActivity(e);
                        break;
                    case R.id.mSettings:
                        Intent set = new Intent(CovidTips.this, CovidSettings.class);
                        startActivity(set);
                        break;
                    default:
                        mDrawerLayout.closeDrawers();
                        break;
                }

                return true;
            }
        });


        //get the data's first
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        String url = "http://54.71.22.155/covid/getAllTips";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //array is saved in here
                        progressDialog.dismiss();
                        try{
                            JSONArray jsonArray = new JSONArray(response);
                            l = jsonArray.length();

                            for(int i = 0; i<l; i++){
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                title = jsonObject.getString("title");
                                content = jsonObject.getString("content");
                                source = jsonObject.getString("source");
                                icon = jsonObject.getString("icon");



                                titled.add(title);
                                contentd.add(content);
                                sourced.add(source);
                                icond.add(icon);


                            }


                        }
                        catch (JSONException e){
                            e.printStackTrace();
                        }


                        String[] tit = new String[titled.size()];
                        final String[] con = new String[contentd.size()];
                        String[] sou = new String[sourced.size()];
                        String[] ico = new String[icond.size()];

                        final GridView gridview = findViewById(R.id.simpleGrid);
                        for (int k=0; k<titled.size(); k++){

                            tit[k] = titled.get(k);
                            con[k] = contentd.get(k);
                            sou[k] = sourced.get(k);
                            ico[k] = icond.get(k);
                        }

                        CustomAdapterCovidTips customAdapter = new CustomAdapterCovidTips(CovidTips.this, tit, con, sou, ico);
                        gridview.setAdapter(customAdapter);
                        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                                String title_text = titled.get(position);
                                String content_text = contentd.get(position);
                                String source_text = sourced.get(position);
                                String icon_text = icond.get(position);
                                Intent mv = new Intent(CovidTips.this, CovidTipsPDFview.class);
                                mv.putExtra("Title", title_text);
                                mv.putExtra("Content", content_text);
                                mv.putExtra("Source", source_text);
                                mv.putExtra("Icon", icon_text);
                                startActivity(mv);
                            }
                        });


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(CovidTips.this, "Error!! Network Failure", Toast.LENGTH_LONG).show();
                System.out.println("Error not going "+error);
            }
        });
        queue.add(stringRequest);


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
                Intent move1 = new Intent(CovidTips.this, HandoutCovid19.class);
                startActivity(move1);
            }
        });
        LinearLayout cov19 = findViewById(R.id.lincovid19);
        cov19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent move2 = new Intent(CovidTips.this, WorldMap.class);
                startActivity(move2);
            }
        });

        LinearLayout tips = findViewById(R.id.lintips);
        tips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent move3 = new Intent(HandoutCovid19.this, CovidTips.class);
//                startActivity(move3);
            }
        });
        LinearLayout games = findViewById(R.id.lintrivia);
        games.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent move4 = new Intent(CovidTips.this, HandoutTrivia.class);
                startActivity(move4);
            }
        });

    }

    @Override
    public void onBackPressed(){
        //do nothing
    }
}
