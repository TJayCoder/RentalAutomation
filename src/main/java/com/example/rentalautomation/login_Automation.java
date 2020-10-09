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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class login_Automation extends AppCompatActivity {

    private Button login_user,createAcount,forgotPassword;
    private EditText email,password;
    private ProgressDialog progressDialog;
    FirebaseAuth firebaseAuth;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login__automation);

        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setSubtitle("User Login");
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(login_Automation.this, MainActivity.class));
            }
        });

        login_user=(Button)findViewById(R.id.btnloginUser);
        forgotPassword=(Button)findViewById(R.id.btnPassword);
       createAcount=(Button)findViewById(R.id.btnSignup);



       email=(EditText)findViewById(R.id.txtEmailLogin);
       password=(EditText)findViewById(R.id.txtPasswordLogin);

        // Initialize Firebase Auth
        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);

        FirebaseUser user = firebaseAuth.getCurrentUser();


        login_user.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               String Lemail=email.getText().toString().trim();
               String Lpassword=password.getText().toString().trim();

               if (Lemail.equals("") ){
                   Toast.makeText(login_Automation.this,"Please enter Email",Toast.LENGTH_SHORT).show();


               }else if ( Lpassword.equals("") ) {
                   Toast.makeText(login_Automation.this, "Please enter password", Toast.LENGTH_SHORT).show();

               }else {
                   validate(email.getText().toString(), password.getText().toString());
               }
           }
       });

       forgotPassword=(Button)findViewById(R.id.btnPassword);
       forgotPassword.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               startActivity(new Intent(login_Automation.this, password_Recovery_automation.class));
           }
       });

       createAcount=(Button)findViewById(R.id.btnSignup);
       createAcount.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               startActivity(new Intent(login_Automation.this,createAccount_automation.class));
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
                   // checkEmailVerification();
                    startActivity(new Intent(login_Automation.this, main_user_automation.class));
                    Toast.makeText(login_Automation.this,"Login Successful", Toast.LENGTH_SHORT).show();


            }else{
                    Toast.makeText(login_Automation.this,"Login Failed (do correct your email details or Signup account if you still new user)",Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();

                }
            }
        });


    }
    private void checkEmailVerification() {
        FirebaseUser firebaseUser = firebaseAuth.getInstance().getCurrentUser();
        Boolean emailflag = firebaseUser.isEmailVerified();

        if (emailflag) {

            startActivity(new Intent(login_Automation.this, main_user_automation.class));
            Toast.makeText(login_Automation.this,"Login Successful", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(this, "Verify your email", Toast.LENGTH_SHORT).show();
            firebaseAuth.signOut();
        }


    }
}
