package com.example.rentalautomation;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.Nullable;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.IOException;

public class Profile_Details_automation extends AppCompatActivity {


    private Button update;
    private EditText name,surname,email,cell;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;
    private ImageView imageView;;
    private static int PICK_IMAGE=400;
    Uri imagePath;
    private ProgressDialog progressDialog;

    private StorageReference storageReference;
    FirebaseStorage firebaseStorage;


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
       


        if(requestCode==PICK_IMAGE && resultCode==RESULT_OK && data.getData() != null){

            imagePath=data.getData();
            try {
                Bitmap bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),imagePath);
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
        setContentView(R.layout.activity_profile__details_automation);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference(firebaseAuth.getUid());
        storageReference = FirebaseStorage.getInstance().getReference();
        firebaseStorage= FirebaseStorage.getInstance();

        name=findViewById(R.id.tvFullnameP);
        surname=findViewById(R.id.txtSurnameP);
        email=findViewById(R.id.tvEmailP);
        cell=findViewById(R.id.tvPhonenoP);
        update=findViewById(R.id.btnUpdateP);

        imageView=findViewById(R.id.imageProfile);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent();
                intent.setType("image/*");  //application/pdf/doc or * for all audio/mp3 *
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"Select image"), PICK_IMAGE);

            }
        });




        email.setEnabled(false);

        storageReference.child(firebaseAuth.getUid()).child("Images/Profile pic").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {

                Picasso.get().load(uri).fit().centerCrop().into(imageView);

            }
        });



        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String Pname = name.getText().toString();
                String Psurname = surname.getText().toString();
                String Pemail = email.getText().toString();
                String Pcell = cell.getText().toString();

                UserProfile userProfile = new UserProfile(Pname, Psurname, Pemail,Pcell);


                databaseReference.child("Name").setValue(userProfile.getName());
                databaseReference.child("Surname").setValue(userProfile.getSurname());
                databaseReference.child("Email").setValue(userProfile.getEmail());
                databaseReference.child("CellNumber").setValue(userProfile.getCellNumber());

                Toast.makeText(Profile_Details_automation.this,"Successfully   Updated !!",Toast.LENGTH_SHORT).show();



                storageReference=storageReference.child(firebaseAuth.getUid()).child("Images").child("Profile pic");
                UploadTask uploadTask=storageReference.putFile(imagePath);
                uploadTask.addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Profile_Details_automation.this,"   Failed",Toast.LENGTH_SHORT).show();
                    }
                }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Toast.makeText(Profile_Details_automation.this,"Successfully  Uploaded ",Toast.LENGTH_SHORT).show();
                    }
                });






            }
        });



        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                String Pname=dataSnapshot.child("Name").getValue().toString();
                String Psurname=dataSnapshot.child("Surname").getValue().toString();
                String Pemail=dataSnapshot.child("Email").getValue().toString();
                String Pcell=dataSnapshot.child("CellNumber").getValue().toString();



                name.setText(Pname);
                surname.setText(Psurname);
                email.setText(Pemail);
                cell.setText(Pcell);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(Profile_Details_automation.this,databaseError.getCode(),Toast.LENGTH_SHORT).show();
            }
        });


    }

}
