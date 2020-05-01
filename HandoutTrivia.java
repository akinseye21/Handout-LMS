package com.akinseye.ndif_yemmanuel.handout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class HandoutTrivia extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    NavigationView naviview;

    LinearLayout soccer, entertainment, politics, history;
    LinearLayout maths, english, logic, physics;
    LinearLayout computer, movies, animals, fashion;

    String text;
    TextView correctAnswer, ranking;

    public static final String RANK_URL = "http://54.71.22.155/hitma/trivia/user_rank";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handout_trivia);

        SharedPreferences prefs = getSharedPreferences("MyCovidInfo", MODE_PRIVATE);
        final String email = prefs.getString("email", "");
        String fullname = prefs.getString("fullname", "");

        TextView username = findViewById(R.id.username);
        correctAnswer = findViewById(R.id.numCorrectAns);
        ranking = findViewById(R.id.ranking);

        username.setText(fullname);

        soccer = findViewById(R.id.soccer);
        entertainment = findViewById(R.id.entertainment);
        politics = findViewById(R.id.politics);
        history = findViewById(R.id.history);
        maths = findViewById(R.id.math);
        english = findViewById(R.id.english);
        logic = findViewById(R.id.logic);
        physics = findViewById(R.id.physics);
        computer = findViewById(R.id.computer);
        movies = findViewById(R.id.movies);
        animals = findViewById(R.id.animals);
        fashion = findViewById(R.id.fashion);

        soccer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text = "Soccer";
                Intent mv = new Intent(HandoutTrivia.this, TriviaInstruction.class);
                mv.putExtra("Text", text);
                startActivity(mv);
            }
        });
        entertainment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text = "Entertainment";
                Intent mv = new Intent(HandoutTrivia.this, TriviaInstruction.class);
                mv.putExtra("Text", text);
                startActivity(mv);
            }
        });
        politics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text = "Politics";
                Intent mv = new Intent(HandoutTrivia.this, TriviaInstruction.class);
                mv.putExtra("Text", text);
                startActivity(mv);
            }
        });
        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text = "History";
                Intent mv = new Intent(HandoutTrivia.this, TriviaInstruction.class);
                mv.putExtra("Text", text);
                startActivity(mv);
            }
        });
        maths.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text = "Maths";
                Intent mv = new Intent(HandoutTrivia.this, TriviaInstruction.class);
                mv.putExtra("Text", text);
                startActivity(mv);
            }
        });
        english.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text = "English";
                Intent mv = new Intent(HandoutTrivia.this, TriviaInstruction.class);
                mv.putExtra("Text", text);
                startActivity(mv);
            }
        });
        logic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text = "Logic";
                Intent mv = new Intent(HandoutTrivia.this, TriviaInstruction.class);
                mv.putExtra("Text", text);
                startActivity(mv);
            }
        });
        physics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text = "Physics";
                Intent mv = new Intent(HandoutTrivia.this, TriviaInstruction.class);
                mv.putExtra("Text", text);
                startActivity(mv);
            }
        });
        computer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text = "Computer";
                Intent mv = new Intent(HandoutTrivia.this, TriviaInstruction.class);
                mv.putExtra("Text", text);
                startActivity(mv);
            }
        });
        movies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text = "Movies";
                Intent mv = new Intent(HandoutTrivia.this, TriviaInstruction.class);
                mv.putExtra("Text", text);
                startActivity(mv);
            }
        });
        animals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text = "Animals";
                Intent mv = new Intent(HandoutTrivia.this, TriviaInstruction.class);
                mv.putExtra("Text", text);
                startActivity(mv);
            }
        });
        fashion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text = "Fashion";
                Intent mv = new Intent(HandoutTrivia.this, TriviaInstruction.class);
                mv.putExtra("Text", text);
                startActivity(mv);
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
                        Intent d = new Intent(HandoutTrivia.this, HandoutCovid19.class);
                        startActivity(d);
                        break;
                    case R.id.mWorldMap:
                        Intent w = new Intent(HandoutTrivia.this, WorldMap.class);
                        startActivity(w);
                        break;
                    case R.id.mtips:
                        Intent j = new Intent(HandoutTrivia.this, CovidTips.class);
                        startActivity(j);
                        break;
                    case R.id.mtrivia:
                        mDrawerLayout.closeDrawers();
                        break;
                    case R.id.mHotline:
                        Intent e = new Intent(HandoutTrivia.this, NcdcHotline.class);
                        startActivity(e);
                        break;
                    case R.id.mSettings:
                        Intent set = new Intent(HandoutTrivia.this, CovidSettings.class);
                        startActivity(set);
                        break;
                    default:
                        mDrawerLayout.closeDrawers();
                        break;
                }

                return true;
            }
        });


        //send info to database to get ranks
        StringRequest stringRequest = new StringRequest(Request.Method.POST, RANK_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println("User ranking array = "+response);
                        JSONObject jsonObject;
                        try {
                            jsonObject = new JSONObject(response);
                            String rank = jsonObject.getString("overall_rank");
                            String test_completed = jsonObject.getString("overall_test_completed");

                            ranking.setText(rank);
                            correctAnswer.setText(test_completed);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "Network connectivity problem", Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", email);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(HandoutTrivia.this);
        requestQueue.add(stringRequest);



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
                Intent move1 = new Intent(HandoutTrivia.this, HandoutCovid19.class);
                startActivity(move1);
            }
        });
        LinearLayout cov19 = findViewById(R.id.lincovid19);
        cov19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent move2 = new Intent(HandoutTrivia.this, WorldMap.class);
                startActivity(move2);
            }
        });

        LinearLayout tips = findViewById(R.id.lintips);
        tips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent move4 = new Intent(HandoutTrivia.this, CovidTips.class);
                startActivity(move4);
            }
        });
        LinearLayout games = findViewById(R.id.lintrivia);
        games.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent move3 = new Intent(HandoutCovid19.this, CovidTips.class);
//                startActivity(move3);
            }
        });

    }

    @Override
    public void onBackPressed(){
        //do nothing
    }
}
