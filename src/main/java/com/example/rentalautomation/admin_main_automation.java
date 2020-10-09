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

public class admin_main_automation extends AppCompatActivity {

    public Button upload,viewApplication,viewnotice;

Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main_automation);

        toolbar=(Toolbar)findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        toolbar.setSubtitle("Admin Main Menu");




        upload=findViewById(R.id.button2);
        viewnotice=findViewById(R.id.button4);
        viewApplication=findViewById(R.id.button3);

        viewnotice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(admin_main_automation.this,adminNoticeView.class));
            }
        });

        viewApplication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(admin_main_automation.this,adminApplicationView.class));
            }
        });




upload.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        startActivity(new Intent(admin_main_automation.this,Upload_Properties.class));
    }
});

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.adminmenu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        switch (item.getItemId()){

            case R.id.SignoutmenuAdmin:
            {
                AlertDialog.Builder a_builder = new AlertDialog.Builder(admin_main_automation.this);
                a_builder.setMessage("Do you want to logout and return to Main menu?")
                        .setCancelable(false)
                        .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                startActivity(new Intent(admin_main_automation.this,login_admin_automation.class));
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
