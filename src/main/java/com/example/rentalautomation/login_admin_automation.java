package com.example.rentalautomation;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class login_admin_automation extends AppCompatActivity {

    private Button loginAdmin;
    private EditText email,password;
    private ProgressDialog progressDialog;
    FirebaseAuth firebaseAuth;
    Toolbar toolbar;
    public int  attempts=4;
    TextView t1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_admin_automation);


        toolbar=(Toolbar)findViewById(R.id.toolbar5);
        setSupportActionBar(toolbar);
        toolbar.setSubtitle("Admin Login");
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(login_admin_automation.this, MainActivity.class));
            }
        });


        email=(EditText) findViewById(R.id.txtAdminEmail);
        password=(EditText) findViewById(R.id.txtPasswordAdmin);
        loginAdmin=(Button)findViewById(R.id.btnLoginAdmin);
        t1=findViewById(R.id.txtCounter);
        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);

        FirebaseUser user = firebaseAuth.getCurrentUser();



        loginAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // validate(email.getText().toString(),password.getText().toString());

                String Lemail=email.getText().toString().trim();
                String Lpassword=password.getText().toString().trim();

                if (Lemail.equals("") ){
                    Toast.makeText(login_admin_automation.this,"Please enter Email",Toast.LENGTH_SHORT).show();


                }else if (Lpassword.equals("") ) {
                    Toast.makeText(login_admin_automation.this, "Please enter password", Toast.LENGTH_SHORT).show();

                } else if (email.getText().toString().equals("senyolotj@gmail.com") && password.getText().toString().equals("password")) {

                    Toast.makeText(login_admin_automation.this, "Correct login Details", Toast.LENGTH_SHORT).show();

                    startActivity(new Intent(login_admin_automation.this, admin_main_automation.class));

                } else {
                    Toast.makeText(login_admin_automation.this, "Incorrect Password/Email", Toast.LENGTH_SHORT).show();
                    attempts--;
                    t1.setText(Integer.toString(attempts));
                    if (attempts == 0) {
                        loginAdmin.setEnabled(false);

                    }


                }
            }
        });
    }




    private void validate(String userName,String UserPassword){




        progressDialog.setMessage("Loading...");
        progressDialog.show();
        firebaseAuth.signInWithEmailAndPassword(userName,UserPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {


                if(task.isSuccessful()){
                    progressDialog.dismiss();
                    startActivity(new Intent(login_admin_automation.this, admin_main_automation.class));
                    Toast.makeText(login_admin_automation.this,"Login Successful", Toast.LENGTH_SHORT).show();


                }else{
                    Toast.makeText(login_admin_automation.this,"Login Failed Wrong Password or Email",Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();

                }
            }
        });


    }
}
