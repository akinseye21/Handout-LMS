package com.akinseye.ndif_yemmanuel.handout;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class CovidTellAFriend extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    NavigationView naviview;
    Button email, sms, whatsapp;
    ImageView menu;

    String strAppLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_covid_tell_afriend);

        email = findViewById(R.id.emailmessage);
        sms = findViewById(R.id.smsmessage);
        whatsapp = findViewById(R.id.whatsappmessage);

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
                        Intent z = new Intent(CovidTellAFriend.this, HandoutCovid19.class);
                        startActivity(z);
                        break;
                    case R.id.mWorldMap:
                        Intent w = new Intent(CovidTellAFriend.this, WorldMap.class);
                        startActivity(w);
                        break;
                    case R.id.mtips:
                        Intent j = new Intent(CovidTellAFriend.this, CovidTips.class);
                        startActivity(j);
                        break;
                    case R.id.mHotline:
                        Intent e = new Intent(CovidTellAFriend.this, NcdcHotline.class);
                        startActivity(e);
                        break;
                    case R.id.mSettings:
                        Intent set = new Intent(CovidTellAFriend.this, CovidSettings.class);
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


        final String appPackageName = getApplicationContext().getPackageName();
//        strAppLink = "";
        strAppLink = "https://play.google.com/store/apps/details?id=" + appPackageName;

//        try
//        {
//            strAppLink = "market://details?id=" + appPackageName;
//        }
//        catch (android.content.ActivityNotFoundException anfe)
//        {
//
//        }

        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sendMail = new Intent(Intent.ACTION_SEND);
                sendMail.setType("text/plain");
                sendMail.setPackage("com.google.android.gm");
                sendMail.putExtra(Intent.EXTRA_SUBJECT, "Invite from HandOut Application");
                sendMail.putExtra(Intent.EXTRA_TEXT, "Kindly download this cool learning management software from "+strAppLink);
                startActivity(sendMail);
            }
        });

        sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phoneNo =  "+234";
                String message = "Kindly download this cool learning management software from "+strAppLink;

                /** Creating an intent to initiate view action */
                Intent intent = new Intent("android.intent.action.VIEW");

                /** creates an sms uri */
                Uri data = Uri.parse("sms:");

                /** Setting sms uri to the intent */
                intent.setData(data);

                /** Initiates the SMS compose screen, because the activity contain ACTION_VIEW and sms uri */
                startActivity(intent);

            }
        });

        whatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent launchwhatsapp = new Intent(Intent.ACTION_SEND);
                launchwhatsapp.setType("text/plain");
                launchwhatsapp.setPackage("com.whatsapp");
                launchwhatsapp.putExtra(Intent.EXTRA_TEXT, "Kindly download this cool learning management software from "+strAppLink);
                try{
                    startActivity(launchwhatsapp);
                }catch (android.content.ActivityNotFoundException exception){
                    Toast.makeText(getApplicationContext(), "Whatsapp not installed", Toast.LENGTH_LONG);
                }
            }
        });
    }

    @Override
    public void onBackPressed(){
        //do nothing
    }
}
