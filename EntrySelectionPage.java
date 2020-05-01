package com.akinseye.ndif_yemmanuel.handout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class EntrySelectionPage extends AppCompatActivity {

    ImageView edu, social, covid;
    String module, emaild, fullname, passed;

    public static final String CHECK_STATUS_URL = "http://54.71.22.155/hitma/mobile/check_status";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry_selection_page);


        edu = findViewById(R.id.handoutedu);
        social = findViewById(R.id.handoutsocial);
        //inec = findViewById(R.id.inecadhoc);
        covid = findViewById(R.id.handoutcovid);

        Intent intent = getIntent();
        emaild = intent.getStringExtra("email");
        fullname = intent.getStringExtra("fullname");

        if (emaild.equals("") || fullname.equals("")){
            SharedPreferences prefs = getSharedPreferences("HandoutAppLogin", MODE_PRIVATE);
            emaild = prefs.getString("email", "");
            String password = prefs.getString("password", "");
            fullname = prefs.getString("fullname", "");
        }

//        SharedPreferences sharedpreferences = getSharedPreferences("MyCovidInfo", Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedpreferences.edit();
//        editor.putString("email", emaild);
//        editor.putString("fullname", fullname);
//        editor.commit();

//        Toast.makeText(getApplicationContext(), "Email = "+emaild, Toast.LENGTH_LONG).show();

        social.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent j = new Intent(EntrySelectionPage.this, HandoutSocialFeeds.class);
//                startActivity(j);
            }
        });

        covid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cov = new Intent(EntrySelectionPage.this, HandoutCovid19.class);
                cov.putExtra("email", emaild);
                cov.putExtra("fullname", fullname);
                startActivity(cov);
            }
        });




        //clicking on Edu
        edu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(EntrySelectionPage.this, ComingSoonEdu.class);
                startActivity(intent);

//                final Dialog dialog = new Dialog(EntrySelectionPage.this);
//                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//                dialog.setCancelable(true);
//                dialog.setContentView(R.layout.custom_coming_soon);
//                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//                dialog.show();
//                if(emaild.equals(null)){
//                    //register from top
//                    Intent reg = new Intent(EntrySelectionPage.this, AppRegister.class);
//                    startActivity(reg);
//                }
//                else{
//                    module = "edu";
//
//                    //send the data(email and module) to db to check if user has updated his profile
//
//                    StringRequest stringRequest = new StringRequest(Request.Method.POST, CHECK_STATUS_URL,
//                            new Response.Listener<String>() {
//                                @Override
//                                public void onResponse(String response) {
//
////                                    Toast.makeText(EntrySelectionPage.this, "Response = "+response, Toast.LENGTH_LONG).show();
////                                    System.out.println("Response = "+response);
//
//                                    if(response.equals("edu update")){
//                                        //goto edu update page
//                                        Intent move = new Intent(EntrySelectionPage.this, ConfirmationCode.class);
//                                        move.putExtra("email", emaild);
//                                        move.putExtra("flag", "update");
//                                        startActivity(move);
//                                    }
//
//                                    if(response.equals("edu login")){
//                                        //goto edu login page
//                                    Intent move2 = new Intent(EntrySelectionPage.this, LogInto.class);
//                                    move2.putExtra("email", emaild);
//                                    startActivity(move2);
//                                    }
//
//                                }
//                            },
//                            new Response.ErrorListener() {
//                                @Override
//                                public void onErrorResponse(VolleyError error) {
//
//
//                                    Toast.makeText(EntrySelectionPage.this, "Error!! Please try again", Toast.LENGTH_LONG).show();
//                                    System.out.println("Error not going "+error);
//                                }
//                            }){
//                        @Override
//                        protected Map<String, String> getParams(){
//                            Map<String, String> params = new HashMap<String, String>();
//                            params.put("email", emaild);
//                            params.put("module", module);
//                            return params;
//                        }
//                    };
//
//                    RequestQueue requestQueue = Volley.newRequestQueue(EntrySelectionPage.this);
//                    stringRequest.setRetryPolicy(new DefaultRetryPolicy(10000,
//                            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
//                            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
//                    requestQueue.add(stringRequest);
//                }



            }
        });
    }

    @Override
    public void onBackPressed(){
        //do nothing
    }
}
