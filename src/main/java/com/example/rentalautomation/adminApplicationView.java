package com.example.rentalautomation;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class adminApplicationView extends AppCompatActivity {

    public TextView title,surname, initial, name, gender, id, country, mobile, email, raddress, raddresscode, paddress, paddresscode, building, unit, month;
    public Button btnDelete, btnFind;
    public EditText getid;

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_application_view);

        title=findViewById(R.id.txtTittleApp);
        surname=findViewById(R.id.txtSurname);
        initial=findViewById(R.id.txtIntial);
        name=findViewById(R.id.txtFullName);
        gender=findViewById(R.id.txtGender);
        id=findViewById(R.id.txtId);
        country=findViewById(R.id.txtCountry);
        mobile=findViewById(R.id.txtMobile);
        email=findViewById(R.id.txtEmailApp);
        raddress=findViewById(R.id.txtResidential);
        raddresscode=findViewById(R.id.txtCode);
        paddress=findViewById(R.id.txtPostal);
        paddresscode=findViewById(R.id.txtPostalCode);
        building=findViewById(R.id.txtBuilding);
        unit=findViewById(R.id.txtUnit);
        month=findViewById(R.id.txtMonth);

        btnDelete=findViewById(R.id.btnDelete);
        btnFind=findViewById(R.id.btnFind);

        btnFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FindRecord();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DeleteClicked();
            }
        });

    }
    private void FindRecord(){
        getid=findViewById(R.id.txtId1st);
        FirebaseDatabase firebaseDatabase= FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("Application/"+getid.getText());


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String sTitle=dataSnapshot.child("Title").getValue().toString();
                String sSurname=dataSnapshot.child("Surname").getValue().toString();
                String sInitial=dataSnapshot.child("Initial").getValue().toString();
                String sName=dataSnapshot.child("Name").getValue().toString();
                String sGender=dataSnapshot.child("Gender").getValue().toString();
                String sId=dataSnapshot.child("ID_Passport").getValue().toString();
                String sCountry=dataSnapshot.child("Country").getValue().toString();
                String sMobile=dataSnapshot.child("Mobile").getValue().toString();
                String sEmail=dataSnapshot.child("Email").getValue().toString();
                String sRAddress=dataSnapshot.child("Residential_Address").getValue().toString();
                String sRAddressCode=dataSnapshot.child("Residential_Code").getValue().toString();
                String sPAddress=dataSnapshot.child("Postal_Address").getValue().toString();
                String sPAddressCode=dataSnapshot.child("Postal_Code").getValue().toString();
                String sBuilding=dataSnapshot.child("Building").getValue().toString();
                String sUnit=dataSnapshot.child("Unit").getValue().toString();
                String sMonth=dataSnapshot.child("Month_Moving_In").getValue().toString();

                title.setText(sTitle);
                surname.setText(sSurname);
                initial.setText(sInitial);
                name.setText(sName);
                gender.setText(sGender);
                id.setText(sId);
                country.setText(sCountry);
                mobile.setText(sMobile);
                email.setText(sEmail);
                raddress.setText(sRAddress);
                raddresscode.setText(sRAddressCode);
                paddress.setText(sPAddress);
                paddresscode.setText(sPAddressCode);
                building.setText(sBuilding);
                unit.setText(sUnit);
                month.setText(sMonth);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(adminApplicationView.this,"Error occured",Toast.LENGTH_SHORT).show();

            }
        });
    }


    private void DeleteClicked(){
        AlertDialog.Builder a_builder = new AlertDialog.Builder(adminApplicationView.this);
        a_builder.setMessage("ARE YOU SURE YOU WANT TO DELETE THE APPLICATION?")
                .setCancelable(false)
                .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        databaseReference.removeValue();
                        Toast.makeText(adminApplicationView.this,"Record DELETED",Toast.LENGTH_SHORT).show();
                        finish();
                        startActivity(new Intent(adminApplicationView.this, admin_main_automation.class));
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

    }
}
