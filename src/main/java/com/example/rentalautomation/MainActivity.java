package com.example.rentalautomation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    private Button loginUser,admin;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        loginUser=(Button)findViewById(R.id.btnloginMain);
        admin=(Button)findViewById(R.id.btn_admin_main);

        toolbar=(Toolbar)findViewById(R.id.toolbar7);
        setSupportActionBar(toolbar);
        toolbar.setSubtitle("Main Menu");

        loginUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,login_Automation.class));
            }
        });

        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,login_admin_automation.class));
            }
        });
    }
}
