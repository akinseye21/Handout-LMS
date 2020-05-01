package com.akinseye.ndif_yemmanuel.handout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ComingSoonEdu extends AppCompatActivity {

    TextView returnHome;
    LinearLayout men, home, covid19, tips, trivia;
    String email, fullname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coming_soon_edu);

        returnHome = findViewById(R.id.returnHome);
        men = findViewById(R.id.linmenu);
        home = findViewById(R.id.home);
        covid19 = findViewById(R.id.lincovid19);
        tips = findViewById(R.id.lintips);
        trivia = findViewById(R.id.lintrivia);

        email = "";
        fullname = "";


        returnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ComingSoonEdu.this, EntrySelectionPage.class);
                intent.putExtra("email", email);
                intent.putExtra("fullname", fullname);
                startActivity(intent);
            }
        });
        men.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ComingSoonEdu.this, EntrySelectionPage.class);
                intent.putExtra("email", email);
                intent.putExtra("fullname", fullname);
                startActivity(intent);
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ComingSoonEdu.this, EntrySelectionPage.class);
                intent.putExtra("email", email);
                intent.putExtra("fullname", fullname);
                startActivity(intent);
            }
        });
        covid19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ComingSoonEdu.this, EntrySelectionPage.class);
                intent.putExtra("email", email);
                intent.putExtra("fullname", fullname);
                startActivity(intent);
            }
        });
        tips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ComingSoonEdu.this, EntrySelectionPage.class);
                intent.putExtra("email", email);
                intent.putExtra("fullname", fullname);
                startActivity(intent);
            }
        });
        trivia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ComingSoonEdu.this, EntrySelectionPage.class);
                intent.putExtra("email", email);
                intent.putExtra("fullname", fullname);
                startActivity(intent);
            }
        });
    }
}
