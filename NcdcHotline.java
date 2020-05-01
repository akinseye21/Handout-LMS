package com.akinseye.ndif_yemmanuel.handout;

import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import okhttp3.internal.Util;

public class NcdcHotline extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    NavigationView naviview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ncdc_hotline);

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
                        Intent q = new Intent(NcdcHotline.this, HandoutCovid19.class);
                        startActivity(q);
                        break;
                    case R.id.mWorldMap:
                        Intent j = new Intent(NcdcHotline.this, WorldMap.class);
                        startActivity(j);
                        break;
                    case R.id.mtips:
                        Intent e = new Intent(NcdcHotline.this, CovidTips.class);
                        startActivity(e);
                        break;
                    case R.id.mtrivia:
                        Intent d = new Intent(NcdcHotline.this, HandoutTrivia.class);
                        startActivity(d);
                        break;
                    case R.id.mHotline:
                        mDrawerLayout.closeDrawers();
                        break;
                    case R.id.mSettings:
                        Intent set = new Intent(NcdcHotline.this, CovidSettings.class);
                        startActivity(set);
                        break;
                    default:
                        mDrawerLayout.closeDrawers();
                        break;
                }

                return true;
            }
        });

        RelativeLayout calls = findViewById(R.id.call);
        RelativeLayout sms = findViewById(R.id.sms);
        RelativeLayout whatsapp = findViewById(R.id.whatsapp);

        TextView callNum = findViewById(R.id.tollfree);
        final String tollfreeNum = callNum.getText().toString().trim();

        calls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(NcdcHotline.this);
                builder.setMessage("Are you sure you want to call the number?");
                builder.setCancelable(true);
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                        Intent intent = new Intent(Intent.ACTION_DIAL);
                        intent.setData(Uri.parse("tel:"+tollfreeNum));
                        startActivity(intent);
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();

            }
        });


        TextView smsNum = findViewById(R.id.smsNum);
        final String smsNumber = smsNum.getText().toString().trim();

        sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(NcdcHotline.this);
                builder.setMessage("Are you sure you want to send sms to the number?");
                builder.setCancelable(true);
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                        try{
                            Intent smsIntent = new Intent(Intent.ACTION_VIEW);

                            smsIntent.setData(Uri.parse("smsto:"));
                            smsIntent.setType("vnd.android-dir/mms-sms");
                            smsIntent.putExtra("address"  , smsNumber);
                            smsIntent.putExtra("sms_body"  , "Test ");
                            startActivity(smsIntent);
                        }
                        catch (Exception e){
                            Toast.makeText(getApplicationContext(),"Something went wrong"+ e.toString(),Toast.LENGTH_SHORT).show();
                        }

                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });


        TextView whatsappNum = findViewById(R.id.whatsappNum);
        final String whatsappNumber = whatsappNum.getText().toString().trim();

        whatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final AlertDialog.Builder builder = new AlertDialog.Builder(NcdcHotline.this);
                builder.setMessage("Are you sure you want to send whatsapp message to the number?");
                builder.setCancelable(true);
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                        String formattedNumber = Util.format(whatsappNumber);

                        try{
                            Intent sendIntent =new Intent("android.intent.action.MAIN");
                            sendIntent.setComponent(new ComponentName("com.whatsapp", "com.whatsapp.Conversation"));
                            sendIntent.setAction(Intent.ACTION_SEND);
                            sendIntent.setType("text/plain");
                            sendIntent.putExtra(Intent.EXTRA_TEXT,"");
                            sendIntent.putExtra("jid", formattedNumber +"@s.whatsapp.net");
                            sendIntent.setPackage("com.whatsapp");
                            startActivity(sendIntent);
                        }
                        catch(Exception e)
                        {
                            Toast.makeText(getApplicationContext(),"Whatsapp not installed"+ e.toString(),Toast.LENGTH_SHORT).show();
                        }

                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();

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
                Intent move1 = new Intent(NcdcHotline.this, HandoutCovid19.class);
                startActivity(move1);
            }
        });
        LinearLayout cov19 = findViewById(R.id.lincovid19);
        cov19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent move2 = new Intent(NcdcHotline.this, WorldMap.class);
                startActivity(move2);
            }
        });

        LinearLayout tips = findViewById(R.id.lintips);
        tips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent move3 = new Intent(NcdcHotline.this, CovidTips.class);
                startActivity(move3);
            }
        });
        LinearLayout games = findViewById(R.id.lintrivia);
        games.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent move4 = new Intent(NcdcHotline.this, HandoutTrivia.class);
                startActivity(move4);
            }
        });
    }

    @Override
    public void onBackPressed(){
        //do nothing
    }
}
