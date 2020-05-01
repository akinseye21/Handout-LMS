package com.akinseye.ndif_yemmanuel.handout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AppRegister extends AppCompatActivity {

    Button next;
    EditText fullname, emailadd, phone, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_register);

        fullname = findViewById(R.id.edt1);
        emailadd = findViewById(R.id.edt2);
        phone = findViewById(R.id.edt3);
        password = findViewById(R.id.edt4);


        next = findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //get all input
                String getName = fullname.getText().toString().trim();
                String getMail = emailadd.getText().toString().trim();
                String getPhone = phone.getText().toString().trim();
                String getPass = password.getText().toString().trim();

                //check for empty fields
                if(getName.isEmpty() || getMail.isEmpty() || getPhone.isEmpty() || getPass.isEmpty()){
                    Toast.makeText(AppRegister.this, "Wrong Input", Toast.LENGTH_LONG).show();
                }
                else{
                    Intent i = new Intent(AppRegister.this, AppRegistration2.class);
                    i.putExtra("name", getName);
                    i.putExtra("mail", getMail);
                    i.putExtra("phone", getPhone);
                    i.putExtra("pass", getPass);
                    startActivity(i);
                }


            }
        });
    }
}
