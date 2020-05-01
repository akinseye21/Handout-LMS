package com.akinseye.ndif_yemmanuel.handout;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class AppRegistration2 extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{

    Button signup;
    EditText date;
    String fullname, emailadd, phone, password, gender, dob;
    RadioGroup radioGroup;
    RadioButton radioButton;
    private ProgressDialog progressDialog;
    public static final String REGISTER_URL = "http://54.71.22.155/hitma/mobile/register";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_registration2);

        progressDialog = new ProgressDialog(this);

        Intent intent = getIntent();
        fullname = intent.getStringExtra("name");
        emailadd = intent.getStringExtra("mail");
        phone = intent.getStringExtra("phone");
        password = intent.getStringExtra("pass");

//        Toast.makeText(getApplicationContext(), "Name = "+fullname+"\nEmail = "+emailadd+"\nPhone = "+phone+"\nPassword = "+password, Toast.LENGTH_LONG).show();

        radioGroup = findViewById(R.id.radioGrp);
        int radioID = radioGroup.getCheckedRadioButtonId();                                                 //checks the checked button in the radiogroup and store in var
        radioButton = findViewById(radioID);
        gender = (String) radioButton.getText();

        date = findViewById(R.id.edtDate);
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "date picker");
            }
        });




        signup = findViewById(R.id.signup);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dob = date.getText().toString();
//                Toast.makeText(getApplicationContext(), "Sending...\nName = "+fullname+"\nEmail = "+emailadd+"\nPhone = "+phone+"\nPassword = "+password+"\nGender = "+gender+"\nDOB = "+dob, Toast.LENGTH_LONG).show();

                progressDialog.setMessage("Registering. Please wait!");
                progressDialog.show();

                //send all fields to the server
                StringRequest stringRequest = new StringRequest(Request.Method.POST, REGISTER_URL,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                System.out.println("Response = "+response);
                                progressDialog.dismiss();

                                if(response.equals("User already exists")){
                                    Intent g = new Intent(AppRegistration2.this, AfterSplash.class);
                                    g.putExtra("fullname", fullname);
                                    g.putExtra("email", emailadd);
                                    startActivity(g);
                                }
                                else{
                                    try{

                                        JSONObject jsonObject = new JSONObject(response);

                                        System.out.print("Outputted JSONObject = "+jsonObject);
                                        String stats = jsonObject.getString("status");

                                        if (stats.equals("register successful")){
                                            Toast.makeText(AppRegistration2.this, "Registration Successful", Toast.LENGTH_LONG).show();
                                            System.out.print(stats);

                                            Intent i = new Intent(AppRegistration2.this, AfterSplash.class);
                                            i.putExtra("fullname", fullname);
                                            i.putExtra("email", emailadd);
                                            i.putExtra("password", password);
                                            startActivity(i);
                                        }else{
                                            Toast.makeText(AppRegistration2.this, "Error, Please try again", Toast.LENGTH_LONG).show();
                                            System.out.println("Error Here"+stats);
                                            Intent w = new Intent(AppRegistration2.this, AppRegister.class);
                                            startActivity(w);
                                        }

                                    }catch (JSONException e){
                                        e.printStackTrace();
                                        Toast.makeText(AppRegistration2.this, "Something is wrong!! Please try again", Toast.LENGTH_LONG).show();
                                        System.out.println("Error code 2 "+e.getMessage());
                                        Intent q = new Intent(AppRegistration2.this, AppRegister.class);
                                        startActivity(q);
                                    }
                                }



                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                                progressDialog.dismiss();

                                Toast.makeText(AppRegistration2.this, "Error!! Please try again", Toast.LENGTH_LONG).show();
                                System.out.println("Error not going "+error);
                                Intent a = new Intent(AppRegistration2.this, AppRegister.class);
                                startActivity(a);
                            }
                        }){
                    @Override
                    protected Map<String, String> getParams(){
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("fullname", fullname);
                        params.put("email", emailadd);
                        params.put("phone", phone);
                        params.put("password", password);
                        params.put("dob", dob);
                        params.put("gender", gender);
                        return params;
                    }
                };

                RequestQueue requestQueue = Volley.newRequestQueue(AppRegistration2.this);
                stringRequest.setRetryPolicy(new DefaultRetryPolicy(10000,
                        DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                requestQueue.add(stringRequest);

            }
        });


    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        Calendar c = Calendar.getInstance();                                                                //process of opening date picker
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, day);

        String currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());
        date.setText(currentDate);

    }
}
