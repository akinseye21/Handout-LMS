package com.akinseye.ndif_yemmanuel.handout;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
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

public class AfterSplash extends AppCompatActivity {

    Button btnlogin, btnregister;
    private static final String PREFS_NAME = "PrefsFile";
    EditText emailEdt, passwordEdt;
    CheckBox remember;
    String email, password, fullname;

    public static final String LOGIN_URL = "http://54.71.22.155/hitma/mobile/login";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_splash);

        final SharedPreferences mPrefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

        Intent intent = getIntent();
        email = intent.getStringExtra("email");
        password = intent.getStringExtra("password");
        fullname = intent.getStringExtra("fullname");

        if(email == null || password == null || fullname == null){

        }else{
            //show the popup
            final Dialog dialog = new Dialog(AfterSplash.this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(true);
            dialog.setContentView(R.layout.custompopuplogin);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

            emailEdt = dialog.findViewById(R.id.edt1);
            passwordEdt = dialog.findViewById(R.id.edt2);
            remember = dialog.findViewById(R.id.checkBoxRememberMe);

            //set the edit text to the passed data
            emailEdt.setText(email);
            passwordEdt.setText(password);

            final ProgressBar progressBar = dialog.findViewById(R.id.progressBar);
            progressBar.setVisibility(View.GONE);

            getPreferencesData();

            final Button login = dialog.findViewById(R.id.loginto);
            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if(remember.isChecked()){
                        Boolean boolIsChecked = remember.isChecked();
                        SharedPreferences.Editor editor = mPrefs.edit();
                        editor.putString("pref_name", email);
                        editor.putString("pref_pass", password);
                        editor.putBoolean("pref_check", boolIsChecked);
                        editor.apply();

                    }else{
                        mPrefs.edit().clear().apply();
                    }


                    //check for emptyness and validity
                    if((email.isEmpty() || email.length()>32) || (password.isEmpty())){
                        emailEdt.setError("Please Enter a valid email");
                        passwordEdt.setError("Please Enter your password");
                    }
                    else{
                        //if no error
                        progressBar.setVisibility(View.VISIBLE);
                        //check DB for both email and password
                        //send the text category to the server
                        StringRequest stringRequest = new StringRequest(Request.Method.POST, LOGIN_URL,
                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        JSONObject jsonObject;
                                        try {
                                            progressBar.setVisibility(View.GONE);

                                            jsonObject = new JSONObject(response);
                                            String status = jsonObject.getString("status");
                                            String username = jsonObject.getString("fullname");
                                            String useremail = jsonObject.getString("email");

                                            //make shared preference for login details
                                            SharedPreferences.Editor editor = getSharedPreferences("HandoutAppLogin", MODE_PRIVATE).edit();
                                            editor.putString("email", useremail);
                                            editor.putString("password", password);
                                            editor.putString("fullname", username);
                                            editor.commit();

                                            if(status.equals("login successful")){
                                                Intent mb = new Intent(AfterSplash.this, EntrySelectionPage.class);
                                                mb.putExtra("fullname", username);
                                                mb.putExtra("email", useremail);
                                                startActivity(mb);
                                            }else{
                                                Toast.makeText(getApplicationContext(), "Wrong details", Toast.LENGTH_LONG).show();
                                            }

                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                            progressBar.setVisibility(View.GONE);
                                            Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                                        }

                                    }
                                },
                                new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        progressBar.setVisibility(View.GONE);
                                        Toast.makeText(getApplicationContext(), "Error in connection", Toast.LENGTH_LONG).show();
                                    }
                                }){
                            @Override
                            protected Map<String, String> getParams(){
                                Map<String, String> params = new HashMap<String, String>();
                                params.put("email", email);
                                params.put("password", password);
                                return params;
                            }
                        };
                        RequestQueue requestQueue = Volley.newRequestQueue(AfterSplash.this);
                        requestQueue.add(stringRequest);

                    }
                }
            });
            dialog.show();
        }



        btnlogin = findViewById(R.id.login);
        btnregister = findViewById(R.id.reg);


        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //pop up login
                final Dialog dialog = new Dialog(AfterSplash.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.custompopuplogin);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                emailEdt = dialog.findViewById(R.id.edt1);
                passwordEdt = dialog.findViewById(R.id.edt2);
                remember = dialog.findViewById(R.id.checkBoxRememberMe);
                final ProgressBar progressBar = dialog.findViewById(R.id.progressBar);
                progressBar.setVisibility(View.GONE);

                getPreferencesData();

                final Button login = dialog.findViewById(R.id.loginto);
                login.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //to capture the inputs
                        email = emailEdt.getText().toString().trim();
                        password = passwordEdt.getText().toString().trim();

                        if(remember.isChecked()){
                            Boolean boolIsChecked = remember.isChecked();
                            SharedPreferences.Editor editor = mPrefs.edit();
                            editor.putString("pref_name", email);
                            editor.putString("pref_pass", password);
                            editor.putBoolean("pref_check", boolIsChecked);
                            editor.apply();

                        }else{
                            mPrefs.edit().clear().apply();
                        }


                        //check for emptyness and validity
                        if((email.isEmpty() || email.length()>32) || (password.isEmpty())){
                            emailEdt.setError("Please Enter a valid email");
                            passwordEdt.setError("Please Enter your password");
                        }
                        else{
                            //if no error
                            progressBar.setVisibility(View.VISIBLE);
                            //check DB for both email and password
                            //send the text category to the server
                            StringRequest stringRequest = new StringRequest(Request.Method.POST, LOGIN_URL,
                                    new Response.Listener<String>() {
                                        @Override
                                        public void onResponse(String response) {
                                            JSONObject jsonObject;
                                            try {
                                                progressBar.setVisibility(View.GONE);

                                                jsonObject = new JSONObject(response);
                                                String status = jsonObject.getString("status");
                                                String username = jsonObject.getString("fullname");
                                                String useremail = jsonObject.getString("email");

                                                //make shared preference for login details
                                                SharedPreferences.Editor editor = getSharedPreferences("HandoutAppLogin", MODE_PRIVATE).edit();
                                                editor.putString("email", useremail);
                                                editor.putString("password", password);
                                                editor.putString("fullname", username);
                                                editor.commit();

                                                if(status.equals("login successful")){
                                                    Intent mb = new Intent(AfterSplash.this, EntrySelectionPage.class);
                                                    mb.putExtra("fullname", username);
                                                    mb.putExtra("email", useremail);
                                                    startActivity(mb);
                                                }else{
                                                    Toast.makeText(getApplicationContext(), "Wrong information \nKindly register if you have not.", Toast.LENGTH_LONG).show();
                                                }

                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                                progressBar.setVisibility(View.GONE);
                                                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                                            }
                                        }
                                    },
                                    new Response.ErrorListener() {
                                        @Override
                                        public void onErrorResponse(VolleyError error) {
                                            progressBar.setVisibility(View.GONE);
                                            Toast.makeText(getApplicationContext(), "Error in connection", Toast.LENGTH_LONG).show();
                                        }
                                    }){
                                @Override
                                protected Map<String, String> getParams(){
                                    Map<String, String> params = new HashMap<String, String>();
                                    params.put("email", email);
                                    params.put("password", password);
                                    return params;
                                }
                            };
                            RequestQueue requestQueue = Volley.newRequestQueue(AfterSplash.this);
                            requestQueue.add(stringRequest);

                        }
                    }
                });
                dialog.show();

            }
        });

        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent j = new Intent(AfterSplash.this, AppRegister.class);
                startActivity(j);
            }
        });





    }


    private void getPreferencesData() {
        SharedPreferences sp = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        if(sp.contains("pref_name")){
            String u = sp.getString("pref_name", "not found");
            emailEdt.setText(u.toString());
        }

        if(sp.contains("pref_pass")){
            String p = sp.getString("pref_pass", "not found");
            passwordEdt.setText(p.toString());
        }

        if(sp.contains("pref_check")){
            Boolean b = sp.getBoolean("pref_check", false);
            remember.setChecked(b);
        }
    }

    @Override
    public void onBackPressed(){
        //do nothing
    }
}
