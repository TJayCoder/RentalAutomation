package com.example.rentalautomation;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class main_user_automation extends AppCompatActivity {
    private Button viewApartment,application,notice;
Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_user_automation);

        toolbar=(Toolbar)findViewById(R.id.toolbar6);
        setSupportActionBar(toolbar);
        toolbar.setSubtitle("Main Menu User");



        viewApartment=(Button)findViewById(R.id.btnViewApartment);
        application=(Button)findViewById(R.id.btnApply);
        notice=(Button)findViewById(R.id.btnNotice);

        application.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(main_user_automation.this, Applications.class));
            }
        });
        notice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(main_user_automation.this, Notice.class));
            }
        });

        viewApartment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(main_user_automation.this, avaliable_apartment.class));
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
     getMenuInflater().inflate(R.menu.menu,menu);
     return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {




        switch (item.getItemId()){
            case R.id.profileMenu:
                {
                    startActivity(new Intent(main_user_automation.this, Profile_Details_automation.class));
break;
            }
            case R.id.Signoutmenu:
                {
                    AlertDialog.Builder a_builder = new AlertDialog.Builder(main_user_automation.this);
                    a_builder.setMessage("Do you want to logout and return to Main menu?")
                            .setCancelable(false)
                            .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    startActivity(new Intent(main_user_automation.this, login_Automation.class));
                                    finish();
                                }
                            })
                            .setNegativeButton("No",new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            }) ;
                    AlertDialog alert = a_builder.create();
                    alert.setTitle("Alert !!!");
                    alert.show();

break;
            }
        }


        return super.onOptionsItemSelected(item);
    }
}
