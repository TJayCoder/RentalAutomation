package com.example.rentalautomation;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class password_Recovery_automation extends AppCompatActivity {

    private Button recovery;
    private EditText emailRecover;
    private FirebaseAuth firebaseAuth;
Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password__recovery_automation);

        toolbar=(Toolbar)findViewById(R.id.toolbar8);
        setSupportActionBar(toolbar);
        toolbar.setSubtitle("Password Recovery");
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(password_Recovery_automation.this, login_Automation.class));
            }
        });


        recovery = (Button) findViewById(R.id.btnRecovery);
        emailRecover = (EditText) findViewById(R.id.txtForgotPass);


        firebaseAuth= FirebaseAuth.getInstance();

        recovery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userEmail=emailRecover.getText().toString().trim();

                if (userEmail.equals("")){
                    Toast.makeText(password_Recovery_automation.this,"Please enter Email",Toast.LENGTH_SHORT).show();
                }else{

                    firebaseAuth.sendPasswordResetEmail(userEmail).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if(task.isSuccessful()){
                                Toast.makeText(password_Recovery_automation.this,"Password reset email sent",Toast.LENGTH_SHORT).show();
                                finish();
                                startActivity(new Intent(password_Recovery_automation.this, MainActivity.class));

                            }          else{

                                Toast.makeText(password_Recovery_automation.this,"Error in resetting Password",Toast.LENGTH_SHORT).show();
                            }


                        }
                    }) ;
                }
            }
        });

    }
}