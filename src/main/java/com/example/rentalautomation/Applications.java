package com.example.rentalautomation;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Applications extends AppCompatActivity {

public EditText title,surname, initial, name, gender, id, country, mobile, email, raddress, raddresscode, paddress, paddresscode, building, unit, month;
public String Stitle,Surname, Initial, Name, Gender, Id, Country, Mobile, Email, Raddress, Raddresscode, Paddress, Paddresscode, Building, Unit, Month;
public Button submit;
FirebaseAuth firebaseAuth;
    ApplicationForm applicationForm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_applications);

        title=findViewById(R.id.txtTitle);
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

        submit=findViewById(R.id.btnApplicationSubmit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                databaseInput();
            }
        });

    }

    private void databaseInput(){
        Stitle=title.getText().toString();
        Surname=surname.getText().toString();
        Initial=initial.getText().toString();
        Name=name.getText().toString();
        Gender=gender.getText().toString();
        Id=id.getText().toString();
        Country=country.getText().toString();
        Mobile=mobile.getText().toString();
        Email=email.getText().toString();
        Raddress=raddress.getText().toString();
        Raddresscode=raddresscode.getText().toString();
        Paddress=paddress.getText().toString();
        Paddresscode=paddresscode.getText().toString();
        Building=building.getText().toString();
        Unit=unit.getText().toString();
        Month=month.getText().toString();


        FirebaseDatabase firebaseDatabase= FirebaseDatabase.getInstance();
        applicationForm=new ApplicationForm(Stitle, Surname, Initial, Name, Gender, Id, Country, Mobile, Email, Raddress, Raddresscode, Paddress, Paddresscode, Building, Unit, Month);
        DatabaseReference myref=firebaseDatabase.getReference("Application/"+applicationForm.getId());

        myref.child("Title").setValue(applicationForm.getTitle());
        myref.child("Surname").setValue(applicationForm.getSurname());
        myref.child("Initial").setValue(applicationForm.getInitial());
        myref.child("Name").setValue(applicationForm.getName());
        myref.child("Gender").setValue(applicationForm.getGender());
        myref.child("ID_Passport").setValue(applicationForm.getId());
        myref.child("Country").setValue(applicationForm.getCountry());
        myref.child("Mobile").setValue(applicationForm.getMobile());
        myref.child("Email").setValue(applicationForm.getEmail());
        myref.child("Residential_Address").setValue(applicationForm.getRaddress());
        myref.child("Residential_Code").setValue(applicationForm.getRaddresscode());
        myref.child("Postal_Address").setValue(applicationForm.getPaddress());
        myref.child("Postal_Code").setValue(applicationForm.getPaddresscode());
        myref.child("Building").setValue(applicationForm.getBuilding());
        myref.child("Unit").setValue(applicationForm.getUnit());
        myref.child("Month_Moving_In").setValue(applicationForm.getMonth());


        Toast.makeText(Applications.this,"Successfully  Registered and Upload Complete!!",Toast.LENGTH_SHORT).show();


    }

}
