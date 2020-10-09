package com.example.rentalautomation;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.annotations.Nullable;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;

public class createAccount_automation extends AppCompatActivity {

    private Button createAccount;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    public EditText name,surname,email,cellNo,password;
    public   String Sname,Ssurname,Semail,Spassword,ScellNo;
    Toolbar toolbar;


    public ImageView image;
    private StorageReference mStorageRef;
    private static int PICK_IMAGE=400;
    Uri imagePath;
    UserProfile userProfile;





    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if(requestCode==PICK_IMAGE && resultCode==RESULT_OK && data.getData() != null){

            imagePath=data.getData();
            try {
                Bitmap bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),imagePath);
                image.setImageBitmap(bitmap);


            } catch (IOException e) {
                e.printStackTrace();
            }


        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account_automation);

        toolbar=(Toolbar)findViewById(R.id.toolbar4);
        setSupportActionBar(toolbar);
        toolbar.setSubtitle("Create Account");
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(createAccount_automation.this, login_Automation.class));
            }
        });


        firebaseAuth= FirebaseAuth.getInstance();
        progressDialog=new ProgressDialog(this);
        mStorageRef = FirebaseStorage.getInstance().getReference();

        name =(EditText) findViewById(R.id.txtNameCreateAccount);
        surname =(EditText) findViewById(R.id.txtSurnameCreateAcc);
        email =(EditText) findViewById(R.id.txtEmailCreateAcc);
        cellNo =(EditText) findViewById(R.id.txtCellNoCreateAcc);
        password =(EditText) findViewById(R.id.txtPasswordCreateAcc);
        image=findViewById(R.id.ImageCreateAcc);

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent();
                intent.setType("image/*");  //application/pdf/doc or * for all audio/mp3 *
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"Select image"), PICK_IMAGE);

            }
        });




        createAccount=(Button)findViewById(R.id.btnSubmitCreate);


        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (validaiton())                   //if the textfield are not empty/blank then create account
                {



                    Semail= email.getText().toString().trim();
                    Spassword= password.getText().toString().trim();

                    progressDialog.setMessage("Loading...");
                    progressDialog.show();

                    firebaseAuth.createUserWithEmailAndPassword(Semail, Spassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()) {

                                progressDialog.dismiss();

                                senduserData();

                                emailVerification();                //sent email to be verified
                                firebaseAuth.signOut();
                                Toast.makeText(createAccount_automation.this, "Upload Complete!!", Toast.LENGTH_SHORT).show();
                                finish();
                                startActivity(new Intent(createAccount_automation.this, login_Automation.class));

                            } else {
                                progressDialog.dismiss();
                                Toast.makeText(createAccount_automation.this, "Invalid email Please do enter correct Email or The user already Exist", Toast.LENGTH_SHORT).show();
                            }

                        }

                    });

                }
            }




        });

    }

    private Boolean validaiton(){

        Boolean results=false;



        Sname=name.getText().toString();
        Ssurname=surname.getText().toString();

        Semail=email.getText().toString();
        Spassword=password.getText().toString();
        ScellNo=cellNo.getText().toString();



        if (Semail.isEmpty() || Spassword.isEmpty() || ScellNo.isEmpty() || Sname.isEmpty() || Ssurname.isEmpty() )          //confirms if the textfield are not blank
        {
            Toast.makeText(this,"Please fill in the blank spaces",Toast.LENGTH_SHORT).show();
        }else if(Spassword.length()<=5){
            Toast.makeText(this,"Password should consist of more 5 character",Toast.LENGTH_SHORT).show();

        } else if(ScellNo.length()<10 ){
            Toast.makeText(this,"Cell number consist of ten digits",Toast.LENGTH_SHORT).show();


        }

        /*else if( imagePath==null){
            Toast.makeText(this,"Upload picture",Toast.LENGTH_SHORT).show();
        }

         */

        else{
            results=true;
        }

        return results;
    }

    private void emailVerification()                // sent email verification to the users email account and return to the login menu
    {

        FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();
        if(firebaseUser !=null){

            firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {

                    if(task.isSuccessful()){

                        // senduserData();
                        Toast.makeText(createAccount_automation.this,"Successfully  Registered and Verification mail Sent!!",Toast.LENGTH_SHORT).show();
                        firebaseAuth.signOut();
                        finish();


                    }
                }
            });
        }
    }
    private void senduserData(){





        FirebaseDatabase firebaseDatabase= FirebaseDatabase.getInstance();
        userProfile=new UserProfile(Sname,Ssurname,Semail,ScellNo);
        DatabaseReference myref=firebaseDatabase.getReference(firebaseAuth.getUid());
        // applicationForm.getTitle();
        // applicationForm.getSurname();

        myref.child("Name").setValue(userProfile.getName());
        myref.child("Surname").setValue(userProfile.getSurname());
        myref.child("Email").setValue(userProfile.getEmail());
        myref.child("CellNumber").setValue(userProfile.getCellNumber());


        mStorageRef=mStorageRef.child(firebaseAuth.getUid()).child("Images").child("Profile pic");
        UploadTask uploadTask=mStorageRef.putFile(imagePath);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(createAccount_automation.this,"   Failed",Toast.LENGTH_SHORT).show();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(createAccount_automation.this,"Successfully  Uploaded ",Toast.LENGTH_SHORT).show();
            }
        });


    }

}
