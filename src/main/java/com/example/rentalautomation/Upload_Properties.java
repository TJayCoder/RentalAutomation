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

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.annotations.Nullable;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;

public class Upload_Properties extends AppCompatActivity {

    private EditText description, price, nameImage,buildingName;
    private ImageView imageView;
    private Button submit;
    DatabaseReference myref;

    private ProgressDialog progressDialog;
    private StorageReference mStorageRef;
    FirebaseStorage storage;
    FirebaseDatabase firebaseDatabase;
    private static int PICK_IMAGE = 400;
    private  Uri imagePath;





    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data.getData() != null) {

            imagePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imagePath);
                imageView.setImageBitmap(bitmap);


            } catch (IOException e) {
                e.printStackTrace();
            }


        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload__properties);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar9);
        setSupportActionBar(toolbar);
        toolbar.setSubtitle("Upload Properties");
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);

        progressDialog = new ProgressDialog(this);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Upload_Properties.this, admin_main_automation.class));
            }
        });


        description = findViewById(R.id.descriptionAdmin);
        price = findViewById(R.id.priceAdmin);
        nameImage=findViewById(R.id.txtImageName);
        submit = findViewById(R.id.btnSubtmitUpload);
        imageView = findViewById(R.id.imageViewAdmin);
        buildingName=findViewById(R.id.txtBuildingName);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setType("image/*");  //application/pdf/doc or * for all audio/mp3 *
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select image"), PICK_IMAGE);

            }
        });


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                propertysendData();



            }
        });
    }


    public void propertysendData() {

        progressDialog.setMessage("Loading...");
        progressDialog.show();


        String Udescription = description.getText().toString();
        String Uprice = price.getText().toString();
        String UimageName=nameImage.getText().toString();
        String UbuildingName=buildingName.getText().toString();
        firebaseDatabase = FirebaseDatabase.getInstance();
        myref = firebaseDatabase.getReference("Properties");
        storage= FirebaseStorage.getInstance();
        mStorageRef=storage.getReference();

        UploadClassAdmin uploadClassAdmin = new UploadClassAdmin(Uprice, Udescription,UbuildingName);


        myref.child("Description").setValue(uploadClassAdmin.getDescription());
        myref.child("Price").setValue(uploadClassAdmin.getPrice());
        myref.child("Building_Name").setValue(uploadClassAdmin.getRoom());



        mStorageRef = mStorageRef.child("Apartment").child(UimageName);
        UploadTask uploadTask = mStorageRef.putFile(imagePath);

        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Upload_Properties.this, "   Failed", Toast.LENGTH_SHORT).show();

                progressDialog.dismiss();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                progressDialog.dismiss();
                Toast.makeText(Upload_Properties.this, "Successful Upload Complete!!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Upload_Properties.this,admin_main_automation.class));
            }
        });


    }
}


