package com.example.rentalautomation;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class changePassword extends AppCompatActivity {

    private Button submit ;
    private EditText password;
    FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);


        submit = findViewById(R.id.btnApply);
        password = findViewById(R.id.txtNewPassword);


        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String userPasswordNew = password.getText().toString();
                firebaseUser.updatePassword(userPasswordNew).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(changePassword.this, "Password Changed", Toast.LENGTH_SHORT).show();
                            finish();
                        }else{
                            Toast.makeText(changePassword.this, "Password Update Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

    }
    }

